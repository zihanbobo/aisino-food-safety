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
import com.pig4cloud.pig.school.api.entity.account.DishwashNote;
import com.pig4cloud.pig.school.api.entity.account.EquipmentWash;
import com.pig4cloud.pig.school.service.account.EquipmentWashService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 设备设施洗消记录表
 *
 * @author 沈凝
 * @date 2019-07-06 14:56:25
 */
@RestController
@AllArgsConstructor
@RequestMapping("/equipmentwash")
public class EquipmentWashController {

  private final RemoteUserService remoteUserService;
  private final EquipmentWashService equipmentWashService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param equipmentWash 设备设施洗消记录表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<EquipmentWash>> getEquipmentWashPage(Page<EquipmentWash> page, EquipmentWash equipmentWash) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      equipmentWash.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    Map<String, Object> map = new HashMap<>();
    return  new R<>(equipmentWashService.getEquipmentWashPage(page,equipmentWash));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<EquipmentWash> getById(@PathVariable("id") String id){
    return new R<>(equipmentWashService.getById(id));
  }

  /**
   * 新增记录
   * @param equipmentWash
   * @return R
   */
  @SysLog("新增设备设施洗消记录表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('sl_equipmentwash_add')")
  public R save(@RequestBody EquipmentWash equipmentWash){
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId = userInfo.getSysUser().getUserId();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    equipmentWash.setSchoolId(schoolId);
    return new R<>(equipmentWashService.save(equipmentWash));
  }

  /**
   * 修改记录
   * @param equipmentWash
   * @return R
   */
  @SysLog("修改设备设施洗消记录表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('sl_equipmentwash_edit')")
  public R update(@RequestBody EquipmentWash equipmentWash){
    return new R<>(equipmentWashService.updateById(equipmentWash));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除设备设施洗消记录表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('sl_equipmentwash_del')")
  public R removeById(@PathVariable String id){
    EquipmentWash equipmentWash = equipmentWashService.getById(id);
    equipmentWash.setDelFlag("1");
    return new R<>(equipmentWashService.updateById(equipmentWash));
  }

}
