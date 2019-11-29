package com.pig4cloud.pig.portalpor.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.entity.CommentPraise;
import com.pig4cloud.pig.portal.api.feign.RemoteLiveService;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import com.pig4cloud.pig.school.api.entity.School;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 评论信息
 *
 * @author -
 * @date 2019-08-28 14:12:21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/source")
public class SourceController {

	private final RemoteUserService remoteUserService;
	private final RemoteSchoolService remoteSchoolService;
	private final RemotePortalService remotePortalService;
	private final RemoteLiveService remoteLiveService;

	/**
	 * 直播信息-查询学校信息
	 * @param page 分页对象
	 * @param school 学校信息
	 * @return
	 */
	@GetMapping("/getSchool")
	public R<Object> getSchoolById(Page<Comment> page, School school) {
		return new R<>(remoteSchoolService.getById(school.getId(),SecurityConstants.FROM_IN));
	}

  /**
   * 查询直播信息学校(11-26新增)
   * @return
   */
  @GetMapping("/getLiveSchoolPortal")
  public R getLiveSchoolWatch(@RequestParam(value = "schoolId") Integer schoolId) {
    R liveSchoolWatch = remoteLiveService.getLiveSchoolWatch(schoolId, SecurityConstants.FROM_IN);
    return new R<>(liveSchoolWatch.getData());
  }

  /**
   * 查询直播流地址(11-26新增)
   * @return
   */
  @GetMapping("/getIoTLivePathPortal")
  public R getIoTLivePathWatch(@RequestParam(value = "eqId") Integer eqId) {
    R ioTLivePathWatch = remoteLiveService.getIoTLivePathWatch(eqId, SecurityConstants.FROM_IN);
    return new R<>(ioTLivePathWatch.getData());
  }

	/**
	 * 公众用户根据学校标识码绑定学校
	 * @return
	 */
	@PostMapping("/bindSchool")
	public R bindSchool(@RequestParam(value = "serviceCode",required = false) String serviceCode,
						@RequestParam(value = "type",required = false) String type,
						@RequestParam(value = "userId", required = false) Integer userId) {
		R r = new R();
		R verify = remoteSchoolService.verify(serviceCode,type,SecurityConstants.FROM_IN);
		if(verify.getCode()==1){
			r.setCode(1);
			r.setMsg("服务码错误");
			return r;
		}
		R byId = remotePortalService.getById(userId, SecurityConstants.FROM_IN);
		if(byId.getData()==null){
			r.setCode(1);
			r.setMsg("未找到与该userId匹配的公共用户信息");
			return r;
		}
		LinkedHashMap linkedHashMap = (LinkedHashMap) verify.getData();
		int schoolIdR = Integer.parseInt(String.valueOf(linkedHashMap.get("id")));
		// 查看该公共用户是否与学校绑定
		Boolean isBindSchool = remotePortalService.isBindSchool(schoolIdR,type,userId, SecurityConstants.FROM_IN);
		if(isBindSchool){
			r.setCode(1);
			r.setMsg("该用户已与该学校绑定");
			return r;
		}
		return new R<>(remotePortalService.bindSchool(schoolIdR,type,userId, SecurityConstants.FROM_IN));
	}


