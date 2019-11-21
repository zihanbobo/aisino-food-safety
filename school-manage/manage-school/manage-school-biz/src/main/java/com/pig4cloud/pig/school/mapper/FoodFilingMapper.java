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
package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.dto.recipe.FoodFilingDTO;
import com.pig4cloud.pig.school.api.entity.recipe.FoodFiling;
import com.pig4cloud.pig.school.api.vo.recipe.FoodFilingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 食材备案表
 *
 * @author 沈凝
 * @date 2019-07-04 00:33:20
 */
public interface FoodFilingMapper extends BaseMapper<FoodFiling> {
  /**
    * 食材备案表简单分页查询
    * @param foodFiling 食材备案表
    * @return
    */
  IPage<List<FoodFiling>>  getFoodFilingPage(Page page, @Param("foodFiling") FoodFiling foodFiling);

	/**
	 * 分页查询食材备案信息（含供应商）
	 *
	 * @param page    分页
	 * @param foodFilingDTO 查询参数
	 * @return list
	 */
	IPage<List<FoodFilingVO>> getFoodFilingVosPage(Page page, @Param("query") FoodFilingDTO foodFilingDTO);

	/**
	 * 通过ID查询食材备案信息
	 *
	 * @param id 食材ID
	 * @return foodFilingVo
	 */
	FoodFilingVO getFoodFilingVoById(Integer id);

	/**
	 * 通过食谱ID，查询食材信息
	 *
	 * @param id
	 * @return
	 */
	List<FoodFiling> listFoodFilingsBySourceId(Integer id);

}
