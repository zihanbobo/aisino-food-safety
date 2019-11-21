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
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.portal.api.entity.CommentPraise;
import com.pig4cloud.pig.portal.api.entity.live.Live;
import com.pig4cloud.pig.portal.api.vo.live.LiveSchoolVO;
import com.pig4cloud.pig.school.api.entity.School;
import com.pig4cloud.pig.school.api.entity.recipe.*;
import com.pig4cloud.pig.school.api.vo.recipe.SourceVO;
import com.pig4cloud.pig.school.service.recipe.DailyService;
import com.pig4cloud.pig.school.service.recipe.DailySourceService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 每日食谱表
 *
 * @author 沈凝
 * @date 2019-07-09 15:35:40
 */
@RestController
@AllArgsConstructor
@RequestMapping("/daily")
public class DailyController {

  private final RemoteUserService remoteUserService;
  private final DailyService dailyService;
  private final DailySourceService dailySourceService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param daily 每日食谱表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Daily>> getDailyPage(Page<Daily> page, Daily daily) {
    return  new R<>(dailyService.getDailyPage(page,daily));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Daily> getById(@PathVariable("id") Integer id){
    return new R<>(dailyService.getById(id));
  }

  /**
   * 新增记录
   * @param daily
   * @return R
   */
  @SysLog("新增每日食谱表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('re_daily_add')")
  public R save(@RequestBody Daily daily){
    return new R<>(dailyService.save(daily));
  }

  /**
   * 修改记录
   * @param daily
   * @return R
   */
  @SysLog("修改每日食谱表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('re_daily_edit')")
  public R update(@RequestBody Daily daily){
    return new R<>(dailyService.updateById(daily));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除每日食谱表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('re_daily_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(dailyService.removeById(id));
  }



  /**
   * 门户端-当日食谱
   * @param size
   * @param current
   * @return
   */
  @Inner
  @GetMapping("/getSourceByDay")
  public R<Object> getSourceByDay(@RequestParam(value = "size")Integer size,
                                              @RequestParam(value = "current")Integer current,
                                              @RequestParam(value = "schoolId")Integer schoolId,
                                              @RequestParam(value = "rdDate") String rdDate/*,
                                              @RequestParam(value = "rdType") String rdType*/) {
      Page<Daily> page = new Page<>();
      page.setSize(new Long((long) size));
      page.setCurrent(new Long((long) current));
      Daily daily = new Daily();
      daily.setSchoolId(schoolId);
      daily.setRdDate(LocalDate.parse(rdDate,DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      //daily.setRdType(rdType);
      //早餐
      daily.setRdType("1");
      List<Map> sourceByDayForZao = dailyService.getSourceByDayForZao(daily);
    //IPage<List<Map>> sourceByDay = dailyService.getSourceByDay(page, daily);
      //午餐
      daily.setRdType("2");
      List<Map> sourceByDayForZhong = dailyService.getSourceByDayForZao(daily);
      //晚餐
      daily.setRdType("3");
      List<Map> sourceByDayForWan = dailyService.getSourceByDayForZao(daily);
      Map map = new LinkedHashMap();
      map.put("breakfast",sourceByDayForZao);
      map.put("lunch",sourceByDayForZhong);
      map.put("dinner",sourceByDayForWan);
    return new R<>(map);
  }

  /**
   * 公众app-当日食谱
   * @param size
   * @param current
   * @return
   */
  @Inner
  @GetMapping("/getDailyRecipe")
  public R<Object> getDailyRecipe(@RequestParam(value = "size")Integer size,
                                  @RequestParam(value = "current")Integer current,
                                  @RequestParam(value = "schoolId")Integer schoolId,
                                  @RequestParam(value = "rdDate") String rdDate,
                                  @RequestParam(value = "rdType",required = false) String rdType) {
    Page<Daily> page = new Page<>();
    page.setSize(new Long((long) size));
    page.setCurrent(new Long((long) current));
    Daily daily = new Daily();
    daily.setSchoolId(schoolId);
    daily.setRdDate(LocalDate.parse(rdDate,DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    if (!StringUtils.isEmpty(rdType)){
      daily.setRdType(rdType);
    }
    IPage<List<Map>> sourceByDay = dailyService.getDailyRecipe(page, daily);
    return new R<>(sourceByDay);
  }


  /**
   * 门户接口-食材追溯-人员信息
   * @param size
   * @return
   * @param current
   */
  @Inner
  @GetMapping("/getFoodTracingPerson")
  public R<Object> getFoodTracingPerson(@RequestParam(value = "size")Integer size,
                                              @RequestParam(value = "current")Integer current,
                                              @RequestParam(value = "schoolId")Integer schoolId,
                                              @RequestParam(value = "rdDate") String rdDate/*,
                                              @RequestParam(value = "rdType") String rdType*/) {
    Page<Daily> page = new Page<>();
    page.setSize(new Long((long) size));
    page.setCurrent(new Long((long) current));

    Map<String, Object> tempMap = new HashMap<>();
    tempMap.put("schoolId",schoolId);
    tempMap.put("rdDate",rdDate);
    //tempMap.put("rdType",rdType);
    // 查询当日厨师
    IPage<List<Map>> sourceByDay = dailyService.getKitchenStaffDay(page, tempMap);// 当日厨师
    Map<String, Object> map = new HashMap<>();
    map.put("cook",sourceByDay);
    tempMap.put("position","2");// 职位设置为食品安全管理员
    IPage<List<Map>> foodSafety = dailyService.getPersonInfo(page, tempMap);
    map.put("foodSatety",foodSafety);
    tempMap.put("position","3");// 职位设置为陪餐人员
    IPage<List<Map>> accompany = dailyService.getPersonInfo2(page, tempMap);

    map.put("accompany",accompany);
    return new R<>(sourceByDay);
  }

  /**
    * 门户接口-食材追溯-人员信息-大厨
   * @param size
   * @return
     * @param current
   */
  @Inner
  @GetMapping("/getPersonCook")
  public R<Object> getPersonCook(@RequestParam(value = "size")Integer size,
                                      @RequestParam(value = "current")Integer current,
                                      @RequestParam(value = "schoolId")Integer schoolId,
                                      @RequestParam(value = "rdDate") String rdDate/*,
                                      @RequestParam(value = "rdType") String rdType*/) {
    Page<Daily> page = new Page<>();
    page.setSize(new Long((long) size));
    page.setCurrent(new Long((long) current));

    Map<String, Object> tempMap = new HashMap<>();
    tempMap.put("schoolId",schoolId);
    tempMap.put("rdDate",rdDate);
    //tempMap.put("rdType",rdType);
    return new R<>(dailyService.getKitchenStaffDay(page, tempMap));
  }
  /**
   * 门户接口-食材追溯-人员信息-食品安全管理员
   * @param size
   * @return
   * @param current
   */
  @Inner
  @GetMapping("/getPersonPosition")
  public R<Object> getPersonPosition(@RequestParam(value = "size")Integer size,
                                        @RequestParam(value = "current")Integer current,
                                        @RequestParam(value = "schoolId")Integer schoolId,
                                        @RequestParam(value = "rdDate") String rdDate/*,
                                        @RequestParam(value = "rdType") String rdType*/) {
    Page<Daily> page = new Page<>();
    page.setSize(new Long((long) size));
    page.setCurrent(new Long((long) current));

    Map<String, Object> tempMap = new HashMap<>();
    tempMap.put("schoolId",schoolId);
    tempMap.put("rdDate",rdDate);
    //tempMap.put("rdType",rdType);
    // 查询食品安全管理员信息
    tempMap.put("position","2");// 职位设置为食品安全管理员
    IPage<List<Map>> foodSafety = dailyService.getPersonInfo(page, tempMap);
    return new R<>(foodSafety);
  }
  /**
   * 门户接口-食材追溯-人员信息-陪餐人员
   * @param size
   * @return
   * @param current
   */
  @Inner
  @GetMapping("/getPersonAccompany")
  public R<Object> getPersonAccompany(@RequestParam(value = "size")Integer size,
                                        @RequestParam(value = "current")Integer current,
                                        @RequestParam(value = "schoolId")Integer schoolId,
                                        @RequestParam(value = "rdDate") String rdDate/*,
                                        @RequestParam(value = "rdType") String rdType*/) {
    Page<Daily> page = new Page<>();
    page.setSize(new Long((long) size));
    page.setCurrent(new Long((long) current));

    Map<String, Object> tempMap = new HashMap<>();
    tempMap.put("schoolId",schoolId);
    tempMap.put("rdDate",rdDate);
    //tempMap.put("rdType",rdType);
    tempMap.put("position","3");// 职位设置为陪餐人员
    IPage<List<Map>> foodSafety = dailyService.getPersonInfo(page, tempMap);
    return new R<>(foodSafety);
  }




  /**
   * 门户接口-食材追溯-供应商信息
   * @param size
   * @return
   * @param current
   */
  @Inner
  @GetMapping("/getSupplierInfo")
  public R<Object> getSupplierInfo(@RequestParam(value = "size")Integer size,
                                              @RequestParam(value = "current")Integer current,
                                              @RequestParam(value = "schoolId")Integer schoolId,
                                              @RequestParam(value = "rdDate") String rdDate/*,
                                              @RequestParam(value = "rdType") String rdType*/) {
    Page<Daily> page = new Page<>();
    page.setSize(new Long((long) size));
    page.setCurrent(new Long((long) current));
    Map<String, Object> tempMap = new HashMap<>();
    tempMap.put("schoolId",schoolId);
    tempMap.put("rdDate",rdDate);
    //tempMap.put("rdType",rdType);
    IPage<List<Map>> personInfo = dailyService.getSupplierInfo(page, tempMap);
    return new R<>(personInfo);
  }
  /**
   * 门户接口-食材追溯-留样记录
   * @param size
   * @return
   * @param current
   */
  @Inner
  @GetMapping("/getSampleInfo")
  public R<Object> getSampleInfo(@RequestParam(value = "size")Integer size,
                                              @RequestParam(value = "current")Integer current,
                                              @RequestParam(value = "dailyId")Integer dailyId,
                                              @RequestParam(value = "sourceId") Integer sourceId) {
    Page<Daily> page = new Page<>();
    page.setSize(new Long((long) size));
    page.setCurrent(new Long((long) current));
    Map<String, Object> tempMap = new HashMap<>();
    tempMap.put("dailyId",dailyId);
    tempMap.put("sourceId",sourceId);
    IPage<List<Map>> sampleInfo = dailyService.getSampleInfo(page, tempMap);
    return new R<>(sampleInfo);
  }

  /**
   * 门户接口-食材追溯-食材用料
   * @param size
   * @return
   * @param current
   */
  @Inner
  @GetMapping("/getIngredientsFood")
  public R<Object> getIngredientsFood(@RequestParam(value = "size")Integer size,
                                              @RequestParam(value = "current")Integer current,
                                              @RequestParam(value = "sourceId") Integer sourceId) {
    Page<Daily> page = new Page<>();
    page.setSize(new Long((long) size));
    page.setCurrent(new Long((long) current));
    Map<String, Object> tempMap = new HashMap<>();
    tempMap.put("sourceId",sourceId);
    IPage<List<Map>> sampleInfo = dailyService.getIngredientsFood(page, tempMap);
    return new R<>(sampleInfo);
  }

  /**
   * 保存学校每日食谱信息
   *
   * @return
   */
//  @Inner
  @PostMapping("/saveRecipeDay")
  public R saveRecipeDay(@RequestParam(value="time", required = false) String time,
                  @RequestParam(value="breakfasts", required = false)String breakfasts,
                  @RequestParam(value="lunchs", required = false)String lunchs,
                  @RequestParam(value="dinners", required = false)String dinners
                  ) {
    R<Object> objectR = new R<>();
//    if(StringUtils.isEmpty(breakfasts)){
//      return objectR;
//    }
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
//    String schoolId = ;
//    if("1".equals(userType)){
//    }else if("2".equals(userType)){
//      source.setSchoolId(userInfo.getSysUser().getUnionId());
//    }
    Integer schoolId = userInfo.getSysUser().getUnionId();  //获取当前登录学校的id
    // 处理早中晚数据
    dealwithDaily(schoolId,time,"1",breakfasts);
    dealwithDaily(schoolId,time,"2",lunchs);
    dealwithDaily(schoolId,time,"3",dinners);
//    if(!StringUtils.isEmpty(breakfasts)) {
//      dealwithDaily(schoolId,time,"1",breakfasts);
//    }
//    if(!StringUtils.isEmpty(lunchs)) {
//      dealwithDaily(schoolId,time,"2",lunchs);
//    }
//    if(!StringUtils.isEmpty(dinners)) {
//      dealwithDaily(schoolId,time,"3",dinners);
//    }
    return objectR;
  }

  // 处理餐次及录入食谱数据
  public void dealwithDaily(Integer schoolId,String time,String rdType,String dinings) {
    Daily daily = dailyService.getOne(Wrappers.<Daily>query().lambda()
      .eq(Daily::getDelFlag, "0")
      .eq(Daily::getSchoolId, schoolId)
      .eq(Daily::getRdDate, time)
      .eq(Daily::getRdType, rdType));
    Integer id; //每日食谱的id值
    if (daily != null) {
      id = daily.getId();
    } else {
      Daily temp = new Daily();
      temp.setSchoolId(schoolId);
      temp.setRdDate(LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
      temp.setRdType(rdType);
      dailyService.save(temp);
      id = temp.getId();  // 存取后的id
    }
    // lamdba删除旧的逻辑关系
    dailySourceService.remove(Wrappers.<DailySource>update().lambda()
      .eq(DailySource::getDailyId, id));
    // 如果等于空则不进行处理
    if(StringUtils.isEmpty(dinings)){
      dailyService.remove(Wrappers.<Daily>update().lambda()
        .eq(Daily::getId, id));
      return;
    }
    String[] diningsArr = dinings.split(",");


    // 处理每日食谱中的菜谱信息(早餐/午餐/晚餐)
//    List<DailySource> breakfastsList = Arrays.asList(diningsArr)
//      .stream().map(sourceId -> {
//        DailySource dailySource = new DailySource();
//        dailySource.setDailyId(id);
//        dailySource.setSourceId(Integer.parseInt(sourceId));
//        return dailySource;
//      }).collect(Collectors.toList());


    List<DailySource> breakfastsList = Arrays.asList(diningsArr)
      .stream().map(dailySourceIds -> { // 以-分隔，存有sourceId,与userId
        String[] split = dailySourceIds.split("-");
        DailySource dailySource = new DailySource();
        dailySource.setDailyId(id);
        dailySource.setSourceId(Integer.parseInt(split[0]));
        if(split.length==2){
          dailySource.setUserId(Integer.parseInt(split[1]));
        }
        return dailySource;
      }).collect(Collectors.toList());

    dailySourceService.saveBatch(breakfastsList);
  }

  /**
   *
   *查询指定日期早中晚食谱
   * @return
   */
//  @Inner
  @PostMapping("/getRecipeDay")
  public R getRecipeDay(@RequestParam(value="time") String time) {
    R<Object> objectR = new R<>();
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer schoolId = userInfo.getSysUser().getUnionId();  //获取当前登录学校的id

    Map<String,Object> map = new HashMap<String,Object>();
    Map<String,Object> mapR = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    map.put("rdDate",time);
    map.put("rdType","1");
    List<Map> breakfasts = dailyService.getDailyBySchoolId(map);
    map.put("rdType","2");
    List<Map> lunchs = dailyService.getDailyBySchoolId(map);
    map.put("rdType","3");
    List<Map> dinners = dailyService.getDailyBySchoolId(map);
    mapR.put("breakfasts",breakfasts);
    mapR.put("lunchs",lunchs);
    mapR.put("dinners",dinners);
    return new R<>(mapR);
  }

  /**
   * 公众端-食材追溯-食材信息
   * @param size
   * @param current
   * @param sourceId
   * @return
   */
  @Inner
  @GetMapping("/getFoodIngredients")
  public R<Object> getFoodIngredients(@RequestParam(value = "size")Integer size,
                                      @RequestParam(value = "current")Integer current,
                                      @RequestParam(value = "sourceId") Integer sourceId) {
    Page<Daily> page = new Page<>();
    page.setSize(new Long((long) size));
    page.setCurrent(new Long((long) current));
    Map<String, Object> tempMap = new HashMap<>();
    tempMap.put("sourceId",sourceId);
    IPage<List<Map>> sampleInfo = dailyService.getFoodIngredients(page, tempMap);
    return new R<>(sampleInfo);
  }


  /**
   *
   *  管理端查询当前月是否已经配置食谱
   * @return
   */
//  @Inner
  @PostMapping("/getIsRecipeDay")
  public R getIsRecipeDay(@RequestParam(value="time") String time) {
    R<Object> objectR = new R<>();
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer schoolId = userInfo.getSysUser().getUnionId();  //获取当前登录学校的id
    // 封装查询参数(time为月参数)
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    map.put("rdDate",time);
    return new R<>(dailyService.getIsDailyDay(map));
  }

  /**
   * app-recipes 人员信息
   * @param size
   * @param current
   * @param schoolId
   * @param position
   * @return
   */
  @Inner
  @GetMapping("/getPersonnelInfo")
  public R<Object> getPersonnelInfo(@RequestParam(value = "size")Integer size,
                                    @RequestParam(value = "current")Integer current,
                                    @RequestParam(value = "schoolId")Integer schoolId,
                                    @RequestParam(value = "position") String position) {
    Page page = new Page();
    page.setSize((long)size);
    page.setCurrent((long) current);

    Map<String, Object> tempMap = new HashMap<>();
    tempMap.put("schoolId",schoolId);
    tempMap.put("position",position);
    IPage<List<Map>> personnelInfos = dailyService.getPersonnelInfo(page, tempMap);
    return new R<>(personnelInfos);
  }
}
