package com.pig4cloud.pig.school.controller.check;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.dto.check.FoodInspectionDTO;
import com.pig4cloud.pig.school.api.entity.check.FoodInspection;
import com.pig4cloud.pig.school.service.check.FoodInspectionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 食材快检管理
 *
 * @author xiesongzhe
 * @date 2019-10-30 20:51:34
 */
@RestController
@AllArgsConstructor
@RequestMapping("/foodinspection")
public class FoodInspectionController {

  private final RemoteUserService remoteUserService;
  private final FoodInspectionService foodInspectionService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param foodInspectionDto 食材快检管理
   * @return
   */
  @GetMapping("/page")
  public R<IPage<FoodInspection>> getFoodInspectionPage(Page<FoodInspection> page, FoodInspectionDTO foodInspectionDto) {
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      foodInspectionDto.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return  new R<>(foodInspectionService.getFoodInspectionPage(page,foodInspectionDto));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<FoodInspection> getById(@PathVariable("id") Integer id){
    return new R<>(foodInspectionService.getById(id));
  }

  /**
   * 新增记录
   * @param foodInspection
   * @return R
   */
  @SysLog("新增食材快检管理")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_foodinspection_add')")
  public R save(@RequestBody FoodInspection foodInspection){
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      foodInspection.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return new R<>(foodInspectionService.save(foodInspection));
  }

  /**
   * 修改记录
   * @param foodInspection
   * @return R
   */
  @SysLog("修改食材快检管理")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_foodinspection_edit')")
  public R update(@RequestBody FoodInspection foodInspection){
    return new R<>(foodInspectionService.updateById(foodInspection));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除食材快检管理")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('schoolMan_foodinspection_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(foodInspectionService.removeById(id));
  }

}
