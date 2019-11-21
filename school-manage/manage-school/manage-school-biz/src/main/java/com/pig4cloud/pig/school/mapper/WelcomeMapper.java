package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.pig4cloud.pig.school.api.entity.check.Welocomedomo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface WelcomeMapper extends BaseMapper<Welocomedomo> {
  /**
   *员工信息和晨检合格率
   * @param schuName
   * @return
  */
  Welocomedomo getStaff(@Param("schuName") String schuName);

  //第2行-校园端-欢迎页-校长-台账统计
  Map getAccount(@Param("map") Map map);

  //第三行-校园端-欢迎页-校长-当日采购信息
  List<Map> getDayPurchase(@Param("map") Map map);

  //第三行-校园端-欢迎页-校长-当日采购票证上传率与高风险
  Map getHighRisk(@Param("map") Map map);

  //获得学校id
  String getSchoolid(@Param("schuName")String schuName);

  //获得设备信息前三条
  List<Map> getdeviceInformation(@Param("schoolId")Integer schoolId);

  //获得设备信息
  List<Map> getdeviceInforsum(@Param("schoolId")Integer schoolId);
}

