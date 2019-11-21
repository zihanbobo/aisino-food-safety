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
import com.pig4cloud.pig.school.api.dto.recipe.SourceDTO;
import com.pig4cloud.pig.school.api.entity.recipe.Source;

/**
 * 食谱资源表
 *
 * @author 沈凝
 * @date 2019-07-09 15:16:31
 */
public interface SourceService extends IService<Source> {

  /**
   * 食谱资源表简单分页查询
   * @param source 食谱资源表
   * @return
   */
  IPage<Source> getSourcePage(Page<Source> page, Source source);


	/**
	 * 食谱资源表简单分页查询（含有配料信息）
	 * @param page    分页对象
	 * @param sourceDTO 参数列表
	 * @return
	 */
	IPage getSourceWithFoodPage(Page page, SourceDTO sourceDTO);

	/**
	 * 保存食谱信息
	 *
	 * @param sourceDto DTO 对象
	 * @return success/fail
	 */
	Boolean saveSource(SourceDTO sourceDto);

	/**
	 * 删除食谱
	 *
	 * @param source 食谱
	 * @return boolean
	 */
	Boolean removeSourceById(Source source);

}
