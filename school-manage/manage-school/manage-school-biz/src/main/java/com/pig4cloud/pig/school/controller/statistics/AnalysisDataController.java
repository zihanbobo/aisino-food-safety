package com.pig4cloud.pig.school.controller.statistics;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.school.service.statistics.AnalysisDataService;
import com.pig4cloud.pig.school.service.statistics.SchoolDetailsService;
import com.pig4cloud.pig.school.service.statistics.WarningsService;
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


//学校详情
  private final SchoolDetailsService schoolDetailsService;

  //学校信息上
  @Inner
  @GetMapping("/getSchoolInformation")
  public R getSchoolInformation(@RequestParam(value="schoolId")String schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(schoolDetailsService.getSchoolInformation(map));
  }

  //学校资质信息
  @Inner
  @GetMapping("/getSchoolQualification")
  public R getSchoolQualification(@RequestParam(value="schoolId")String schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(schoolDetailsService.getSchoolQualification(map));
  }

  //供应商信息
  @Inner
  @GetMapping("/getSupplierInformation")
  public R getSupplierInformation(@RequestParam(value="schoolId")String schoolId){
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(schoolDetailsService.getSupplierInformation( map ));
  }

  //人员信息
  @Inner
  @GetMapping("/getPersonnelInformation")
  public R getPersonnelInformation(@RequestParam(value="schoolId")String schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(schoolDetailsService.getPersonnelInformation(map));
  }
  //设备信息
  @Inner
  @GetMapping("/getDeviceInformation")
  public R getDeviceInformation(@RequestParam(value="schoolId")String schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(schoolDetailsService.getDeviceInformation(map));
  }
  //食材信息
  @Inner
  @GetMapping("/getIngredientsInformation")
  public R getIngredientsInformation(@RequestParam(value="page")Integer page,
                                     @RequestParam(value="size")Integer size,
                                     @RequestParam(value="schoolId")String schoolId,
                                     @RequestParam(value="startingTime")String startingTime,
                                     @RequestParam(value="endTime")String endTime) {

    // 根据输入参数组装分页类
    Page<Object> page1 = new Page<>();
    page1.setSize(new Long((long)size));
    page1.setCurrent(new Long((long)page));
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    map.put("startingTime",startingTime);
    map.put("endTime",endTime);
    return new R<>(schoolDetailsService.getIngredientsInformation(page1,map));
  }
  //台账信息
  @Inner
  @GetMapping("/getAccount")
  public R getAccount(@RequestParam(value="schoolId")String schoolId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(schoolDetailsService.getAccount(map));
  }
  //历史报警
  @Inner
  @GetMapping("/getHistoricalAlarm")
  public R getHistoricalAlarm(@RequestParam(value="page")Integer page,
                              @RequestParam(value="size")Integer size,
                              @RequestParam(value="schoolId")String schoolId) {
    // 根据输入参数组装分页类
    Page<Object> page1 = new Page<>();
    page1.setSize(new Long((long)size));
    page1.setCurrent(new Long((long)page));
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    return new R<>(schoolDetailsService.getHistoricalAlarm(page1,map));
  }
  //历史报警(供应商)详情
  @Inner
  @GetMapping("/getHistoryDetails")
  public R getHistoryDetails(@RequestParam(value="schoolId")String schoolId,
                             @RequestParam(value="iswarning")Integer iswarning,
                             @RequestParam(value="warningId")Integer warningId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    map.put("iswarning",iswarning);
    map.put("warningId",warningId);
    return new R<>(schoolDetailsService.getHistoryDetails(map));
  }
  //历史报警(食材)详情
  @Inner
  @GetMapping("/getHistoryFood")
  public R getHistoryFood(@RequestParam(value="schoolId")String schoolId,
                             @RequestParam(value="iswarning")Integer iswarning,
                             @RequestParam(value="warningId")Integer warningId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    map.put("iswarning",iswarning);
    map.put("warningId",warningId);
    return new R<>(schoolDetailsService.getHistoryFood(map));
  }
  //历史报警(人员)详情
  @Inner
  @GetMapping("/getHistoryMan")
  public R getHistoryMan(@RequestParam(value="schoolId")String schoolId,
                             @RequestParam(value="iswarning")Integer iswarning,
                             @RequestParam(value="warningId")Integer warningId) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    map.put("iswarning",iswarning);
    map.put("warningId",warningId);
    return new R<>(schoolDetailsService.getHistoryMan(map));
  }





  private final WarningsService warningsService;

  //预警和报警信息

  //预警类型
  @Inner
  @GetMapping("/getWarningType")
  public R getWarningType(@RequestParam(value="year")String year,
                          @RequestParam(value="regionalLevel")String regionalLevel,
                          @RequestParam(value="areaCode")String areaCode) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("year",year);//年
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    return new R<>(warningsService.getWarningType(map));
  }
  //全部预警
  @Inner
  @GetMapping("/getWarnings")
  public R getWarnings(@RequestParam(value="WarningInfor",required = false)Integer WarningInfor,
                       @RequestParam(value="schoolName",required = false)String schoolName,
                       @RequestParam(value="startingTime",required = false)String startingTime,
                       @RequestParam(value="endTime",required = false)String endTime,
                       @RequestParam(value="dealWith",required = false)String dealWith,
                       @RequestParam(value="page")Integer page,
                       @RequestParam(value="size")Integer size,
                       @RequestParam(value="regionalLevel")String regionalLevel,
                       @RequestParam(value="areaCode")String areaCode,
                       @RequestParam(value="Type")Integer Type){
    // 根据输入参数组装分页类
    Page<Object> page1 = new Page<>();
    page1.setSize(new Long((long)size));
    page1.setCurrent(new Long((long)page));
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("WarningInfor",WarningInfor);//预警信息
    map.put("schoolName",schoolName);
    map.put("startingTime",startingTime);//开始时间
    map.put("endTime",endTime);//结束时间
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    map.put("Type",Type);//预警类型
    map.put("dealWith",dealWith);//是否已查看
    return new R<>(warningsService.getWarnings(page1,map));
  }

  //报警类型
  @Inner
  @GetMapping("/getAlarmType")
  public R getAlarmType(@RequestParam(value="year")String year,
                          @RequestParam(value="regionalLevel")String regionalLevel,
                          @RequestParam(value="areaCode")String areaCode) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("year",year);//年
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    return new R<>(warningsService.getAlarmType(map));
  }
  //全部报警
  @Inner
  @GetMapping("/getAlarms")
  public R getAlarms(@RequestParam(value="AlarmInfor",required = false)Integer AlarmInfor,
                       @RequestParam(value="schoolName",required = false)String schoolName,
                       @RequestParam(value="startingTime",required = false)String startingTime,
                       @RequestParam(value="endTime",required = false)String endTime,
                       @RequestParam(value="page")Integer page,
                       @RequestParam(value="size")Integer size,
                       @RequestParam(value="regionalLevel")String regionalLevel,
                       @RequestParam(value="areaCode")String areaCode,
                       @RequestParam(value="Type")Integer Type){
    // 根据输入参数组装分页类
    Page<Object> page1 = new Page<>();
    page1.setSize(new Long((long)size));
    page1.setCurrent(new Long((long)page));
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("AlarmInfor",AlarmInfor);//预警信息
    map.put("schoolName",schoolName);
    map.put("startingTime",startingTime);//开始时间
    map.put("endTime",endTime);//结束时间
    map.put("regionalLevel",regionalLevel);//监管用户等级(1全国2省3市4区)[暂时预留]
    map.put("areaCode",areaCode);
    map.put("Type",Type);//预警类型
    return new R<>(warningsService.getAlarms(page1,map));
  }
}
