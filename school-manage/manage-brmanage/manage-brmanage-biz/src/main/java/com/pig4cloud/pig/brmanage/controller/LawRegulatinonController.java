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
package com.pig4cloud.pig.brmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.brmanage.api.entity.LawRegulatinon;
import com.pig4cloud.pig.brmanage.service.LawRegulatinonService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 法律法规表
 *
 * @author 沈凝
 * @date 2019-07-29 10:32:30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/lawregulatinon")
public class LawRegulatinonController {

  private final LawRegulatinonService lawRegulatinonService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param lawRegulatinon 法律法规表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<LawRegulatinon>> getLawRegulatinonPage(Page<LawRegulatinon> page, LawRegulatinon lawRegulatinon) {
    return  new R<>(lawRegulatinonService.getLawRegulatinonPage(page,lawRegulatinon));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<LawRegulatinon> getById(@PathVariable("id") Integer id){
    return new R<>(lawRegulatinonService.getById(id));
  }

  /**
   * 新增记录
   * @param lawRegulatinon
   * @return R
   */
  @SysLog("新增法律法规表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('re_lawregulatinon_add')")
  public R save(@RequestBody LawRegulatinon lawRegulatinon){
    return new R<>(lawRegulatinonService.save(lawRegulatinon));
  }

  /**
   * 修改记录
   * @param lawRegulatinon
   * @return R
   */
  @SysLog("修改法律法规表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('re_lawregulatinon_edit')")
  public R update(@RequestBody LawRegulatinon lawRegulatinon){
    return new R<>(lawRegulatinonService.updateById(lawRegulatinon));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除法律法规表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('re_lawregulatinon_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(lawRegulatinonService.removeById(id));
  }

}
