package com.pig4cloud.pig.school.service.statistics;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WarningsService extends IService<EarlyAlarm> {

  //预警类别
  Map getWarningType(@Param("map") Map map);
  //所有预警
  IPage<List<Map>> getWarnings(Page page, @Param("map") Map map);
  //报警类别
  Map getAlarmType(@Param("map") Map map);
  //所有报警
  IPage<List<Map>> getAlarms(Page page,@Param("map") Map map);

}
