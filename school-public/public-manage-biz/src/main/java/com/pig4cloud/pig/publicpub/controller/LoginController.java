package com.pig4cloud.pig.publicpub.controller;

import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.UUID;


/**
 * 登陆注册模块
 *
 * @author - Mr_Miao
 * @date 2019-09-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/public")
public class LoginController {

  private final RemoteUserService remoteUserService;
  private final RemoteSchoolService remoteSchoolService;
  private final RemotePortalService remotePortalService;

  private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();	//密码类

  /**
   * 用户注册
   * @param pubPhone
   * @param pubPassword
   * @return
   */
  @PostMapping("/regist")
  public R registerPubuser(@RequestParam(value = "pubPhone") String pubPhone,@RequestParam(value = "pubPassword") String pubPassword) {
    R r = new R();
    R verify = remotePortalService.registerPubuser(pubPhone,SecurityConstants.FROM_IN);
    if(verify.getData()!=null){
      r.setCode(1);
      r.setMsg("手机号已注册");
      return r;
    }
    // 插入公共用户
    PublicUser publicUser = new PublicUser();
    publicUser.setPubPhone(pubPhone);
    publicUser.setDelFlag(CommonConstants.STATUS_NORMAL);
    publicUser.setPubPassword(ENCODER.encode(pubPassword));	//密码加密
    publicUser.setNickName("用户"+ pubPhone);
    return new R<>(remotePortalService.savePublicuser(publicUser,SecurityConstants.FROM_IN));
  }

  /**
   * 用户登陆
   * @param pubPhone
   * @param pubPassword
   * @return
   */
  @PostMapping("/login")
  public R publicLogin(@RequestParam(value = "pubPhone") String pubPhone,@RequestParam(value = "pubPassword") String pubPassword) {
    R r = new R();
    PublicUser publicUser = remotePortalService.queryInfo(pubPhone,SecurityConstants.FROM_IN);
    if(publicUser==null){
      r.setCode(1);
      r.setMsg("未存在该手机号");
      return r;
    }
    if (!ENCODER.matches(pubPassword, publicUser.getPubPassword())) {
      return new R<>(CommonConstants.FAIL,null,"账号密码错误");
    }
    String token = UUID.randomUUID().toString().replaceAll("-", "");
    publicUser.setToken(token);
    remotePortalService.updatePublicuser(publicUser,SecurityConstants.FROM_IN);
    return new R<>(publicUser);
  }

  /**
   * 查询公共用户个人信息
   * @param userId
   * @return
   */
  @GetMapping("/getSchoolByPubId")
  public R getSchoolByPubId(@RequestParam(value = "userId") Integer userId) {
    return new R<>(remotePortalService.getSchoolByPubId(userId, SecurityConstants.FROM_IN));
  }
}
