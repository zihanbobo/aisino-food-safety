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
package com.pig4cloud.pig.school.service.recipe;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.recipe.PurchaseMainList;
import com.pig4cloud.pig.school.api.vo.recipe.IngredientsFilingVO;
import com.pig4cloud.pig.school.api.vo.recipe.PurchaseFilingVO;

import java.util.List;
import java.util.Map;

/**
 * 采购清单管理
 *
 * @author 沈凝
 * @date 2019-07-15 17:23:49
 */
public interface PurchaseMainListService extends IService<PurchaseMainList> {

  /**
   * 采购清单管理简单分页查询
   * @param purchaseMainList 采购清单管理
   * @return
   */
  IPage<PurchaseMainList> getPurchaseMainListPage(Page<PurchaseMainList> page, PurchaseMainList purchaseMainList);

  /**
   * 校园端,根据每日食谱id查询食材
   * @param map 每日食谱表
   * @return
   */
  List<IngredientsFilingVO> getFoodByDay(Map map);
  /**
   * 校园端,根据每日食谱id查询食材
   * @param id 每日食谱表
   * @return
   */
  List<PurchaseFilingVO> purchaseFoodList(Integer id);


}
