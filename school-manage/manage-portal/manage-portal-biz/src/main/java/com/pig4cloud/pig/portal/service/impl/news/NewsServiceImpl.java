package com.pig4cloud.pig.portal.service.impl.news;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.portal.api.entity.news.News;
import com.pig4cloud.pig.portal.mapper.NewsMapper;
import com.pig4cloud.pig.portal.service.news.NewsService;
import org.springframework.stereotype.Service;

/**
 * 食安新闻
 *
 * @author xiesongzhe
 * @date 2019-09-06 17:15:14
 */
@Service("newsService")
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

  /**
   * 食安新闻简单分页查询
   * @param news 食安新闻
   * @return
   */
  @Override
  public IPage<News> getNewsPage(Page<News> page, News news){
      return baseMapper.getNewsPage(page,news);
  }

}
