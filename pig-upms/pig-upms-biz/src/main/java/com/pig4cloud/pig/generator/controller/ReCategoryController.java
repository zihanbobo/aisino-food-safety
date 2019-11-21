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
package com.pig4cloud.pig.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.generator.entity.ReCategory;
import com.pig4cloud.pig.generator.service.ReCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 食材类别名称
 *
 * @author pig code generator
 * @date 2019-06-03 16:24:23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recategory")
public class ReCategoryController {

  private final  ReCategoryService reCategoryService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param reCategory 食材类别名称
   * @return
   */
  @GetMapping("/page")
  public R<IPage<ReCategory>> getReCategoryPage(Page<ReCategory> page, ReCategory reCategory) {
    return  new R<>(reCategoryService.getReCategoryPage(page,reCategory));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<ReCategory> getById(@PathVariable("id") String id){
    return new R<>(reCategoryService.getById(id));
  }

  /**
   * 新增记录
   * @param reCategory
   * @return R
   */
  @SysLog("新增食材类别名称")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('generator_recategory_add')")
  public R save(@RequestBody ReCategory reCategory){
    return new R<>(reCategoryService.save(reCategory));
  }

  /**
   * 修改记录
   * @param reCategory
   * @return R
   */
  @SysLog("修改食材类别名称")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('generator_recategory_edit')")
  public R update(@RequestBody ReCategory reCategory){
    return new R<>(reCategoryService.updateById(reCategory));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除食材类别名称")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('generator_recategory_del')")
  public R removeById(@PathVariable String id){
    return new R<>(reCategoryService.removeById(id));
  }

}