	// 门户端个人中心查询所有已发出评论(带分页)
	@GetMapping("/personalComment")
	public R<Object> personalComment(@RequestParam(value = "size",required = false) Integer size,
									 @RequestParam(value = "current",required = false) Integer current,
									 @RequestParam(value = "userId") Integer userId) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
		return new R<>(remotePortalService.personalComment(size,current,userId,SecurityConstants.FROM_IN));
	}

	// 门户端个人中心查询所有已发出被回复的评论(带分页)
	@GetMapping("/receiveComment")
	public R<Object> receiveComment(@RequestParam(value = "size",required = false) Integer size,
									 @RequestParam(value = "current",required = false) Integer current,
									 @RequestParam(value = "userId") Integer userId) {
		if(size==null){
			size = 10;
		}
		if(current==null){
			current = 1;
		}
		return new R<>(remotePortalService.receiveComment(size,current,userId,SecurityConstants.FROM_IN));
	}

	// 门户端个人中心查询所有已发出被回复的评论新(带分页)
	@GetMapping("/getReplyComment")
	public R<Object> getReplyComment(@RequestParam(value = "size",required = false) Integer size,
									 @RequestParam(value = "current",required = false) Integer current,
									 @RequestParam(value = "userId") Integer userId) {
		if(size==null){
			size = 10;
		}
		if(current==null){
			current = 1;
		}
		return new R<>(remotePortalService.getReplyComment(size,current,userId,SecurityConstants.FROM_IN));
	}

	// 删除评论
	@PostMapping("/logicDeleteComment")
	public R<Object> removeLogicById(@RequestParam(value = "commentId") Integer commentId
									 ) {
		return new R<>(remotePortalService.removeLogicById(commentId,SecurityConstants.FROM_IN));
	}

	/**
	 * 根据学校id查询所有直播信息
	 * @param size
	 * @param current
	 * @param schoolId
	 * @return
	 */
	@GetMapping("/livePage")
	public R<Object> getComment2(@RequestParam(value = "size",required = false) Integer size ,
                               @RequestParam(value = "current",required = false) Integer current,
								               @RequestParam(value = "schoolId") Integer schoolId) {
		if(size==null){
			size = 10;
		}
		if(current==null){
			current = 1;
		}
		return new R<>(remoteLiveService.getLiveSchoolPage(size,current,schoolId,SecurityConstants.FROM_IN));
	}

  /**
   * 点赞
   * @param commentId
   * @param userId
   * @return
   */
  @PostMapping("/savePraise")
  public R<Object> savePraise(@RequestParam(value = "commentId") Integer commentId,
                              @RequestParam(value = "userId") Integer userId) {
      R r = new R();
      Boolean praise = remotePortalService.isPraise(commentId, userId, SecurityConstants.FROM_IN);
      if(praise){
        r.setCode(1);
        r.setMsg("该公共用户已对此条评论点过赞");
        return r;
      }
      CommentPraise commentPraise = new CommentPraise();
      commentPraise.setCommentId(commentId);
      commentPraise.setPubId(userId);
      commentPraise.setTime(LocalDateTime.now());
      return new R<>(remotePortalService.savePraise(commentPraise, SecurityConstants.FROM_IN));
  }

  /**
   * 根据学校id和用户id查询评论信息和点赞信息(弃用)
   *
   * @return
   */
  @GetMapping("/getCommentOld")
  public R<Object> getComment(@RequestParam(value = "size",required = false) Integer size ,@RequestParam(value = "current",required = false) Integer current,
                              @RequestParam(value = "schoolId") Integer schoolId, @RequestParam(value = "userId") Integer userId,
                              @RequestParam(value = "commentCategory") String commentCategory) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
    return new R<>(remotePortalService.getComment(size,current,schoolId,userId,commentCategory,SecurityConstants.FROM_IN));
  }

  /**
   * 根据学校id和用户id查询评论信息和点赞信息
   *
   * @return
   */
  @GetMapping("/getComment")
  public R<Object> getCommentNew(@RequestParam(value = "size",required = false) Integer size ,@RequestParam(value = "current",required = false) Integer current,
                              @RequestParam(value = "schoolId") Integer schoolId, @RequestParam(value = "userId") Integer userId,
                              @RequestParam(value = "commentCategory") String commentCategory) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
    R comment = remotePortalService.getComment(size, current, schoolId, userId, commentCategory, SecurityConstants.FROM_IN);
    Object data = comment.getData();
    int commentCount = remotePortalService.getCommentCount(schoolId, commentCategory, SecurityConstants.FROM_IN);

    Map<String,Object> map = new HashMap<>();
    map.put("comment",data);
    map.put("people",commentCount);
    return new R<>(map);
  }



  /**
   * 直播插入评论
   * @return
   */
  @PostMapping("/insertLiveComment")
  public R insertComment(@RequestParam(value = "schoolId") Integer schoolId,@RequestParam(value = "userId") Integer userId,@RequestParam(value = "content") String content,
                         @RequestParam(value = "commentType") String commentType, @RequestParam(value = "commentId",required = false) Integer commentId,
                         @RequestParam(value = "commentCategory") String commentCategory) {
    //commentCategory为插入的是直播的评论 还是食谱的评论
    Comment comment = new Comment();
    if("2".equals(commentType)){
      comment.setParentId(commentId);
    }
    comment.setCommentType(commentType);
    comment.setUserId(userId);
    comment.setContent(content);
    //comment.setCommentCategory("1");	//直播评论
    comment.setCommentCategory(commentCategory);	//设置是直播评论还是食谱评论
    comment.setSchoolId(schoolId);
    comment.setPraiseCounts(0);	//赋予点赞数初始值=0
    comment.setTime(LocalDateTime.now());	//评论时间
    return new R<>(remotePortalService.saveComment(comment,SecurityConstants.FROM_IN));
  }


  /**
   * 直播插入评论
   * @return
   *//*
	@PostMapping("/insertLiveComment")
	public R insertComment(@RequestParam(value = "schoolId") Integer schoolId,@RequestParam(value = "userId") Integer userId,@RequestParam(value = "content") String content,
						   @RequestParam(value = "commentType") String commentType, @RequestParam(value = "commentId",required = false) Integer commentId) {
		Comment comment = new Comment();
		if("2".equals(commentType)){
			comment.setParentId(commentId);
		}
		comment.setCommentType(commentType);
		comment.setUserId(userId);
		comment.setContent(content);
		comment.setCommentCategory("1");	//直播评论
		comment.setSchoolId(schoolId);

		comment.setPraiseCounts(0);	//赋予点赞数初始值=0
//		comment.setTime(LocalDateTime.now());	//评论时间
		return new R<>(remotePortalService.saveComment(comment,SecurityConstants.FROM_IN));
	}*/

  /**
   * 根据学校id和用户id查询评论信息和点赞信息
   *
   * @return
   */
	/*@GetMapping("/getComment")
	public R<Object> getComment(@RequestParam(value = "size",required = false) Integer size ,@RequestParam(value = "current",required = false) Integer current,
								@RequestParam(value = "schoolId") Integer schoolId, @RequestParam(value = "userId") Integer userId) {
		if(size==null){
			size = 10;
		}
		if(current==null){
			current = 1;
		}
		return new R<>(remotePortalService.getComment(size,current,schoolId,userId,SecurityConstants.FROM_IN));
	}*/
}
