package com.pig4cloud.pig.school.controller.check;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysDict;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.portal.api.entity.CommentPraise;
import com.pig4cloud.pig.school.api.dto.check.MorningCheckDTO;
import com.pig4cloud.pig.school.api.entity.check.MorningCheck;
import com.pig4cloud.pig.school.service.check.MorningCheckService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 人员晨检管理
 *
 * @author xiesongzhe
 * @date 2019-10-30 20:14:51
 */
@RestController
@AllArgsConstructor
@RequestMapping("/morningcheck")
public class MorningCheckController {

  private final RemoteUserService remoteUserService;
  private final MorningCheckService morningCheckService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param morningCheckDto 人员晨检管理
   * @return
   */
  @GetMapping("/page")
  public R<IPage<MorningCheck>> getMorningCheckPage(Page<MorningCheck> page, MorningCheckDTO morningCheckDto) {
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      morningCheckDto.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return  new R<>(morningCheckService.getMorningCheckPage(page,morningCheckDto));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<MorningCheck> getById(@PathVariable("id") Integer id){
    return new R<>(morningCheckService.getById(id));
  }

  /**
   * 新增记录
   * @param morningCheck
   * @return R
   */
  @SysLog("新增人员晨检管理")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_morningcheck_add')")
  public R save(@RequestBody MorningCheck morningCheck){
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      morningCheck.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return new R<>(morningCheckService.save(morningCheck));
  }

  /**
   * 修改记录
   * @param morningCheck
   * @return R
   */
  @SysLog("修改人员晨检管理")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_morningcheck_edit')")
  public R update(@RequestBody MorningCheck morningCheck){
    return new R<>(morningCheckService.updateById(morningCheck));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除人员晨检管理")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('schoolMan_morningcheck_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(morningCheckService.removeById(id));
  }

}
