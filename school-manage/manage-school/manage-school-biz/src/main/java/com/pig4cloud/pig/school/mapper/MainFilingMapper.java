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
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 供应商备案表
 *
 * @author 沈凝
 * @date 2019-06-21 14:59:45
 */
public interface MainFilingMapper extends BaseMapper<MainFiling> {
  /**
    * 供应商备案表简单分页查询
    * @param mainFiling 供应商备案表
    * @return
    */
  IPage<MainFiling> getMainFilingPage(Page page, @Param("mainFiling") MainFiling mainFiling);
	/**
	 * 通过食材ID，查询供应商信息
	 *
	 * @param id
	 * @return
	 */
	List<Map> listMainsByFoodId(@Param("id") Integer id);

}
