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
package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.entity.recipe.PurchaseMainList;
import com.pig4cloud.pig.school.api.vo.recipe.IngredientsFilingVO;
import com.pig4cloud.pig.school.api.vo.recipe.PurchaseFilingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 采购清单管理
 *
 * @author 沈凝
 * @date 2019-07-15 17:23:49
 */
public interface PurchaseMainListMapper extends BaseMapper<PurchaseMainList> {
  /**
    * 采购清单管理简单分页查询
    * @param purchaseMainList 采购清单管理
    * @return
    */
  IPage<PurchaseMainList> getPurchaseMainListPage(Page page, @Param("purchaseMainList") PurchaseMainList purchaseMainList);

  /**
   * 校园端,根据每日食谱id查询食材
   * @param map 每日食谱表
   * @return
   */
  List<IngredientsFilingVO> getFoodByDay(@Param("map") Map map);



  /**
   * 通过采购id,查询食材id(采购计划和采购清单都用到了)
   *
   * @param id
   * @return
   */
  List<PurchaseFilingVO> purchaseFoodList(@Param("id") Integer id);
}
