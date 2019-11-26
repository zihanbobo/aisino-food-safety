package com.pig4cloud.pig.school.service.statistics;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WarningsService extends IService<EarlyAlarm> {

  //预警类别
  Map getWarningType(@Param("map") Map map);
  //所有预警
  List getWarnings(@Param("map") Map map);
  //没接收预警
  List getNotReceived(@Param("map") Map map);
  //已接收预警
  List getReceivedWarnings(@Param("map") Map map);
}
