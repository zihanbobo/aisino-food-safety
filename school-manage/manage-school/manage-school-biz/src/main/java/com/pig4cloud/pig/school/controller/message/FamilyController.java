package com.pig4cloud.pig.school.controller.message;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import com.pig4cloud.pig.school.service.message.AnnouncementService;
import com.pig4cloud.pig.school.service.message.FamilyService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 家长信息
 *
 * @author xiesongzhe
 * @date 2019-10-15 09:58:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/family")
public class FamilyController {

  private final RemoteUserService remoteUserService;
  private final FamilyService familyService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param publicUser 通知中心信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<PublicUserVO>> getFamilyPage(Page<PublicUser> page, PublicUser publicUser) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId = userInfo.getSysUser().getUserId();
    Integer schoolId = userInfo.getSysUser().getUnionId();

    publicUser.setSchoolId(schoolId);
    return  new R<>(familyService.getFamilyPage(page,publicUser));
  }
//
//
//  /**
//   * 通过id查询单条记录
//   * @param id
//   * @return R
//   */
//  @GetMapping("/{id}")
//  public R<Announcement> getById(@PathVariable("id") Integer id){
//   /* Announcement announcement = announcementService.getById(id);
//    announcement.setStatus("1");
//    announcementService.updateById(announcement);*/
//    return new R<>(announcementService.getById(id));
//  }
//
//  /**
//   * 新增记录
//   * @param announcement
//   * @return R
//   */
//  @SysLog("新增通知中心信息")
//  @PostMapping
//  @PreAuthorize("@pms.hasPermission('schoolMan_announcement_add')")
//  public R save(@RequestBody Announcement announcement){
//    return new R<>(announcementService.save(announcement));
//  }
//
//  /**
//   * 修改记录
//   * @param announcement
//   * @return R
//   */
//  @SysLog("修改通知中心信息")
//  @PutMapping
//  @PreAuthorize("@pms.hasPermission('schoolMan_announcement_edit')")
//  public R update(@RequestBody Announcement announcement){
//    return new R<>(announcementService.updateById(announcement));
//  }
//
//  /**
//   * 通过id删除一条记录
//   * @param id
//   * @return R
//   */
//  @SysLog("删除通知中心信息")
//  @DeleteMapping("/{id}")
//  @PreAuthorize("@pms.hasPermission('schoolMan_announcement_del')")
//  public R removeById(@PathVariable Integer id){
//    return new R<>(announcementService.removeById(id));
//  }
//

}
