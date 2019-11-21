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
package com.pig4cloud.pig.school.controller.selseal;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.school.api.dto.selseal.SelMealDTO;
import com.pig4cloud.pig.school.api.entity.selseal.SelMeal;
import com.pig4cloud.pig.school.service.selseal.SelMealService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 套餐表
 *
 * @author 崔俊前
 * @date 2019-08-05 14:45:21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/selmeal")
public class SelMealController {

  private final SelMealService selMealService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param selMealDTO 套餐表
   * @return
   */
//  @GetMapping("/page")
//  public R<IPage<SelMeal>> getSelMealPage(Page<SelMeal> page, SelMeal selMeal) {
//    return  new R<>(selMealService.getSelMealPage(page,selMeal));
//  }

  	@GetMapping("/page")
	public R getFoodFilingPage(Page page, SelMealDTO selMealDTO) {
		return  new R<>(selMealService.getMealWithServicePage(page,selMealDTO));
	}


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<SelMeal> getById(@PathVariable("id") Integer id){
    return new R<>(selMealService.getById(id));
  }

  /**
   * 新增记录
   * @param selMealDTO
   * @return R
   */
  @SysLog("新增套餐表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('se_selmeal_add')")
//  public R save(@RequestBody SelMeal selMeal){
//    return new R<>(selMealService.save(selMeal));
//  }
  public R save(@RequestBody SelMealDTO selMealDTO){
    return new R<>(selMealService.saveSelMeal(selMealDTO));
  }

  /**
   * 修改记录
   * @param selMeal
   * @return R
   */
/*  @SysLog("修改套餐表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('se_selmeal_edit')")
  public R update(@RequestBody SelMeal selMeal){
    return new R<>(selMealService.updateById(selMeal));
  }*/

	/**
	 * 更新套餐信息
	 *
	 * @param selMealDTO 套餐信息
	 * @return R
	 */
	@SysLog("更新食谱信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('se_selmeal_edit')")
	public R updateSelMeal(@Valid @RequestBody SelMealDTO selMealDTO) {
		return new R<>(selMealService.updateSelMeal(selMealDTO));
	}


  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除套餐表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('se_selmeal_del')")
  public R removeById(@PathVariable Integer id){
//    return new R<>(selMealService.removeById(id));
	  SelMeal selMeal = selMealService.getById(id);
	  return new R<>(selMealService.removeSelMealById(selMeal));
  }

	/**
	 * 获取套餐列表
	 *
	 * @return 套餐列表
	 */
	@GetMapping("/list")
	public R listSelMeals() {
		return new R<>(selMealService.list(Wrappers.emptyWrapper()));
	}


}
