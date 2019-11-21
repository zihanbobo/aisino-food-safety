/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.pig4cloud.pig.school.controller.selseal;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.school.api.entity.selseal.ThridpartyManage;
import com.pig4cloud.pig.school.service.selseal.ThridpartyManageService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 第三方服务商管理
 *
 * @author -
 * @date 2019-08-07 16:00:23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/thridpartymanage")
public class ThridpartyManageController {

  private final ThridpartyManageService thridpartyManageService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param thridpartyManage 第三方服务商管理
   * @return
   */
  @GetMapping("/page")
  public R<IPage<ThridpartyManage>> getThridpartyManagePage(Page<ThridpartyManage> page, ThridpartyManage thridpartyManage) {
    return  new R<>(thridpartyManageService.getThridpartyManagePage(page,thridpartyManage));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<ThridpartyManage> getById(@PathVariable("id") Integer id){
    return new R<>(thridpartyManageService.getById(id));
  }

  /**
   * 新增记录
   * @param thridpartyManage
   * @return R
   */
  @SysLog("新增第三方服务商管理")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('se_thridpartymanage_add')")
  public R save(@RequestBody ThridpartyManage thridpartyManage){
    return new R<>(thridpartyManageService.save(thridpartyManage));
  }

  /**
   * 修改记录
   * @param thridpartyManage
   * @return R
   */
  @SysLog("修改第三方服务商管理")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('se_thridpartymanage_edit')")
  public R update(@RequestBody ThridpartyManage thridpartyManage){
    return new R<>(thridpartyManageService.updateById(thridpartyManage));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除第三方服务商管理")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('se_thridpartymanage_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(thridpartyManageService.removeById(id));
  }

}
