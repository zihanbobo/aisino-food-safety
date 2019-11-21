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
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 学校信息表
 *
 * @author 徐
 * @date 2019-07-09 15:35:40
 */
public interface TrendAnalysisMapper extends BaseMapper<EarlyAlarm> {

  //监管端-趋势分析-学校预警总数Top5-List<Map>
  List<Map> getEarlyWarningRank(@Param("map") Map map);

  //监管端-趋势分析-学校报警总数Top5-List<Map>
  List<Map> getEarlyAlarmRank(@Param("map") Map map);

  //监管端-预警报警分析-预警环节数量对比(根据月查询)
  List<Map> getEarlyTotalContrast(@Param("map") Map map);

  //监管端-预警报警分析-报警环节数量对比(根据月查询)
  List<Map> getAlarmTotalContrast(@Param("map") Map map);

  //监管端-预警报警分析-预警数量统计&报警数量统计(根据月查询)-Map
  Map getWarnAlarmTotalContrast(@Param("map") Map map);
  //预警数量
  Map getsum(@Param("map") Map map);

  //报警数量
  Map getsumb(@Param("map") Map map);
  //食材供应商
  Map getFoodSupplier(@Param("map") Map map);
  //查询所有学校数量
  Map getAlarmsArea1(@Param("map") Map map);
  //学校分类的数量
  Map getSchoolClassification(@Param("map") Map map);


}
