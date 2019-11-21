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
package com.tofirst.supervision.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.tofirst.supervision.admin.entity.Regulator;
import com.tofirst.supervision.admin.service.RegulatorService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 监管局信息表
 *
 * @author -
 * @date 2019-08-05 14:49:59
 */
@RestController
@AllArgsConstructor
@RequestMapping("/regulator")
public class RegulatorController {
  private final RegulatorService regulatorService;
	private final RemoteUserService remoteUserService;
  /**
   * 简单分页查询
   * @param page 分页对象
   * @param regulator 监管局信息表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Regulator>> getRegulatorPage(Page<Regulator> page, Regulator regulator) {

	  R<UserInfo> result = remoteUserService.info("shenning", SecurityConstants.FROM_IN);
    return  new R<>(regulatorService.getRegulatorPage(page,regulator));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Regulator> getById(@PathVariable("id") Integer id){
    return new R<>(regulatorService.getById(id));
  }

  /**
   * 新增记录
   * @param regulator
   * @return R
   */
  @SysLog("新增监管局信息表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('se_regulator_add')")
  public R save(@RequestBody Regulator regulator){
    return new R<>(regulatorService.save(regulator));
  }

  /**
   * 修改记录
   * @param regulator
   * @return R
   */
  @SysLog("修改监管局信息表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('se_regulator_edit')")
  public R update(@RequestBody Regulator regulator){
    return new R<>(regulatorService.updateById(regulator));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除监管局信息表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('se_regulator_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(regulatorService.removeById(id));
  }

}
