package com.pig4cloud.pig.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.portal.api.entity.news.News;
import org.apache.ibatis.annotations.Param;

/**
 * 食安新闻
 *
 * @author xiesongzhe
 * @date 2019-09-06 17:15:14
 */
public interface NewsMapper extends BaseMapper<News> {
  /**
    * 食安新闻简单分页查询
    * @param news 食安新闻
    * @return
    */
  IPage<News> getNewsPage(Page page, @Param("news") News news);


}
