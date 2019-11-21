package com.pig4cloud.pig.portal.controller.news;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.portal.api.entity.news.News;
import com.pig4cloud.pig.portal.service.news.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 食安新闻
 *
 * @author xiesongzhe
 * @date 2019-09-06 17:15:14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/news")
public class NewsController {

  private final NewsService newsService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param news 食安新闻
   * @return
   */
  @GetMapping("/page")
  public R<IPage<News>> getNewsPage(Page<News> page, News news) {
    return  new R<>(newsService.getNewsPage(page,news));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<News> getById(@PathVariable("id") Integer id){
    return new R<>(newsService.getById(id));
  }

  /**
   * 新增记录
   * @param news
   * @return R
   */
  @SysLog("新增食安新闻")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('portalMan_news_add')")
  public R save(@RequestBody News news){
    return new R<>(newsService.save(news));
  }

  /**
   * 修改记录
   * @param news
   * @return R
   */
  @SysLog("修改食安新闻")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('portalMan_news_edit')")
  public R update(@RequestBody News news){
    return new R<>(newsService.updateById(news));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除食安新闻")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('portalMan_news_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(newsService.removeById(id));
  }

}
