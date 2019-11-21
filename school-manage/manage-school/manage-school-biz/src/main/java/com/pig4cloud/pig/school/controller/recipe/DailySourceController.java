package com.pig4cloud.pig.school.controller.recipe;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import com.pig4cloud.pig.school.api.entity.recipe.DailySource;
import com.pig4cloud.pig.school.service.recipe.DailySourceService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 每日食谱资源关联信息
 *
 * @author xie
 * @date 2019-09-17 20:23:24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dailysource")
public class DailySourceController {

  private final DailySourceService dailySourceService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param dailySource 每日食谱资源关联信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<DailySource>> getDailySourcePage(Page<DailySource> page, DailySource dailySource) {
    return  new R<>(dailySourceService.getDailySourcePage(page,dailySource));
  }


  /**
   * 通过id查询单条记录
   * @param dailyId
   * @return R
   */
  @GetMapping("/{dailyId}")
  public R<DailySource> getById(@PathVariable("dailyId") Integer dailyId){
    return new R<>(dailySourceService.getById(dailyId));
  }

  /**
   * 新增记录
   * @param dailySource
   * @return R
   */
  @SysLog("新增每日食谱资源关联信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('school_dailysource_add')")
  public R save(@RequestBody DailySource dailySource){
    return new R<>(dailySourceService.save(dailySource));
  }

  /**
   * 修改记录
   * @param dailySource
   * @return R
   */
  @SysLog("修改每日食谱资源关联信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('school_dailysource_edit')")
  public R update(@RequestBody DailySource dailySource){
    return new R<>(dailySourceService.updateById(dailySource));
  }

  /**
   * 通过id删除一条记录
   * @param dailyId
   * @return R
   */
  @SysLog("删除每日食谱资源关联信息")
  @DeleteMapping("/{dailyId}")
  @PreAuthorize("@pms.hasPermission('school_dailysource_del')")
  public R removeById(@PathVariable Integer dailyId){
    return new R<>(dailySourceService.removeById(dailyId));
  }


}
