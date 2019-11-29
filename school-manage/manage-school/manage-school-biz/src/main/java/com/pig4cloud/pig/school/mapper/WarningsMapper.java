package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface WarningsMapper extends BaseMapper<EarlyAlarm> {
  //预警类别
  List getWarningType();
  //报警类别
  List getAlarmType();
  //报警类别统计
  Map getAlarmTotalByYear(@Param("map") Map map);
  //预警类别统计
  Map getWarningTotalByYear(@Param("map") Map map);
  //所有预警
  IPage<List<Map>> getWarnings(Page page, @Param("map") Map map);
  //同一预警数量
  Integer getSameWarning(@Param("map") Map map);
  //关联预警数量
  Map getAssociationWarning(@Param("map") Map map);
  //所有报警
  IPage<List<Map>> getAlarms(Page page,@Param("map") Map map);
  //同一报警数量
  Integer getSameAlarm(@Param("map") Map map);
  //关联报警数量
  Map getAssociationAlarm(@Param("map") Map map);
}
