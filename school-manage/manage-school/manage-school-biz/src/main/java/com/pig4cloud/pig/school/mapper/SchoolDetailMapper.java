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
 * @author 沈凝
 * @date 2019-07-09 15:35:40
 */
public interface SchoolDetailMapper extends BaseMapper<EarlyAlarm> {

  //学校资质信息
  Map getSchoolQualification(@Param("map") Map map);
  //根据学校id获得供应商信息
  List<Map> getSupplierInformation(@Param("map") Map map);

  //学校资质信息
  Map getxuke(@Param("map") Map map);
  //学校资质信息
  Map getyingye(@Param("map") Map map);
  //供应商总数
  Map getzong(@Param("map") Map map);
  //台账信息
  List<Map> getAccount(@Param("map") Map map);

  //设备信息
 List<Map>getSchoolVdDevice(@Param("map") Map map);
  //设备检查合格比率
  Map getEqOperationRatio(@Param("map") Map map);
}
