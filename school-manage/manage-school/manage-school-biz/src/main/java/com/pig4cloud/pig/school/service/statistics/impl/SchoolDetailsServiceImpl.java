package com.pig4cloud.pig.school.service.statistics.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import com.pig4cloud.pig.school.mapper.AnalysisDataMapper;
import com.pig4cloud.pig.school.mapper.SchoolDetailsMapper;
import com.pig4cloud.pig.school.service.statistics.AnalysisDataService;
import com.pig4cloud.pig.school.service.statistics.SchoolDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * 学校信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
@Service("schoolDetailsService")
public class SchoolDetailsServiceImpl extends ServiceImpl<SchoolDetailsMapper, EarlyAlarm> implements SchoolDetailsService {


  //学校信息上
  @Override
  public Map getSchoolInformation(Map map) {
    Map a = new HashMap();
    Map schoolInformation = baseMapper.getSchoolInformation( map );
    Map alarmNumber = baseMapper.getAlarmNumber( map );
    Map warningNumber = baseMapper.getWarningNumber( map );
    a.put( "schoolInformation",schoolInformation );
    a.put( "alarmNumber",alarmNumber );
    a.put( "warningNumber" ,warningNumber);
    return a;
  }

  //学校资质信息
  @Override
  public Map getSchoolQualification(Map map) {
    return baseMapper.getSchoolQualification( map );
  }

  //供应商信息
  @Override
  public Map getSupplierInformation(Map map) {
    Map a = new HashMap();
    List<Map<String,Object>> supplierInformation = baseMapper.getSupplierInformation( map );
    for(int i=0;i<supplierInformation.size();i++){
      Map map1 = supplierInformation.get( i );
      Object id = map1.get( "id" );
      Map b = new HashMap();
      b.put( "supId" ,id);
      Map schoolName = baseMapper.getSchoolName( b );
      map1.put( "school",schoolName );
    }

    Map total = baseMapper.getTotal( map );//供应商总数
    Map open = baseMapper.getOpen( map ); //营业数
    Map license = baseMapper.getLicense( map );//许可数
    a.put( "supplierInformation", supplierInformation );
    a.put( "total", total );
    a.put( "open", open );
    a.put( "license", license );
    return a;
  }

  //人员信息
  @Override
  public Map getPersonnelInformation(Map map) {
    Map a = new HashMap();
    List chefInformation = baseMapper.getChefInformation( map );//大厨信息
    List foodSafetyInformation = baseMapper.getFoodSafetyInformation( map );//食品安全人员信息
    List foodEscortInformation = baseMapper.getFoodEscortInformation( map );//陪餐人员信息
    List manNumber = baseMapper.getManNumber( map );//各种类人员数量
    Map morningCheck = baseMapper.getMorningCheck( map );//人员总数和晨检合格率
    Map healthCheck = baseMapper.getHealthCheck( map );//健康证数量
    a.put( "chefInformation", chefInformation );
    a.put( "foodSafetyInformation", foodSafetyInformation );
    a.put( "foodEscortInformation", foodEscortInformation );
    a.put( "manNumber", manNumber );
    a.put( "morningCheck", morningCheck );
    a.put( "healthCheck", healthCheck );
    return a;
  }

  //设备信息
  @Override
  public Map getDeviceInformation(Map map) {
    Map a = new HashMap();
    List deviceInformation = baseMapper.getDeviceInformation( map );
    Map eqOperationRatio = baseMapper.getEqOperationRatio( map );//设备数量和合格率
    a.put( "deviceInformation", deviceInformation );
    a.put( "eqOperationRatio", eqOperationRatio );
    return a;
  }

  //食材信息
  @Override
  public List getIngredientsInformation(Map map) {

    return baseMapper.getIngredientsInformation( map );
  }

  //台账信息
  @Override
  public Map getAccount(Map map) {
    return baseMapper.getAccount( map );
  }

  //历史报警
  @Override
  public Map getHistoricalAlarm(Map map) {
    Map a = new HashMap();
    List historicalAlarm = baseMapper.getHistoricalAlarm( map );//报警信息
    List getHistoricalWarning = baseMapper.getHistoricalWarning( map );//预警信息
    a.put( "historicalAlarm", historicalAlarm);
    a.put( "getHistoricalWarning", getHistoricalWarning);
    return a;
  }

}
