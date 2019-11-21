package com.pig4cloud.pig.school.service.recipe;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.recipe.DailySource;

/**
 * 每日食谱资源关联信息
 *
 * @author xie
 * @date 2019-09-17 20:23:24
 */
public interface DailySourceService extends IService<DailySource> {

  /**
   * 每日食谱资源关联信息简单分页查询
   * @param dailySource 每日食谱资源关联信息
   * @return
   */
  IPage<DailySource> getDailySourcePage(Page<DailySource> page, DailySource dailySource);



}
