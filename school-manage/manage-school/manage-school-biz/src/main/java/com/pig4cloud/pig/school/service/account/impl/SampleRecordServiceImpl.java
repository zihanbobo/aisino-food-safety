package com.pig4cloud.pig.school.service.account.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.account.SampleRecord;
import com.pig4cloud.pig.school.mapper.SampleRecordMapper;
import com.pig4cloud.pig.school.service.account.SampleRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 留样记录
 *
 * @author 沈凝
 * @date 2019-07-08 01:48:09
 */
@Service("sampleRecordService")
public class SampleRecordServiceImpl extends ServiceImpl<SampleRecordMapper, SampleRecord> implements SampleRecordService {

  /**
   * 留样记录简单分页查询
   * @param sampleRecord 留样记录
   * @return
   */
  @Override
  public IPage<SampleRecord> getSampleRecordPage(Page<SampleRecord> page, SampleRecord sampleRecord){
      return baseMapper.getSampleRecordPage(page,sampleRecord);
  }
  /**
   * 留样记录简单分页查询
   * @param map 留样记录
   * @return
   */
  @Override
  public IPage<List<Map>> getSampleSchoolPage(Page page, Map map){
    return baseMapper.getSampleSchoolPage(page,map);
  }
  /**
   * 查询留样详情
   * @param map 查询参数(schoolId,rdDate)
   * @return
   */
  @Override
  public List<Map> getRecipeSampleDay(Map map){
    return baseMapper.getRecipeSampleDay(map);
  }
}
