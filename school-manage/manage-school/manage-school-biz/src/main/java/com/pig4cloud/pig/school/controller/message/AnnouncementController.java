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
import com.pig4cloud.pig.portal.api.entity.Feedback;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import com.pig4cloud.pig.school.service.message.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;


/**
 * 通知中心信息
 *
 * @author xiesongzhe
 * @date 2019-10-15 09:58:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/announcement")
public class AnnouncementController {

  private final RemoteUserService remoteUserService;
  private final AnnouncementService announcementService;
  private final RemoteSchoolService remoteSchoolService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param announcement 通知中心信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Announcement>> getAnnouncementPage(Page<Announcement> page, Announcement announcement) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId = userInfo.getSysUser().getUserId();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    announcement.setSchoolId(schoolId);
    return  new R<>(announcementService.getAnnouncementPage(page,announcement));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Announcement> getById(@PathVariable("id") Integer id){
   /* Announcement announcement = announcementService.getById(id);
    announcement.setStatus("1");
    announcementService.updateById(announcement);*/
    return new R<>(announcementService.getById(id));
  }

  /**
   * 新增记录
   * @param announcement
   * @return R
   */
  @SysLog("新增通知中心信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_announcement_add')")
  public R save(@RequestBody Announcement announcement){
    return new R<>(announcementService.save(announcement));
  }

  /**
   * 修改记录
   * @param announcement
   * @return R
   */
  @SysLog("修改通知中心信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_announcement_edit')")
  public R update(@RequestBody Announcement announcement){
    return new R<>(announcementService.updateById(announcement));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除通知中心信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('schoolMan_announcement_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(announcementService.removeById(id));
  }


  /**
   * 监管端发布通知公告接口
   */
  @Inner
  @PostMapping("/insertAnnouncement")
  public R insertAnnouncement(@RequestBody Announcement announcement) {
    return new R<>(announcementService.save(announcement));
  }

  /* *//**
   * 监管端查询通知公告接口
   */
  @GetMapping("/findPage")
  public R<IPage<Announcement>> getAnnouncementPageforSup(@RequestParam(value = "size")Integer size,
                                                          @RequestParam(value = "current")Integer current,
                                                          @RequestParam(value = "userId")Integer userId
                                                        ) {
    Page page = new Page();
    page.setSize((long)size);
    page.setCurrent((long) current);
    Announcement announcement =new Announcement();
    announcement.setUserId(userId);
    return  new R<>(announcementService.getAnnouncementPage(page,announcement));
  }

  /* *//**
   * 监管端查询通知公告接口(根据监管人的查看状态来查看)
   */
  @GetMapping("/findForStatus")
  public R<IPage<Announcement>> findForStatus(@RequestParam(value = "size")Integer size,
                                              @RequestParam(value = "current")Integer current,
                                              @RequestParam(value = "userId")Integer userId,
                                              @RequestParam(value = "supStatus")String supStatus
  ) {
    Page page = new Page();
    page.setSize((long)size);
    page.setCurrent((long) current);
    Announcement announcement =new Announcement();
    announcement.setUserId(userId);
    announcement.setSupStatus(supStatus);
    announcement.setStatus("1");//只查询校园端已查看的信息
    return  new R<>(announcementService.getAnnouncementPage(page,announcement));
  }

  /**
   * 监管端点击公告通知后更改查看状态
   */
  @Inner
  @PostMapping("/updateAnnouncement")
  public R updateAnnouncement(@RequestBody Announcement announcement) {
    return new R<>(announcementService.updateById(announcement));
  }
}
