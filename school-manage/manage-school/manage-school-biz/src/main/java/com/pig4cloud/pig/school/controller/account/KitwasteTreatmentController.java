package com.pig4cloud.pig.school.controller.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.entity.account.FoodAdditive;
import com.pig4cloud.pig.school.api.entity.account.KitwasteTreatment;
import com.pig4cloud.pig.school.service.account.KitwasteTreatmentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 厨余处理记录
 *
 * @author 沈凝
 * @date 2019-07-06 16:54:52
 */
@RestController
@AllArgsConstructor
@RequestMapping("/kitwastetreatment")
public class KitwasteTreatmentController {
  private final RemoteUserService remoteUserService;
  private final KitwasteTreatmentService kitwasteTreatmentService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param kitwasteTreatment 厨余处理记录
   * @return
   */
  @GetMapping("/page")
  public R<IPage<KitwasteTreatment>> getKitwasteTreatmentPage(Page<KitwasteTreatment> page, KitwasteTreatment kitwasteTreatment) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      kitwasteTreatment.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return  new R<>(kitwasteTreatmentService.getKitwasteTreatmentPage(page,kitwasteTreatment));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<KitwasteTreatment> getById(@PathVariable("id") String id){
    return new R<>(kitwasteTreatmentService.getById(id));
  }

  /**
   * 新增记录
   * @param kitwasteTreatment
   * @return R
   */
  @SysLog("新增厨余处理记录")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('sl_kitwastetreatment_add')")
  public R save(@RequestBody KitwasteTreatment kitwasteTreatment){
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId = userInfo.getSysUser().getUserId();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    kitwasteTreatment.setSchoolId(schoolId);
    return new R<>(kitwasteTreatmentService.save(kitwasteTreatment));
  }

  /**
   * 修改记录
   * @param kitwasteTreatment
   * @return R
   */
  @SysLog("修改厨余处理记录")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('sl_kitwastetreatment_edit')")
  public R update(@RequestBody KitwasteTreatment kitwasteTreatment){
    return new R<>(kitwasteTreatmentService.updateById(kitwasteTreatment));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除厨余处理记录")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('sl_kitwastetreatment_del')")
  public R removeById(@PathVariable String id){
    KitwasteTreatment kitwasteTreatment = kitwasteTreatmentService.getById(id);
    kitwasteTreatment.setDelFlag("1");
    return new R<>(kitwasteTreatmentService.updateById(kitwasteTreatment));
}
}
