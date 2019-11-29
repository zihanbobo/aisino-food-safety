package com.pig4cloud.pig.school.service.statistics.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import com.pig4cloud.pig.school.mapper.SchoolDetailsMapper;
import com.pig4cloud.pig.school.service.statistics.SchoolDetailsService;
import org.springframework.stereotype.Service;

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
  public IPage<List<Map>> getIngredientsInformation(Page page, Map map) {
    return baseMapper.getIngredientsInformation( page,map );
  }

  //台账信息
  @Override
  public Map getAccount(Map map) {
    return baseMapper.getAccount( map );
  }

  //历史报警
  @Override
  public IPage<List<Map>> getHistoricalAlarm(Page page,Map map) {
    //报警信息
    IPage<List<Map>> historicalAlarm =  baseMapper.getHistoricalAlarm(page, map );
    List<List<Map>> records = historicalAlarm.getRecords();
    for(int i=0;i<records.size();i++) {
      Map maps = (Map) records.get( i );
      Object id = maps.get( "alarm" );
      Map b = new HashMap();
      b.put( "supId", id );
      Map schoolName = baseMapper.getSchoolName( b );
      maps.put( "school", schoolName );
    }
    return historicalAlarm;
  }

  //历史报警(供应商)详情
  @Override
  public Map getHistoryDetails(Map map) {
    Integer iswarning = (Integer) map.get( "iswarning" );

    Map a = new HashMap();
    if (iswarning== 0){
      //是预警

      Map basicInformationW = baseMapper.getBasicInformationW( map );
      Object early_warning = basicInformationW.get( "early_warning" );
      map.put( "early_warning", early_warning);
      Integer sameWarning = baseMapper.getSameWarning( map );
      a.put( "sameWarning",sameWarning );
      a.put( "basicInformationW" ,basicInformationW);
      Object sup_id = basicInformationW.get( "sup_id" );
      Map map1 = new HashMap<>();
      map1.put( "supId",sup_id );
      Map schoolName = baseMapper.getSchoolName( map1 );
      a.put( "schoolName",schoolName );
    }
    else {
      //是报警
      Map basicInformationA = baseMapper.getBasicInformationA( map );
      Object alarm = basicInformationA.get( "alarm" );
      map.put( "alarm", alarm);
      Integer sameAlarm = baseMapper.getSameAlarm( map );
      a.put( "sameWarning",sameAlarm );
      a.put( "basicInformationA" ,basicInformationA);
      Object sup_id = basicInformationA.get( "sup_id" );
      Map map1 = new HashMap<>();
      map1.put( "supId",sup_id );
      Map schoolName = baseMapper.getSchoolName( map1 );
      a.put( "schoolName",schoolName );
    }
    List historyAlarm = baseMapper.getHistoryDetailsA( map );
    List historyWarning = baseMapper.getHistoryDetailsW( map );
    a.put( "historyAlarm", historyAlarm);
    a.put( "historyWarning",historyWarning );
    return a;
  }
  //历史报警(食材)详情
  @Override
  public Map getHistoryFood(Map map) {
    Integer iswarning = (Integer) map.get( "iswarning" );
    if (iswarning== 0){
      //是预警
      Map foodInformationW = baseMapper.getFoodInformationW( map );
      Object early_warning = foodInformationW.get( "early_warning" );
      Object foodId = foodInformationW.get( "foodId" );
      map.put( "early_warning", early_warning);
      map.put( "foodId", foodId);
      List<Map> schNameFood = baseMapper.getSchNameFood( map );
      Integer sameWarning = baseMapper.getSameWarning( map );
      foodInformationW.put( "schNameFood",schNameFood );
      foodInformationW.put( "sameWarning",sameWarning );
      return foodInformationW;

    }
    else {
      //是报警
      Map foodInformationA = baseMapper.getFoodInformationA( map );
      Object alarm = foodInformationA.get( "alarm" );
      Object foodId = foodInformationA.get( "foodId" );
      map.put( "foodId", foodId);
      map.put( "alarm", alarm);
      List<Map> schNameFood = baseMapper.getSchNameFood( map );
      Integer sameAlarm = baseMapper.getSameAlarm( map );
      foodInformationA.put( "sameWarning",sameAlarm );
      foodInformationA.put( "schNameFood",schNameFood );
      return foodInformationA;
    }
  }
  //历史报警(人员)详情
  @Override
  public Map getHistoryMan(Map map) {
    Integer iswarning = (Integer) map.get( "iswarning" );
    if (iswarning== 0){
      //是预警
      Map manInformationW = baseMapper.getManInformationW( map );
      Object early_warning = manInformationW.get( "early_warning" );
      map.put( "early_warning", early_warning);
      Integer sameWarning = baseMapper.getSameWarning( map );
      manInformationW.put( "sameWarning",sameWarning );
      return manInformationW;

    }
    else {
      //是报警
      Map manInformationA = baseMapper.getManInformationA( map );
      Object alarm = manInformationA.get( "alarm" );
      map.put( "alarm", alarm);
      Integer sameAlarm = baseMapper.getSameAlarm( map );
      manInformationA.put( "sameWarning",sameAlarm );
      return manInformationA;
    }
  }
}
