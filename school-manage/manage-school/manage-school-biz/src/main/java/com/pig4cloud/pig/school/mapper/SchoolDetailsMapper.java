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
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 学校信息表
 *
 * @author 沈凝
 * @date 2019-07-09 15:35:40
 */
public interface SchoolDetailsMapper extends BaseMapper<EarlyAlarm> {

  //学校信息上
  Map getSchoolInformation(@Param("map") Map map);
  Map getWarningNumber(@Param("map") Map map);
  Map getAlarmNumber(@Param("map") Map map);
  //学校资质信息
  Map getSchoolQualification(@Param("map") Map map);
  //供应商信息
  List<Map<String,Object>> getSupplierInformation(@Param("map") Map map);
  //根据供应商id获得学校名称
  Map getSchoolName(@Param("map") Map map);
  //许可数
  Map getLicense(@Param("map") Map map);
  //营业数
  Map getOpen(@Param("map") Map map);
  //供应商总数
  Map getTotal(@Param("map") Map map);
  //大厨信息
  List<Map> getChefInformation(@Param("map") Map map);
  //食品安全人员信息
  List<Map> getFoodSafetyInformation(@Param("map") Map map);
  //陪餐人员信息
  List<Map> getFoodEscortInformation(@Param("map") Map map);
  //各种类人员数量
  List getManNumber(@Param("map") Map map);
  //人员总数和晨检合格率
  Map getMorningCheck(@Param("map") Map map);
  //健康证数量
  Map getHealthCheck(@Param("map") Map map);
  //设备信息
  List getDeviceInformation(@Param("map") Map map);
  //设备数量和比率
  Map getEqOperationRatio(@Param("map") Map map);
  //台账统计
  Map getAccount(@Param("map") Map map);
  //食材信息
  IPage<List<Map>> getIngredientsInformation(Page page, @Param("map") Map map);
  //报警信息
  IPage<List<Map>> getHistoricalAlarm(Page page,@Param("map") Map map);
  //历史报警详情
  List getHistoryDetailsA(@Param("map") Map map);
  //历史预警详情
  List getHistoryDetailsW(@Param("map") Map map);
  //历史预警基础信息(供应商)
  Map getBasicInformationW(@Param("map") Map map);
  //历史报警基础信息(供应商)
  Map getBasicInformationA(@Param("map") Map map);
  //历史报警基础信息(人员)
  Map getManInformationA(@Param("map") Map map);
  //历史预警基础信息(人员)
  Map getManInformationW(@Param("map") Map map);
  //历史报警基础信息(食材)
  Map getFoodInformationA(@Param("map") Map map);
  //历史预警基础信息(食材)
  Map getFoodInformationW(@Param("map") Map map);
  //食材所关联学校
  List<Map>getSchNameFood(@Param("map") Map map);
  //同一预警数量
  Integer getSameWarning(@Param("map") Map map);
  //同一报警数量
  Integer getSameAlarm(@Param("map") Map map);
}
