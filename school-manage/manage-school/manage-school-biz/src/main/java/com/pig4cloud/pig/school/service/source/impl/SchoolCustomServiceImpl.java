package com.pig4cloud.pig.school.service.source.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.source.SchoolCustom;
import com.pig4cloud.pig.school.mapper.SchoolCustomMapper;

import com.pig4cloud.pig.school.service.source.SchoolCustomService;
import org.springframework.stereotype.Service;

/**
 * 学校自定义食材信息
 *
 * @author xiesongzhe
 * @date 2019-09-05 20:59:49
 */
@Service("schoolCustomService")
public class SchoolCustomServiceImpl extends ServiceImpl<SchoolCustomMapper, SchoolCustom> implements SchoolCustomService {

  /**
   * 学校自定义食材信息简单分页查询
   * @param schoolCustom 学校自定义食材信息
   * @return
   */
  @Override
  public IPage<SchoolCustom> getSchoolCustomPage(Page<SchoolCustom> page, SchoolCustom schoolCustom){
      return baseMapper.getSchoolCustomPage(page,schoolCustom);
  }

}
