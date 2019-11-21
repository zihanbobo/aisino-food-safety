package com.pig4cloud.pig.supervision.controller;

import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.api.entity.message.RegulatoryOpinion;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


/**
 * 监管端通知公告接口
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/supervision")
public class SupervisionController {

	private final RemoteUserService remoteUserService;
	private final RemoteSchoolService remoteSchoolService;
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();	//密码类

	/**
	 * 监管端发布通知公告
	 * @return
	 */
	@PostMapping("/insertAnnouncement")
	public R insertAnnouncement(@RequestParam(value = "schoolId") Integer schoolId,
                           @RequestParam(value = "file") String file,
                           @RequestParam(value = "title") String title,
                           @RequestParam(value = "content") String content,
                           @RequestParam(value = "userId") Integer userId) {

    Announcement announcement = new Announcement();
    announcement.setSchoolId(schoolId);
    announcement.setFile(file);
    announcement.setTitle(title);
    announcement.setContent(content);
    announcement.setStatus("2");//状态为未读取
    announcement.setType("1");//设置类型为通知中心的信息
    announcement.setUserId(userId);
    announcement.setSupStatus("2");//设置监管端查看状态为未查看
    return new R<>(remoteSchoolService.insertAnnouncement(announcement,SecurityConstants.FROM_IN));
	}

  /**
   * 监管端查询所有通知公告信息(分页)
   * @param size
   * @param current
   * @param userId
   * @return
   */
  @GetMapping("/findPage")
  public R findPage(@RequestParam(value = "size",required = false) Integer size,
                    @RequestParam(value = "current",required = false) Integer current,
                    @RequestParam(value = "userId")Integer userId) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
    return remoteSchoolService.findPage(size, current, userId, SecurityConstants.FROM_IN);
  }

  /**
   * 监管端查询所有通知公告信息(根据监管人查看状态查询)
   * @param userId
   * @return
   */
  @GetMapping("/findForStatus")
  public R findForStatus(
                        @RequestParam(value = "size",required = false) Integer size,
                        @RequestParam(value = "current",required = false) Integer current,
                        @RequestParam(value = "userId")Integer userId,
                        @RequestParam(value = "supStatus",required = false)String supStatus) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
    return remoteSchoolService.findForStatus(size, current,userId,supStatus, SecurityConstants.FROM_IN);
  }

  /**
   * 监管端发布通知公告
   * @return
   */
  @PostMapping("/updateAnnouncement")
  public R updateAnnouncement(@RequestParam(value = "id") Integer id) {
    Announcement announcement = new Announcement();
    announcement.setId(id);
    announcement.setSupStatus("1");//1 已查看 2 未查看
    return new R<>(remoteSchoolService.updateAnnouncement(announcement,SecurityConstants.FROM_IN));
  }

  /**
   * 监管端下发意见反馈
   * @return
   */
  @PostMapping("/insertRegulatoryOpinion")
  public R insertRegulatoryOpinion(@RequestParam(value = "schoolId") Integer schoolId,
                                   @RequestParam(value = "content") String content,
                                   @RequestParam(value = "userId") Integer userId,
                                   @RequestParam(value = "type") String type) {
    RegulatoryOpinion regulatoryOpinion = new RegulatoryOpinion();
    regulatoryOpinion.setSchoolId(schoolId); //下发哪个学校
    regulatoryOpinion.setContent(content);  //下发内容
    regulatoryOpinion.setUserId(userId);  //谁下发的
    regulatoryOpinion.setType(type);//报警类型(1证照报警2其他报警)
    regulatoryOpinion.setStatus("2");//状态为未读取
    return new R<>(remoteSchoolService.insertRegulatoryOpinion(regulatoryOpinion,SecurityConstants.FROM_IN));
  }

  /**
   * 监管端查询所有下发意见(分页)
   * @param size
   * @param current
   * @param userId
   * @return
   */
  @GetMapping("/findPageForOp")
  public R findPageForOp(@RequestParam(value = "size",required = false) Integer size,
                    @RequestParam(value = "current",required = false) Integer current,
                    @RequestParam(value = "userId")Integer userId) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
    return remoteSchoolService.findPageForOp(size, current, userId, SecurityConstants.FROM_IN);
  }


}
