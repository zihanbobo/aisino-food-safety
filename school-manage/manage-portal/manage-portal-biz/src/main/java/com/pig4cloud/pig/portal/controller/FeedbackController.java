package com.pig4cloud.pig.portal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.entity.CommentPraise;
import com.pig4cloud.pig.portal.api.entity.Feedback;
import com.pig4cloud.pig.portal.api.entity.PublicUserSchool;
import com.pig4cloud.pig.portal.api.vo.CommentVO;
import com.pig4cloud.pig.portal.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 意见反馈信息
 *
 * @author xiesongzhe
 * @date 2019-09-03 15:07:52
 */
@RestController
@AllArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {

  private final  FeedbackService feedbackService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param feedback 意见反馈信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Feedback>> getFeedbackPage(Page<Feedback> page, Feedback feedback) {
    return  new R<>(feedbackService.getFeedbackPage(page,feedback));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Feedback> getById(@PathVariable("id") Integer id){
    return new R<>(feedbackService.getById(id));
  }


  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除意见反馈信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('portalMan_feedback_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(feedbackService.removeById(id));
  }

	/**
	 * 修改记录
	 * @param feedback
	 * @return R
	 */
	@SysLog("修改意见反馈信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('portalMan_feedback_edit')")
	public R update(@RequestBody Feedback feedback){
		feedback.setIsResponse("1");
		return new R<>(feedbackService.updateById(feedback));
	}

  @Inner
  @GetMapping("/getFeedback")
  public R getFeedback(@RequestParam(value = "size")Integer size,
                                   @RequestParam(value = "current")Integer current,
                                   @RequestParam(value = "userId")Integer userId) {
    // 根据输入参数组装分页类
    Page<Object> page = new Page<>();
    page.setSize(new Long((long)size));
    page.setCurrent(new Long((long)current));
    IPage<List<Map>> feedbackPageForP = feedbackService.getFeedbackPageForP(page, userId);
    //feedbackService.getOne(Wrappers.<CommentPraise>query().lambda()
    return new R<>(feedbackPageForP);
  }


  /**
     * 新增评论
     *
     * @param feedback 评论信息实体
     * @return success/false
     *//*
	@Inner
	@PostMapping
	public R save(@RequestBody Feedback feedback) {
		return new R<>(feedbackService.save(feedback));
}*/

	/**
	 * 公众用户新增意见反馈
	 *
	 * @return
	 *//*
	@Inner
	@PostMapping("/insertFeedback")
	public R insertFeedback(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "opName")String opName,
							@RequestParam(value = "opEmail")String opEmail,@RequestParam(value = "opTel ")String opTel,
							@RequestParam(value = "opTitle")String opTitle,@RequestParam(value = "opContent")String opContent) {
		R<Object> objectR = new R<>();

		Feedback feedback = new Feedback();
		//新增的意见反馈为未回复  0 未回复  1 已回复
		feedback.setIsResponse("0");
		feedback.setDelFlag("0");
		//设置意见反馈信息的用户id
		feedback.setUserId(userId);
		//设置意见反馈人名
		feedback.setOpName(opName);
		//设置email地址
		feedback.setOpEmail(opEmail);
		//设置电话
		feedback.setOpTel(opTel);
		//设置意见反馈标题
		feedback.setOpTitle(opTitle);
		//设置意见反馈内容
		feedback.setOpContent(opContent);

		//boolean save = feedbackService.save(feedback);

		return new R<>(feedbackService.save(feedback));
		//return new R<>(publicUserSchoolService.save(publicUserSchool));
	}*/
	/**
	 * 新增意见反馈
	 *
	 * @param feedback 意见反馈实体
	 * @return success/false
	 */
	@Inner
	@PostMapping
	public R save(@RequestBody Feedback feedback) {
		return new R<>(feedbackService.save(feedback));
	}

	/**
	 * 管理端回复意见反馈
	 *
	 * @param feedback 意见反馈实体
	 * @return success/false
	 */
	/*@Inner
	@PostMapping("/updateFeedback")
	public R update(@RequestBody Feedback feedback) {
		return new R<>(feedbackService.updateById(feedback));
	}

*/
}
