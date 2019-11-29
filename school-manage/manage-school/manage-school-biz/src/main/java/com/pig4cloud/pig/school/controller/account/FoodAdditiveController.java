package com.pig4cloud.pig.school.controller.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.entity.account.FoodAdditive;
import com.pig4cloud.pig.school.service.account.FoodAdditiveService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 食品添加剂使用记录表
 *
 * @author 沈凝
 * @date 2019-07-04 17:35:53
 */
@RestController
@AllArgsConstructor
@RequestMapping("/foodadditive")
public class FoodAdditiveController {

  private final RemoteUserService remoteUserService;
  private final FoodAdditiveService foodAdditiveService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param foodAdditive 食品添加剂使用记录表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<FoodAdditive>> getFoodAdditivePage(Page<FoodAdditive> page, FoodAdditive foodAdditive) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      foodAdditive.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    Map<String, Object> map = new HashMap<>();
    return  new R<>(foodAdditiveService.getFoodAdditivePage(page,foodAdditive));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<FoodAdditive> getById(@PathVariable("id") Integer id){
    return new R<>(foodAdditiveService.getById(id));
  }

  /**
   * 新增记录
   * @param foodAdditive
   * @return R
   */
  @SysLog("新增食品添加剂使用记录表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('sl_foodadditive_add')")
  public R save(@RequestBody FoodAdditive foodAdditive){
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId = userInfo.getSysUser().getUserId();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    foodAdditive.setSchoolId(schoolId);
    foodAdditive.setCreateId(userId);
    return new R<>(foodAdditiveService.save(foodAdditive));
  }

  /**
   * 修改记录
   * @param foodAdditive
   * @return R
   */
  @SysLog("修改食品添加剂使用记录表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('sl_foodadditive_edit')")
  public R update(@RequestBody FoodAdditive foodAdditive){
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    foodAdditive.setUpdateId(userInfo.getSysUser().getUserId());
    return new R<>(foodAdditiveService.updateById(foodAdditive));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除食品添加剂使用记录表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('sl_foodadditive_del')")
  public R removeById(@PathVariable Integer id){
    FoodAdditive foodAdditive = foodAdditiveService.getById(id);
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    foodAdditive.setUpdateId(userInfo.getSysUser().getUserId());
    foodAdditive.setDelFlag("1");
    return new R<>(foodAdditiveService.updateById(foodAdditive));
  }

}
