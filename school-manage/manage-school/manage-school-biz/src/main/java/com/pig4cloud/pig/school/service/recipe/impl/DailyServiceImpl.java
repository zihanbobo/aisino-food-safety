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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.recipe.Daily;
import com.pig4cloud.pig.school.api.entity.recipe.Source;
import com.pig4cloud.pig.school.api.vo.recipe.SourceVO;
import com.pig4cloud.pig.school.mapper.DailyMapper;
import com.pig4cloud.pig.school.service.recipe.DailyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 每日食谱表
 *
 * @author 沈凝
 * @date 2019-07-09 15:35:40
 */
@Service("dailyService")
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

	/**
	 * 每日食谱表简单分页查询
	 * @param daily 每日食谱表
	 * @return
	 */
	@Override
	public IPage<Daily> getDailyPage(Page<Daily> page, Daily daily){
		return baseMapper.getDailyPage(page,daily);
	}

	/**
   * 门户端接口-分页查询学校每日食谱信息
   * @param page 分页信息
   * @param daily 每日食谱信息
	 * @return
	 */
	/*@Override
	public IPage<List<Map>> getSourceByDay(Page page, Daily daily){
		return baseMapper.getSourceByDay(page,daily);
	}*/

  /**
   * 门户端接口-查询学校每日食谱信息(新)
   * @param daily 每日食谱信息
   * @return
   */
  @Override
  public List<Map> getSourceByDayForZao(Daily daily){
    return baseMapper.getSourceByDay(daily);
  }

	/**
   * 门户端接口-食材追溯-人员信息-当日厨房人员
   * @param page 分页信息
	 * @return
	 */
	@Override
	public IPage<List<Map>> getKitchenStaffDay(Page page, Map map){
		return baseMapper.getKitchenStaffDay(page,map);
	}

	/**
   * 门户端接口-食材追溯-人员信息-食品安全管理员
   * @param page 分页信息
	 * @return
	 */
	@Override
	public IPage<List<Map>> getPersonInfo(Page page, Map map){
		return baseMapper.getPersonInfo(page,map);
	}

	/**
   * 门户端接口-食材追溯-人员信息-食品安全管理员
   * @param page 分页信息
	 * @return
	 */
	@Override
	public IPage<List<Map>> getPersonInfo2(Page page, Map map){
		return baseMapper.getPersonInfo2(page,map);
	}
	/**
   * 门户端接口-食材追溯-人员信息-食品安全管理员
   * @param page 分页信息
	 * @return
	 */
	@Override
	public IPage<List<Map>> getSupplierInfo(Page page, Map map){
		return baseMapper.getSupplierInfo(page,map);
	}
	/**
   * 门户端接口-食材追溯-食材信息-留样信息
   * @param page 分页信息
	 * @return
	 */
	@Override
	public IPage<List<Map>> getSampleInfo(Page page, Map map){
		return baseMapper.getSampleInfo(page,map);
	}
	/**
   * 门户端接口-食材追溯-食材信息-食材用料
   * @param page 分页信息
	 * @return
	 */
	@Override
	public IPage<List<Map>> getIngredientsFood(Page page, Map map){
		return baseMapper.getIngredientsFood(page,map);
	}

	/**
   * 校园端,根据学校id回显当天具体用餐时段的数据
	 * @return
	 */
	@Override
	public List<Map> getDailyBySchoolId(Map map){
		return baseMapper.getDailyBySchoolId(map);
	}

  /**
   * 公众端-- 按条件检索每日食谱
   * @param page
   * @param daily
   * @return
   */
  @Override
  public IPage<List<Map>> getDailyRecipe(Page<Daily> page, Daily daily) {
    return baseMapper.getDailyRecipe(page,daily);
  }

  /**
   * 公众端-食材追溯-食材信息
   * @param page
   * @param map
   * @return
   */
  @Override
  public IPage<List<Map>> getFoodIngredients(Page page, Map map){
    return baseMapper.getFoodIngredients(page,map);
  }


  /**
   * 校园端-- 查询当前月是否已经配置食谱
   * @param map 参数
   * @return
   */
  @Override
  public List<Map> getIsDailyDay(Map map){
    return baseMapper.getIsDailyDay(map);
  }

  /**
   * app-recipes 人员信息
   * @param page
   * @param tempMap
   * @return
   */
  @Override
  public IPage<List<Map>> getPersonnelInfo(Page page, Map tempMap) {
    return baseMapper.getPersonnelInfo(page,tempMap);
  }


}
