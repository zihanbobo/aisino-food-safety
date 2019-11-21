package com.pig4cloud.pig.school.service.statistics;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
public interface TrendAnalysisService extends IService<EarlyAlarm> {

  //监管端-趋势分析-学校预警总数Top5
  List<Map> getEarlyWarningRank(@Param("map") Map map);

  //监管端-趋势分析-学校报警总数Top5
  List<Map> getEarlyAlarmRank(@Param("map") Map map);

  //监管端-预警报警分析-预警环节数量对比(根据月查询)
  List<Map> getEarlyTotalContrast(@Param("map") Map map);

  //监管端-预警报警分析-报警环节数量对比(根据月查询)
  List<Map> getAlarmTotalContrast(@Param("map") Map map);

  //监管端-预警报警分析-预警数量统计&报警数量统计(根据月查询)-Map
  Map getWarnAlarmTotalContrast(@Param("map") Map map);
  //学校资质信息
  List<Map> getyuandbaosum(@Param("map") Map map);

  //食材供应商
  Map getFoodSupplier(@Param("map") Map map);

  //学校统计
  List<Map> getSchoolStatistics(@Param("map") Map map);

}
