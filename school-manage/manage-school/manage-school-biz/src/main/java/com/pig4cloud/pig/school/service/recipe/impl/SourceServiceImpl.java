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
package com.pig4cloud.pig.school.service.recipe.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.school.api.dto.recipe.SourceDTO;
import com.pig4cloud.pig.school.api.entity.recipe.FoodMain;
import com.pig4cloud.pig.school.api.entity.recipe.Source;
import com.pig4cloud.pig.school.api.entity.recipe.SourceFood;
import com.pig4cloud.pig.school.mapper.SourceMapper;
import com.pig4cloud.pig.school.service.recipe.SourceFoodService;
import com.pig4cloud.pig.school.service.recipe.SourceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 食谱资源表
 *
 * @author 沈凝
 * @date 2019-07-09 15:16:31
 */
@Slf4j
@Service("sourceService")
@AllArgsConstructor
public class SourceServiceImpl extends ServiceImpl<SourceMapper, Source> implements SourceService {

	private final SourceFoodService sourceFoodService;

  /**
   * 食谱资源表简单分页查询
   * @param source 食谱资源表
   * @return
   */
  @Override
  public IPage<Source> getSourcePage(Page<Source> page, Source source){
      return baseMapper.getSourcePage(page,source);
  }


	/**
	 * 食谱资源表简单分页查询（含有配料信息）
	 *
	 * @param page    分页对象
	 * @param sourceDTO 参数列表
	 * @return
	 */
	@Override
	public IPage getSourceWithFoodPage(Page page, SourceDTO sourceDTO) {
		return baseMapper.getSourceVosPage(page, sourceDTO);
	}

	/**
	 * 保存食谱信息
	 *
	 * @param sourceDto DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveSource(SourceDTO sourceDto) {
		Source source = new Source();
		BeanUtils.copyProperties(sourceDto, source);
		source.setDelFlag(CommonConstants.STATUS_NORMAL);
		baseMapper.insert(source);

		List<SourceFood> sourceFoodList = new ArrayList<SourceFood>();	//CollUtil.isEmpty

		// 处理主料
		List<SourceFood> sourceFoodMainList = sourceDto.getMainMaterial()
			.stream().map(foodId -> {
				SourceFood sourceFood = new SourceFood();
				sourceFood.setSourceId(source.getId());
				sourceFood.setFoodId(foodId);
				sourceFood.setType("1");	//调料类型（1主料 2辅料）
				return sourceFood;
			}).collect(Collectors.toList());
		// 处理辅料
		List<SourceFood> sourceFoodMajorList = sourceDto.getMinorIngredient()
			.stream().map(foodId -> {
				SourceFood sourceFood = new SourceFood();
				sourceFood.setSourceId(source.getId());
				sourceFood.setFoodId(foodId);
				sourceFood.setType("2");	//调料类型（1主料 2辅料）
				return sourceFood;
			}).collect(Collectors.toList());
		// 将主料辅料合并后插入
		sourceFoodList.addAll(sourceFoodMainList);
		sourceFoodList.addAll(sourceFoodMajorList);
		return sourceFoodService.saveBatch(sourceFoodList);
	}

	/**
	 * 删除食谱
	 *
	 * @param source 食谱
	 * @return Boolean
	 */
	@Override
	public Boolean removeSourceById(Source source) {
		sourceFoodService.removeFoodBySourceId(source.getId());
		this.removeById(source.getId());
		return Boolean.TRUE;
	}


}
