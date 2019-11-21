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
import com.pig4cloud.pig.school.api.dto.recipe.FoodFilingDTO;
import com.pig4cloud.pig.school.api.entity.recipe.FoodFiling;
import com.pig4cloud.pig.school.api.entity.recipe.FoodMain;
import com.pig4cloud.pig.school.api.vo.recipe.FoodFilingVO;
import com.pig4cloud.pig.school.mapper.FoodFilingMapper;
import com.pig4cloud.pig.school.service.recipe.FoodFilingService;
import com.pig4cloud.pig.school.service.source.FoodMainService;
import com.pig4cloud.pig.school.service.source.FoodTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 食材备案表
 *
 * @author 沈凝
 * @date 2019-07-04 00:33:20
 */
@Slf4j
@Service("foodFilingService")
@AllArgsConstructor
public class FoodFilingServiceImpl extends ServiceImpl<FoodFilingMapper, FoodFiling> implements FoodFilingService {

	private final FoodTypeService foodTypeService;
	private final FoodMainService foodMainService;
  /**
   * 食材备案表简单分页查询（含有供应商信息）
   * @param foodFiling 食材备案表
   * @return
   */
  @Override
  public IPage getFoodFilingPage(Page page, FoodFiling foodFiling){
      return baseMapper.getFoodFilingPage(page,foodFiling);
  }
  /**
   * 分页查询食材信息（含有供应商信息）
   *
   * @param page    分页对象
   * @param foodFilingDTO 参数列表
   * @return
   */
  @Override
  public IPage getFoodFilingWithMainFilingPage(Page page, FoodFilingDTO foodFilingDTO) {
		return baseMapper.getFoodFilingVosPage(page, foodFilingDTO);
	}

  /**
   * 通过ID查询食材信息
   *
   * @param id 食材ID
   * @return 食材信息
   */
  @Override
  public FoodFilingVO getFoodFilingVoById(Integer id) {
		return baseMapper.getFoodFilingVoById(id);
	}


	/**
	 * 更新食材信息
	 *
	 * @param foodFilingDto DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateFoodFiling(FoodFilingDTO foodFilingDto) {
		FoodFiling foodFiling = new FoodFiling();
		BeanUtils.copyProperties(foodFilingDto, foodFiling);
		foodFiling.setUpdateTime(LocalDateTime.now());
		this.updateById(foodFiling);
		foodMainService.remove(Wrappers.<FoodMain>update().lambda()
			.eq(FoodMain::getFoodId, foodFilingDto.getId()));
		foodFilingDto.getMainFilingList().forEach(supId -> {
			FoodMain foodMain = new FoodMain();	//中间表
			foodMain.setFoodId(foodFiling.getId());
			foodMain.setMainId(supId);
			foodMain.insert();
		});
		return Boolean.TRUE;
	}

	/**
	 * 保存用户信息
	 *
	 * @param foodFilingDto DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveFoodFiling(FoodFilingDTO foodFilingDto) {
		FoodFiling foodFiling = new FoodFiling();
		BeanUtils.copyProperties(foodFilingDto, foodFiling);
		foodFiling.setDelFlag(CommonConstants.STATUS_NORMAL);
		baseMapper.insert(foodFiling);
		List<FoodMain> userRoleList = foodFilingDto.getMainFilingList()
			.stream().map(supId -> {
				FoodMain foodMain = new FoodMain();	//中间表
				foodMain.setFoodId(foodFiling.getId());
				foodMain.setMainId(supId);
				return foodMain;
			}).collect(Collectors.toList());
		return foodMainService.saveBatch(userRoleList);
	}

	/**
	 * 删除食材
	 *
	 * @param foodFiling 食材
	 * @return Boolean
	 */
	@Override
//	@CacheEvict(value = "user_details", key = "#sysUser.username")
	public Boolean removeFoodById(FoodFiling foodFiling) {
		foodMainService.removeMainByUserId(foodFiling.getId());
		this.removeById(foodFiling.getId());
		return Boolean.TRUE;
	}


}
