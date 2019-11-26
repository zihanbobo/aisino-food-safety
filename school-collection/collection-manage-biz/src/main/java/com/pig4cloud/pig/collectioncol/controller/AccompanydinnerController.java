package com.pig4cloud.pig.collectioncol.controller;

import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.school.api.entity.account.AccompanyDinner;
import com.pig4cloud.pig.school.api.feign.RemoteRecipeService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * h5数据采集陪餐信息
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/accompanydinner")
public class AccompanydinnerController {

  private RemoteRecipeService remoteRecipeService;

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();	//密码类


  /**
   * 新增陪餐记录
   * @param accompanyDinner
   * @return
   */
  @PostMapping("/addAccompanyDinner")
  public R addAccompanyDinner(@RequestBody AccompanyDinner accompanyDinner) {
    return remoteRecipeService.addAccompanyDinner(accompanyDinner,SecurityConstants.FROM_IN);
  }


  /**
   * h5数据采集查询该学校的所有陪餐记录
   * @param size
   * @param current
   * @param schoolId
   * @return
   */
  @GetMapping("/getAllAccompanyDinner")
  public R getAllAccompanyDinner(
    @RequestParam(value = "size",required = false) Integer size,
    @RequestParam(value = "current",required = false) Integer current,
    @RequestParam(value = "schoolId") Integer schoolId,
    @RequestParam(value = "adTime",required = false) String adTime
    ) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
    return remoteRecipeService.getAllAccompanyDinner(size,current,schoolId,adTime,SecurityConstants.FROM_IN);
  }

  /**
   * h5端根据id查询当前陪餐信息
   * @param id
   * @return
   */
  @GetMapping("/getAccompanyDinnerById")
  public R getAccompanyDinnerById(@RequestParam(value = "id") Integer id) {
    return remoteRecipeService.getAccompanyDinnerById(id, SecurityConstants.FROM_IN);
  }

  /**
   * h5修改陪餐信息
   * @param
   * @return
   */
  @PostMapping("/updateAccompanyDinner")
  public R updateAccompanyDinner(@RequestBody AccompanyDinner accompanyDinner) {
    R<Boolean> booleanR = remoteRecipeService.updateAccompanyDinner(accompanyDinner, SecurityConstants.FROM_IN);
    //Boolean data = booleanR.getData();
    //return new R<>(data);
    return booleanR;
  }

  /**
   * h5删除用户信息
   * @param
   * @return
   */
  @GetMapping("/delAccompanyDinner")
  public R delAccompanyDinner(@RequestParam(value = "id") Integer id) {
    R booleanR = remoteRecipeService.delAccompanyDinner(id, SecurityConstants.FROM_IN);
//    Boolean data = booleanR.getData();
//    return new R<>(data);
    return booleanR;
  }

}
