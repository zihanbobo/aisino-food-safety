package com.pig4cloud.pig.publicpub.controller;

import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import com.pig4cloud.pig.school.api.feign.RemoteRecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 食谱
 */

@RestController
@AllArgsConstructor
@RequestMapping("/public")
public class RecipesController {

  private final RemoteRecipeService remoteRecipeService;
  private final RemotePortalService remotePortalService;


  @PostMapping("/recipesMain")
  public R recipesMain(@RequestParam(value = "userId") Integer userId) {
    List schoolList = remotePortalService.checkSchoolByPubId(userId, "2", SecurityConstants.FROM_IN);
    if (schoolList != null && schoolList.size() > 0) {
      List list = new ArrayList();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      for (int i = 0; i < schoolList.size(); i++) {
        Map map = new HashMap();
        Map schoolMap = (Map) schoolList.get(i);
        R<Object> menu = remoteRecipeService.getDailyRecipe(3, 1, Integer.valueOf(schoolMap.get("schoolId").toString()), "2019-09-04", "", SecurityConstants.FROM_IN);
        map.put("schoolId", schoolMap.get("schoolId"));
        map.put("schoolName", schoolMap.get("schoolName"));
        map.put("recipeDate", "2019-09-04");
        map.put("recipes", menu.getData());
        map.put("comments", remotePortalService.getAppCommentCount(Integer.valueOf(schoolMap.get("schoolId").toString()), "3", SecurityConstants.FROM_IN));
        list.add(map);
      }
      return new R(list);
    } else {
      return new R<>(CommonConstants.FAIL, null, "未绑定学校！");
    }
  }


  /**
   * 每日食谱信息
   *
   * @param size
   * @param current
   * @param schoolId
   * @param rdDate
   * @param rdType
   * @return
   */
  @PostMapping("/getDailyRecipe")
  public R getDailyRecipe(@RequestParam(value = "size") Integer size,
                          @RequestParam(value = "current") Integer current,
                          @RequestParam(value = "schoolId") Integer schoolId,
                          @RequestParam(value = "rdDate") String rdDate,
                          @RequestParam(value = "rdType") String rdType) {
    R<Object> sourceByDay = remoteRecipeService.getDailyRecipe(size, current, schoolId, rdDate, rdType, SecurityConstants.FROM_IN);
    return sourceByDay;
  }

  /**
   * 食材追溯-人员信息
   *
   * @param size
   * @param current
   * @param schoolId
   * @param rdDate
   * @param position
   * @return
   */
  @PostMapping("/getPersonnelInfo")
  public R getPersonnelInfo(@RequestParam(value = "size") Integer size,
                                @RequestParam(value = "current") Integer current,
                                @RequestParam(value = "schoolId") Integer schoolId,
                                @RequestParam(value = "rdDate") String rdDate,
                                @RequestParam(value = "position") String position) {

    if ("1".equals(position)){
      return remoteRecipeService.getPersonCook(size, current, schoolId, rdDate, SecurityConstants.FROM_IN);
    }else{
      return remoteRecipeService.getPersonnelInfo(size, current, schoolId, position, SecurityConstants.FROM_IN);
    }
  }


  /**
   * 食材追溯-人员信息-厨房人员
   *
   * @param size
   * @param current
   * @param schoolId
   * @param rdDate
   * @param rdType
   * @return
   */
  @PostMapping("/getKitchenStaff")
  public R getKitchenStaff(@RequestParam(value = "size") Integer size,
                           @RequestParam(value = "current") Integer current,
                           @RequestParam(value = "schoolId") Integer schoolId,
                           @RequestParam(value = "rdDate") String rdDate,
                           @RequestParam(value = "rdType") String rdType) {
    R<Object> result = remoteRecipeService.getPersonCook(size, current, schoolId, rdDate, SecurityConstants.FROM_IN);
    return result;
  }

  /**
   * 食材追溯-人员信息-食品安全员
   *
   * @param size
   * @param current
   * @param schoolId
   * @param rdDate
   * @param rdType
   * @return
   */
  @PostMapping("/getPersonPosition")
  public R getPersonPosition(@RequestParam(value = "size") Integer size,
                             @RequestParam(value = "current") Integer current,
                             @RequestParam(value = "schoolId") Integer schoolId,
                             @RequestParam(value = "rdDate") String rdDate,
                             @RequestParam(value = "rdType") String rdType) {
    R<Object> result = remoteRecipeService.getPersonPosition(size, current, schoolId, rdDate, SecurityConstants.FROM_IN);
    return result;
  }

  /**
   * 食材追溯-人员信息-陪餐人员
   *
   * @param size
   * @param current
   * @param schoolId
   * @param rdDate
   * @param rdType
   * @return
   */
  @PostMapping("/getPersonAccompany")
  public R getPersonAccompany(@RequestParam(value = "size") Integer size,
                              @RequestParam(value = "current") Integer current,
                              @RequestParam(value = "schoolId") Integer schoolId,
                              @RequestParam(value = "rdDate") String rdDate,
                              @RequestParam(value = "rdType") String rdType) {
    R<Object> result = remoteRecipeService.getPersonAccompany(size, current, schoolId, rdDate, SecurityConstants.FROM_IN);
    return result;
  }

  /**
   * 食材追溯-供应商信息
   *
   * @param size
   * @param current
   * @param schoolId
   * @param rdDate
   * @param rdType
   * @return
   */
  @PostMapping("/getSupplierInfo")
  public R getSupplierInfo(@RequestParam(value = "size") Integer size,
                           @RequestParam(value = "current") Integer current,
                           @RequestParam(value = "schoolId") Integer schoolId,
                           @RequestParam(value = "rdDate") String rdDate,
                           @RequestParam(value = "rdType") String rdType ) {
    R<Object> result = remoteRecipeService.getSupplierInfo(size, current, schoolId, rdDate, SecurityConstants.FROM_IN);
    return result;
  }

  /**
   * 食材追溯-留样信息
   *
   * @return
   */
  @PostMapping("/getSampleInfo")
  public R getSampleInfo(@RequestParam(value = "size", required = false) Integer size,
                         @RequestParam(value = "current", required = false) Integer current,
                         @RequestParam(value = "dailyId") Integer dailyId,
                         @RequestParam(value = "sourceId") Integer sourceId) {
    if (size == null) {
      size = 10;
    }
    if (current == null) {
      current = 1;
    }
    R<Object> result = remoteRecipeService.getSampleInfo(size, current, dailyId, sourceId, SecurityConstants.FROM_IN);
    return result;
  }

  /**
   * 食材追溯-食材信息、留样信息
   *
   * @return
   */
  @PostMapping("/getFoodIngredients")
  public R getFoodIngredients(@RequestParam(value = "size") Integer size,
                              @RequestParam(value = "current") Integer current,
                              @RequestParam(value = "dailyId") Integer dailyId,
                              @RequestParam(value = "sourceId") Integer sourceId) {
    Map map = new HashMap();
    // 留样信息
    R<Object> result1 = remoteRecipeService.getSampleInfo(size, current, dailyId, sourceId, SecurityConstants.FROM_IN);
    result1.getData();
    map.put("sampleInfo",result1.getData());
    R<Object> result2 = remoteRecipeService.getFoodIngredients(size, current, sourceId, SecurityConstants.FROM_IN);
    map.put("foodIngredients",result2.getData());
    return new R(map);
  }

}
