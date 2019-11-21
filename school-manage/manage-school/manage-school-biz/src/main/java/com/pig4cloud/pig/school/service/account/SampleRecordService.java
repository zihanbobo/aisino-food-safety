package com.pig4cloud.pig.school.service.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.account.SampleRecord;

import java.util.List;
import java.util.Map;

/**
 * 留样记录
 *
 * @author 沈凝
 * @date 2019-07-08 01:48:09
 */
public interface SampleRecordService extends IService<SampleRecord> {

  /**
   * 留样记录简单分页查询
   * @param sampleRecord 留样记录
   * @return
   */
  IPage<SampleRecord> getSampleRecordPage(Page<SampleRecord> page, SampleRecord sampleRecord);
  /**
   * 留样记录简单分页查询
   * @param map 留样记录
   * @return
   */
  IPage<List<Map>> getSampleSchoolPage(Page page, Map map);
  /**
   * 留样记录简单分页查询
   * @param map 留样记录
   * @return
   */
  List<Map> getRecipeSampleDay(Map map);


}
