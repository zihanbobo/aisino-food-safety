package com.pig4cloud.pig.collectioncol.controller;

import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.school.api.entity.account.AccompanyDinner;
import com.pig4cloud.pig.school.api.entity.account.Ultravioletlight;
import com.pig4cloud.pig.school.api.feign.RemoteRecipeService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


/**
 * h5数据采集
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ultravioletLight")
public class UltravioletLightController {

  private RemoteRecipeService remoteRecipeService;

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();	//密码类


  /**
   * 新增紫外线灯设备信息
   * @param ultravioletlight
   * @return
   */
  @PostMapping("/addUltravioletLight")
  public R addUltravioletLight(@RequestBody Ultravioletlight ultravioletlight) {
    return remoteRecipeService.addUltravioletLight(ultravioletlight,SecurityConstants.FROM_IN);
  }

  /**
   * h5端查询所有紫外线灯设备信息
   * @return
   */
  @GetMapping("/getLightPage")
  public R getLightPage(
  @RequestParam(value = "size",required = false) Integer size,
  @RequestParam(value = "current",required = false)Integer current,
  @RequestParam(value = "schoolId")Integer schoolId,
  @RequestParam(value = "useTime",required = false)String useTime) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
    return remoteRecipeService.getLightPage(size,current,schoolId,useTime,SecurityConstants.FROM_IN);
  }

  /**
   * h5端根据id查询当前紫外线灯设备信息
   * @param id
   * @return
   */
  @GetMapping("/getUltravioletLightById")
  public R getUltravioletLightById(@RequestParam(value = "id") Integer id) {
    return remoteRecipeService.getUltravioletLightById(id, SecurityConstants.FROM_IN);
  }
  /**
   * 修改紫外线灯设备信息
   * @param ultravioletlight
   * @return
   */
  @PostMapping("/updateUltravioletLight")
  public R updateUltravioletLight(@RequestBody Ultravioletlight ultravioletlight) {
    return remoteRecipeService.updateUltravioletLight(ultravioletlight,SecurityConstants.FROM_IN);
  }

  /**
   * 删除紫外线灯
   * @param id
   * @return
   */
  @GetMapping("/delUltravioletLight")
  public R delUltravioletLight(@RequestParam(value = "id") Integer id) {
    return remoteRecipeService.delUltravioletLight(id,SecurityConstants.FROM_IN);
  }

}
