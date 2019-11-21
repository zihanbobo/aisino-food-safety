package com.pig4cloud.pig.portalpor.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.entity.Feedback;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import com.pig4cloud.pig.school.api.entity.School;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 意见反馈
 *
 * @author -
 * @date 2019-09-03 14:12:21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/feedback")
public class UserCenterController {

	private final RemotePortalService remotePortalService;

	/**
	 * 插入意见反馈信息
	 * @return
	 */
	@PostMapping("/insertFeedback")
	public R insertFeedback(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "opName")String opName,
							@RequestParam(value = "opEmail")String opEmail,@RequestParam(value = "opTel")String opTel,
							@RequestParam(value = "opTitle")String opTitle,@RequestParam(value = "opContent")String opContent) {
		Feedback feedback = new Feedback();
		feedback.setUserId(userId);
		feedback.setOpName(opName);
		feedback.setOpEmail(opEmail);
		feedback.setOpTel(opTel);
		feedback.setOpTitle(opTitle);
		feedback.setOpContent(opContent);
		feedback.setDelFlag("0");
		feedback.setIsResponse("0");
		return new R<>(remotePortalService.saveFeedback(feedback,SecurityConstants.FROM_IN));
	}

  @GetMapping("/getFeedback")
  public R getFeedback(@RequestParam(value = "size",required = false) Integer size,
                         @RequestParam(value = "current",required = false) Integer current,
                         @RequestParam(value = "userId")Integer userId) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
  }
    //R feedback = remotePortalService.getFeedback(size, current, userId, SecurityConstants.FROM_IN);
    return remotePortalService.getFeedback(size, current, userId, SecurityConstants.FROM_IN);
  }

	/**
	 * 管理端回复意见反馈信息
	 * @return
	 *//*
	@PostMapping("/updateFeedback")
	public R updateFeedback(@RequestParam(value = "feedbackId")Integer feedbackId,
							@RequestParam(value = "responseContent")String responseContent) {
		Feedback feedback = new Feedback();
		feedback.setId(feedbackId);
		//设置回复内容
		feedback.setResponseContent(responseContent);
		//设置反馈信息状态
		feedback.setDelFlag("0");
		//设置意见反馈信息已回复
		feedback.setIsResponse("1");
		//设置回复时间
		LocalDateTime now = LocalDateTime.now();
		feedback.setResponseTime(now);
		return new R<>(remotePortalService.updateFeedback(feedback,SecurityConstants.FROM_IN));
	}*/
}
