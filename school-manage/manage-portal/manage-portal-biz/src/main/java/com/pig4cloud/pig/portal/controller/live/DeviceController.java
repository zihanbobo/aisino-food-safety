package com.pig4cloud.pig.portal.controller.live;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.portal.api.entity.live.Device;
import com.pig4cloud.pig.portal.api.entity.live.Live;
import com.pig4cloud.pig.portal.service.live.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 其他设备信息
 *
 * @author xiesongzhe
 * @date 2019-10-11 14:07:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/device")
public class DeviceController {

  private final DeviceService deviceService;

  private final RemoteUserService remoteUserService;


  /**
   * 简单分页查询
   * @param page 分页对象
   * @param device 其他设备信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Device>> getDevicePage(Page<Device> page, Device device) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      device.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return  new R<>(deviceService.getDevicePage(page,device));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Device> getById(@PathVariable("id") Integer id){
    return new R<>(deviceService.getById(id));
  }

  /**
   * 新增记录
   * @param device
   * @return R
   */
  @SysLog("新增其他设备信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('portalMan_device_add')")
  public R save(@RequestBody Device device){

    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    //Integer userId = userInfo.getSysUser().getUserId();
    //device.setCreateById(userId);
    device.setSchoolId(userInfo.getSysUser().getUnionId());
    //boolean save = deviceService.save(device);
    device.setDelFlag("0");
    return new R<>(deviceService.save(device));
  }

  /**
   * 修改记录
   * @param device
   * @return R
   */
  @SysLog("修改其他设备信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('portalMan_device_edit')")
  public R update(@RequestBody Device device){
    return new R<>(deviceService.updateById(device));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除其他设备信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('portalMan_device_del')")
  public R removeById(@PathVariable Integer id){
    Device device = deviceService.getById(id);
    device.setDelFlag("1");
    return new R<>(deviceService.updateById(device));
  }

}
