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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.school.api.dto.recipe.FoodFilingDTO;
import com.pig4cloud.pig.school.api.entity.recipe.FoodFiling;
import com.pig4cloud.pig.school.service.recipe.FoodFilingService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 食材备案表
 *
 * @author 沈凝
 * @date 2019-07-04 00:33:20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/foodfiling")
public class FoodFilingController {

  private final FoodFilingService foodFilingService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param foodFiling 食材备案表
   * @return
   */
/*  @GetMapping("/page")
  public R<IPage<FoodFiling>> getFoodFilingPage(Page<FoodFiling> page, FoodFiling foodFiling) {
    return  new R<>(foodFilingService.getFoodFilingPage(page,foodFiling));
  }*/

  /**
   * 分页查询食材
   * @param page 分页对象
   * @param foodFilingDTO 食材备案表
   * @return
   */
  @GetMapping("/page")
  public R getFoodFilingPage(Page page, FoodFilingDTO foodFilingDTO) {
    return  new R<>(foodFilingService.getFoodFilingWithMainFilingPage(page,foodFilingDTO));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<FoodFiling> getById(@PathVariable("id") Integer id){
    return new R<>(foodFilingService.getById(id));
  }

  /**
   * 新增记录
   * @param foodFilingDto
   * @return R
   */
  @SysLog("新增食材备案表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('foodfiling_foodfiling_add')")
  public R save(@RequestBody FoodFilingDTO foodFilingDto){
    return new R<>(foodFilingService.saveFoodFiling(foodFilingDto));
  }

  /**
   * 修改记录
   * @param foodFiling
   * @return R
   */
//  @SysLog("修改食材备案表")
//  @PutMapping
//  @PreAuthorize("@pms.hasPermission('foodfiling_foodfiling_edit')")
//  public R update(@RequestBody FoodFiling foodFiling){
//    return new R<>(foodFilingService.updateById(foodFiling));
//  }
	/**
	 * 更新食材备案信息
	 *
	 * @param foodFilingDto 食材信息
	 * @return R
	 */
	@SysLog("更新食材信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('foodfiling_foodfiling_edit')")
	public R updateFood(@Valid @RequestBody FoodFilingDTO foodFilingDto) {
		return new R<>(foodFilingService.updateFoodFiling(foodFilingDto));
	}

  /**
   * 通过id删除一条食材记录
   * @param id
   * @return R
   */
  @SysLog("删除食材备案表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('foodfiling_foodfiling_del')")
  public R removeById(@PathVariable Integer id){
  	FoodFiling foodFiling = foodFilingService.getById(id);
    return new R<>(foodFilingService.removeFoodById(foodFiling));
  }


}
