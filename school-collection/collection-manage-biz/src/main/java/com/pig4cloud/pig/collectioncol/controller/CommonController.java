package com.pig4cloud.pig.collectioncol.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.admin.api.vo.SysUserVO;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.BASE64DecodedMultipartFile;
import com.pig4cloud.pig.common.core.util.OCRDemo;
import com.pig4cloud.pig.common.core.util.OSSUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import com.pig4cloud.pig.school.api.feign.RemoteRecipeService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * h5数据采集公共方法
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/common")
public class CommonController {

  private RemoteUserService remoteUserService;

  private RemoteRecipeService remoteRecipeService;

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();	//密码类

  /**
   * 获取省信息
   * @return
   */
  @GetMapping("/getProvinceForCol")
  public R getProvinceForCol() {
    R dictByTypeW = remoteUserService.getProvinceForCol(SecurityConstants.FROM_IN);
    return new R<>(dictByTypeW.getData());
  }

  /**
   * 获取市信息
   * @param provice
   * @return
   */
  @GetMapping("/getCityForCol")
  public R getCityForCol(@RequestParam(value = "provice") String provice) {
    R dictByTypeW = remoteUserService.getCityForCol(provice, SecurityConstants.FROM_IN);
    return new R<>(dictByTypeW.getData());
  }

  /**
   * 获取区信息
   * @param district
   * @return
   */
  @GetMapping("/getDistrictForCol")
  public R getDistrictForCol(@RequestParam(value = "district") String district) {
    R dictByTypeW = remoteUserService.getDistrictForCol(district, SecurityConstants.FROM_IN);
    return new R<>(dictByTypeW.getData());
  }

}
