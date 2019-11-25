package com.pig4cloud.pig.school.controller.project;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.entity.School;
import com.pig4cloud.pig.school.api.entity.project.ProjectManage;
import com.pig4cloud.pig.school.api.vo.project.ProjectManageVO;
import com.pig4cloud.pig.school.service.project.ProjectManageService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 管理端-项目管理
 *
 * @author 沈凝
 * @date 2019-10-17 15:30:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/projectmanage")
public class ProjectManageController {

  private final ProjectManageService projectManageService;
  private final RemoteUserService remoteUserService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param projectManage 管理端-项目管理
   * @return
   */
  @GetMapping("/page")
  public R<IPage<ProjectManageVO>> getProjectManagePage(Page<ProjectManage> page, ProjectManage projectManage) {
    return  new R<>(projectManageService.getProjectManagePage(page,projectManage));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<ProjectManage> getById(@PathVariable("id") Integer id){
    return new R<>(projectManageService.getById(id));
  }

  /**
   * 新增记录
   * @param projectManage
   * @return R
   */
  @SysLog("新增管理端-项目管理")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('project_projectmanage_add')")
  public R save(@RequestBody ProjectManage projectManage){
    return new R<>(projectManageService.save(projectManage));
  }

  /**
   * 修改记录
   * @param projectManage
   * @return R
   */
  @SysLog("修改管理端-项目管理")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('project_projectmanage_edit')")
  public R update(@RequestBody ProjectManage projectManage){
    return new R<>(projectManageService.updateById(projectManage));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除管理端-项目管理")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('project_projectmanage_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(projectManageService.removeById(id));
  }



  /**
   * 获取项目列表
   *
   * @return 学校列表
   */
  @GetMapping("/list")
  public R listProjects() {
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();  // 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId= userInfo.getSysUser().getUserId();

    Boolean isAisinoSub = remoteUserService.isAisinoSub(userId, SecurityConstants.FROM_IN);
    List<ProjectManage> list = null;
    if(isAisinoSub){
      list = projectManageService.list(Wrappers.<ProjectManage>query().lambda()
        .eq(ProjectManage::getDelFlag, "0")
        .eq(ProjectManage::getUserId,userId));
    }else{
      list = projectManageService.list(Wrappers.<ProjectManage>query().lambda()
        .eq(ProjectManage::getDelFlag, "0"));
    }
    return new R<>(list);
  }


}
