package com.pig4cloud.pig.school.service.statistics.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import com.pig4cloud.pig.school.mapper.AnalysisDataMapper;
import com.pig4cloud.pig.school.mapper.WelHeadmasterMapper;
import com.pig4cloud.pig.school.service.statistics.AnalysisDataService;
import com.pig4cloud.pig.school.service.statistics.WelHeadmasterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报警信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
@Service("analysisDataService")
public class AnalysisDataServiceImpl extends ServiceImpl<AnalysisDataMapper, EarlyAlarm> implements AnalysisDataService {


  public List<Map> getEarlywarningListYear(Map map) {
    return baseMapper.getEarlywarningListYear(map);
  }
  @Override
  public List<Map> getAlarmListYear(Map map) {
    return baseMapper.getAlarmListYear(map);
  }

  public Map getAlarmTotalByYear(Map map) {
    return baseMapper.getAlarmTotalByYear(map);
  }

  public List getAlarmsNumberBymonth(Map map){
    List list=new ArrayList(12);
    for(int i = 1;i<13;i++){
      map.put("mouth",i);
      Integer total = baseMapper.getAlarmsNumberBymonth(map);
      list.add(i-1,total);
    }
    return list;
  }

  @Override
  public List getAlarmsArea(Map map) {
    return baseMapper.getAlarmsArea(map);
  }

  @Override
  public List<Map> getAlarmsAreaByRegion(Map map) {
    return baseMapper.getAlarmsAreaByRegion(map);
  }

  @Override
  public Integer getEarlyTotal(Map map) {
    return baseMapper.getEarlyTotal(map);
  }

  @Override
  public Integer getAlarmTotal(Map map) {
    return baseMapper.getAlarmTotal(map);
  }



}
