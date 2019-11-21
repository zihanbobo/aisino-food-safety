package com.pig4cloud.pig.school.service.welcome.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.check.Welocomedomo;
import com.pig4cloud.pig.school.mapper.WelcomeMapper;
import com.pig4cloud.pig.school.service.welcome.WelcomeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("seffService")
public class WelcomeServiceImpl extends ServiceImpl<WelcomeMapper, Welocomedomo> implements WelcomeService {

  /**
   * 员工信息
   * @param schuName
   * @return
   */
  @Override
  public  Welocomedomo findbyid(String schuName) {
    return baseMapper.getStaff( schuName );
  }

  /**
   * 校园端-欢迎页-台账统计
   * @param map
   * @return
   */
  @Override
  public Map getAccount(Map map) {
    return baseMapper.getAccount(map);
  }

  //获得学校id
  @Override
  public String getSchoolid(String schuName) {
    return baseMapper.getSchoolid( schuName );
  }
//设备信息前三条
  @Override
  public List<Map> getdeviceInformation(Integer schoolId) {
    return baseMapper.getdeviceInformation( schoolId );
  }
//设备信息
  @Override
  public List<Map> getdeviceInforsum(Integer schoolId) {
    return baseMapper.getdeviceInforsum( schoolId );
  }

  //第三行-校园端-欢迎页-当日采购信息
  @Override
  public List<Map> getDayPurchase(Map map) {
    return baseMapper.getDayPurchase(map);
  }

  //第三行-校园端-欢迎页-校长-当日采购票证上传率与高风险
  @Override
  public Map getHighRisk(Map map) {
    return baseMapper.getHighRisk(map);
  }
}
