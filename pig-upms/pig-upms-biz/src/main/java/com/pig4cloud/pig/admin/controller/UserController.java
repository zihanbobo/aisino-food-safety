/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pig4cloud.pig.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysDict;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.entity.SysUserRole;
import com.pig4cloud.pig.admin.api.vo.SysUserVO;
import com.pig4cloud.pig.admin.api.vo.UserVO;
import com.pig4cloud.pig.admin.service.SysDictService;
import com.pig4cloud.pig.admin.service.SysUserRoleService;
import com.pig4cloud.pig.admin.service.SysUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.school.api.entity.School;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author lengleng
 * @date 2019/2/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final SysUserService userService;
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	// Feign学校服务
  private final RemoteSchoolService remoteSchoolService;
  private final SysUserRoleService sysUserRoleService;
  private final SysUserService sysUserService;
  private final SysDictService sysDictService;

	/**
	 * 获取当前用户全部信息
	 *
	 * @return 用户信息
	 */
	@GetMapping(value = {"/info"})
	public R info() {
		String username = SecurityUtils.getUser().getUsername();
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
			.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new R<>(Boolean.FALSE, "获取当前用户信息失败");
		}
		return new R<>(userService.getUserInfo(user));
	}

	/**
	 * 获取指定用户全部信息
	 *
	 * @return 用户信息
	 */
	@Inner
	@GetMapping("/info/{username}")
	public R info(@PathVariable String username) {
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
			.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new R<>(Boolean.FALSE, String.format("用户信息为空 %s", username));
		}
		return new R<>(userService.getUserInfo(user));
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	public R user(@PathVariable Integer id) {

	  return new R<>(userService.getUserVoById(id));
	}

	/**
	 * 根据用户名查询用户信息
	 *
	 * @param username 用户名
	 * @return
	 */
	@GetMapping("/details/{username}")
	public R user(@PathVariable String username) {
		SysUser condition = new SysUser();
		condition.setUsername(username);
		return new R<>(userService.getOne(new QueryWrapper<>(condition)));
	}


	/**
	 * 删除用户信息
	 *
	 * @param id ID
	 * @return R
	 */
	@SysLog("删除用户信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_user_del')")
	public R userDel(@PathVariable Integer id) {
    SysUser sysUser = userService.getById(id);
    remoteSchoolService.schoolUserId(sysUser.getUserId(),sysUser.getUnionId(),"2", SecurityConstants.FROM_IN);
		return new R<>(userService.removeUserById(sysUser));
	}

	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return success/false
	 */
//	@SysLog("添加用户")
//	@PostMapping
//	@PreAuthorize("@pms.hasPermission('sys_user_add')")
//	public R user(@RequestBody UserDTO userDto) {
//		return new R<>(userService.saveUser(userDto));
//	}

	@SysLog("添加用户")
	@PostMapping
