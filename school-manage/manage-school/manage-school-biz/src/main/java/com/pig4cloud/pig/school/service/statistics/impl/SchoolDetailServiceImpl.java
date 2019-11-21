package com.pig4cloud.pig.school.service.statistics.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import com.pig4cloud.pig.school.mapper.SchoolDetailMapper;

import com.pig4cloud.pig.school.service.statistics.SchoolDetailService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 学校信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
@Service("schoolDetailService")
public class SchoolDetailServiceImpl extends ServiceImpl<SchoolDetailMapper, EarlyAlarm> implements SchoolDetailService {


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
  //台账信息
  @Override
  public List<Map> getAccount(Map map) {
    return baseMapper.getAccount( map );

  }
  //设备信息
  @Override
  public List<Map> getSchoolVdDevice(Map map) {
    return baseMapper.getSchoolVdDevice( map );
  }
  //设备检查合格比率
  @Override
  public Map getEqOperationRatio(Map map) {
    return baseMapper.getEqOperationRatio(map);
  }

}
