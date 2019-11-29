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
package com.pig4cloud.pig.school.service.impl;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.school.api.dto.SchoolDTO;
import com.pig4cloud.pig.school.api.entity.School;
import com.pig4cloud.pig.school.api.entity.selseal.SchoolMeal;
import com.pig4cloud.pig.school.mapper.SchoolMapper;
import com.pig4cloud.pig.school.service.SchoolService;
import com.pig4cloud.pig.school.service.selseal.SchoolMealService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 学校表
 *
 * @author 崔俊前
 * @date 2019-08-05 14:48:06
 */
@Slf4j
@Service("schoolService")
@AllArgsConstructor
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {

  private final SchoolMealService schoolMealService;

  /**
   * 学校表简单分页查询
   *
   * @param school 学校表
   * @return
   */
  @Override
  public IPage<School> getSchoolPage(Page<School> page, School school) {
    return baseMapper.getSchoolPage(page, school);
  }

  /**
   * 学校表简单分页查询（含有套餐信息）
   *
   * @param page      分页对象
   * @param schoolDTO 参数列表
   * @return
   */
  @Override
  public IPage getSchoolWithMealPage(Page page, SchoolDTO schoolDTO) {
    return baseMapper.getSchoolVosPage(page, schoolDTO);
  }

  /**
   * 保存套餐信息
   *
   * @param schoolDTO DTO 对象
   * @return success/fail
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean saveSchool(SchoolDTO schoolDTO) {
    School school = new School();
    BeanUtils.copyProperties(schoolDTO, school);
    school.setDelFlag(CommonConstants.STATUS_NORMAL);
    // 直播码与食谱码自动生成(8位)
    school.setLiveIdentifier(RandomUtil.randomString(8));
    school.setRecipeIdentifier(RandomUtil.randomString(8));
    baseMapper.insert(school);

    // Hutool判断服务是否为空
    if (IterUtil.isEmpty(schoolDTO.getMealListAll())) {
      return true;
    }
    List<SchoolMeal> schoolList = schoolDTO.getMealListAll()
      .stream().map(map -> {
        SchoolMeal schoolMeal = new SchoolMeal();
        schoolMeal.setSchoolId(school.getId());
        schoolMeal.setMealId(Integer.parseInt(String.valueOf(map.get("mealId"))));
        schoolMeal.setStartDate(LocalDate.parse(String.valueOf(map.get("startDate")), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        schoolMeal.setEndDate(LocalDate.parse(String.valueOf(map.get("endDate")), DateTimeFormatter.ofPattern("yyyy-MM-dd")));  //增加一年
        return schoolMeal;
      }).collect(Collectors.toList());
    return schoolMealService.saveBatch(schoolList);
  }


  /**
   * 更新学校信息
   *
   * @param schoolDTO DTO 对象
   * @return success/fail
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean updateSchool(SchoolDTO schoolDTO) {
    School school = new School();
    BeanUtils.copyProperties(schoolDTO, school);  //Hutool属性复制
    school.setUpdateTime(LocalDateTime.now());
    this.updateById(school);

    schoolMealService.remove(Wrappers.<SchoolMeal>update().lambda()
      .eq(SchoolMeal::getSchoolId, schoolDTO.getId()));

    // 处理服务
    schoolDTO.getMealList().forEach(mealId -> {
      SchoolMeal schoolMeal = new SchoolMeal();
      schoolMeal.setSchoolId(school.getId());
      schoolMeal.setMealId(mealId);
      schoolMeal.setStartDate(LocalDate.now());
      schoolMeal.setEndDate(LocalDate.now().plusYears(1));
      schoolMeal.insert();
    });
    return Boolean.TRUE;
  }

  /**
   * 删除学校
   *
   * @param school 学校
   * @return Boolean
   */
  @Override
  public Boolean removeSchoolById(School school) {
    schoolMealService.removeMealBySchoolId(school.getId());
    this.removeById(school.getId());
    return Boolean.TRUE;
  }

  /**
   * app-live --获取学校信息
   *
   * @param id
   * @return
   */
  @Override
  public Map getSchoolEasyInfo(Integer id) {
    return baseMapper.getSchoolEasyInfo(id);
  }
  
  /**
   * @Description //获取监管端趋势分析
   * @Date 13:55 2019/11/22
   * @Param
   * @return
   **/
  @Override
  public Map<String,Object> getAnalysisSchoolData(Integer areaCode){
      Map<String,Object> data = baseMapper.getAreaSchoolAndStuNum(areaCode);
      if (data != null){
        List<Map<String,Object>> list = baseMapper.getSchoolDataByType(areaCode);
        data.put("place",list);
      }
      return data;
  }
  
  /**
   * @Description //获取监管端趋势分析页面食堂相关信息
   * @Date 13:55 2019/11/22
   * @Param
   * @return
   **/
  @Override
  public Map<String,Object> getMesshallMessageByArea(Integer areaCode){
    Map<String,Object> data = baseMapper.getMesshallMessageByArea(areaCode);
    return data;
  }
}
