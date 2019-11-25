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
package com.pig4cloud.pig.school.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.dto.SchoolDTO;
import com.pig4cloud.pig.school.api.entity.School;

import java.util.Map;

/**
 * 学校表
 *
 * @author 崔俊前
 * @date 2019-08-05 14:48:06
 */
public interface SchoolService extends IService<School> {

  /**
   * 学校表简单分页查询
   * @param school 学校表
   * @return
   */
  IPage<School> getSchoolPage(Page<School> page, School school);

	/**
	 * 学校表简单分页查询（含有套餐信息）
	 * @param page    分页对象
	 * @param schoolDTO 参数列表
	 * @return
	 */
	IPage getSchoolWithMealPage(Page page, SchoolDTO schoolDTO);


	/**
	 * 保存学校信息
	 *
	 * @param schoolDto DTO 对象
	 * @return success/fail
	 */
	Boolean saveSchool(SchoolDTO schoolDto);

	/**
	 * 更新指定学校信息
	 *
	 * @param schoolDto 学校信息
	 * @return
	 */
	Boolean updateSchool(SchoolDTO schoolDto);

	/**
	 * 删除学校
	 *
	 * @param school 学校
	 * @return boolean
	 */
	Boolean removeSchoolById(School school);

  /**
   * app-live --获取学校信息
   * @param id
   * @return
   */
  Map getSchoolEasyInfo(Integer id);
  
  /**
   * @Description //获取监管端趋势分析
   * @Date 13:55 2019/11/22
   * @Param
   * @return
   **/
  
  Map getAnalysisSchoolData(Integer areaCode);
  
  /**
   * @Description //获取监管端趋势分析页面食堂相关信息
   * @Date 13:55 2019/11/22
   * @Param
   * @return
   **/
  
  Map getMesshallMessageByArea(Integer areaCode);
}
