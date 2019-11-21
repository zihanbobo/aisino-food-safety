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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.dto.recipe.PurchaseMainListDTO;
import com.pig4cloud.pig.school.api.entity.recipe.PurchaseFiling;
import com.pig4cloud.pig.school.api.entity.recipe.PurchaseMainList;
import com.pig4cloud.pig.school.api.vo.recipe.IngredientsFilingVO;
import com.pig4cloud.pig.school.service.recipe.PurchaseFilingService;
import com.pig4cloud.pig.school.service.recipe.PurchaseMainListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * 采购清单管理
 *
 * @author 沈凝
 * @date 2019-07-15 17:23:49
 */
@RestController
@AllArgsConstructor
@RequestMapping("/purchaseplan")
public class PurchasePlanController {

  private final RemoteUserService remoteUserService;
  private final PurchaseMainListService purchaseMainListService;
  private final PurchaseFilingService purchaseFilingService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param purchaseMainList 采购清单管理
   * @return
   */
  @GetMapping("/page")
  public R<IPage<PurchaseMainList>> getPurchaseMainListPage(Page<PurchaseMainList> page, PurchaseMainList purchaseMainList) {
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();  // 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if ("1".equals(userType)) {
    } else if ("2".equals(userType)) {
      purchaseMainList.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return  new R<>(purchaseMainListService.getPurchaseMainListPage(page,purchaseMainList));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<PurchaseMainList> getById(@PathVariable("id") Integer id){
    return new R<>(purchaseMainListService.getById(id));
  }

  /**
   * 新增记录
   * @param purchaseMainListDto
   * @return R
   */
  @SysLog("新增采购清单管理")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('pubchase_purchaseplan_add')")
  public R save(@RequestBody PurchaseMainListDTO purchaseMainListDto){
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      purchaseMainListDto.setSchoolId(userInfo.getSysUser().getUnionId());
    }

    // 处理采购记录
    PurchaseMainList purchaseMainList = new PurchaseMainList();
    BeanUtils.copyProperties(purchaseMainListDto, purchaseMainList);
    purchaseMainList.setPurPerson(userInfo.getSysUser().getUserId());// 采购计划制定人id
    purchaseMainList.setPurStatus("1");// 采购状态默认未采购

    purchaseMainListService.save(purchaseMainList);

    // 处理采购清单
    List purchasesList = purchaseMainListDto.getPurchasesList();
    List<PurchaseFiling> purchaseFilingInfix = new ArrayList<PurchaseFiling>();	//采购清单关联表(待插入集合)
    for(int i=0;i<purchasesList.size();i++){
      Map map = (Map)purchasesList.get(i);
      PurchaseFiling purchaseFiling = new PurchaseFiling();
      purchaseFiling.setPurId(purchaseMainList.getId());
      purchaseFiling.setFilingId(Integer.parseInt(String.valueOf(map.get("id")))); //食材备案id
      purchaseFiling.setUnitPrice(map.get("unitPrice")==null?"":String.valueOf(map.get("unitPrice")));  //计划-单价
      purchaseFiling.setWeight(map.get("weight")==null?"":String.valueOf(map.get("weight")));  //计划-重量
//      purchaseFiling.setRealityWeight(map.get("realityWeight")==null?"":String.valueOf(map.get("realityWeight")));  //清单-实际重量
      purchaseFiling.setRealityWeight(map.get("weight")==null?"":String.valueOf(map.get("weight")));  //清单-实际重量
      purchaseFiling.setCheckFile(map.get("checkFile")==null?"":String.valueOf(map.get("checkFile")));  //清单-快件报告
      purchaseFiling.setIsQualified(map.get("isQualified")==null?"":String.valueOf(map.get("isQualified")));//清单-快检报告是否合格

      purchaseFiling.setFoodChannel(map.get("foodChannel")==null?"":String.valueOf(map.get("foodChannel")));  //清单-快件报告
      purchaseFiling.setIsDel(map.get("isDel")==null?"":String.valueOf(map.get("isDel")));//清单-快检报告是否合格
//      purchaseFiling.setFoodChannel("1"); //进项类别(1计划2清单)
//      purchaseFiling.setIsDel("0");  //是否在清单中删除(0正常1删除)
      purchaseFilingInfix.add(purchaseFiling);
    }
    return new R<>(purchaseFilingService.saveBatch(purchaseFilingInfix));
  }

