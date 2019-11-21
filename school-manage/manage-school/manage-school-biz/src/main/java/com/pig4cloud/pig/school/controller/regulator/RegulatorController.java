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
package com.pig4cloud.pig.school.controller.regulator;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.admin.api.vo.TreeUtil;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.school.api.dto.regulator.RegulatorTree;
import com.pig4cloud.pig.school.api.dto.regulator.TreeNode;
import com.pig4cloud.pig.school.api.entity.regulator.Regulator;
import com.pig4cloud.pig.school.service.regulator.RegulatorService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 监管局信息表
 *
 * @author -
 * @date 2019-08-05 14:49:59
 */
@RestController
@AllArgsConstructor
@RequestMapping("/regulator")
public class RegulatorController {
  private final RegulatorService regulatorService;
	private final RemoteUserService remoteUserService;
  /**
   * 简单分页查询
   * @param page 分页对象
   * @param regulator 监管局信息表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Regulator>> getRegulatorPage(Page<Regulator> page, Regulator regulator) {

	  R<UserInfo> result = remoteUserService.info("shenning", SecurityConstants.FROM_IN);
    return  new R<>(regulatorService.getRegulatorPage(page,regulator));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Regulator> getById(@PathVariable("id") Integer id){
    return new R<>(regulatorService.getById(id));
  }

  /**
   * 新增记录
   * @param regulator
   * @return R
   */
  @SysLog("新增监管局信息表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('se_regulator_add')")
  public R save(@RequestBody Regulator regulator){
    return new R<>(regulatorService.save(regulator));
  }

  /**
   * 修改记录
   * @param regulator
   * @return R
   */
  @SysLog("修改监管局信息表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('se_regulator_edit')")
  public R update(@RequestBody Regulator regulator){
    return new R<>(regulatorService.updateById(regulator));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除监管局信息表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('se_regulator_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(regulatorService.removeById(id));
  }


  /**
   * 返回树形菜单集合
   *
   * @return 树形菜单
   */
  @GetMapping(value = "/tree")
  public R listRegulatorTrees() {
    return new R<>(regulatorService.listRegulatorTrees());
  }



  /**
   * 返回树形菜单集合
   *
   * @return 树形菜单
   */
//  @GetMapping(value = "/tree")
//  public R getTree() {
//    return new R<>(buildTree(regulatorService.list(Wrappers.emptyWrapper()), -1));
//
//  }


  /**
   * 通过sysMenu创建树形节点
   *
   * @param regulators
   * @param root
   * @return
   */
//  public List<RegulatorTree> buildTree(List<Regulator> regulators, int root) {
//    List<RegulatorTree> trees = new ArrayList<>();
//    RegulatorTree node;
//    for (Regulator regulator : regulators) {
//      node = new RegulatorTree();
//      node.setId(regulator.getId());
//      node.setParentId(regulator.getParentId());
//      node.setRegName(regulator.getRegName());
//      node.setRegAddress(regulator.getRegAddress());
//      node.setRegArea(regulator.getRegArea());
//      node.setRegTel(regulator.getRegTel());
//      trees.add(node);
//    }
//    return buildByLoop(trees, root);
//  }


  /**
   * 两层循环实现建树
   *
   * @param treeNodes 传入的树节点列表
   * @return
   */
  public <T extends TreeNode> List<T> buildByLoop(List<T> treeNodes, Object root) {

    List<T> trees = new ArrayList<>();

    for (T treeNode : treeNodes) {

      if (root.equals(treeNode.getParentId())) {
        trees.add(treeNode);
      }

      for (T it : treeNodes) {
        if (it.getParentId() == treeNode.getId()) {
          if (treeNode.getChildren() == null) {
            treeNode.setChildren(new ArrayList<>());
          }
          treeNode.add(it);
        }
      }
    }
     return trees;
  }
}
