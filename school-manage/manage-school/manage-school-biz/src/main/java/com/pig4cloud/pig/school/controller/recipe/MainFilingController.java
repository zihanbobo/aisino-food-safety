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
package com.pig4cloud.pig.school.controller.recipe;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.entity.PublicUserSchool;
import com.pig4cloud.pig.portal.api.entity.live.Equipment;
import com.pig4cloud.pig.portal.api.entity.live.Live;
import com.pig4cloud.pig.school.api.entity.School;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import com.pig4cloud.pig.school.service.recipe.MainFilingService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 供应商备案表
 *
 * @author 沈凝
 * @date 2019-06-21 14:59:45
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mainfiling")
public class MainFilingController {

  private final RemoteUserService remoteUserService;

  private final MainFilingService mainFilingService;


  /**
   * 简单分页查询
   *
   * @param page       分页对象
   * @param mainFiling 供应商备案表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<MainFiling>> getMainFilingPage(Page<MainFiling> page, MainFiling mainFiling) {
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();  // 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if ("1".equals(userType)) {
    } else if ("2".equals(userType)) {
      mainFiling.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return new R<>(mainFilingService.getMainFilingPage(page, mainFiling));
  }


  /**
   * 通过id查询单条记录
   *
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<MainFiling> getById(@PathVariable("id") Integer id) {
    return new R<>(mainFilingService.getById(id));
  }

  /**
   * 新增记录
   *
   * @param mainFiling
   * @return R
   */
  @SysLog("新增供应商备案表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('admin_mainfiling_add')")
  public R save(@RequestBody MainFiling mainFiling) {
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();  // 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if ("1".equals(userType)) {
    } else if ("2".equals(userType)) {
      mainFiling.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return new R<>(mainFilingService.save(mainFiling));
  }

  /**
   * 修改记录
   *
   * @param mainFiling
   * @return R
   */
  @SysLog("修改供应商备案表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('admin_mainfiling_edit')")
  public R update(@RequestBody MainFiling mainFiling) {
    return new R<>(mainFilingService.updateById(mainFiling));
  }

  /**
   * 通过id删除一条记录
   *
   * @param id
   * @return R
   */
  @SysLog("删除供应商备案表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('admin_mainfiling_del')")
  public R removeById(@PathVariable Integer id) {
    MainFiling mainFiling = mainFilingService.getById(id);
    mainFiling.setDelFlag("1");
    return new R<>(mainFilingService.updateById(mainFiling));
//    return new R<>(mainFilingService.removeById(id));
  }


  /**
   * 获取供应商列表
   *
   * @return 供应商列表
   */
  @GetMapping("/list")
  public R listMainFilings() {
    return new R<>(mainFilingService.list(Wrappers.emptyWrapper()));
  }

  /**
   * 获取供应商列表
   *
   * @return 供应商列表
   */
  @GetMapping("/map")
  public R map(@RequestBody Map param) {

    System.out.println(param.get("username"));
    System.out.println(param.get("password"));
    return new R<>(mainFilingService.list(Wrappers.emptyWrapper()));
  }

  /**
   * 获取供应商列表
   *
   * @return 供应商列表
   */
  @PostMapping("/map2")
  public R map2(@RequestBody Map<String, Object> param, @RequestHeader(required = false) String from) {

    System.out.println(param.get("username"));
    System.out.println(param.get("password"));
    return new R<>(mainFilingService.list(Wrappers.emptyWrapper()));
  }

  /**
   * 获取供应商列表
   *
   * @return 供应商列表
   */
  @GetMapping("/mainfilingSchool")
  public R mainfilingSchool() {
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();  // 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer unionId = userInfo.getSysUser().getUnionId();
    LambdaQueryWrapper<MainFiling> eq = null;
    if ("1".equals(userType)) {
      eq = Wrappers.<MainFiling>query()
        .lambda()
        .eq(MainFiling::getDelFlag, '0');
    } else if ("2".equals(userType)) {
      eq = Wrappers.<MainFiling>query()
        .lambda()
        .eq(MainFiling::getSchoolId, unionId)
        .eq(MainFiling::getDelFlag, '0');
    }
    return new R<>(mainFilingService.list(eq));
//    return new R<>(mainFilingService.list(Wrappers.<MainFiling>query()
//      .lambda()
//      .eq(MainFiling::getSchoolId, unionId)
//      .eq(MainFiling::getDelFlag, '0')));
  }

  /**
   * h5数据采集 新增供应商
   *
   * @param mainFiling
   * @return R
   */
  @Inner
  @PostMapping("/addSupplier")
  public R addSupplier(@RequestBody MainFiling mainFiling) {
    /*// 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();  // 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if ("1".equals(userType)) {
    } else if ("2".equals(userType)) {
      mainFiling.setSchoolId(userInfo.getSysUser().getUnionId());
    }*/
    return new R<>(mainFilingService.save(mainFiling));
  }

  /**
   * h5数据采集通过id查询单条供应商记录
   *
   * @param id
   * @return R
   */
  @Inner
  @GetMapping("/getSupplierById")
  public R getSupplierById(@RequestParam("id") Integer id) {
    MainFiling mainFiling = mainFilingService.getById(id);
    return new R<>(mainFiling);
  }

  // h5数据采集 根据学校id供应商列表查询
  @Inner
  @GetMapping("/getLiveListBySchoolId")
  public R<Object> getLiveListBySchoolId(@RequestParam(value = "size")Integer size,
                                   @RequestParam(value = "current")Integer current,
                                   @RequestParam(value = "schoolId")Integer schoolId,
                                   @RequestParam(value = "supName",required = false)String supName) {
    // 根据输入参数组装分页类,
    Page page = new Page<>();
    page.setSize(new Long((long)size));
    page.setCurrent(new Long((long)current));
    MainFiling mainFiling = new MainFiling();
    mainFiling.setSchoolId(schoolId);
    if(!StringUtils.isEmpty(supName)){
      mainFiling.setSupName(supName);
    }
    return new R<>(mainFilingService.getMainFilingPage(page,mainFiling));
  }


  /**
   * h5数据采集修改供应商
   *
   * @param mainFiling 供应商实体
   * @return success/false
   */
  @Inner
  @PostMapping("/updateMainfiling")
  public R updateMainfiling(@RequestBody MainFiling mainFiling) {
    return new R<>(mainFilingService.updateById(mainFiling));
  }

  /**
   * h5数据采集通过id删除一条记录
   *
   * @param id
   * @return R
   */
  @Inner
  @GetMapping("/delMainfiling")
  public R delMainfiling(@RequestParam(value = "id")Integer id) {
    MainFiling mainFiling = mainFilingService.getById(id);
    mainFiling.setDelFlag("1");
    return new R<>(mainFilingService.updateById(mainFiling));
//    return new R<>(mainFilingService.removeById(id));
  }


}
