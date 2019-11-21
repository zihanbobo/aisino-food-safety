package com.pig4cloud.pig.publicpub.controller;

import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.entity.CommentPraise;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;


/**
 * 各模块通用接口
 *
 * @author - Mr_Miao
 * @date 2019-09-27 14:12:21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/public")
public class CommonController {

  private final RemotePortalService remotePortalService;


  /**
   * 评论列表
   *
   * @param size
   * @param current
   * @param schoolId
   * @param commentCategory
   * @return
   */
  @PostMapping("/getComment")
  public R getComment(@RequestParam(value = "size", required = false) Integer size,
                      @RequestParam(value = "current", required = false) Integer current,
                      @RequestParam(value = "userId")Integer userId,
                      @RequestParam(value = "schoolId") Integer schoolId,
                      @RequestParam(value = "commentCategory") String commentCategory) {
    return remotePortalService.getAppComment(size, current,userId, schoolId, commentCategory, SecurityConstants.FROM_IN);
  }

  /**
   * 点赞
   *
   * @param commentId
   * @param userId
   * @return
   */
  @PostMapping("/savePraise")
  public R savePraise(@RequestParam(value = "commentId") Integer commentId,
                      @RequestParam(value = "userId") Integer userId) {
    R r = new R();
    Boolean praise = remotePortalService.isPraise(commentId, userId, SecurityConstants.FROM_IN);
    if (praise) {
      r.setCode(1);
      r.setMsg("该公共用户已对此条评论点过赞");
      return r;
    }
    CommentPraise commentPraise = new CommentPraise();
    commentPraise.setCommentId(commentId);
    commentPraise.setPubId(userId);
    commentPraise.setTime(LocalDateTime.now());
    return remotePortalService.savePraise(commentPraise, SecurityConstants.FROM_IN);
  }

  /**
   * 评论
   *
   * @param comment
   * @return
   */
  @PostMapping("/insertLiveComment")
  public R insertComment(@RequestBody Comment comment) {
    comment.setPraiseCounts(0);
    comment.setTime(LocalDateTime.now());  //评论时间
    comment.setDelFlag("0");
    comment = remotePortalService.addComment(comment, SecurityConstants.FROM_IN);
    return remotePortalService.findCommentById(comment.getId(),SecurityConstants.FROM_IN);
  }

  /**
   * 评论详情（包含该条评论的所有回复）
   * @param commentId
   * @param userId
   * @return
   */
  @PostMapping("/getCommentDetails")
  public R getCommentDetails(@RequestParam(value = "size") Integer size,
                          @RequestParam(value = "current") Integer current,
                             @RequestParam(value = "userId") Integer userId,
                          @RequestParam(value = "commentId") Integer commentId){
    return remotePortalService.getAppCommentDetails(size, current,userId, commentId, SecurityConstants.FROM_IN);
  }

}
