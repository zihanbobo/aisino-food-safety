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
package com.pig4cloud.pig.school.service.selseal.impl;

import cn.hutool.core.collection.IterUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.school.api.dto.selseal.SelMealDTO;
import com.pig4cloud.pig.school.api.entity.selseal.MealService;
import com.pig4cloud.pig.school.api.entity.selseal.SelMeal;
import com.pig4cloud.pig.school.mapper.SelMealMapper;
import com.pig4cloud.pig.school.service.selseal.MealServiceService;
import com.pig4cloud.pig.school.service.selseal.SelMealService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐表
 *
 * @author 崔俊前
 * @date 2019-08-05 14:45:21
 */
@Slf4j
@Service("selMealService")
@AllArgsConstructor
public class SelMealServiceImpl extends ServiceImpl<SelMealMapper, SelMeal> implements SelMealService {

	private final MealServiceService mealServiceService;

  /**
   * 套餐表简单分页查询
   * @param selMeal 套餐表
   * @return
   */
  @Override
  public IPage<SelMeal> getSelMealPage(Page<SelMeal> page, SelMeal selMeal){
      return baseMapper.getSelMealPage(page,selMeal);
  }


	/**
	 * 套餐表简单分页查询（含有服务信息）
	 *
	 * @param page    分页对象
	 * @param selMealDTO 参数列表
	 * @return
	 */
	@Override
	public IPage getMealWithServicePage(Page page, SelMealDTO selMealDTO) {
		return baseMapper.getSelMealVosPage(page, selMealDTO);
	}

	/**
	 * 保存套餐信息
	 *
	 * @param selMealDTO DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveSelMeal(SelMealDTO selMealDTO) {
		SelMeal selMeal = new SelMeal();
		BeanUtils.copyProperties(selMealDTO, selMeal);
		selMeal.setDelFlag(CommonConstants.STATUS_NORMAL);
		baseMapper.insert(selMeal);

		// Hutool判断服务是否为空
		if(IterUtil.isEmpty(selMealDTO.getServiceList())){
			return true;
		}
		List<MealService> mealServiceList = selMealDTO.getServiceList()
			.stream().map(serviceId -> {
				MealService mealService = new MealService();
				mealService.setMealId(selMeal.getId());
				mealService.setServiceId(serviceId);
				return mealService;
			}).collect(Collectors.toList());
		return mealServiceService.saveBatch(mealServiceList);
	}


	/**
	 * 更新套餐信息
	 *
	 * @param selMealDTO DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateSelMeal(SelMealDTO selMealDTO) {
		SelMeal selMeal = new SelMeal();
		BeanUtils.copyProperties(selMealDTO, selMeal);	//Hutool属性复制
		selMeal.setUpdateTime(LocalDateTime.now());
		this.updateById(selMeal);

		mealServiceService.remove(Wrappers.<MealService>update().lambda()
			.eq(MealService::getMealId, selMealDTO.getId()));
		// 处理服务
		selMealDTO.getServiceList().forEach(serviceId -> {
			MealService mealService = new MealService();	//中间表
			mealService.setMealId(selMeal.getId());
			mealService.setServiceId(serviceId);
			mealService.insert();
		});
		return Boolean.TRUE;
	}

	/**
	 * 删除套餐
	 *
	 * @param selMeal 套餐
	 * @return Boolean
	 */
	@Override
	public Boolean removeSelMealById(SelMeal selMeal) {
		mealServiceService.removeServiceByMealId(selMeal.getId());
		this.removeById(selMeal.getId());
		return Boolean.TRUE;
	}
}
