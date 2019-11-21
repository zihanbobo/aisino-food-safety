package com.pig4cloud.pig.school.controller.statistics;


import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.school.service.statistics.TrendAnalysisService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 食材类别表
 *
 * @author 沈凝
 * @date 2019-07-08 15:50:37
 */
@RestController
@AllArgsConstructor
@RequestMapping("/trendAnalysis")
public class TrendAnalysisController {

  private final TrendAnalysisService trendAnalysisService;


  //监管端-趋势分析-学校预警总数Top5
  @Inner
  @GetMapping("/getEarlyWarningRank")
  public R getEarlyWarningRank(@RequestParam(value="month")String month,
                             @RequestParam(value="regionalLevel")String regionalLevel,
                             @RequestParam(value="areaCode")String areaCode,
                             @RequestParam(value="limit")String limit) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("month",month);//月份
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    map.put("limit",limit);
    return new R<>(trendAnalysisService.getEarlyWarningRank(map));
  }

  //监管端-趋势分析-学校报警总数Top5
  @Inner
  @GetMapping("/getEarlyAlarmRank")
  public R getEarlyAlarmRank(@RequestParam(value="month")String month,
                              @RequestParam(value="regionalLevel")String regionalLevel,
                              @RequestParam(value="areaCode")String areaCode,
                              @RequestParam(value="limit")Integer limit) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("month",month);//学校id
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    map.put("limit",limit);
    return new R<>(trendAnalysisService.getEarlyAlarmRank(map));
  }

  //监管端-趋势分析-预警环节数量对比(根据月查询)
  @Inner
  @GetMapping("/getEarlyTotalContrast")
  public R getEarlyTotalContrast(@RequestParam(value="month")String month,
                             @RequestParam(value="regionalLevel")String regionalLevel,
                             @RequestParam(value="areaCode")Integer areaCode) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("month",month);//学校id
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    return new R<>(trendAnalysisService.getEarlyTotalContrast(map));
  }

  //监管端-趋势分析-报警环节数量对比(根据月查询)
  @Inner
  @GetMapping("/getAlarmTotalContrast")
  public R getAlarmTotalContrast(@RequestParam(value="month")String month,
                                         @RequestParam(value="regionalLevel")String regionalLevel,
                                         @RequestParam(value="areaCode")String areaCode) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("month",month);//学校id
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    return new R<>(trendAnalysisService.getAlarmTotalContrast(map));
  }


  //监管端-趋势分析-预警数量统计&报警数量统计(根据月查询)
  @Inner
  @GetMapping("/getWarnAlarmTotalContrast")
  public R getWarnAlarmTotalContrast(@RequestParam(value="month")String month,
                                         @RequestParam(value="regionalLevel")String regionalLevel,
                                         @RequestParam(value="areaCode")String areaCode) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("month",month);
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    return new R<>(trendAnalysisService.getWarnAlarmTotalContrast(map));
  }
  //报警数和预警数
  @GetMapping("/getyuandbaosum")
  public List<Map> getyuandbaosum(@RequestParam(value="year")String year,
                                  @RequestParam(value="areaCode")String areaCode) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("year",year);//年
    map.put("areaCode",areaCode);
    return trendAnalysisService.getyuandbaosum(map);
  }

  //食材供应商总量
  @GetMapping("/getFoodSupplier")
  public Map getFoodSupplier(@RequestParam(value="areaCode")String areaCode) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("areaCode",areaCode);
    return trendAnalysisService.getFoodSupplier( map );
  }

  //学校统计
  @GetMapping("/getSchoolStatistics")
  public List<Map> getSchoolStatistics(@RequestParam(value="areaCode")String areaCode) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("areaCode",areaCode);
    return trendAnalysisService.getSchoolStatistics( map );
  }


}
