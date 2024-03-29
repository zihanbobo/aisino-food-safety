package com.pig4cloud.pig.school.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysDeptRelation;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.portal.api.entity.live.Equipment;
import com.pig4cloud.pig.portal.api.entity.live.Live;
import com.pig4cloud.pig.school.api.dto.SchoolDTO;
import com.pig4cloud.pig.school.api.entity.School;
import com.pig4cloud.pig.school.service.SchoolService;
import com.pig4cloud.pig.school.service.project.ProjectManageService;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 学校表
 *
 * @author 崔俊前
 * @date 2019-08-05 14:48:06
 */
@RestController
@AllArgsConstructor
@RequestMapping("/school")
public class SchoolController {

  private final SchoolService schoolService;
  private final RemoteUserService remoteUserService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param school 学校表
   * @return
   */
//  @GetMapping("/page")
//  public R<IPage<School>> getSchoolPage(Page<School> page, School school) {
//    return  new R<>(schoolService.getSchoolPage(page,school));
//  }

  /**
   * 分页查询
   *
   * @param page      分页对象
   * @param schoolDTO 学校增强对象
   * @return
   */
  @GetMapping("/page")
  public R getSchoolPage(Page page, SchoolDTO schoolDTO) {
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();  // 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId= userInfo.getSysUser().getUserId();
    Boolean isAisinoSub = remoteUserService.isAisinoSub(userId, SecurityConstants.FROM_IN);
    if(isAisinoSub){
      schoolDTO.setUserId(userId);
    }
    return new R<>(schoolService.getSchoolWithMealPage(page, schoolDTO));
  }

  /**
   * 通过id查询单条记录
   *
   * @param id
   * @return R
   */
  @Inner
  @GetMapping("/{id}")
  public R<School> getById(@PathVariable("id") Integer id) {
    return new R<>(schoolService.getById(id));
  }

  /**
   * 新增记录
   *
   * @param schoolDTO
   * @return R
   */
  @SysLog("新增学校表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('se_school_add')")
//  public R save(@RequestBody School school){
//    return new R<>(schoolService.save(school));
//  }
  public R save(@RequestBody SchoolDTO schoolDTO) {
    return new R<>(schoolService.saveSchool(schoolDTO));
  }
  /**
   * 修改记录
   *
   * @param school
   * @return R
   */
  @SysLog("修改学校表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('se_school_edit')")
  public R update(@RequestBody School school) {
    return new R<>(schoolService.updateById(school));
  }

  /**
   * 通过id删除一条记录
   *
   * @param id
   * @return R
   */
  @SysLog("删除学校表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('se_school_del')")
  public R removeById(@PathVariable Integer id) {
    return new R<>(schoolService.removeById(id));
  }


  /**
   * 获取学校列表
   *
   * @return 学校列表
   */
  @Inner
  @GetMapping("/list")
  public R listRoles() {
    return new R<>(schoolService.list(Wrappers.emptyWrapper()));
  }

  /**
   * 获取学校列表
   *
   * @return 学校列表
   */
  @Inner
  @PostMapping("/isadmin")
  public R isadmin(@RequestParam(value = "params") Map<String, Object> params) {
    int schoolId = Integer.parseInt(String.valueOf(params.get("schoolId")));
    int userId = Integer.parseInt(String.valueOf(params.get("userId")));
    School school = schoolService.getById(schoolId);
    school.setSchoolUserid(userId);
    return new R<>(schoolService.save(school));
  }

  /**
   * 批量导入接口
   * @param file
   * @param userId
   * @param schoolId
   * @return
   * @throws IOException
   */
  @PostMapping("/schoolUpload")
  public Map<String,Object> schoolUpload(@RequestParam("file") MultipartFile file,
                                            @RequestParam("userId") Integer userId,
                                            @RequestParam("schoolId") Integer schoolId
  ) throws IOException {
    ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
    List<School> schoolList = reader.readAll(School.class);
    // 判断循环下标
    int count = 0;
    School school = null;
    for(int i=0;i<schoolList.size();i++){
      school = schoolList.get(i);
      if(count==0){
        count++;
        continue;
      }
      // 直播码与食谱码自动生成(8位)
      school.setLiveIdentifier(RandomUtil.randomString(8));
      school.setRecipeIdentifier(RandomUtil.randomString(8));
      schoolService.save(school);
      count++;
    }
    Map map = new HashMap();
    map.put("count",count-1);
    return map;
  }

  /**
   * 验证学校服务码
   *
   * @return 学校列表
   */
  @Inner
  @PostMapping("/verify")
  public R verify(@RequestParam(value = "serviceCode") String serviceCode, @RequestParam(value = "type") String type) {
    R<Object> objectR = new R<>();

    School school = null;
    if ("1".equals(type)) {
      school = schoolService.getOne(Wrappers.<School>query().lambda()
        .eq(School::getLiveIdentifier, serviceCode));
    } else if ("2".equals(type)) {
      school = schoolService.getOne(Wrappers.<School>query().lambda()
        .eq(School::getRecipeIdentifier, serviceCode));
    }
    if (school == null) {
      objectR.setCode(CommonConstants.FAIL);
      objectR.setMsg("该验证码有误");
      return objectR;
    }
    objectR.setData(school);
    return objectR;
  }

  /**
   * app-live 获取学校信息
   *
   * @param id
   * @return R
   */
  @Inner
  @GetMapping("/getSchoolEasyInfo/{id}")
  public R getSchoolEasyInfo(@PathVariable("id") Integer id) {
    return new R<>(schoolService.getSchoolEasyInfo(id));
  }


  /**
   * 学校用户管理-获取学校信息（无超级管理员的学校）
   *
   * @return R
   */
  @Inner
  @GetMapping("/getNoSchoolUserid")
  public List getNoSchoolUserid() {
    return schoolService.list(Wrappers.<School>query().lambda()
      .eq(School::getDelFlag, "0")
      .eq(School::getSchoolUserid, ""));
  }


  /**
   * 学校绑定或者解绑超级管理员接口
   *
   * @return 学校列表
   */
  @Inner
  @PostMapping("/schoolUserId")
  public R<Boolean> schoolUserId(@RequestParam(value = "userId") Integer userId,
                  @RequestParam(value = "schoolId") Integer schoolId,
                  @RequestParam(value = "type") String type) {
    School school = schoolService.getById(schoolId);
    // 1绑定
    if("1".equals(type)){
      school.setSchoolUserid(userId);
    }else{// 否则解绑
      school.setSchoolUserid(null);
    }
    boolean b = schoolService.updateById(school);
    return new R<>(b);
  }

  /**
   * 通过id查询单条记录
   *
   * @param id
   * @return R
   */
  @GetMapping("/getSchoolNameById")
  public R getDetailById(@RequestParam(value = "id") Integer id) {
    Map<String, Object> map = new HashMap<>();
    School school = schoolService.getById(id);
    map.put("name",school.getSchName());
    return new R<>(map);
  }

}
