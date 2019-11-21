package com.pig4cloud.pig.school.service.statistics.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import com.pig4cloud.pig.school.mapper.EarlyAlarmMapper;
import com.pig4cloud.pig.school.mapper.WelHeadmasterMapper;
import com.pig4cloud.pig.school.service.check.EarlyAlarmService;
import com.pig4cloud.pig.school.service.statistics.WelHeadmasterService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 报警信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
@Service("headmasterService")
public class WelHeadmasterServiceImpl extends ServiceImpl<WelHeadmasterMapper, EarlyAlarm> implements WelHeadmasterService {


  //第一行-校园端-欢迎页-校长-预警统计
  public Map getEarlyWarnTotal(Map map) {
    return baseMapper.getEarlyWarnTotal(map);
  }

  public List<Map> getAlarmTotal(Map map) {
    return baseMapper.getAlarmTotal(map);
  }

  @Override
  public List<Map> getAlarmWeekTotal(Map map) {
    return baseMapper.getAlarmWeekTotal(map);
  }

  @Override
  public Map getPurchaseWarn(Map map) {
    return baseMapper.getPurchaseWarn(map);
  }

  @Override
  public Map getAccount(Map map) {
    return baseMapper.getAccount(map);
  }

  @Override
  public Map getSchoolUserInfo(Map map) {
    return baseMapper.getSchoolUserInfo(map);
  }

  @Override
  public Map getDayUserQualified(Map map) {
    return baseMapper.getDayUserQualified(map);
  }

  @Override
  public Map getFoodInfo(Map map) {
    return baseMapper.getFoodInfo(map);
  }

  @Override
  public List<Map> getDayPurchase(Map map) {
    return baseMapper.getDayPurchase(map);
  }

  @Override
  public Map getHighRisk(Map map) {
    return baseMapper.getHighRisk(map);
  }

  @Override
  public Map getMainFilingInfo(Map map) {
    return baseMapper.getMainFilingInfo(map);
  }

  @Override
  public Map getVdDevice(Map map) {
    return baseMapper.getVdDevice(map);
  }
  @Override
  public Map getUserInfo(Map map) { return baseMapper.getUserInfo(map); }


  @Override
  public List<Map> getSchoolVdDevice(Map map) { return baseMapper.getSchoolVdDevice(map); }
  @Override
  public Map getEqOperationRatio(Map map) { return baseMapper.getEqOperationRatio(map); }




}
