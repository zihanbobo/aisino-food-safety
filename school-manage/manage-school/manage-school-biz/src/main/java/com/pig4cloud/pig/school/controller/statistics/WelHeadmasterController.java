/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.pig4cloud.pig.school.controller.statistics;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.entity.check.EarlyWarning;
import com.pig4cloud.pig.school.api.entity.source.FoodType;
import com.pig4cloud.pig.school.service.source.FoodTypeService;
import com.pig4cloud.pig.school.service.statistics.WelHeadmasterService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 食材类别表
 *
 * @author 沈凝
 * @date 2019-07-08 15:50:37
 */
@RestController
@AllArgsConstructor
@RequestMapping("/welHeadmaster")
public class WelHeadmasterController {

  private final RemoteUserService remoteUserService;
  private final WelHeadmasterService welHeadmasterService;

  // 第一行-校园端-欢迎页-校长-预警统计
  @GetMapping("/getEarlyWarnTotal")
  public R getEarlyWarnTotal(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(welHeadmasterService.getEarlyWarnTotal(map));
  }
  // 第一行-校园端-欢迎页-校长-报警统计
  @GetMapping("/getAlarmTotal")
  public R getAlarmTotal(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(welHeadmasterService.getAlarmTotal(map));
  }
  //第一行-校园端-欢迎页-校长-本周报警统计按类别
  @GetMapping("/getAlarmWeekTotal")
  public R getAlarmWeekTotal(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(welHeadmasterService.getAlarmWeekTotal(map));
  }


  //第2行-校园端-欢迎页-校长-监管及通知公告统计
  @GetMapping("/getPurchaseWarn")
  public R getPurchaseWarn(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(welHeadmasterService.getPurchaseWarn(map));
  }
  //第2行-校园端-欢迎页-校长-台账统计
  @GetMapping("/getAccount")
  public R getAccount(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(welHeadmasterService.getAccount(map));
  }


  //第三行-校园端-欢迎页-校长-人员信息
  @GetMapping("/getSchoolUserInfo")
  public R getSchoolUserInfo(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(welHeadmasterService.getSchoolUserInfo(map));
  }
  //第三行-校园端-欢迎页-校长-当日人员晨检合格率
  @GetMapping("/getDayUserQualified")
  public R getDayUserQualified(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(welHeadmasterService.getDayUserQualified(map));
  }
  //第三行-校园端-欢迎页-校长-食材信息
  @GetMapping("/getFoodInfo")
  public R getFoodInfo(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(welHeadmasterService.getFoodInfo(map));
  }
  //第三行-校园端-欢迎页-校长-当日采购信息
  @GetMapping("/getDayPurchase")
  public R getDayPurchase(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(welHeadmasterService.getDayPurchase(map));
  }
  //第三行-校园端-欢迎页-校长-当日采购票证上传率与高风险
  @GetMapping("/getHighRisk")
  public R getHighRisk(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(welHeadmasterService.getHighRisk(map));
  }


  // 第四行-校园端-欢迎页-校长-供应商信息
  @GetMapping("/getMainFilingInfo")
  public R getMainFilingInfo(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(welHeadmasterService.getMainFilingInfo(map));
  }
  // 第四行-校园端-欢迎页-校长-厨余设备
  @GetMapping("/getVdDevice")
  public R getVdDevice(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(welHeadmasterService.getVdDevice(map));
  }

  // 第四行-校园端-欢迎页-员工-用户信息
  @GetMapping("/getUserInfo")
  public R getUserInfo(@RequestParam(value="userId")Integer userId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("userId",userId);
    return new R<>(welHeadmasterService.getUserInfo(map));
  }

  // 第四行-校园端-欢迎页-员工-设备信息&设备运转比率
  @GetMapping("/getEqOperationRatio")
  public R getEqOperationRatio(@RequestParam(value="schoolId")Integer schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);

    List<Map> vdDevice = welHeadmasterService.getSchoolVdDevice(map);
    Map eqRatio = welHeadmasterService.getEqOperationRatio(map);