//	@PreAuthorize("@pms.hasPermission('sys_user_add')")
	public R user(@RequestBody UserDTO userDto) {
    // 权限控制
    String username = SecurityUtils.getUser().getUsername();	// 当前登录用户昵称
    SysUser user = userService.getOne(Wrappers.<SysUser>query()
      .lambda().eq(SysUser::getUsername, username));
    UserInfo userInfo = userService.getUserInfo(user);
    String userType = userInfo.getSysUser().getUserType();
    String isAdmin = userInfo.getSysUser().getIsAdmin();

    // 用户类型等于校园端&超级管理员则为校园端。
    if("2".equals(userType)&&"1".equals(isAdmin)) {
      userDto.setUserType("2");
      userDto.setUnionId(userInfo.getSysUser().getUnionId());
    }
    //如果是航信管理端新增的学校超级管理员
    if(userDto.getUnionId()!=null){
      userDto.setUserType("2");
    }
    SysUser sysUser = new SysUser();
    BeanUtils.copyProperties(userDto, sysUser);
    sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
    sysUser.setPassword(ENCODER.encode("123456"));
    userService.save(sysUser);
    List<SysUserRole> userRoleList = userDto.getRole()
      .stream().map(roleId -> {
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(sysUser.getUserId());
        userRole.setRoleId(roleId);
        return userRole;
      }).collect(Collectors.toList());
    boolean b = sysUserRoleService.saveBatch(userRoleList);

    if(sysUser.getUnionId()!=null){
      // 处理绑定学校
      remoteSchoolService.schoolUserId(sysUser.getUserId(),sysUser.getUnionId(),"1", SecurityConstants.FROM_IN);
    }
    return new R<>(b);
	}


	/**
	 * 更新用户信息
	 *
	 * @param userDto 用户信息
	 * @return R
	 */
	@SysLog("更新用户信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public R updateUser(@Valid @RequestBody UserDTO userDto) {
    Integer userId = userDto.getUserId();
    SysUser oldUser = userService.getById(userId);
    Integer oldUnionId = oldUser.getUnionId();
    Integer newUnionId = userDto.getUnionId();
    String userType = oldUser.getUserType();


    // 有用户id以及学校绑定的id
    if(!"null".equals(String.valueOf(oldUnionId))&&!"null".equals(String.valueOf(newUnionId))){
      // 先解绑
      if(oldUnionId!=newUnionId){
        remoteSchoolService.schoolUserId(userDto.getUserId(),oldUnionId,"2", SecurityConstants.FROM_IN);
        remoteSchoolService.schoolUserId(userDto.getUserId(),newUnionId,"1", SecurityConstants.FROM_IN);
      }
    }
    return new R<>(userService.updateUser(userDto));
	}

	/**
	 * 分页查询用户
	 *
	 * @param page    参数集
	 * @param userDTO 查询参数列表
	 * @return 用户集合
	 */
	@GetMapping("/page")
	public R getUserPage(Page page, UserDTO userDTO) {
    // 权限控制
    String username = SecurityUtils.getUser().getUsername();	// 当前登录用户昵称
    SysUser user = userService.getOne(Wrappers.<SysUser>query()
      .lambda().eq(SysUser::getUsername, username));
    UserInfo userInfo = userService.getUserInfo(user);
    String userType = userInfo.getSysUser().getUserType();
    String isAdmin = userInfo.getSysUser().getIsAdmin();
    // 判断类型(航信校园)
    if("1".equals(userType)&&!"1".equals(isAdmin)){
      userDTO.setUserType("1");
    }else{
      userDTO.setUserType("2");
      userDTO.setUnionId(userInfo.getSysUser().getUnionId());
    }
		return new R<>(userService.getUserWithRolePage(page, userDTO));
	}

	/**
	 * 修改个人信息
	 *
	 * @param userDto userDto
	 * @return success/false
	 */
	@SysLog("修改个人信息")
	@PutMapping("/edit")
	public R updateUserInfo(@Valid @RequestBody UserDTO userDto) {
    // 如果是超级管理员在操作
    if(StringUtils.isEmpty(userDto.getIsAdmin())){
      Integer userId = userDto.getUserId(); // 当前用户的id(固定不变的)
      Integer unionId = userDto.getUnionId();// 所关联的学校id(可能会变化)
    }

		return userService.updateUserInfo(userDto);
	}

	/**
	 * @param username 用户名称
	 * @return 上级部门用户列表
	 */
	@GetMapping("/ancestor/{username}")
	public R listAncestorUsers(@PathVariable String username) {
		return new R<>(userService.listAncestorUsersByUsername(username));
	}


	/**
	 * 账号密码
	 *
	 * @return 账号密码
	 */
	@PostMapping("/homeLogin")
	public R homeLogin(@RequestBody String username,@RequestBody String password) {
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
			return new R<>(CommonConstants.FAIL,null,"未传入账号密码");
		}
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
			.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new R<>(CommonConstants.FAIL,null,String.format("用户信息为空 %s", username));
		}
		if (ENCODER.matches(password, user.getPassword())) {
			return new R<>(userService.getUserInfo(user));
		}else{
			return new R<>(CommonConstants.FAIL,null,"账号密码错误");
		}
	}

  /**
   * 分页查询用户
   *
   * @param page    参数集
   * @param userDTO 查询参数列表
   * @return 用户集合
   */
  @GetMapping("/pageCook")
  public R getUserPageCook(Page page, UserDTO userDTO) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = info(username);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    userDTO.setDelFlag("0");
    userDTO.setUserType("2");
    userDTO.setUnionId(schoolId);
    userDTO.setPosition("1");// 大厨
    return new R<>(userService.getUserWithRolePage(page, userDTO));
  }

  /**
   * 分页查询陪餐人员
   *
   * @param page    参数集
   * @param userDTO 查询参数列表
   * @return 用户集合
   */
  @GetMapping("/getUserPageDinner")
  public R getUserPageDinner(Page page, UserDTO userDTO) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = info(username);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    userDTO.setDelFlag("0");
    userDTO.setUserType("2");
    userDTO.setUnionId(schoolId);
    userDTO.setPosition("3");//
    return new R<>(userService.getUserWithRolePage(page, userDTO));
  }
  /**
   * 分页查询学校人员(采购计划)
   *
   * @param page    参数集
   * @param userDTO 类型
   * @return 用户集合
   */
  @GetMapping("/getSchoolUser")
  public R getSchoolUser(Page page, UserDTO userDTO) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = info(username);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer schoolId = userInfo.getSysUser().getUnionId();
