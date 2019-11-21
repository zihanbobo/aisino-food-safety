package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.entity.recipe.DailySource;
import org.apache.ibatis.annotations.Param;

/**
 * 每日食谱资源关联信息
 *
 * @author xie
 * @date 2019-09-17 20:23:24
 */
public interface DailySourceMapper extends BaseMapper<DailySource> {
  /**
    * 每日食谱资源关联信息简单分页查询
    * @param dailySource 每日食谱资源关联信息
    * @return
    */
  IPage<DailySource> getDailySourcePage(Page page, @Param("dailySource") DailySource dailySource);


}
