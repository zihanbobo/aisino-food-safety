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
import com.pig4cloud.pig.school.api.dto.recipe.SourceDTO;
import com.pig4cloud.pig.school.api.entity.recipe.Source;
import com.pig4cloud.pig.school.api.vo.source.SourceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 食谱资源表
 *
 * @author 沈凝
 * @date 2019-07-09 15:16:31
 */
public interface SourceMapper extends BaseMapper<Source> {
  /**
    * 食谱资源表简单分页查询
    * @param source 食谱资源表
    * @return
    */
  IPage<Source> getSourcePage(Page page, @Param("source") Source source);

	/**
	 * 分页查询食谱备案信息（含供应商）
	 *
	 * @param page    分页
	 * @param sourceDTO 查询参数
	 * @return list
	 */
	IPage<List<SourceVO>> getSourceVosPage(Page page, @Param("query") SourceDTO sourceDTO);



}
