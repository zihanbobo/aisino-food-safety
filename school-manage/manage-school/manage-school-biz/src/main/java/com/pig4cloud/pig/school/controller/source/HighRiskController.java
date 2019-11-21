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
package com.pig4cloud.pig.school.controller.source;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.school.api.entity.source.HighRisk;
import com.pig4cloud.pig.school.service.source.HighRiskService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 高风险食材表
 *
 * @author 沈凝
 * @date 2019-07-15 17:33:37
 */
@RestController
@AllArgsConstructor
@RequestMapping("/highrisk")
public class HighRiskController {

  private final HighRiskService highRiskService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param highRisk 高风险食材表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<HighRisk>> getHighRiskPage(Page<HighRisk> page, HighRisk highRisk) {
    return  new R<>(highRiskService.getHighRiskPage(page,highRisk));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<HighRisk> getById(@PathVariable("id") Integer id){
    return new R<>(highRiskService.getById(id));
  }

  /**
   * 新增记录
   * @param highRisk
   * @return R
   */
  @SysLog("新增高风险食材表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('purchase_highrisk_add')")
  public R save(@RequestBody HighRisk highRisk){
    return new R<>(highRiskService.save(highRisk));
  }

  /**
   * 修改记录
   * @param highRisk
   * @return R
   */
  @SysLog("修改高风险食材表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('purchase_highrisk_edit')")
  public R update(@RequestBody HighRisk highRisk){
    return new R<>(highRiskService.updateById(highRisk));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除高风险食材表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('purchase_highrisk_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(highRiskService.removeById(id));
  }

}
