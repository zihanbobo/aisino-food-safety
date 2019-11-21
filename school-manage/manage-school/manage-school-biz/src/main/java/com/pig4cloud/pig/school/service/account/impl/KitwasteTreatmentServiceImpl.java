package com.pig4cloud.pig.school.service.account.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.account.KitwasteTreatment;
import com.pig4cloud.pig.school.mapper.KitwasteTreatmentMapper;
import com.pig4cloud.pig.school.service.account.KitwasteTreatmentService;
import org.springframework.stereotype.Service;

/**
 * 厨余处理记录
 *
 * @author 沈凝
 * @date 2019-07-06 16:54:52
 */
@Service("kitwasteTreatmentService")
public class KitwasteTreatmentServiceImpl extends ServiceImpl<KitwasteTreatmentMapper, KitwasteTreatment> implements KitwasteTreatmentService {

  /**
   * 厨余处理记录简单分页查询
   * @param kitwasteTreatment 厨余处理记录
   * @return
   */
  @Override
  public IPage<KitwasteTreatment> getKitwasteTreatmentPage(Page<KitwasteTreatment> page, KitwasteTreatment kitwasteTreatment){
      return baseMapper.getKitwasteTreatmentPage(page,kitwasteTreatment);
  }

}
