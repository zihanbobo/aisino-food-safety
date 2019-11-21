package com.pig4cloud.pig.portalpor.controller;

import com.aliyun.oss.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.netflix.discovery.util.StringUtil;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/publicuser")
public class PublicUserController {

	private final RemoteUserService remoteUserService;
	private final RemoteSchoolService remoteSchoolService;
	private final RemotePortalService remotePortalService;

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();	//密码类

	/**
	 * 用户注册
	 * @return
	 */
	@PostMapping("/registerPubuser")
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
		return new R<>(remotePortalService.savePublicuser(publicUser,SecurityConstants.FROM_IN));
	}


	/**
	 * 用户登陆
	 * @return
	 */
	@PostMapping("/publicLogin")
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
		return new R<>(publicUser);
	}

	/**
	 * 重置密码
	 * @return
	 */
	@PostMapping("/publicResetPass")
	public R publicUpdatePass(
	            @RequestParam(value = "pubPhone",required = false) String pubPhone,
		 				 @RequestParam(value = "oldPassword",required = false) String oldPassword,
						 @RequestParam(value = "newPassword") String newPassword) {
		R r = new R();
		PublicUser publicUser = remotePortalService.queryInfo(pubPhone,SecurityConstants.FROM_IN);
		if(publicUser==null){
			r.setCode(1);
			r.setMsg("未存在该手机号");
			return r;
		}
		if(!StringUtils.isNullOrEmpty(oldPassword)){
      if (!ENCODER.matches(oldPassword, publicUser.getPubPassword())) {
        return new R<>(CommonConstants.FAIL,null,"该账号原密码错误");
      }
    }
		// 修改为新密码
		publicUser.setPubPassword(ENCODER.encode(newPassword));	//密码加密
		return new R<>(remotePortalService.updatePublicuser(publicUser,SecurityConstants.FROM_IN));
	}

  /**
   * 修改密码
   * @return
   */
  @PostMapping("/publicUpdatePass")
  public R publicUpdatePass(
    @RequestParam(value = "userId") Integer userId,
    //@RequestParam(value = "pubPhone",required = false) String pubPhone,
    @RequestParam(value = "oldPassword",required = false) String oldPassword,
    @RequestParam(value = "newPassword") String newPassword) {
    R r = new R();

    PublicUser byPubId = remotePortalService.getByPubId(userId, SecurityConstants.FROM_IN);
    if(byPubId==null){
      r.setCode(1);
      r.setMsg("该用户不存在");
      return r;
    }
    if(!StringUtils.isNullOrEmpty(oldPassword)){
      if (!ENCODER.matches(oldPassword, byPubId.getPubPassword())) {
        return new R<>(CommonConstants.FAIL,null,"该账号原密码错误");
      }
    }
    // 修改为新密码
    byPubId.setPubPassword(ENCODER.encode(newPassword));	//密码加密
    return new R<>(remotePortalService.updatePublicuser(byPubId,SecurityConstants.FROM_IN));
  }
 /* *//**
   * 重置密码
   *//*
  @PostMapping("/publicUpdatePass")
  public R publicUpdatePass(@RequestParam(value = "pubPhone") String pubPhone,
                            @RequestParam(value = "newPassword") String newPassword) {
    R r = new R();
    PublicUser publicUser = remotePortalService.queryInfo(pubPhone,SecurityConstants.FROM_IN);
    if(publicUser==null){
      r.setCode(1);
      r.setMsg("未存在该手机号");
      return r;
    }
    if (!ENCODER.matches(oldPassword, publicUser.getPubPassword())) {
      return new R<>(CommonConstants.FAIL,null,"该账号原密码错误");
    }
    // 修改为新密码
    publicUser.setPubPassword(ENCODER.encode(newPassword));	//密码加密
    return new R<>(remotePortalService.updatePublicuser(publicUser,SecurityConstants.FROM_IN));
  }
*/
  /**
   * 更换手机号
   * @return
   */
  @PostMapping("/publicUpdatePhone")
  public R publicUpdatePass(
    @RequestParam(value = "userId") Integer userId,
    @RequestParam(value = "pubPhone") String pubPhone) {
    R r = new R();
    R verify = remotePortalService.registerPubuser(pubPhone,SecurityConstants.FROM_IN);
    if(verify.getData()!=null){
      r.setCode(1);
      r.setMsg("手机号已注册");
      return r;
    }
    PublicUser byPubId = remotePortalService.getByPubId(userId, SecurityConstants.FROM_IN);
    byPubId.setPubPhone(pubPhone);
    return new R<>(remotePortalService.updatePublicuser(byPubId,SecurityConstants.FROM_IN));
  }

	/**
	 * 查询公共用户个人信息
	 * @return
	 */
	@GetMapping("/getSchoolByPubId")
	public R getSchoolByPubId(@RequestParam(value = "userId") Integer userId) {
		return new R<>(remotePortalService.getSchoolByPubId(userId, SecurityConstants.FROM_IN));
	}

	/**
	 * 公众用户根据学校标识码解除绑定学校
	 * @return
	 */
	@PostMapping("/unBindSchool")
	public R bindSchool(@RequestParam(value = "serviceCode") String serviceCode,
						@RequestParam(value = "type") String type,
						@RequestParam(value = "userId", required = false) Integer userId) {
		R r = new R();
		R verify = remoteSchoolService.verify(serviceCode,type,SecurityConstants.FROM_IN);
		if(verify.getCode()==1){
			r.setCode(1);
			r.setMsg("服务码错误");
			return r;
		}
		R byId = remotePortalService.getById(userId, SecurityConstants.FROM_IN);
		if(byId.getData()==null){
			r.setCode(1);
			r.setMsg("未找到与该userId匹配的公共用户信息");
			return r;
		}
		LinkedHashMap linkedHashMap = (LinkedHashMap) verify.getData();
		int schoolIdR = Integer.parseInt(String.valueOf(linkedHashMap.get("id")));
		// 首先验证该用户是否与此学校绑定
		Boolean isBindSchool = remotePortalService.isBindSchool(schoolIdR,type,userId, SecurityConstants.FROM_IN);
		if(!isBindSchool){
			r.setCode(1);
			r.setMsg("该用户未与此学校绑定");
			return r;
		}
		return new R<>(remotePortalService.unBindSchool(schoolIdR,type,userId,SecurityConstants.FROM_IN));
	}

	/**
	 * @return
	 * 修改公共用户信息
	 */
	@PostMapping("/publicUpdateInfo")
	public R publicUpdateInfo(@RequestParam(value = "userId") Integer userId,
						      @RequestParam(value = "pubUrl", required = false) String pubUrl,
							  @RequestParam(value = "nickName") String nickName,
							  @RequestParam(value = "pubName") String pubName) {
		PublicUser publicUser = remotePortalService.getByPubId(userId, SecurityConstants.FROM_IN);
		publicUser.setPubPic(pubUrl);
		publicUser.setNickName(nickName);
		publicUser.setPubName(pubName);
		return new R<>(remotePortalService.updatePublicuser(publicUser,SecurityConstants.FROM_IN));
	}

  /**
   * @return
   * 根据用户id查询对应绑定的学校列表
   */
  @GetMapping("/checkSchoolByPubId")
  public R publicUpdateInfo(@RequestParam(value = "userId") Integer userId,
                               @RequestParam(value = "type") String type) {
    return new R<>(remotePortalService.checkSchoolByPubId(userId,type,SecurityConstants.FROM_IN));
  }

}
