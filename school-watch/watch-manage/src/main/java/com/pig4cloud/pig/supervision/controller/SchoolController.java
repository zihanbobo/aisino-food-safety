package com.pig4cloud.pig.supervision.controller;

import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.feign.RemoteLiveService;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.api.entity.message.RegulatoryOpinion;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.AllArgsConstructor;
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
@RequestMapping("/school")
public class SchoolController {

	private final RemoteLiveService remoteLiveService;
	private final RemoteSchoolService remoteSchoolService;

	/**
	 * 查询直播信息学校
	 * @return
	 */
	@GetMapping("/getLiveSchoolWatch")
	public R getLiveSchoolWatch(@RequestParam(value = "schoolId") Integer schoolId) {
    R liveSchoolWatch = remoteLiveService.getLiveSchoolWatch(schoolId, SecurityConstants.FROM_IN);
    return new R<>(liveSchoolWatch.getData());
	}

  /**
   * 查询直播流地址
   * @return
   */
  @GetMapping("/getIoTLivePathWatch")
  public R getIoTLivePathWatch(@RequestParam(value = "eqId") Integer eqId) {
    R ioTLivePathWatch = remoteLiveService.getIoTLivePathWatch(eqId, SecurityConstants.FROM_IN);
    return new R<>(ioTLivePathWatch.getData());
  }
  
  /**
   * @Description //获取监管端趋势分页页面学校统计信息
   * @Date 14:38 2019/11/22
   * @Param []
   * @return com.pig4cloud.pig.common.core.util.R
   **/
  
  @GetMapping("getAnalysisSchoolData")
  public R getAnalysisSchoolData(){
    return remoteSchoolService.getAnalysisSchoolData();
  }

}
