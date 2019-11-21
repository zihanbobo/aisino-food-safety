package com.pig4cloud.pig.school.controller.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.entity.account.AccompanyDinner;
import com.pig4cloud.pig.school.api.entity.account.Ultravioletlight;
import com.pig4cloud.pig.school.api.vo.account.AccompanyDinnerVO;
import com.pig4cloud.pig.school.service.account.UltravioletlightService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * 紫外线灯使用信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 15:35:57
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ultravioletLight")
public class UltravioletlightController {

  private final RemoteUserService remoteUserService;
  private final UltravioletlightService ultravioletlightService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param ultravioletlight 紫外线灯使用信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Ultravioletlight>> getUltravioletlightPage(Page<Ultravioletlight> page, Ultravioletlight ultravioletlight) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId = userInfo.getSysUser().getUserId();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    ultravioletlight.setSchoolId(schoolId);
    return  new R<>(ultravioletlightService.getUltravioletlightPage(page,ultravioletlight));
  }

  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Ultravioletlight> getById(@PathVariable("id") Integer id){
    return new R<>(ultravioletlightService.getById(id));
  }

  /**
   * 新增记录
   * @param ultravioletlight
   * @return R
   */
  @SysLog("新增紫外线灯使用信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_ultravioletlight_add')")
  public R save(@RequestBody Ultravioletlight ultravioletlight){
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId = userInfo.getSysUser().getUserId();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    ultravioletlight.setSchoolId(schoolId);
    return new R<>(ultravioletlightService.save(ultravioletlight));
  }

  /**
   * 修改记录
   * @param ultravioletlight
   * @return R
   */
  @SysLog("修改紫外线灯使用信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_ultravioletlight_edit')")
  public R update(@RequestBody Ultravioletlight ultravioletlight){
    return new R<>(ultravioletlightService.updateById(ultravioletlight));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除紫外线灯使用信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('schoolMan_ultravioletlight_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(ultravioletlightService.removeById(id));
  }


  /**
   * h5数据采集新增紫外线灯使用信息
   * @param ultravioletLight
   * @return R
   */
  @Inner
  @PostMapping("/addUltravioletLight")
  public R addUltravioletLight(@RequestBody Ultravioletlight ultravioletLight){
    return new R<>(ultravioletlightService.save(ultravioletLight));
  }


  /**
   * h5数据采集查询所有紫外线灯使用信息
   * @param size
   * @param current
   * @param schoolId
   * @return
   */
  @Inner
  @GetMapping("/getLightPage")
  public R getAllUltravioletlight(@RequestParam(value = "size")Integer size,
                                  @RequestParam(value = "current")Integer current,
                                  @RequestParam(value = "schoolId")Integer schoolId,
                                  @RequestParam(value = "useTime",required = false)String useTime) {
    // 根据输入参数组装分页类,
    Page page = new Page<>();
    page.setSize(new Long((long)size));
    page.setCurrent(new Long((long)current));
    Ultravioletlight ultravioletlight = new Ultravioletlight();
    ultravioletlight.setSchoolId(schoolId);
    //string转成localdatetime
   /* LocalDateTime ldt = null;
    if(!StringUtils.isEmpty(useTime)){
      DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      ldt = LocalDateTime.parse(useTime,df);
      System.out.println("String类型的时间转成LocalDateTime："+ldt);
    }*/
    ultravioletlight.setUseTime(useTime);
    IPage ultravioletlightPage = ultravioletlightService.getUltravioletlightPage(page, ultravioletlight);
    return new R<Object>(ultravioletlightPage);
  }

  /**
   * h5数据采集通过id查询单条紫外线灯使用信息
   *
   * @param id
   * @return R
   */
  @Inner
  @GetMapping("/getUltravioletLightById")
  public R getUltravioletlightById(@RequestParam(value = "id") Integer id) {
    Ultravioletlight ultravioletlight = ultravioletlightService.getById(id);
    return new R<>(ultravioletlight);
  }

  /**
   * 修改紫外线灯
   * @param ultravioletLight
   * @return
   */
  @Inner
  @PostMapping("/updateUltravioletLight")
  public R updateUltravioletLight(@RequestBody Ultravioletlight ultravioletLight){
    boolean b = ultravioletlightService.updateById(ultravioletLight);
    System.out.println(b);
    return new R<>(b);
  }

  /**
   * h5数据采集删除紫外线灯使用信息
   * @param id
   * @return R
   */
  @Inner
  @GetMapping("/delUltravioletLight")
  public R delUltravioletLight(@RequestParam(value = "id")Integer id){
    Ultravioletlight ultravioletlight = ultravioletlightService.getById(id);
    ultravioletlight.setDelFlag("1");
    return new R<>(ultravioletlightService.updateById(ultravioletlight));
  }


}
