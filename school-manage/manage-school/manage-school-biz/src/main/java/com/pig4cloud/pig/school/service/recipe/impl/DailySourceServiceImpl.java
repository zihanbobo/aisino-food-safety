package com.pig4cloud.pig.school.service.recipe.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.recipe.DailySource;
import com.pig4cloud.pig.school.mapper.DailySourceMapper;

import com.pig4cloud.pig.school.service.recipe.DailySourceService;
import org.springframework.stereotype.Service;

/**
 * 每日食谱资源关联信息
 *
 * @author xie
 * @date 2019-09-17 20:23:24
 */
@Service("dailySourceService")
public class DailySourceServiceImpl extends ServiceImpl<DailySourceMapper, DailySource> implements DailySourceService {

  /**
   * 每日食谱资源关联信息简单分页查询
   * @param dailySource 每日食谱资源关联信息
   * @return
   */
  @Override
  public IPage<DailySource> getDailySourcePage(Page<DailySource> page, DailySource dailySource){
      return baseMapper.getDailySourcePage(page,dailySource);
  }

}
