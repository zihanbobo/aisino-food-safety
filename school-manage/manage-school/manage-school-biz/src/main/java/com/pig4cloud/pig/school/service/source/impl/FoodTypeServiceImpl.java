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
package com.pig4cloud.pig.school.service.source.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.dto.source.FoodTypeTree;
import com.pig4cloud.pig.school.api.entity.source.FoodType;
import com.pig4cloud.pig.school.api.vo.source.TreeUtil;
import com.pig4cloud.pig.school.mapper.FoodTypeMapper;
import com.pig4cloud.pig.school.service.source.FoodTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 食材类别表
 *
 * @author 沈凝
 * @date 2019-07-08 15:50:37
 */
@Service("foodTypeService")
public class FoodTypeServiceImpl extends ServiceImpl<FoodTypeMapper, FoodType> implements FoodTypeService {
/*	private final FoodTypeRelationService foodTypeRelationService;*/

  /**
   * 食材类别表简单分页查询
   * @param foodType 食材类别表
   * @return
   */
  @Override
  public IPage<FoodType> getFoodTypePage(Page<FoodType> page, FoodType foodType){
      return baseMapper.getFoodTypePage(page,foodType);
  }

	/**
	 * 查询全部部门树
	 *
	 * @return 树
	 */
	@Override
	public List<FoodTypeTree> listFoodTypeTrees() {
		return getFoodTypeTree(this.list(Wrappers.emptyWrapper()));
	}

	/**
	 * 查询用户食材类别树
	 *listFoodTypeTrees
	 * @return
	 */
/*	@Override
	public List<DeptTree> listCurrentFoodTypeTrees() {
		Integer deptId = SecurityUtils.getUser().getDeptId();
		List<Integer> descendantIdList = sysDeptRelationService
			.list(Wrappers.<SysDeptRelation>query().lambda()
				.eq(SysDeptRelation::getAncestor, deptId))
			.stream().map(SysDeptRelation::getDescendant)
			.collect(Collectors.toList());

		List<SysDept> deptList = baseMapper.selectBatchIds(descendantIdList);
		return getFoodTypeTree(deptList);
	}*/

	/**
	 * 构建食材类别树
	 *
	 * @param foodTypes 食材类别
	 * @return
	 */
	private List<FoodTypeTree> getFoodTypeTree(List<FoodType> foodTypes) {
		List<FoodTypeTree> treeList = foodTypes.stream()
			.filter(foodType -> !foodType.getFoodtypeId().equals(foodType.getParentId()))
			.map(foodType -> {
				FoodTypeTree node = new FoodTypeTree();
				node.setId(foodType.getFoodtypeId());
				node.setParentId(foodType.getParentId());
				node.setName(foodType.getName());
				return node;
			}).collect(Collectors.toList());
		return TreeUtil.buildByLoop(treeList, 0);
	}


}
