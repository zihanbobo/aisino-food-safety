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
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import com.pig4cloud.pig.school.api.entity.recipe.Daily;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 每日食谱表
 *
 * @author 沈凝
 * @date 2019-07-09 15:35:40
 */
public interface WelHeadmasterMapper extends BaseMapper<EarlyAlarm> {

  //第一行-校园端-欢迎页-校长-预警统计
  Map getEarlyWarnTotal(@Param("map") Map map);

  //第一行-校园端-欢迎页-校长-报警统计
  List<Map> getAlarmTotal(@Param("map") Map map);

  //第一行-校园端-欢迎页-校长-本周报警统计按类别
  List<Map> getAlarmWeekTotal(@Param("map") Map map);



  //第2行-校园端-欢迎页-校长-监管及通知公告统计
  Map getPurchaseWarn(@Param("map") Map map);

  //第2行-校园端-欢迎页-校长-台账统计
  Map getAccount(@Param("map") Map map);



  //第三行-校园端-欢迎页-校长-人员信息
  Map getSchoolUserInfo(@Param("map") Map map);

  //第三行-校园端-欢迎页-校长-当日人员晨检合格率
  Map getDayUserQualified(@Param("map") Map map);

  //第三行-校园端-欢迎页-校长-食材信息
  Map getFoodInfo(@Param("map") Map map);

  //第三行-校园端-欢迎页-校长-当日采购信息
  List<Map> getDayPurchase(@Param("map") Map map);

  //第三行-校园端-欢迎页-校长-当日采购票证上传率与高风险
  Map getHighRisk(@Param("map") Map map);



  // 第四行-校园端-欢迎页-校长-供应商信息
  Map getMainFilingInfo(@Param("map") Map map);

  // 第四行-校园端-欢迎页-校长-厨余设备
  Map getVdDevice(@Param("map") Map map);



  // 第一行-校园端-欢迎页-员工信息
  Map getUserInfo(@Param("map") Map map);
  // 第四行-校园端-欢迎页-设备信息
  List<Map> getSchoolVdDevice(@Param("map") Map map);
  // 第四行-校园端-欢迎页-设备比率
  Map getEqOperationRatio(@Param("map") Map map);
}
