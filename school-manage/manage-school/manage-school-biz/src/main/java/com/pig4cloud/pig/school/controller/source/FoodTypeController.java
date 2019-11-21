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
import com.pig4cloud.pig.school.api.entity.source.FoodType;
import com.pig4cloud.pig.school.service.source.FoodTypeService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 食材类别表
 *
 * @author 沈凝
 * @date 2019-07-08 15:50:37
 */
@RestController
@AllArgsConstructor
@RequestMapping("/foodtype")
public class FoodTypeController {

  private final FoodTypeService foodTypeService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param foodType 食材类别表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<FoodType>> getFoodTypePage(Page<FoodType> page, FoodType foodType) {
    return  new R<>(foodTypeService.getFoodTypePage(page, foodType));
  }


	/**
	 * 通过ID查询
	 *
	 * @param id ID
	 * @return SysDept
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable Integer id) {
		return new R<>(foodTypeService.getById(id));
	}

	/**
	 * 返回树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public R listFoodTypeTrees() {
		return new R<>(foodTypeService.listFoodTypeTrees());
	}

	/**
	 * 返回当前用户树形菜单集合
	 *
	 * @return 树形菜单
	 */
/*	@GetMapping(value = "/filing-tree")
	public R listCurrentFoodFypeTrees() {
		return new R<>(foodTypeService.listCurrentFoodFypeTrees());
	}*/

	/**
	 * 新增记录
	 * @param foodType
	 * @return R
	 */
	@SysLog("新增食材类别表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sup_foodtype_add')")
	public R save(@RequestBody FoodType foodType){
		return new R<>(foodTypeService.save(foodType));
	}

	/**
	 * 修改记录
	 * @param foodType
	 * @return R
	 */
	@SysLog("修改食材类别表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sup_foodtype_edit')")
	public R update(@RequestBody FoodType foodType){
		return new R<>(foodTypeService.updateById(foodType));
	}

	/**
	 * 通过id删除一条记录
	 * @param deptId
	 * @return R
	 */
	@SysLog("删除食材类别表")
	@DeleteMapping("/{deptId}")
	@PreAuthorize("@pms.hasPermission('sup_foodtype_del')")
	public R removeById(@PathVariable Integer deptId){
		return new R<>(foodTypeService.removeById(deptId));
	}



}
