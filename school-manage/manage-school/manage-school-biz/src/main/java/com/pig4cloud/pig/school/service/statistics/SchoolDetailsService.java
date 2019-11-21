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

  //学校资质信息
  Map getSchoolQualification(@Param("map") Map map);
  //根据学校id获得供应商信息
  List<Map> getSupplierInformation(@Param("map") Map map);

  //学校资质信息
  List<Map> getSuppliersum(@Param("map") Map map);
}
