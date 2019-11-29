package com.pig4cloud.pig.school.service.statistics.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import com.pig4cloud.pig.school.mapper.WarningsMapper;
import com.pig4cloud.pig.school.mapper.WelHeadmasterMapper;
import com.pig4cloud.pig.school.service.statistics.WarningsService;
import com.pig4cloud.pig.school.service.statistics.WelHeadmasterService;
import org.bouncycastle.crypto.tls.AlertLevel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.bouncycastle.crypto.tls.AlertLevel.warning;

/**
 * 报警信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
@Service("warningsService")
public class WarningsServiceImpl extends ServiceImpl<WarningsMapper, EarlyAlarm> implements WarningsService {

  //预警类别
  @Override
  public Map getWarningType(Map map) {
    Map<String,Object> a = new HashMap<>();
    List warningType = baseMapper.getWarningType();
    Map warningNumber = baseMapper.getWarningTotalByYear( map );
    a.put("warningType",warningType  );
    a.put( "warningNumber",warningNumber );
    return a;
  }
  //所有预警
  @Override
  public IPage<List<Map>> getWarnings(Page page, Map map) {
    IPage<List<Map>> warnings = baseMapper.getWarnings( page, map );
    List<List<Map>> records = warnings.getRecords();
    for(int i=0;i<records.size();i++){
      Map map1 = (Map)records.get( i );
      Object value = map1.get( "VALUE" );
      Object schoolId = map1.get( "SchoolId" );
      map.put( "schoolId", schoolId);
      map.put( "WarningInfor", value);
      Integer sameWarning = baseMapper.getSameWarning( map );
      Map associationWarning = baseMapper.getAssociationWarning( map );
      Object school = associationWarning.get( "school" );
      Object warning = associationWarning.get( "warning" );
      map1.put( "sameWarning",sameWarning );
      map1.put( "school",school );
      map1.put( "warning", warning);
      /*List a=new ArrayList(  );
      a.add(map1);
      records.set( i,a );*/
    }
    return warnings;
  }


  @Override
  public Map getAlarmType(Map map) {
    return null;
  }
//全部报警
  @Override
  public IPage<List<Map>> getAlarms(Page page,Map map) {
    IPage<List<Map>> warnings = baseMapper.getAlarms( page,map );
    List<List<Map>> records = warnings.getRecords();
    for(int i=0;i<records.size();i++){
      Map map1 = (Map)records.get( i );
      Object value = map1.get( "VALUE" );
      Object schoolId = map1.get( "SchoolId" );
      map.put( "schoolId", schoolId);
      map.put( "WarningInfor", value);
      Integer sameWarning = baseMapper.getSameAlarm( map );
      Map associationWarning = baseMapper.getAssociationAlarm( map );
      Object school = associationWarning.get( "school" );
      Object warning = associationWarning.get( "warning" );
      map1.put( "sameWarning",sameWarning );
      map1.put( "school",school );
      map1.put( "warning", warning);
     /* List a=new ArrayList(  );
      a.add(map1);
      records.set( i,a );*/
    }
    return warnings;
  }

}
