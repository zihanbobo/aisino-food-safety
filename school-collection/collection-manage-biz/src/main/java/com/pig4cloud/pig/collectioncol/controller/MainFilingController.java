package com.pig4cloud.pig.collectioncol.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.admin.api.vo.SysUserVO;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.BASE64DecodedMultipartFile;
import com.pig4cloud.pig.common.core.util.OCRDemo;
import com.pig4cloud.pig.common.core.util.OSSUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.api.entity.message.RegulatoryOpinion;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import com.pig4cloud.pig.school.api.feign.RemoteRecipeService;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.AllArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * h5数据采集供应商信息
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/collection")
public class MainFilingController {

  private RemoteUserService remoteUserService;

  private RemoteRecipeService remoteRecipeService;

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();	//密码类

  /**
   * h5端用户登陆
   * @return
   */
  @PostMapping("/CollectionLogin")
  public R publicLogin(@RequestParam(value = "pubPhone") String pubPhone,
                       @RequestParam(value = "pubPassword") String pubPassword) {
    R r = new R();
    SysUserVO sysUserVO = remoteUserService.queryInfo2(pubPhone,SecurityConstants.FROM_IN);
    if(sysUserVO==null){
      r.setCode(1);
      r.setMsg("未存在该手机号");
      return r;
    }
    String userType = sysUserVO.getUserType();
    if(!("2").equals(userType)){
      r.setCode(1);
      r.setMsg("该用户非校园端用户");
      return r;
    }
    if (!ENCODER.matches(pubPassword, sysUserVO.getPassword())) {
      return new R<>(CommonConstants.FAIL,null,"账号密码错误");
    }
    return new R<>(sysUserVO);
  }

  /**
   * 上传图片
   * @param file
   * @return
   */
  @PostMapping("/uploadPic")
  public Map uploadPic(@RequestParam("file") MultipartFile file) {

    String upload = OSSUtil.upload(file, OSSUtil.FileDirType.ZHANGHAN_TEST);
    String fileName = file.getOriginalFilename();
    Map map = new HashMap();
    map.put("name", fileName);
    map.put("url", upload);
    return map;

  }

  /**
   * 新增供应商信息
   * @param mainFiling
   * @return
   */
  @PostMapping("/insertSupplier")
  public R updateUserInfo(@RequestBody MainFiling mainFiling) {
    return remoteRecipeService.addSupplier(mainFiling, SecurityConstants.FROM_IN);
  }

  /**
   * h5端根据id查询当前供应商信息
   * @param id
   * @return
   */
  @GetMapping("/getSupplierById")
  public R getSupplierById(@RequestParam(value = "id") Integer id) {
    return remoteRecipeService.getSupplierById(id, SecurityConstants.FROM_IN);
  }

  /**
   * h5数据采集查询该学校的所有供应商信息
   * @param size
   * @param current
   * @param schoolId
   * @return
   */
  @GetMapping("/getAllSupplier")
  public R getAllSupplier(
    @RequestParam(value = "size",required = false) Integer size,
    @RequestParam(value = "current",required = false) Integer current,
    @RequestParam(value = "schoolId") Integer schoolId,
    @RequestParam(value = "supName",required = false) String supName
    ) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
    if(StringUtils.isEmpty(supName)){
      supName = "";
    }
    return remoteRecipeService.getLiveListBySchoolId(size,current,schoolId,supName,SecurityConstants.FROM_IN);
  }


  /**
   * h5采购修改供应商信息
   * @param mainFiling
   * @return
   */
  @PostMapping("/updateSupplier")
  public R updateSupplier(@RequestBody MainFiling mainFiling) {
    return remoteRecipeService.updateMainFiling(mainFiling, SecurityConstants.FROM_IN);
  }

  /**
   * 上传base64字符串图片并解密
   * @param file
   * @return
   * @throws IOException
   */
  @PostMapping("/uploadImage")
  /*public Map<String,Object> getQrcode(@RequestParam(value = "file", required = false) String file) throws IOException {*/
    public Map<String,Object> getQrcode(@RequestBody String file) throws IOException {
    MultipartFile multipartFile = BASE64DecodedMultipartFile.base64ToMultipart(file);
    String upload = OSSUtil.upload(multipartFile, OSSUtil.FileDirType.ZHANGHAN_TEST);
    String fileName = multipartFile.getOriginalFilename();
    Map map = new HashMap();
    map.put("name",fileName);
    map.put("url",upload);
    if(!StringUtils.isEmpty(upload)){
      map.put("code",0);
    }else {
      map.put("code",1);
    }
    return map;
  }

  /**
   * h5数据采集查询字典表信息
   * @return
   */
  @GetMapping("/getDictByTypeW")
  public R getDictByTypeW(@RequestParam(value = "dicType") String dicType) {
    R dictByTypeW = remoteUserService.getDictByTypeW(dicType, SecurityConstants.FROM_IN);
    return new R<>(dictByTypeW.getData());
  }

  @GetMapping("/delMainfiling")
  public R delMainfiling(@RequestParam(value = "id") Integer id) {
    R r = remoteRecipeService.delMainfiling(id, SecurityConstants.FROM_IN);
    //R dictByTypeW = remoteUserService.getDictByTypeW(dicType, SecurityConstants.FROM_IN);
    return new R<>(r);
  }

  /**
   * ocr营业执照图片识别(参数为base64编码的营业执照)
   * @param imgUrl
   * @return
   * @throws IOException
   */
  @PostMapping("/uploadOCR")
  public Map<String,Object> uploadOCR(@RequestBody String imgUrl) throws IOException {
    //传过来的图片解析base64
    MultipartFile multipartFile = BASE64DecodedMultipartFile.base64ToMultipart(imgUrl);
    //上传到阿里云返回路径
    String upload = OSSUtil.upload(multipartFile, OSSUtil.FileDirType.ZHANGHAN_TEST);
    //JSONObject jsonObject = JSONObject.parseObject(upload);
    //String r = jsonObject.getString("imgUrl");
    String ocrJson = OCRDemo.OCRIdentification(upload);
    Map map = new HashMap();
    map.put("OCRJson",ocrJson);
    if(!StringUtils.isEmpty(ocrJson)){
      map.put("code",0);
      map.put("status","解析成功");
    }else {
      map.put("code",1);
      map.put("status","解析失败");
    }
    return map;
  }



}
