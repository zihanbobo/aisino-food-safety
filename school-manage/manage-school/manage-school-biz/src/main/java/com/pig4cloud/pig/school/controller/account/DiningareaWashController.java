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
import com.pig4cloud.pig.school.api.entity.account.AccompanyDinner;
import com.pig4cloud.pig.school.api.entity.account.DiningareaWash;
import com.pig4cloud.pig.school.service.account.DiningareaWashService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 就餐区域洗消记录表
 *
 * @author 沈凝
 * @date 2019-07-06 16:44:56
 */
@RestController
@AllArgsConstructor
@RequestMapping("/diningareawash")
public class DiningareaWashController {

  private final RemoteUserService remoteUserService;
  private final DiningareaWashService diningareaWashService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param diningareaWash 就餐区域洗消记录表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<DiningareaWash>> getDiningareaWashPage(Page<DiningareaWash> page, DiningareaWash diningareaWash) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      diningareaWash.setSchoolId(userInfo.getSysUser().getUnionId());
      diningareaWash.setCreateId(userInfo.getSysUser().getUserId());
    }
    return  new R<>(diningareaWashService.getDiningareaWashPage(page,diningareaWash));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<DiningareaWash> getById(@PathVariable("id") Integer id){
    return new R<>(diningareaWashService.getById(id));
  }

  /**
   * 新增记录
   * @param diningareaWash
   * @return R
   */
  @SysLog("新增就餐区域洗消记录表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('sl_diningareawash_add')")
  public R save(@RequestBody DiningareaWash diningareaWash){
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId = userInfo.getSysUser().getUserId();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    diningareaWash.setSchoolId(schoolId);
    diningareaWash.setCreateId(userId);
    return new R<>(diningareaWashService.save(diningareaWash));
  }

  /**
   * 修改记录
   * @param diningareaWash
   * @return R
   */
  @SysLog("修改就餐区域洗消记录表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('sl_diningareawash_edit')")
  public R update(@RequestBody DiningareaWash diningareaWash){
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    diningareaWash.setUpdateId(userInfo.getSysUser().getUserId());
    return new R<>(diningareaWashService.updateById(diningareaWash));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除就餐区域洗消记录表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('sl_diningareawash_del')")
  public R removeById(@PathVariable Integer id){
    DiningareaWash diningareaWash = diningareaWashService.getById(id);
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    diningareaWash.setUpdateId(userInfo.getSysUser().getUserId());
    diningareaWash.setDelFlag("1");
    return new R<>(diningareaWashService.updateById(diningareaWash));
  }

}
