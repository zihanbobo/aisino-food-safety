package com.pig4cloud.pig.school.service.statistics.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import com.pig4cloud.pig.school.mapper.SchoolDetailsMapper;
import com.pig4cloud.pig.school.mapper.TrendAnalysisMapper;
import com.pig4cloud.pig.school.service.statistics.SchoolDetailsService;
import com.pig4cloud.pig.school.service.statistics.TrendAnalysisService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
@Service("trendAnalysisService")
public class TrendAnalysisServiceImpl extends ServiceImpl<TrendAnalysisMapper, EarlyAlarm> implements TrendAnalysisService {


  //监管端-趋势分析-学校预警总数Top5-List<Map>
  @Override
  public List<Map> getEarlyWarningRank(Map map) {
    return baseMapper.getEarlyWarningRank(map);
  }

  //监管端-趋势分析-学校报警总数Top5-List<Map>
  @Override
  public List<Map> getEarlyAlarmRank(Map map) {
    return baseMapper.getEarlyAlarmRank(map);
  }

  //监管端-预警报警分析-预警环节数量对比(根据月查询)
  @Override
  public List<Map> getEarlyTotalContrast(Map map) {
    return baseMapper.getEarlyTotalContrast(map);
  }

  //监管端-预警报警分析-报警环节数量对比(根据月查询)
  @Override
  public List<Map> getAlarmTotalContrast(Map map){
    return baseMapper.getAlarmTotalContrast(map);
  }

  //监管端-预警报警分析-预警数量统计&报警数量统计(根据月查询)
  @Override
  public Map getWarnAlarmTotalContrast(Map map) {
    return baseMapper.getWarnAlarmTotalContrast(map);
  }


  //报警数量
  @Override
  public List<Map> getyuandbaosum(Map map) {
    List<Map> list = new ArrayList<>(  );
    Map getsum = baseMapper.getsum( map );
    Map getsumb = baseMapper.getsumb( map );
    list.add(0, getsum );
    list.add( 1,getsumb );
    return list;
  }
  //食材供应商
  @Override
  public Map getFoodSupplier(Map map) {
    return baseMapper.getFoodSupplier( map );
  }
  //学校统计
  @Override
  public List<Map> getSchoolStatistics(Map map) {
    //查询所有学校数量
    Map schoolsum = baseMapper.getAlarmsArea1( map );
    //学校分类的数量
    Map schoolClassification = baseMapper.getSchoolClassification( map );
    List <Map> schoollist = new ArrayList<>(  );
    schoollist.add(0, schoolsum );
    schoollist.add( 1,schoolClassification );

    return schoollist;
  }

}
