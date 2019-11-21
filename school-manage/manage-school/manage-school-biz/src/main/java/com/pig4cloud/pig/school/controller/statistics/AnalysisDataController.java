package com.pig4cloud.pig.school.controller.statistics;

import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.school.service.statistics.AnalysisDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监管端-预警报警分析
 *
 * @author 沈凝
 * @date 2019-07-08 15:50:37
 */
@RestController
@AllArgsConstructor
@RequestMapping("/analysisData")
public class AnalysisDataController {

  private final AnalysisDataService analysisDataService;

  //1-1:预警列表
  @Inner
  @GetMapping("/getEarlywarningListYear")
  public R getEarlywarningListYear(@RequestParam(value="year")String year,
                                           @RequestParam(value="regionalLevel")String regionalLevel,
                                           @RequestParam(value="areaCode")String areaCode
                                           ) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("year",year);//学校id
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    Map<String,Object> mapR = new HashMap<String,Object>();
    List<Map> earlyList = analysisDataService.getEarlywarningListYear(map);
    Integer earlyTotal = analysisDataService.getEarlyTotal(map);
    mapR.put("earlyList",earlyList);
    mapR.put("earlyTotal",earlyTotal);
    return new R<>(mapR);
  }

  //1-2:报警列表
  @Inner
  @GetMapping("/getAlarmListYear")
  public R getAlarmListYear(@RequestParam(value="year")String year,
                                    @RequestParam(value="regionalLevel")String regionalLevel,
                                   @RequestParam(value="areaCode")String areaCode) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("year",year);//年
    map.put("regionalLevel",regionalLevel);//年
    map.put("areaCode",areaCode);
    Map<String,Object> mapR = new HashMap<String,Object>();
    List<Map> alarmList = analysisDataService.getAlarmListYear(map);
    Integer alarmTotal = analysisDataService.getAlarmTotal(map);
    mapR.put("alarmList",alarmList);
    mapR.put("alarmTotal",alarmTotal);
    return new R<>(mapR);
  }

  //2-1:报警数量统计柱状图
  @Inner
  @GetMapping("/getAlarmsNumberByMonth")
  public R getAlarmsNumberBymonth(@RequestParam(value="year")String year,
                                     @RequestParam(value="regionalLevel")String regionalLevel,
                                     @RequestParam(value="areaCode")String areaCode){
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("year",year);
    map.put("regionalLevel",regionalLevel);
    map.put("areaCode",areaCode);
    return new R<>(analysisDataService.getAlarmsNumberBymonth(map));
  }


  //2-2:报警类别统计饼状图
  @Inner
  @GetMapping("/getAlarmTotalByYear")
  public R getAlarmTotalByYear(@RequestParam(value="year")String year,
                             @RequestParam(value="regionalLevel")String regionalLevel,
                             @RequestParam(value="areaCode")String areaCode) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("year",year);//学校id
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    return new R<>(analysisDataService.getAlarmTotalByYear(map));
  }

  //2-3:报警区域统计柱状图
  @Inner
  @GetMapping("/getAlarmsAreaByRegion")
  public R getAlarmsAreabyregion(@RequestParam(value="year")String year,
                                         @RequestParam(value="regionalLevel")String regionalLevel,
                                         @RequestParam(value="areaCode")String areaCode,
                                         @RequestParam(value="limit")Integer limit){
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("year",year);//学校id
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    map.put("limit",limit);
    return new R<>(analysisDataService.getAlarmsAreaByRegion(map));
  }


  // 2-4:报警区域横向柱状图
  @Inner
  @GetMapping("/getAlarmsArea")
  public R getAlarmsArea(@RequestParam(value="year")String year,
                            @RequestParam(value="regionalLevel")String regionalLevel,
                            @RequestParam(value="areaCode")String areaCode,
                            @RequestParam(value="limit")Integer limit){
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("year",year);//学校id
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    map.put("limit",limit);
    return new R<>(analysisDataService.getAlarmsArea(map));
  }

}
