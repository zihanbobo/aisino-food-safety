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
import com.pig4cloud.pig.school.api.dto.selseal.SelMealDTO;
import com.pig4cloud.pig.school.api.entity.selseal.SelMeal;
import com.pig4cloud.pig.school.api.vo.selseal.SelMealVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 套餐表
 *
 * @author 崔俊前
 * @date 2019-08-05 14:45:21
 */
public interface SelMealMapper extends BaseMapper<SelMeal> {
  /**
    * 套餐表简单分页查询
    * @param selMeal 套餐表
    * @return
    */
  IPage<SelMeal> getSelMealPage(Page page, @Param("selMeal") SelMeal selMeal);

	/**
	 * 分页查询食谱备案信息（含供应商）
	 *
	 * @param page    分页
	 * @param selMealDTO 查询参数
	 * @return list
	 */
	IPage<List<SelMealVO>> getSelMealVosPage(Page page, @Param("query") SelMealDTO selMealDTO);

	/**
	 * 通过学校ID，查询套餐信息
	 *
	 * @param id
	 * @return
	 */
	List<SelMeal> listMealBySchoolId(Integer id);
}
