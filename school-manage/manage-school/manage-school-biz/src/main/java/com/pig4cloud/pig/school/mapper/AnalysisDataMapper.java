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
 * 监管端-异常检测
 *
 * @author 沈凝
 * @date 2019-11-21 09:12:40
 */
public interface AnalysisDataMapper extends BaseMapper<EarlyAlarm> {

  // 1-1:预警列表
  List<Map> getEarlywarningListYear(@Param("map") Map map);
  Integer getEarlyTotal(@Param("map") Map map);  // 预警数量

  // 1-2:报警列表
  List<Map> getAlarmListYear(@Param("map") Map map);
  Integer getAlarmTotal(@Param("map") Map map);  // 报警数量

  // 2-1:报警数量统计柱状图
  Integer getAlarmsNumberBymonth(@Param("map") Map map);

  // 2-2:报警类别统计饼状图
  Map getAlarmTotalByYear(@Param("map") Map map);

  // 2-3:报警区域统计柱状图
  List<Map>getAlarmsAreaByRegion(@Param("map") Map map);

  // 2-4:报警区域横向柱状图
  List<Map> getAlarmsArea(@Param("map") Map map);

}
