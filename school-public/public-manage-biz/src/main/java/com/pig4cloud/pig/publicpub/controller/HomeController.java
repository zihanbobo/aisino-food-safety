package com.pig4cloud.pig.publicpub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import java.util.Map;


/**
 * 公共用户信息
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {

	private final RemoteUserService remoteUserService;
	private final RemoteSchoolService remoteSchoolService;
	private final RemotePortalService remotePortalService;

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();	//密码类


}
