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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysDeptRelation;
import com.pig4cloud.pig.admin.api.entity.SysRole;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.service.SysRoleMenuService;
import com.pig4cloud.pig.admin.service.SysRoleService;
import com.pig4cloud.pig.admin.service.SysUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {
	private final SysRoleService sysRoleService;
	private final SysRoleMenuService sysRoleMenuService;
  private final SysUserService userService;

	/**
	 * 通过ID查询角色信息
	 *
	 * @param id ID
	 * @return 角色信息
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable Integer id) {
		return new R<>(sysRoleService.getById(id));
	}

	/**
	 * 添加角色
	 *
	 * @param sysRole 角色信息
	 * @return success、false
	 */
	@SysLog("添加角色")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_role_add')")
	public R save(@Valid @RequestBody SysRole sysRole) {
    // 权限控制
    String username = SecurityUtils.getUser().getUsername();	// 当前登录用户昵称
    SysUser user = userService.getOne(Wrappers.<SysUser>query()
      .lambda().eq(SysUser::getUsername, username));
    UserInfo userInfo = userService.getUserInfo(user);
    String userType = userInfo.getSysUser().getUserType();
    String isAdmin = userInfo.getSysUser().getIsAdmin();
    // 判断类型(航信校园)
    if("1".equals(userType)&&!"1".equals(isAdmin)){
      sysRole.setRoleType("1");
    }else if("1".equals(userType)&&"1".equals(isAdmin)){
      sysRole.setRoleType("2");
      sysRole.setUnionId(userInfo.getSysUser().getUnionId());
    }else{
      sysRole.setRoleType("2");
      sysRole.setUnionId(userInfo.getSysUser().getUnionId());
    }
		return new R<>(sysRoleService.save(sysRole));
	}

	/**
	 * 修改角色
	 *
	 * @param sysRole 角色信息
	 * @return success/false
	 */
	@SysLog("修改角色")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_role_edit')")
	public R update(@Valid @RequestBody SysRole sysRole) {
		return new R<>(sysRoleService.updateById(sysRole));
	}

	/**
	 * 删除角色
	 *
	 * @param id
	 * @return
	 */
	@SysLog("删除角色")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_role_del')")
	public R removeById(@PathVariable Integer id) {
		return new R<>(sysRoleService.removeRoleById(id));
	}

	/**
	 * 获取角色列表
	 *
	 * @return 角色列表
	 */
	@GetMapping("/list")
	public R listRoles() {
//		return new R<>(sysRoleService.list(Wrappers.emptyWrapper()));
    // 权限控制
    String username = SecurityUtils.getUser().getUsername();	// 当前登录用户昵称
    SysUser user = userService.getOne(Wrappers.<SysUser>query()
      .lambda().eq(SysUser::getUsername, username));
    UserInfo userInfo = userService.getUserInfo(user);
    String userType = userInfo.getSysUser().getUserType();
    String isAdmin = userInfo.getSysUser().getIsAdmin();
    LambdaQueryWrapper<SysRole> eq = null;
    if("1".equals(userType)&&!"1".equals(isAdmin)){
      eq = Wrappers.<SysRole>query()
        .lambda()
        .ne(SysRole::getRoleCode,"school")// 不等于
        .ne(SysRole::getRoleCode,"shichangjianguan")// 不等于
        .ne(SysRole::getRoleCode,"jiaoyujuguanli")// 不等于
        .eq(SysRole::getRoleType, '1')
        .eq(SysRole::getDelFlag, '0');
    }else  if("3".equals(userType)&&!"1".equals(isAdmin)){
      eq = Wrappers.<SysRole>query()
        .lambda()
        .ne(SysRole::getRoleCode,"school")// 不等于
        .ne(SysRole::getRoleCode,"shichangjianguan")// 不等于
        .ne(SysRole::getRoleCode,"jiaoyujuguanli")// 不等于
        .eq(SysRole::getRoleType, '1')
        .eq(SysRole::getDelFlag, '0');
    }else if("2".equals(userType)&&"1".equals(isAdmin)){
      eq = Wrappers.<SysRole>query()
        .lambda()
        .eq(SysRole::getRoleType, "2")
        .eq(SysRole::getUnionId, userInfo.getSysUser().getUnionId())
        .eq(SysRole::getDelFlag, '0');
    }else{
      eq = Wrappers.<SysRole>query()
        .lambda()
        .eq(SysRole::getRoleType, "2")
        .eq(SysRole::getUnionId, userInfo.getSysUser().getUnionId())
        .eq(SysRole::getDelFlag, '0');
    }
    return new R<>(sysRoleService.list(eq));
	}
	/**
	 * 学校角色列表
	 *
	 * @return 角色列表
	 */
	@GetMapping("/listSchool")
	public R listRolesSchool() {
    // 权限控制
    LambdaQueryWrapper<SysRole> eq = null;
      eq = Wrappers.<SysRole>query()
        .lambda()
        .eq(SysRole::getRoleCode,"school")// 不等于
        .eq(SysRole::getDelFlag, '0');
    return new R<>(sysRoleService.list(eq));
	}

	/**
	 * 分页查询角色信息
	 *
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public R getRolePage(Page page) {
//    return new R<>(sysRoleService.page(page, Wrappers.emptyWrapper()));
    // 权限控制
    String username = SecurityUtils.getUser().getUsername();	// 当前登录用户昵称
    SysUser user = userService.getOne(Wrappers.<SysUser>query()
      .lambda().eq(SysUser::getUsername, username));
    UserInfo userInfo = userService.getUserInfo(user);
    String userType = userInfo.getSysUser().getUserType();
    String isAdmin = userInfo.getSysUser().getIsAdmin();
    // 航信端(用户类型为1&非超级管理员)
    if("1".equals(userType)&&!"1".equals(isAdmin)){
      return new R<>(sysRoleService.page(page, Wrappers.<SysRole>query().lambda()
        .eq(SysRole::getRoleType, userType)
        .eq(SysRole::getDelFlag, "0")
      ));
    // 校园端(用户类型2&超级管理员)
    }else if("2".equals(userType)&&"1".equals(isAdmin)){
      return new R<>(sysRoleService.page(page, Wrappers.<SysRole>query().lambda()
        .eq(SysRole::getRoleType, "2")
        .eq(SysRole::getUnionId, userInfo.getSysUser().getUnionId())
        .eq(SysRole::getDelFlag, "0")
      ));
    // 其余类型
    }else{
        return new R<>(sysRoleService.page(page, Wrappers.<SysRole>query().lambda()
          .eq(SysRole::getRoleType, "2")
          .eq(SysRole::getUnionId, userInfo.getSysUser().getUnionId())
          .eq(SysRole::getDelFlag, "0")
          ));
    }
  }

	/**
	 * 更新角色菜单
	 *
	 * @param roleId  角色ID
	 * @param menuIds 菜单ID拼成的字符串，每个id之间根据逗号分隔
	 * @return success、false
	 */
	@SysLog("更新角色菜单")
	@PutMapping("/menu")
	@PreAuthorize("@pms.hasPermission('sys_role_perm')")
	public R saveRoleMenus(Integer roleId, @RequestParam(value = "menuIds", required = false) String menuIds) {
		SysRole sysRole = sysRoleService.getById(roleId);
		return new R<>(sysRoleMenuService.saveRoleMenus(sysRole.getRoleCode(), roleId, menuIds));
	}

  /**
   * 角色列表(市场监管局教育局)
   *
   * @return 角色列表
   */
  @GetMapping("/listMarket")
  public R listMarket(@RequestParam(value = "roleCode") String roleCode) {
    // 权限控制
    LambdaQueryWrapper<SysRole> eq = null;
    eq = Wrappers.<SysRole>query()
      .lambda()
      .eq(SysRole::getRoleCode,roleCode)// 角色标识码
      .eq(SysRole::getDelFlag, '0');
    return new R<>(sysRoleService.list(eq));
  }
}
