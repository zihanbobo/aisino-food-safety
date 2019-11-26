package com.pig4cloud.pig.school.service.statistics.impl;

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
  public List<Map> getWarnings(Map map) {
    List<Map> warnings = baseMapper.getWarnings( map );
    for(int i=0;i<warnings.size();i++){
      Map map1 = warnings.get( i );
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
      warnings.set( i,map1 );
    }
    return warnings;
  }
  //没接收预警
  @Override
  public List getNotReceived(Map map) {
    return baseMapper.getNotReceived( map );
  }
  //已接收预警
  @Override
  public List getReceivedWarnings(Map map) {
    return baseMapper.getReceivedWarnings( map );
  }
}
