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
package com.pig4cloud.pig.school.controller.recipe;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.dto.recipe.SourceDTO;
import com.pig4cloud.pig.school.api.entity.recipe.FoodMain;
import com.pig4cloud.pig.school.api.entity.recipe.IngredientsFiling;
import com.pig4cloud.pig.school.api.entity.recipe.Source;
import com.pig4cloud.pig.school.api.entity.recipe.SourceFood;
import com.pig4cloud.pig.school.api.entity.source.SchoolCustom;
import com.pig4cloud.pig.school.service.recipe.SourceFoodService;
import com.pig4cloud.pig.school.service.recipe.SourceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 食谱资源表
 *
 * @author 沈凝
 * @date 2019-07-09 15:16:31
 */
@RestController
@AllArgsConstructor
@RequestMapping("/source")
public class SourceController {

  private final RemoteUserService remoteUserService;
  private final SourceService sourceService;
  private final SourceFoodService sourceFoodService;


  @GetMapping("/page")
  public R getFoodFilingPage(Page page, Source source) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      source.setSchoolId(userInfo.getSysUser().getUnionId());
    }
	  return new R<>(sourceService.getSourcePage(page,source));
  }

  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Source> getById(@PathVariable("id") Integer id){
    return new R<>(sourceService.getById(id));
  }

  /**
   * 新增记录
   * @param sourceDto(带主料)
   * @return R
   */
  @SysLog("新增食谱资源表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('re_source_add')")
  public R save(@RequestBody SourceDTO sourceDto){
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      sourceDto.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    // 处理食谱
    Source source = new Source();
    BeanUtils.copyProperties(sourceDto, source);
//    source.setDelFlag(CommonConstants.STATUS_NORMAL);
    sourceService.save(source);
    // 处理主料/辅料
    List<SourceFood> sourceFoodList = new ArrayList<SourceFood>();	//CollUtil.isEmpty
    List<SourceFood> sourceFoodMainList = sourceDto.getMainMaterial()
      .stream().map(foodId -> {
        SourceFood sourceFood = new SourceFood();
        sourceFood.setSourceId(source.getId());
        sourceFood.setFoodId(foodId);
        sourceFood.setType("1");	//调料类型（1主料 2辅料）
        return sourceFood;
      }).collect(Collectors.toList());
    // 处理辅料
    List<SourceFood> sourceFoodMajorList = sourceDto.getMinorIngredient()
      .stream().map(foodId -> {
        SourceFood sourceFood = new SourceFood();
        sourceFood.setSourceId(source.getId());
        sourceFood.setFoodId(foodId);
        sourceFood.setType("2");	//调料类型（1主料 2辅料）
        return sourceFood;
      }).collect(Collectors.toList());
    // 将主料辅料合并后插入
    sourceFoodList.addAll(sourceFoodMainList);
    sourceFoodList.addAll(sourceFoodMajorList);
    return new R<>(sourceFoodService.saveBatch(sourceFoodList));
  }

	/**
	 * 更新食谱信息
	 *
	 * @param sourceDto 食谱信息
	 * @return R
	 */
//	@SysLog("更新食谱信息")
//	@PutMapping
//	@PreAuthorize("@pms.hasPermission('re_source_edit')")
//	public R updateSource(@Valid @RequestBody SourceDTO sourceDto) {
//		return new R<>(sourceService.updateSource(sourceDto));
//	}


	@SysLog("更新食谱信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('re_source_edit')")
	public R updateSource(@RequestBody SourceDTO sourceDto) {
    Source source = new Source();
    BeanUtils.copyProperties(sourceDto, source);	//Hutool属性复制
    sourceService.updateById(source);
    // lamdba删除旧的逻辑关系
    sourceFoodService.remove(Wrappers.<SourceFood>update().lambda()
      .eq(SourceFood::getSourceId, source.getId()));
    // 处理主料
    sourceDto.getMainMaterial().forEach(foodId -> {
      SourceFood sourceFood = new SourceFood();	//中间表
      sourceFood.setSourceId(source.getId());
      sourceFood.setFoodId(foodId);
      sourceFood.setType("1");
      sourceFood.insert();
    });
    // 处理辅料
    sourceDto.getMinorIngredient().forEach(foodId -> {
      SourceFood sourceFood = new SourceFood();	//中间表
      sourceFood.setSourceId(source.getId());
      sourceFood.setFoodId(foodId);
      sourceFood.setType("2");
      sourceFood.insert();
    });
    return new R<>(Boolean.TRUE);
	}





  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除食谱资源表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('re_source_del')")
  public R removeById(@PathVariable Integer id){
    // 先清除食谱关联食材信息
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("source_id",id);
    sourceFoodService.removeByMap(map);
    // 逻辑删除食谱信息
	  Source source = sourceService.getById(id);
    source.setDelFlag("1");
	  return new R<>(sourceService.updateById(source));
  }

}
