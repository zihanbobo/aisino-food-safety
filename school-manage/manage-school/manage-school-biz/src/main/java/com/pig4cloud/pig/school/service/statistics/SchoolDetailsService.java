package com.pig4cloud.pig.school.service.statistics;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 学校信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
public interface SchoolDetailsService extends IService<EarlyAlarm> {

  //学校信息上
  Map getSchoolInformation(@Param("map") Map map);
  //学校资质信息
  Map getSchoolQualification(@Param("map") Map map);
  //供应商信息
  Map getSupplierInformation(@Param("map") Map map);
  //人员信息
  Map getPersonnelInformation(@Param("map") Map map);
  //设备信息
  Map getDeviceInformation(@Param("map") Map map);
  //食材信息
  List getIngredientsInformation(@Param("map") Map map);
  //台账信息
  Map getAccount(@Param("map") Map map);
  //历史报警
  Map getHistoricalAlarm(@Param("map") Map map);

}