    Map<String,Object> returnMap = new HashMap<String,Object>();
    returnMap.put("vdDevice",vdDevice);
    returnMap.put("eqRatio",eqRatio);
    return new R<>(returnMap);
  }










 /* // 第一行-校园端-欢迎页-校长-预警统计
  @GetMapping("/getEarlyWarnTotal")
  public R getEarlyWarnTotal() {
    Map<String,Object> map = new HashMap<String,Object>();
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    map.put("schoolId",unionId);
    return new R<>(welHeadmasterService.getEarlyWarnTotal(map));
  }

  // 第一行-校园端-欢迎页-校长-报警统计
  @GetMapping("/getAlarmTotal")
  public R getAlarmTotal() {
    Map<String,Object> map = new HashMap<String,Object>();
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    map.put("schoolId",unionId);
    return new R<>(welHeadmasterService.getAlarmTotal(map));
  }
  //第一行-校园端-欢迎页-校长-本周报警统计按类别
  @GetMapping("/getAlarmWeekTotal")
  public R getAlarmWeekTotal() {
    Map<String,Object> map = new HashMap<String,Object>();
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    map.put("schoolId",unionId);
    return new R<>(welHeadmasterService.getAlarmWeekTotal(map));
  }








  //第2行-校园端-欢迎页-校长-监管及通知公告统计
  @GetMapping("/getPurchaseWarn")
  public R getPurchaseWarn() {
    Map<String,Object> map = new HashMap<String,Object>();
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    map.put("schoolId",unionId);
    return new R<>(welHeadmasterService.getPurchaseWarn(map));
  }
  //第2行-校园端-欢迎页-校长-台账统计
  @GetMapping("/getAccount")
  public R getAccount() {
    Map<String,Object> map = new HashMap<String,Object>();
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    map.put("schoolId",unionId);
    return new R<>(welHeadmasterService.getAccount(map));
  }






  //第三行-校园端-欢迎页-校长-人员信息
  @GetMapping("/getSchoolUserInfo")
  public R getSchoolUserInfo() {
    Map<String,Object> map = new HashMap<String,Object>();
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    map.put("schoolId",unionId);
    return new R<>(welHeadmasterService.getSchoolUserInfo(map));
  }
  //第三行-校园端-欢迎页-校长-当日人员晨检合格率
  @GetMapping("/getDayUserQualified")
  public R getDayUserQualified() {
    Map<String,Object> map = new HashMap<String,Object>();
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    map.put("schoolId",unionId);
    return new R<>(welHeadmasterService.getDayUserQualified(map));
  }
  //第三行-校园端-欢迎页-校长-食材信息
  @GetMapping("/getFoodInfo")
  public R getFoodInfo() {
    Map<String,Object> map = new HashMap<String,Object>();
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    map.put("schoolId",unionId);
    return new R<>(welHeadmasterService.getFoodInfo(map));
  }
  //第三行-校园端-欢迎页-校长-当日采购信息
  @GetMapping("/getDayPurchase")
  public R getDayPurchase() {
    Map<String,Object> map = new HashMap<String,Object>();
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    map.put("schoolId",unionId);
    return new R<>(welHeadmasterService.getDayPurchase(map));
  }
  //第三行-校园端-欢迎页-校长-当日采购票证上传率与高风险
  @GetMapping("/getHighRisk")
  public R getHighRisk() {
    Map<String,Object> map = new HashMap<String,Object>();
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    map.put("schoolId",unionId);
    return new R<>(welHeadmasterService.getHighRisk(map));
  }




  // 第四行-校园端-欢迎页-校长-供应商信息
  @GetMapping("/getMainFilingInfo")
  public R getMainFilingInfo() {
    Map<String,Object> map = new HashMap<String,Object>();
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    map.put("schoolId",unionId);
    return new R<>(welHeadmasterService.getMainFilingInfo(map));
  }
  // 第四行-校园端-欢迎页-校长-厨余设备
  @GetMapping("/getVdDevice")
  public R getVdDevice() {
    Map<String,Object> map = new HashMap<String,Object>();
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    map.put("schoolId",unionId);
    return new R<>(welHeadmasterService.getVdDevice(map));
  }








  */



}
