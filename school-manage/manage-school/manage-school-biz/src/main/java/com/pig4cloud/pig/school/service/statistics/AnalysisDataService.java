package com.pig4cloud.pig.school.service.statistics;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 监管端-异常检测
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
public interface AnalysisDataService extends IService<EarlyAlarm> {

  //预警列表
  List<Map> getEarlywarningListYear(@Param("map") Map map);
  Integer getEarlyTotal(@Param("map") Map map);// 预警数量

  //报警列表
  List<Map> getAlarmListYear(@Param("map") Map map);
  Integer getAlarmTotal(@Param("map") Map map);// 报警数量


  //2-1:报警数量统计柱状图
  List getAlarmsNumberBymonth(@Param("map") Map map);

  //2-2:报警类别统计饼状图
  Map getAlarmTotalByYear(@Param("map") Map map);

  //2-3:报警区域统计柱状图
  List<Map> getAlarmsAreaByRegion(@Param("map") Map map);

  //2-4:报警区域横向柱状图
  List getAlarmsArea(@Param("map") Map map);

}
