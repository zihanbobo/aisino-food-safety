package com.pig4cloud.pig.school.controller.source;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import com.pig4cloud.pig.school.api.entity.source.SchoolCustom;
import com.pig4cloud.pig.school.service.source.SchoolCustomService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 学校自定义食材信息
 *
 * @author xiesongzhe
 * @date 2019-09-05 20:59:49
 */
@RestController
@AllArgsConstructor
@RequestMapping("/schoolcustom")
public class SchoolCustomController {

  private final RemoteUserService remoteUserService;

  private final SchoolCustomService schoolCustomService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param schoolCustom 学校自定义食材信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<SchoolCustom>> getSchoolCustomPage(Page<SchoolCustom> page, SchoolCustom schoolCustom) {
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      schoolCustom.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return  new R<>(schoolCustomService.getSchoolCustomPage(page,schoolCustom));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<SchoolCustom> getById(@PathVariable("id") Integer id){
    return new R<>(schoolCustomService.getById(id));
  }

  /**
   * 新增记录
   * @param schoolCustom
   * @return R
   */
  @SysLog("新增学校自定义食材信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_schoolcustom_add')")
  public R save(@RequestBody SchoolCustom schoolCustom){
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      schoolCustom.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return new R<>(schoolCustomService.save(schoolCustom));
  }

  /**
   * 修改记录
   * @param schoolCustom
   * @return R
   */
  @SysLog("修改学校自定义食材信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_schoolcustom_edit')")
  public R update(@RequestBody SchoolCustom schoolCustom){
    return new R<>(schoolCustomService.updateById(schoolCustom));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除学校自定义食材信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('schoolMan_schoolcustom_del')")
  public R removeById(@PathVariable Integer id){
    SchoolCustom schoolCustom = schoolCustomService.getById(id);
    schoolCustom.setDelFlag("1");
    return new R<>(schoolCustomService.updateById(schoolCustom));
    //    return new R<>(schoolCustomService.removeById(id));
  }

}
