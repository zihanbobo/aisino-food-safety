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
import com.pig4cloud.pig.school.api.dto.SchoolDTO;
import com.pig4cloud.pig.school.api.entity.School;
import com.pig4cloud.pig.school.api.vo.SchoolVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 学校表
 *
 * @author 崔俊前
 * @date 2019-08-05 14:48:06
 */
public interface SchoolMapper extends BaseMapper<School> {
  /**
    * 学校表简单分页查询
    * @param school 学校表
    * @return
    */
  IPage<School> getSchoolPage(Page page, @Param("school") School school);

	/**
	 * 分页查询食谱备案信息（含供应商）
	 *
	 * @param page    分页
	 * @param schoolDTO 查询参数
	 * @return list
	 */
	IPage<List<SchoolVO>> getSchoolVosPage(Page page, @Param("query") SchoolDTO schoolDTO);

  Map getSchoolEasyInfo(Integer id);
  
  /**
   * @Description //根据区划返回各类学校的数量
   * @Date 14:10 2019/11/22
   * @Param [areaCode]
   * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
   **/
  
  List<Map<String,Object>> getSchoolDataByType(Integer areaCode);
  /**
   * @Description //根据区划返回区域内学校和学生的数量
   * @Date 14:10 2019/11/22
   * @Param [areaCode]
   * @return java.util.Map<java.lang.String,java.lang.Object>
   **/
  Map<String,Object> getAreaSchoolAndStuNum(Integer areaCode);
}
