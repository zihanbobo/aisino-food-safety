package com.pig4cloud.pig.portalpor.controller;

import com.aliyun.oss.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import com.pig4cloud.pig.school.api.feign.RemoteRecipeService;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 公共用户信息
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

  private final RemoteSchoolService remoteSchoolService;
  private final RemoteRecipeService remoteRecipeService;

  /**
   * 根据学校id查询指定学校指定用餐时间段的食谱信息（带分页）
   *
   * @return
   */
  @GetMapping("/getSourceByDay")
  public R getSourceByDay(@RequestParam(value = "size",required = false) Integer size,
                          @RequestParam(value = "current",required = false) Integer current,
                          @RequestParam(value = "schoolId")Integer schoolId,
                          @RequestParam(value = "rdDate") String rdDate,
                          @RequestParam(value = "rdType",required = false) String rdType) {
    if(size==null){
      size = 100;
    }
    if(current==null){
      current = 1;
    }
    rdType = null;
    R<Object> sourceByDay = remoteRecipeService.getSourceByDay(size, current, schoolId, rdDate,SecurityConstants.FROM_IN);
    return sourceByDay;
  }

  /**
   * 根据学校id查询指定食材追溯-人员信息
   *
   * @return
   */
  @GetMapping("/getFoodTracingPerson")
  public R getFoodTracingPerson(@RequestParam(value = "size",required = false) Integer size,
                                @RequestParam(value = "current",required = false) Integer current,
                                @RequestParam(value = "schoolId")Integer schoolId,
                                @RequestParam(value = "rdDate") String rdDate/*,
                                @RequestParam(value = "rdType") String rdType*/) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
//    R<Object> result = remoteRecipeService.getFoodTracingPerson(size, current, schoolId, rdDate, rdType, SecurityConstants.FROM_IN);
    Map<String,Object> map = new HashMap<String,Object>();
    R<Object> personCook = remoteRecipeService.getPersonCook(size, current, schoolId, rdDate, SecurityConstants.FROM_IN);
    map.put("cook",personCook.getData());
    R<Object> personPosition = remoteRecipeService.getPersonPosition(size, current, schoolId, rdDate, SecurityConstants.FROM_IN);
    map.put("position",personPosition.getData());
    R<Object> personAccompany = remoteRecipeService.getPersonAccompany(size, current, schoolId, rdDate, SecurityConstants.FROM_IN);
    map.put("accompany",personAccompany.getData());
    return new R<>(map);
  }

  /**
   * 根据学校id查询指定学校食材追溯-供应商信息
   *
   * @return
   */
  @GetMapping("/getSupplierInfo")
  public R getSupplierInfo(@RequestParam(value = "size",required = false) Integer size,
                           @RequestParam(value = "current",required = false) Integer current,
                           @RequestParam(value = "schoolId")Integer schoolId,
                           @RequestParam(value = "rdDate") String rdDate/*,
                           @RequestParam(value = "rdType") String rdType*/) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
    R<Object> result = remoteRecipeService.getSupplierInfo(size, current, schoolId, rdDate, /*rdType, */SecurityConstants.FROM_IN);
    return result;
  }
  /**
   * 根据学校id查询指定学校食材追溯-留样信息
   *
   * @return
   */
  @GetMapping("/getSampleInfo")
  public R getSampleInfo(@RequestParam(value = "size",required = false) Integer size,
                         @RequestParam(value = "current",required = false) Integer current,
                         @RequestParam(value = "dailyId")Integer dailyId,
                         @RequestParam(value = "sourceId") Integer sourceId) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
    R<Object> result = remoteRecipeService.getSampleInfo(size, current, dailyId, sourceId, SecurityConstants.FROM_IN);
    return result;
  }
  /**
   * 根据学校id查询指定学校食材追溯-食材用料
   *
   * @return
   */
  @GetMapping("/getIngredientsFood")
  public R getIngredientsFood(@RequestParam(value = "size",required = false) Integer size,
                              @RequestParam(value = "current",required = false) Integer current,
                              @RequestParam(value = "sourceId") Integer sourceId) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
    R<Object> result = remoteRecipeService.getIngredientsFood(size, current, sourceId, SecurityConstants.FROM_IN);
    return result;
  }


}
