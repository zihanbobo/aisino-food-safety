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
package com.pig4cloud.pig.school.service.regulator.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.dto.regulator.RegulatorTree;
import com.pig4cloud.pig.school.api.dto.source.FoodTypeTree;
import com.pig4cloud.pig.school.api.entity.regulator.Regulator;
import com.pig4cloud.pig.school.api.entity.source.FoodType;
import com.pig4cloud.pig.school.api.vo.source.TreeUtil;
import com.pig4cloud.pig.school.mapper.RegulatorMapper;
import com.pig4cloud.pig.school.service.regulator.RegulatorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 监管局信息表
 *
 * @author -
 * @date 2019-08-05 14:49:59
 */
@Service("regulatorService")
public class RegulatorServiceImpl extends ServiceImpl<RegulatorMapper, Regulator> implements RegulatorService {

  /**
   * 监管局信息表简单分页查询
   * @param regulator 监管局信息表
   * @return
   */
  @Override
  public IPage<Regulator> getRegulatorPage(Page<Regulator> page, Regulator regulator){
    return baseMapper.getRegulatorPage(page,regulator);
  }


  /**
   * 查询全部部门树
   *
   * @return 树
   */
  @Override
  public List<RegulatorTree> listRegulatorTrees() {
    return getRegulatorTree(this.list(Wrappers.emptyWrapper()));
  }


  /**
   * 构建食材类别树
   *
   * @param regulators 食材类别
   * @return
   */
  private List<RegulatorTree> getRegulatorTree(List<Regulator> regulators) {
    List<RegulatorTree> treeList = regulators.stream()
      .filter(regulator -> !regulator.getId().equals(regulator.getParentId()))
      .map(regulator -> {
        RegulatorTree node = new RegulatorTree();
        node.setId(regulator.getId());
        node.setParentId(regulator.getParentId());
        node.setRegName(regulator.getRegName());
        node.setRegArea(regulator.getRegArea());
        node.setRegTel(regulator.getRegTel());
        node.setRegAddress(regulator.getRegAddress());
        return node;
      }).collect(Collectors.toList());
    return TreeUtil.buildByLoop(treeList, 0);
  }

}
