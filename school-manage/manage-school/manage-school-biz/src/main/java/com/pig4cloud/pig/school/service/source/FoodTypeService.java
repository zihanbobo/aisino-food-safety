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
package com.pig4cloud.pig.school.service.source;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.dto.source.FoodTypeTree;
import com.pig4cloud.pig.school.api.entity.source.FoodType;

import java.util.List;

/**
 * 食材类别表
 *
 * @author 沈凝
 * @date 2019-07-08 15:50:37
 */
public interface FoodTypeService extends IService<FoodType> {

  /**
   * 食材类别表简单分页查询
   * @param foodType 食材类别表
   * @return
   */
  IPage<FoodType> getFoodTypePage(Page<FoodType> page, FoodType foodType);

	/**
	 * 查询部门树菜单
	 *
	 * @return 树
	 */
	List<FoodTypeTree> listFoodTypeTrees();

	/**
	 * 查询食材信息的食材类别树
	 *
	 * @return
	 */
//	List<FoodTypeTree> listCurrentFoodFypeTrees();

}
