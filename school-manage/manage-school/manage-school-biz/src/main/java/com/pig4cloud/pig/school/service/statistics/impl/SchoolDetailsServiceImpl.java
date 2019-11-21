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


  //学校资质信息
  @Override
  public Map getSchoolQualification(Map map) {
    return baseMapper.getSchoolQualification( map );
  }

  //根据学校id获得供应商信息
  @Override
  public List<Map> getSupplierInformation(Map map) {
    return baseMapper.getSupplierInformation( map );
  }

  //根据学校id获得供应商数量
  @Override
  public List<Map> getSuppliersum(Map map) {
    List<Map> a =new ArrayList<>(  );
    Date date = new Date();
    map.put( "date",date );
    Map getxuke = baseMapper.getxuke( map );
    Map getyingye = baseMapper.getyingye( map );
    Map getzong = baseMapper.getzong( map );
    a.add( getzong );
    a.add( getyingye );
    a.add( getxuke );
    return a;
  }
}
