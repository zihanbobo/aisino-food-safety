package com.pig4cloud.pig.school.service.statistics;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 报警信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
public interface WelHeadmasterService extends IService<EarlyAlarm> {

  //第一行-校园端-欢迎页-校长-预警统计
  Map getEarlyWarnTotal(@Param("map") Map map);

  //第一行-校园端-欢迎页-校长-报警统计
  List<Map> getAlarmTotal(@Param("map") Map map);

  //第一行-校园端-欢迎页-校长-本周报警统计按类别
  List<Map> getAlarmWeekTotal(@Param("map") Map map);



  //第2行-校园端-欢迎页-校长-监管及通知公告统计
  Map getPurchaseWarn(@Param("map") Map map);

  //第2行-校园端-欢迎页-校长-台账统计
  Map getAccount(@Param("map") Map map);



  //第三行-校园端-欢迎页-校长-人员信息
  Map getSchoolUserInfo(@Param("map") Map map);

  //第三行-校园端-欢迎页-校长-当日人员晨检合格率
  Map getDayUserQualified(@Param("map") Map map);

  //第三行-校园端-欢迎页-校长-食材信息
  Map getFoodInfo(@Param("map") Map map);

  //第三行-校园端-欢迎页-校长-当日采购信息
  List<Map> getDayPurchase(@Param("map") Map map);

  //第三行-校园端-欢迎页-校长-当日采购票证上传率与高风险
  Map getHighRisk(@Param("map") Map map);



  // 第四行-校园端-欢迎页-校长-供应商信息
  Map getMainFilingInfo(@Param("map") Map map);

  // 第四行-校园端-欢迎页-校长-厨余设备
  Map getVdDevice(@Param("map") Map map);

  // 第一行-校园端-欢迎页-员工信息
  Map getUserInfo(@Param("map") Map map);
  // 第四行-校园端-欢迎页-设备信息
  List<Map> getSchoolVdDevice(@Param("map") Map map);
  // 第四行-校园端-欢迎页-设备比率
  Map getEqOperationRatio(@Param("map") Map map);


}
