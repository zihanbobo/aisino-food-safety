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
package com.pig4cloud.pig.school.service.selseal;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.dto.selseal.SelMealDTO;
import com.pig4cloud.pig.school.api.entity.selseal.SelMeal;

/**
 * 套餐表
 *
 * @author 崔俊前
 * @date 2019-08-05 14:45:21
 */
public interface SelMealService extends IService<SelMeal> {

  /**
   * 套餐表简单分页查询
   * @param selMeal 套餐表
   * @return
   */
  IPage<SelMeal> getSelMealPage(Page<SelMeal> page, SelMeal selMeal);

	/**
	 * 套餐表简单分页查询（含有服务信息）
	 * @param page    分页对象
	 * @param selMealDTO 参数列表
	 * @return
	 */
	IPage getMealWithServicePage(Page page, SelMealDTO selMealDTO);

	/**
	 * 保存套餐信息
	 *
	 * @param selMealDto DTO 对象
	 * @return success/fail
	 */
	Boolean saveSelMeal(SelMealDTO selMealDto);

	/**
	 * 更新指定套餐信息
	 *
	 * @param selMealDto 套餐信息
	 * @return
	 */
	Boolean updateSelMeal(SelMealDTO selMealDto);

	/**
	 * 删除套餐
	 *
	 * @param selMeal 套餐
	 * @return boolean
	 */
	Boolean removeSelMealById(SelMeal selMeal);

}
