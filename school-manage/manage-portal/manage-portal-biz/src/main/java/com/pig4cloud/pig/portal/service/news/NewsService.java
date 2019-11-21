package com.pig4cloud.pig.portal.service.news;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.portal.api.entity.news.News;

/**
 * 食安新闻
 *
 * @author xiesongzhe
 * @date 2019-09-06 17:15:14
 */
public interface NewsService extends IService<News> {

  /**
   * 食安新闻简单分页查询
   * @param news 食安新闻
   * @return
   */
  IPage<News> getNewsPage(Page<News> page, News news);


}
