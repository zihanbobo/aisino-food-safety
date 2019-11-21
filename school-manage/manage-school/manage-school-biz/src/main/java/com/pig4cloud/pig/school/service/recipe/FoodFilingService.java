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
package com.pig4cloud.pig.school.service.recipe;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.dto.recipe.FoodFilingDTO;
import com.pig4cloud.pig.school.api.entity.recipe.FoodFiling;
import com.pig4cloud.pig.school.api.vo.recipe.FoodFilingVO;

/**
 * 食材备案表
 *
 * @author 沈凝
 * @date 2019-07-04 00:33:20
 */
public interface FoodFilingService extends IService<FoodFiling> {

  /**
   * 食材备案表简单分页查询
   * @param foodFiling 食材备案表
   * @return
   */
  IPage getFoodFilingPage(Page page, FoodFiling foodFiling);

  /**
   * 食材备案表简单分页查询（含有供应商信息）
   * @param page    分页对象
   * @param foodFilingDTO 参数列表
   * @return
   */
  IPage getFoodFilingWithMainFilingPage(Page page, FoodFilingDTO foodFilingDTO);

  /**
   * 通过ID查询食材信息
   *
   * @param id 食材ID
   * @return 食材信息
   */
  FoodFilingVO getFoodFilingVoById(Integer id);

	/**
	 * 更新指定食材信息
	 *
	 * @param foodFilingDto 食材信息
	 * @return
	 */
	Boolean updateFoodFiling(FoodFilingDTO foodFilingDto);

	/**
	 * 保存食材信息
	 *
	 * @param foodFilingDto DTO 对象
	 * @return success/fail
	 */
	Boolean saveFoodFiling(FoodFilingDTO foodFilingDto);

	/**
	 * 删除食材
	 *
	 * @param FoodFiling 食材
	 * @return boolean
	 */
	Boolean removeFoodById(FoodFiling FoodFiling);


}