  /**
   * 修改记录
   * @param purchaseMainListDto
   * @return R
   */
  @SysLog("修改采购清单管理")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('pubchase_purchaseplan_edit')")
  public R update(@RequestBody PurchaseMainListDTO purchaseMainListDto){

    // 更新采购记录
    PurchaseMainList purchaseMainList = new PurchaseMainList();
    BeanUtils.copyProperties(purchaseMainListDto, purchaseMainList);
    purchaseMainListService.updateById(purchaseMainList);
    // 判断采购状态(用于修改实际重量)
    String purStatus = purchaseMainList.getPurStatus();
    // lamdba删除旧的逻辑关系
    purchaseFilingService.remove(Wrappers.<PurchaseFiling>update().lambda()
      .eq(PurchaseFiling::getPurId, purchaseMainList.getId()));

    // 处理采购清单
    List purchasesList = purchaseMainListDto.getPurchasesList();
    List<PurchaseFiling> purchaseFilingInfix = new ArrayList<PurchaseFiling>();	//采购清单关联表(待插入集合)
    for(int i=0;i<purchasesList.size();i++){
      Map map = (Map)purchasesList.get(i);
      PurchaseFiling purchaseFiling = new PurchaseFiling();
      purchaseFiling.setPurId(purchaseMainList.getId());
      purchaseFiling.setFilingId(Integer.parseInt(String.valueOf(map.get("id")))); //食材备案id
      purchaseFiling.setUnitPrice(map.get("unitPrice")==null?"":String.valueOf(map.get("unitPrice")));  //计划-单价
      purchaseFiling.setWeight(map.get("weight")==null?"":String.valueOf(map.get("weight")));  //计划-重量
      // 为1则为未采购(可随意修改)
      if("1".equals(purStatus)){
        purchaseFiling.setRealityWeight(map.get("weight")==null?"":String.valueOf(map.get("weight")));  //计划-重量
      }else{
        purchaseFiling.setRealityWeight(map.get("realityWeight")==null?"":String.valueOf(map.get("realityWeight")));  //清单-实际重量
      }
      purchaseFiling.setCheckFile(map.get("checkFile")==null?"":String.valueOf(map.get("checkFile")));  //清单-快件报告
      purchaseFiling.setIsQualified(map.get("isQualified")==null?"":String.valueOf(map.get("isQualified")));//清单-快检报告是否合格

      purchaseFiling.setFoodChannel(map.get("foodChannel")==null?"":String.valueOf(map.get("foodChannel")));  //清单-快件报告
      purchaseFiling.setIsDel(map.get("isDel")==null?"":String.valueOf(map.get("isDel")));//清单-快检报告是否合格
      purchaseFilingInfix.add(purchaseFiling);
    }
    return new R<>(purchaseFilingService.saveBatch(purchaseFilingInfix));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除采购清单管理")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('pubchase_purchaseplan_del')")
  public R removeById(@PathVariable Integer id){
    // 先清除食谱关联食材信息
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pur_id",id);
    purchaseFilingService.removeByMap(map);

    // 逻辑删除采购信息
    PurchaseMainList purchaseMainList = purchaseMainListService.getById(id);
    purchaseMainList.setDelFlag("1");
    return new R<>(purchaseMainListService.updateById(purchaseMainList));
  }

  /**
   *
   *查询指定日期早中晚食谱
   * @return
   */
//  @Inner
  @PostMapping("/getFoodByDay")
  public R getFoodByDay(@RequestParam(value="time") String time) {
    R<Object> objectR = new R<>();
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer schoolId = userInfo.getSysUser().getUnionId();  //获取当前登录学校的id

    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    map.put("rdDate",time);
    List<IngredientsFilingVO> foodList = purchaseMainListService.getFoodByDay(map);
    return new R<>(foodList);
  }


}