//    UserDTO userDTO = new UserDTO();
    userDTO.setDelFlag("0");
    userDTO.setUserType("2");
    userDTO.setUnionId(schoolId);
//    if(!StringUtils.isEmpty(position)){
//      userDTO.setPosition(position);
//    }
    return new R<>(userService.getUserWithRolePage(page, userDTO));
  }


  /**
   * 分页查询用户(学校超级管理员)
   *
   * @param page    参数集
   * @param userDTO 查询参数列表
   * @return 用户集合
   */
  @GetMapping("/pageUserSchool")
  public R getPageUserSchool(Page page, UserDTO userDTO) {
    // 权限控制
    String username = SecurityUtils.getUser().getUsername();	// 当前登录用户昵称
    SysUser user = userService.getOne(Wrappers.<SysUser>query()
      .lambda().eq(SysUser::getUsername, username));
    UserInfo userInfo = userService.getUserInfo(user);
    String userType = userInfo.getSysUser().getUserType();
    String isAdmin = userInfo.getSysUser().getIsAdmin();
    userDTO.setUserType("2");
    userDTO.setIsAdmin("1");
    return new R<>(userService.getUserWithRolePage(page, userDTO));
  }
  /**
   * 分页查询用户
   *
   * @param page    参数集
   * @param userDTO 查询参数列表
   * @return 用户集合
   */
  @GetMapping("/pageMarket")
  public R getUserPageMarket(Page page, UserDTO userDTO) {
    // 权限控制
    String username = SecurityUtils.getUser().getUsername();	// 当前登录用户昵称
    SysUser user = userService.getOne(Wrappers.<SysUser>query()
      .lambda().eq(SysUser::getUsername, username));
    UserInfo userInfo = userService.getUserInfo(user);
    String userType = userInfo.getSysUser().getUserType();
    String isAdmin = userInfo.getSysUser().getIsAdmin();
    userDTO.setUserType("3");
    userDTO.setRoleCode("shichangjianguan");
    return new R<>(userService.getUserWithRolePageMarket(page, userDTO));
  }

  /**
   * 分页查询用户
   *
   * @param page    参数集
   * @param userDTO 查询参数列表
   * @return 用户集合
   */
  @GetMapping("/pageEdu")
  public R getUserPageEdu(Page page, UserDTO userDTO) {
    // 权限控制
    String username = SecurityUtils.getUser().getUsername();	// 当前登录用户昵称
    SysUser user = userService.getOne(Wrappers.<SysUser>query()
      .lambda().eq(SysUser::getUsername, username));
    UserInfo userInfo = userService.getUserInfo(user);
    String userType = userInfo.getSysUser().getUserType();
    String isAdmin = userInfo.getSysUser().getIsAdmin();
    userDTO.setUserType("3");
    userDTO.setRoleCode("jiaoyujuguanli");
    return new R<>(userService.getUserWithRolePageMarket(page, userDTO));
  }

  /**
   * 根据手机号查询学校用户信息(h5数据采集登录使用)
   *
   * @param pubPhone 手机号
   * @return
   */
  @Inner
  @GetMapping("/details2/{pubPhone}")
  public SysUserVO queryInfo(@PathVariable String pubPhone) {
    SysUser sysUser = new SysUser();
    sysUser.setPhone(pubPhone);
    sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
    sysUser.setLockFlag("0");//0正常 9锁定
    //有数据的sysuser
    SysUser sysUser1 = sysUserService.getOne(new QueryWrapper<>(sysUser));
    Integer unionId = sysUser1.getUnionId();
    R schoolR = remoteSchoolService.getById(unionId, SecurityConstants.FROM_IN);
    LinkedHashMap linkedHashMap = (LinkedHashMap) schoolR.getData();
    //获取学校名称
    String schName = (String) linkedHashMap.get("schName");
    SysUserVO sysUserVO = new SysUserVO();
    BeanUtils.copyProperties(sysUser1,sysUserVO);
    sysUserVO.setSchoolName(schName);
    String label = null;
    if(!StringUtils.isEmpty(sysUserVO.getPosition())){
      SysDict sysDict = sysDictService.getOne(Wrappers.<SysDict>query().lambda()
        .eq(SysDict::getType, "position")
        .eq(SysDict::getValue, sysUserVO.getPosition()));
      label= sysDict.getLabel();
    }
    sysUserVO.setPositionName(label);
    //int schoolIdR = Integer.parseInt(String.valueOf(linkedHashMap.get("id")));
    return sysUserVO;
  }

  /**
   * 根据手机号查询学校用户信息(h5数据采集登录使用)
   *
   * @param pubPhone 手机号
   * @return
   */
  @Inner
  @GetMapping("/details3")
  public SysUserVO queryInfo2(@RequestParam(value = "pubPhone")String pubPhone) {
    SysUser sysUser = new SysUser();
    sysUser.setPhone(pubPhone);
    sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
    sysUser.setLockFlag("0");//0正常 9锁定
    //有数据的sysuser
    SysUser sysUser1 = sysUserService.getOne(new QueryWrapper<>(sysUser));
    Integer unionId = sysUser1.getUnionId();
    R schoolR = remoteSchoolService.getById(unionId, SecurityConstants.FROM_IN);
    LinkedHashMap linkedHashMap = (LinkedHashMap) schoolR.getData();
    //获取学校名称
    String schName = (String) linkedHashMap.get("schName");
    SysUserVO sysUserVO = new SysUserVO();
    BeanUtils.copyProperties(sysUser1,sysUserVO);
    sysUserVO.setSchoolName(schName);
    String label = null;
    if(!StringUtils.isEmpty(sysUserVO.getPosition())){
      SysDict sysDict = sysDictService.getOne(Wrappers.<SysDict>query().lambda()
        .eq(SysDict::getType, "position")
        .eq(SysDict::getValue, sysUserVO.getPosition()));
      label= sysDict.getLabel();
    }
    sysUserVO.setPositionName(label);
    //int schoolIdR = Integer.parseInt(String.valueOf(linkedHashMap.get("id")));
    return sysUserVO;
  }

  /**
   * 分页查询用户(校园端每日晨检)
   *
   * @param page    参数集
   * @param userDTO 查询参数列表
   * @return 用户集合
   */
  @GetMapping("/pageMorningCheck")
  public R pageMorningCheck(Page page, UserDTO userDTO) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = info(username);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    userDTO.setDelFlag("0");
    userDTO.setUserType("2");
    userDTO.setUnionId(schoolId);
    userDTO.setNotFindIsAdmin("1");// 大厨
    return new R<>(userService.getUserWithRolePage(page, userDTO));
  }

  //getAllHuman
  // h5数据采集 根据学校id学校用户列表查询
  @Inner
  @GetMapping("/getAllHuman")
  public R<Object> getAllHuman(@RequestParam(value = "size")Integer size,
                               @RequestParam(value = "current")Integer current,
                               @RequestParam(value = "schoolId")Integer schoolId,
                               @RequestParam(value = "realName",required = false)String realName,
                               @RequestParam(value = "position",required = false)String position) {
    // 根据输入参数组装分页类,
    Page page = new Page<>();
    page.setSize(new Long((long)size));
    page.setCurrent(new Long((long)current));
    UserDTO userDTO = new UserDTO();
    userDTO.setUserType("2");
    userDTO.setUnionId(schoolId);
    userDTO.setRealName(realName);
    userDTO.setPosition(position);
    return new R<>(userService.getUserWithRolePage(page, userDTO));
  }

  /**
   * h5数据采集 新增用户信息
   *
   * @param sysUser
   * @return R
   */
  @Inner
  @PostMapping("/addHuman")
  public R addHuman(@RequestBody(required = false) SysUser sysUser) {
    /*SysUser sysUser = new SysUser();
    BeanUtils.copyProperties(userDto, sysUser);*/
    sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
    sysUser.setPassword(ENCODER.encode(sysUser.getPassword()));
    boolean save = userService.save(sysUser);
    /*List<SysUserRole> userRoleList = userDto.getRole()
      .stream().map(roleId -> {
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(sysUser.getUserId());
        userRole.setRoleId(roleId);
        return userRole;
      }).collect(Collectors.toList());
    boolean b = sysUserRoleService.saveBatch(userRoleList);*/
    return new R<>(save);
  }


  /**
   * 修改用户信息
   *
   * @param userDto 用户信息
   * @return success/false
   */
  @Inner
  @PostMapping("/updateHuman")
  public R<Boolean> updateHuman(@RequestBody UserDTO userDto) {
    Boolean aBoolean = userService.updateUser2(userDto);
    return new R<>(aBoolean);
  }

  /**
   * h5端删除用户角色
   * @param id
   * @return
   */
  @Inner
  @GetMapping("/DelHumanById")
  public R<Boolean> DelHumanById(@RequestParam(value = "id")Integer id) {
    SysUser sysUser = userService.getById(id);
    //remoteSchoolService.schoolUserId(sysUser.getUserId(),sysUser.getUnionId(),"2", SecurityConstants.FROM_IN);
    return new R<>(userService.removeUserById2(sysUser));
  }

  /**
   * h5数据采集通过id查询单条用户记录
   *
   * @param userId
   * @return R
   */
  @Inner
  @GetMapping("/getHumanById")
  public R getHumanById(@RequestParam("userId") Integer userId) {
    UserVO userVo = userService.getUserVoById(userId);
    return new R<>(userVo);
  }
  
  /**
   * 监管端获取当前用户全部信息
   *
   * @return 用户信息
   */
  @GetMapping(value = {"/getUserInfo"})
  public R getUserInfo() {
    String username = SecurityUtils.getUser().getUsername();
    Map<String,Object> user = userService.getUserByUserName(username);
    if (user == null) {
      return new R<>(Boolean.FALSE, "获取当前用户信息失败");
    }
    return new R<>(userService.getUserInfo2(user));
  }

}
