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
import com.pig4cloud.pig.school.api.entity.selseal.ServiceMain;
import com.pig4cloud.pig.school.service.selseal.ServiceMainService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 服务表
 *
 * @author 沈凝
 * @date 2019-08-05 10:32:15
 */
@RestController
@AllArgsConstructor
@RequestMapping("/servicemain")
public class ServiceMainController {

  private final ServiceMainService serviceMainService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param serviceMain 服务表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<ServiceMain>> getServiceMainPage(Page<ServiceMain> page, ServiceMain serviceMain) {
    return  new R<>(serviceMainService.getServiceMainPage(page,serviceMain));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<ServiceMain> getById(@PathVariable("id") Integer id){
    return new R<>(serviceMainService.getById(id));
  }

  /**
   * 新增记录
   * @param serviceMain
   * @return R
   */
  @SysLog("新增服务表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('se_servicemain_add')")
  public R save(@RequestBody ServiceMain serviceMain){
    return new R<>(serviceMainService.save(serviceMain));
  }

  /**
   * 修改记录
   * @param serviceMain
   * @return R
   */
  @SysLog("修改服务表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('se_servicemain_edit')")
  public R update(@RequestBody ServiceMain serviceMain){
    return new R<>(serviceMainService.updateById(serviceMain));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除服务表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('se_servicemain_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(serviceMainService.removeById(id));
  }

}
