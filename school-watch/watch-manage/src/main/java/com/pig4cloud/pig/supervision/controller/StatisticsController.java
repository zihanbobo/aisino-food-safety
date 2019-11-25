package com.pig4cloud.pig.supervision.controller;

import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import com.pig4cloud.pig.school.api.feign.RemoteStatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 异常检测
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

	private final RemoteUserService remoteUserService;
	private final RemoteSchoolService remoteSchoolService;
	private final RemoteStatisticsService remoteStatisticsService;

	/**
	 * 1-预警列表
	 * @return
	 */
	@GetMapping("/getEarlywarningListYear")
	public R getEarlywarningListYear(@RequestParam(value="year")String year,
                              @RequestParam(value="regionalLevel")String regionalLevel,
                              @RequestParam(value="areaCode")String areaCode) {
    R rList = remoteStatisticsService.getEarlywarningListYear(year, regionalLevel, areaCode, SecurityConstants.FROM_IN);
    return new R<>(rList.getData());
	}

  /**
   * 2-报警列表
   * @return
   */
  @GetMapping("/getAlarmListYear")
  public R getAlarmListYear(@RequestParam(value="year")String year,
                                   @RequestParam(value="regionalLevel")String regionalLevel,
                                   @RequestParam(value="areaCode")String areaCode) {
    R rList = remoteStatisticsService.getAlarmListYear(year, regionalLevel, areaCode, SecurityConstants.FROM_IN);
    return new R<>(rList.getData());
  }

  /**
   * 3-报警数量统计折线图
   * @return
   */
  @GetMapping("/getAlarmsNumberByMonth")
  public R getAlarmsNumberByMonth(@RequestParam(value="year")String year,
                                   @RequestParam(value="regionalLevel")String regionalLevel,
                                   @RequestParam(value="areaCode")String areaCode) {
    R rList = remoteStatisticsService.getAlarmsNumberByMonth(year, regionalLevel, areaCode, SecurityConstants.FROM_IN);
    return new R<>(rList.getData());
  }
  /**
   * 报警类别统计饼状图
   * @return
   */
  @GetMapping("/getAlarmTotalByYear")
  public R getAlarmTotalByYear(@RequestParam(value="year")String year,
                               @RequestParam(value="regionalLevel")String regionalLevel,
                               @RequestParam(value="areaCode")String areaCode) {
    R rList = remoteStatisticsService.getAlarmTotalByYear(year, regionalLevel, areaCode, SecurityConstants.FROM_IN);
    return new R<>(rList.getData());
  }
  /**
   * 报警区域统计柱状图
   * @return
   */
  @GetMapping("/getAlarmsAreaByRegion")
  public R getAlarmsAreaByRegion(@RequestParam(value="year")String year,
                               @RequestParam(value="regionalLevel")String regionalLevel,
                               @RequestParam(value="areaCode")String areaCode,
                               @RequestParam(value="limit",required = false)Integer limit) {
    if(limit==null){
      limit = 5;
    }
    R rList = remoteStatisticsService.getAlarmsAreaByRegion(year, regionalLevel, areaCode, limit, SecurityConstants.FROM_IN);
    return new R<>(rList.getData());
  }
  /**
   * 报警区域统计横向柱状图
   * @return
   */
  @GetMapping("/getAlarmsArea")
  public R getAlarmsArea(@RequestParam(value="year")String year,
                               @RequestParam(value="regionalLevel")String regionalLevel,
                               @RequestParam(value="areaCode")String areaCode,
                               @RequestParam(value="limit",required = false)Integer limit) {
    if(limit==null){
      limit = 5;
    }
    R rList = remoteStatisticsService.getAlarmsArea(year, regionalLevel, areaCode, limit, SecurityConstants.FROM_IN);
    return new R<>(rList.getData());
  }




  /**
   * 监管端-趋势分析-预警环节数量对比(根据月查询)
   * @return
   */
  @GetMapping("/getEarlyTotalContrast")
  public R getEarlyTotalContrast(@RequestParam(value="month")String month,
                                 @RequestParam(value="regionalLevel")String regionalLevel,
                                 @RequestParam(value="areaCode")String areaCode) {
    R rList = remoteStatisticsService.getEarlyTotalContrast(month, regionalLevel, areaCode, SecurityConstants.FROM_IN);
    return new R<>(rList.getData());
  }

  /**
   * 监管端-趋势分析-学校预警总数Top5
   * @return
   */
  @GetMapping("/getEarlyWarningRank")
  public R getEarlyWarningRank(@RequestParam(value="month")String month,
                               @RequestParam(value="regionalLevel")String regionalLevel,
                               @RequestParam(value="areaCode")String areaCode,
                               @RequestParam(value="limit",required = false)Integer limit) {
    if(limit==null){
      limit = 5;
    }
    R rList = remoteStatisticsService.getEarlyWarningRank(month, regionalLevel,areaCode,limit,SecurityConstants.FROM_IN);
    return new R<>(rList.getData());
  }
  /**
   * 监管端-趋势分析-学校报警总数Top5
   * @return
   */
  @GetMapping("/getEarlyAlarmRank")
  public R getEarlyAlarmRank(@RequestParam(value="month")String month,
                             @RequestParam(value="regionalLevel")String regionalLevel,
                             @RequestParam(value="areaCode")String areaCode,
                             @RequestParam(value="limit",required = false)Integer limit) {
    if(limit==null){
      limit = 5;
    }
    R rList = remoteStatisticsService.getEarlyAlarmRank(month, regionalLevel,areaCode,limit,SecurityConstants.FROM_IN);
    return new R<>(rList.getData());
  }

  /**
   * 监管端-趋势分析-报警环节数量对比(根据月查询)
   * @return
   */
  @GetMapping("/getAlarmTotalContrast")
  public R getAlarmTotalContrast(@RequestParam(value="month")String month,
                               @RequestParam(value="regionalLevel")String regionalLevel,
                               @RequestParam(value="areaCode")String areaCode) {
    R rList = remoteStatisticsService.getAlarmTotalContrast(month, regionalLevel, areaCode, SecurityConstants.FROM_IN);
    return new R<>(rList.getData());
  }
  /**
   * 监管端-趋势分析-预警数量统计&报警数量统计(根据月查询)
   * @return
   */
  @GetMapping("/getWarnAlarmTotalContrast")
  public R getWarnAlarmTotalContrast(@RequestParam(value="month")String month,
                               @RequestParam(value="regionalLevel")String regionalLevel,
                               @RequestParam(value="areaCode")String areaCode) {
    R rList = remoteStatisticsService.getWarnAlarmTotalContrast(month, regionalLevel, areaCode, SecurityConstants.FROM_IN);
    return new R<>(rList.getData());
  }






  //学校信息上
  @GetMapping("/getSchoolInformation")
  public R getSchoolInformation(@RequestParam(value="schoolId")String schoolId) {
    return new R<>(remoteStatisticsService.getSchoolInformation(schoolId, SecurityConstants.FROM_IN));
  }
  //学校资质信息
  @GetMapping("/getSchoolQualification")
  public R getSchoolQualification(@RequestParam(value="schoolId")String schoolId) {
    return new R<>(remoteStatisticsService.getSchoolQualification(schoolId, SecurityConstants.FROM_IN));
  }

  //供应商信息
  @GetMapping("/getSupplierInformation")
  public R getSupplierInformation(@RequestParam(value="schoolId")String schoolId){
    return new R<>(remoteStatisticsService.getSupplierInformation( schoolId, SecurityConstants.FROM_IN ));
  }
  //人员信息
  @GetMapping("/getPersonnelInformation")
  public R getPersonnelInformation(@RequestParam(value="schoolId")String schoolId){
    return new R<>(remoteStatisticsService.getPersonnelInformation( schoolId, SecurityConstants.FROM_IN ));
  }

  //学校设备信息
  @GetMapping("/getDeviceInformation")
  public R getDeviceInformation(@RequestParam(value="schoolId")String schoolId){
    return new R<>(remoteStatisticsService.getDeviceInformation( schoolId, SecurityConstants.FROM_IN ));
  }

  //食材信息
  @GetMapping("/getIngredientsInformation")
  public R getIngredientsInformation(@RequestParam(value="schoolId")String schoolId,
                                     @RequestParam(value="startingTime",required = false)String startingTime,
                                     @RequestParam(value="endTime",required = false)String endTime){
    if(startingTime==null){
      startingTime = "1900-01-01";
    }
    if(endTime==null){
      endTime = "3333-01-01";
    }
    return new R<>(remoteStatisticsService.getIngredientsInformation( schoolId, startingTime,endTime,SecurityConstants.FROM_IN ));
  }

  //台账信息
  @GetMapping("/getAccount")
  public R getAccount(@RequestParam(value="schoolId")String schoolId){
    return new R<>(remoteStatisticsService.getAccount( schoolId, SecurityConstants.FROM_IN ));
  }
  //历史报警
  @GetMapping("/getHistoricalAlarm")
  public R getHistoricalAlarm(@RequestParam(value="year")String year,@RequestParam(value="schoolId")String schoolId){
    return new R<>(remoteStatisticsService.getHistoricalAlarm( schoolId, year,SecurityConstants.FROM_IN ));
  }
}
