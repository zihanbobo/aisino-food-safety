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
import com.pig4cloud.pig.school.api.entity.recipe.Daily;
import com.pig4cloud.pig.school.api.entity.recipe.Source;
import com.pig4cloud.pig.school.api.vo.recipe.SourceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 每日食谱表
 *
 * @author 沈凝
 * @date 2019-07-09 15:35:40
 */
public interface DailyMapper extends BaseMapper<Daily> {
  /**
    * 每日食谱表简单分页查询
    * @param daily 每日食谱表
    * @return
    */
  IPage<Daily> getDailyPage(Page page, @Param("daily") Daily daily);

  /**
   * 门户端接口-分页查询学校每日食谱信息
   * @param page 分页信息
   * @param daily 每日食谱信息
   * @return
   */
  //IPage<List<Map>> getSourceByDay(Page page, @Param("daily") Daily daily);

  /**
   * 门户端接口-分页查询学校每日食谱信息
   * @param daily 每日食谱信息
   * @return
   */
  List<Map> getSourceByDay(@Param("daily") Daily daily);
  /**
   * 门户端接口-食材追溯-人员信息-当日厨房人员
   * @param page 分页信息
   * @param map 每日食谱信息
    * @return
    */
  IPage<List<Map>> getKitchenStaffDay(Page page, @Param("map") Map map);

  /**
   * 门户端接口-食材追溯-人员信息-食品安全管理员
   * @param page 分页信息
    * @return
    */
  IPage<List<Map>> getPersonInfo(Page page, @Param("map") Map map);
  /**
   * 门户端接口-食材追溯-人员信息-食品安全管理员
   * @param page 分页信息
    * @return
    */
  IPage<List<Map>> getPersonInfo2(Page page, @Param("map") Map map);
  /**
   * 门户端接口-食材追溯-食材信息-供应商信息
   * @param page 分页信息
    * @return
    */
  IPage<List<Map>> getSupplierInfo(Page page, @Param("map") Map map);
  /**
   * 门户端接口-食材追溯-食材信息-留样信息
   * @param page 分页信息
    * @return
    */
  IPage<List<Map>> getSampleInfo(Page page, @Param("map") Map map);
  /**
   * 门户端接口-食材追溯-食材信息-食材用料
   * @param page 分页信息
    * @return
    */
  IPage<List<Map>> getIngredientsFood(Page page, @Param("map") Map map);

  /**
   * 校园端,根据学校id回显当天具体用餐时段的数据
   * @param map 每日食谱表
   * @return
   */
  List<Map> getDailyBySchoolId(@Param("map") Map map);
  /**
   * 公众端-- 按条件检索每日食谱
   * @param daily
   * @return
   */
  IPage<List<Map>> getDailyRecipe(Page page, @Param("daily")Daily daily);

  /**
   * 公众端-食材追溯-食材信息
   * @param page 分页信息
   * @return
   */
  IPage<List<Map>> getFoodIngredients(Page page, @Param("map") Map map);

  /**
   * 校园端-- 查询当前月是否已经配置食谱
   * @param map 每日食谱表
   * @return
   */
  List<Map> getIsDailyDay(@Param("map") Map map);

  /**
   * app-recipes 人员信息
   * @param page
   * @param tempMap
   * @return
   */
  IPage<List<Map>> getPersonnelInfo(Page page, @Param("map") Map tempMap);
}
