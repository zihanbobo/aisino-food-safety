package com.pig4cloud.pig.portal.controller;

import cn.hutool.json.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.xdevapi.JsonArray;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.portal.api.entity.CommentPraise;
import com.pig4cloud.pig.portal.api.entity.Feedback;
import com.pig4cloud.pig.portal.api.entity.live.Live;
import com.pig4cloud.pig.portal.service.CommentPraiseService;
import com.pig4cloud.pig.portal.service.CommentService;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.vo.CommentVO;
import com.pig4cloud.pig.school.api.entity.School;
import lombok.AllArgsConstructor;
import net.sf.jsqlparser.expression.StringValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 评论信息
 *
 * @author -
 * @date 2019-08-28 14:12:21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {

  	private final CommentService commentService;
    private final RemoteUserService remoteUserService;
    private final CommentPraiseService commentPraiseService;
    /**
     * 简单分页查询(门户端)
     * @param page 分页对象
     * @param comment 评论信息
     * @return
     */
    /*@GetMapping("/page")
    public R<IPage<CommentVO>> getCommentPage(Page<Comment> page, Comment comment) {
      PigUser user = SecurityUtils.getUser();
      String username = user.getUsername();	// 当前登录用户昵称
      R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
      UserInfo userInfo = result.getData();
      String userType = userInfo.getSysUser().getUserType();
      if("1".equals(userType)){
      }else if("2".equals(userType)){
        comment.setSchoolId(userInfo.getSysUser().getUnionId());
      }
      comment.setCommentType("1");// 评论

      IPage<CommentVO> commentPage = commentService.getCommentPage(page, comment);

      if(commentPage.getRecords()==null||commentPage.getSize()==0){
        return new R<>(commentPage);
      }
      List<CommentVO> records = commentPage.getRecords();
      Page<Comment> page2 = new Page<>();

      for(int i=0;i<records.size();i++){
        Comment comment1 = records.get(i);
        Comment comment2 = new Comment();
        comment2.setParentId(comment1.getId());
        IPage<CommentVO> commentPage1 = commentService.getCommentPage(page2, comment2);
        if(commentPage1.getRecords()!=null&&commentPage1.getSize()>1){
          commentPage.getRecords().get(i).setChildren(commentPage1.getRecords());
        }
      }
      return  new R<>(commentPage);
    }*/

  /**
   * 管理端查询所有评论和回复信息(根据commentCategory分别直播还是食谱)
   * @param page
   * @param comment
   * @param commentCategory
   * @return
   */
  @GetMapping("/getCommentPageForManage2")
  public R<IPage<CommentVO>> getCommentPageForManage2(Page<Comment> page, Comment comment,String commentCategory) {

    //控制管理端和校园端的权限问题
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();  // 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      comment.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    comment.setCommentType("1");// 评论
    comment.setCommentCategory(commentCategory);
    //IPage<CommentVO> commentPage = commentService.getCommentPage(page, comment);
    IPage<CommentVO> commentPageForManage = commentService.getCommentPageForManage(page,comment);
    if(commentPageForManage.getRecords()==null||commentPageForManage.getSize()==0){
      return new R<>(commentPageForManage);
    }
    List<CommentVO> records = commentPageForManage.getRecords();

    for(int i=0;i<records.size();i++){
      CommentVO commentVO = records.get(i);
      Integer parentId = commentVO.getId();

      List<Map> commentChildForManage = commentService.getCommentChildForManage(parentId);

      if(commentChildForManage!=null&&commentChildForManage.size()>1){
        //commentPageForManage.getRecords().get(i).setChildrenPor(commentChildForManage);
        commentPageForManage.getRecords().get(i).setChildren(commentChildForManage);
      }
    }
    return  new R<>(commentPageForManage);
  }

  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Comment> getById(@PathVariable("id") Integer id){
    return new R<>(commentService.getById(id));
  }

  /**
   * 新增记录
   * @param comment
   * @return R
   */
//  @SysLog("新增评论信息")
//  @PostMapping
//  @PreAuthorize("@pms.hasPermission('comment_comment_add')")
//  public R save(@RequestBody Comment comment){
//    return new R<>(commentService.save(comment));
//  }

  /**
   * 修改记录
   * @param comment
   * @return R
   */
  @SysLog("修改评论信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('comment_comment_edit')")
  public R update(@RequestBody Comment comment){
    return new R<>(commentService.updateById(comment));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除评论信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('comment_comment_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(commentService.removeById(id));
  }

  //	@Inner
  @GetMapping("/getComment")
  /*	public R<Object> getComment(Page<Comment> page,Comment comment) {*/
  public R<Object> getComment(Integer size,Integer current,Integer schoolId,Integer userId,String commentCategory) {
    //Integer size,Integer current,Integer schoolId,Integer userId
    Comment comment = new Comment();
    comment.setUserId(userId);
    comment.setSchoolId(schoolId);
    comment.setCommentCategory(commentCategory);
    Page<Comment> page = new Page<>();
    page.setSize(new Long((long) size));
    page.setCurrent(new Long((long) current));
    R<Object> resultR = new R<Object>();
    // 学校ID为空直接返回
    if(comment.getSchoolId()==null||comment.getUserId()==null){
      resultR.setCode(1);
      resultR.setMsg("未传入学校id或用户id");
      resultR.setData(null);
      return resultR;
    }
    // 查询该学校所有评论
    comment.setCommentType("1");// 类型定位评论
    IPage<CommentVO> commentPage = commentService.getCommentPage(page, comment);

    // 如果学校评论为空则直接返回NULL
    if(commentPage.getRecords()==null||commentPage.getSize()==0){
      return new R<>(commentPage);
    }

    List<CommentVO> records = commentPage.getRecords();
    for(int i=0;i<records.size();i++){
      int parentId = records.get(i).getId();
      List<Map> commentChild = commentService.getCommentChild(parentId,comment.getUserId());
      if(commentChild!=null&&commentChild.size()>0){
        commentPage.getRecords().get(i).setChildrenPor(commentChild);
      }
    }
    return new R<>(commentPage);
  }


  // 个人中心查询已经发出的评论
	@Inner
	@GetMapping("/personalComment")
	public R<Object> personalComment(@RequestParam(value = "size")Integer size,
                                   @RequestParam(value = "current")Integer current,
		                              @RequestParam(value = "userId")Integer userId) {
		// 根据输入参数组装分页类
		Page<Object> page = new Page<>();
    page.setSize(new Long((long)size));
    page.setCurrent(new Long((long)current));
    return new R<>(commentService.personalComment(page,userId));
	}

  // 个人中心查询已经收到的评论
  @Inner
  @GetMapping("/receiveComment")
  public R<Object> receiveComment(@RequestParam(value = "size")Integer size,
                                  @RequestParam(value = "current")Integer current,
                                  @RequestParam(value = "userId")Integer userId) {
    // 根据输入参数组装分页类
    Page<Object> page = new Page<>();
    page.setSize(new Long((long)size));
    page.setCurrent(new Long((long)current));
    return new R<>(commentService.receiveComment(page,userId));
  }

  // 个人中心查询已经收到的评论(新)
  @Inner
  @GetMapping("/getReplyComment")
  public R<Object> gerReplyComment(@RequestParam(value = "size")Integer size,
                                  @RequestParam(value = "current")Integer current,
                                  @RequestParam(value = "userId")Integer userId) {
    // 根据输入参数组装分页类
    Page<Object> page = new Page<>();
    page.setSize(new Long((long)size));
    page.setCurrent(new Long((long)current));
    // 获取被回复的id集合
    List<Map> maps = commentService.gerReplyComment(userId);
    if(maps==null||maps.size()==0){
      return new R<>();
    }
    List<Integer> list = new ArrayList<Integer>();
    if(maps!=null&&maps.size()>0){
       for(int i=0;i<maps.size();i++){
         // 获取父级id
         Map idMap = commentService.getReplyCommentParentId(Integer.parseInt(String.valueOf(maps.get(i).get("id"))));
         list.add(Integer.parseInt(String.valueOf(idMap.get("id"))));
       }
    }
    // steam流集合去重
    List<Integer> collect = list.stream().distinct().collect(Collectors.toList());
    StringBuffer sb = new StringBuffer();
    for(int i=0;i<collect.size();i++){
      sb.append(collect.get(i)+",");
    }
    sb.deleteCharAt(sb.length()-1);
    IPage<CommentVO> commentPage = commentService.getReplyCommentPage(page, sb.toString());

    // 如果学校评论为空则直接返回NULL
    if(commentPage.getRecords()==null||commentPage.getSize()==0){
      return new R<>(commentPage);
    }

    List<CommentVO> records = commentPage.getRecords();
    for(int i=0;i<records.size();i++){
      int parentId = records.get(i).getId();
      List<Map> commentChild = commentService.getCommentChild(parentId,userId);
      if(commentChild!=null&&commentChild.size()>0){
        commentPage.getRecords().get(i).setChildrenPor(commentChild);
      }
    }
    return new R<>(commentPage);
  }




  // 个人中心查询已经收到的评论后改
  @Inner
  @GetMapping("/receiveComment2")
  public R<Object> receiveComment2(@RequestParam(value = "size")Integer size,
                                  @RequestParam(value = "current")Integer current,
                                  @RequestParam(value = "userId")Integer userId) {
    // 根据输入参数组装分页类
    Page<Object> page = new Page<>();
    page.setSize(new Long((long)size));
    page.setCurrent(new Long((long)current));
    IPage<List<Map>> listIPage = commentService.receiveComment(page, userId);
    List<List<Map>> records = listIPage.getRecords();
    for(int i =0;i<records.size();i++){
      List<Map> maps = records.get(i);
      Map map = maps.get(i);
      String timeHui = (String)map.get("timeHui");
      String praiseCountsHui = (String)map.get("praiseCountsHui");
      String idHui = (String)map.get("idHui");
      String contentHui = (String)map.get("contentHui");
      String pubPicHui = (String)map.get("pubPicHui");
      String isZanHui = (String)map.get("isZanHui");

    }
    return new R<>(commentService.receiveComment(page,userId));
  }

	/**
	 * 新增评论
	 *
	 * @param comment 评论信息实体
	 * @return success/false
	 */
	@Inner
	@PostMapping
	public R save(@RequestBody Comment comment) {
		return new R<>(commentService.save(comment));
	}

  /**
   * app-comment 新增评论
   *
   * @param comment 评论信息实体
   * @return success/false
   */
  @Inner
  @PostMapping("/addComment")
  public Comment addComment(@RequestBody Comment comment) {
    commentService.save(comment);
    return comment;
  }

  /**
   * app-comment  评论回显
   *
   * @param id
   * @return
   */
  @Inner
  @GetMapping("/findCommentById")
  public R findCommentById(@RequestParam(value = "id") Integer id) {
    return new R(commentService.findCommentById(id));
  }

  /**
   * app-comment  评论总数
   * @param schoolId
   * @param commentCategory
   * @return
   */
  @Inner
  @GetMapping("/getAppCommentCount")
  public Integer getAppCommentCount(@RequestParam(value = "schoolId") Integer schoolId,
                                    @RequestParam(value = "commentCategory") String commentCategory){
    return commentService.getAppCommentCount(schoolId,commentCategory);
  }

	/**
	 * 通过id删除一条记录
	 * @param commentId
	 * @return R
	 */
	@Inner
	@SysLog("删除评论信息")
	@DeleteMapping("/logicDelete/{commentId}")
	public R<Object> removeLogicById(@PathVariable Integer commentId){
		Comment comment = commentService.getById(commentId);
		List<Comment> commentAll = commentService.getCommentByIdAll(commentId);
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<commentAll.size();i++){
			Comment commentTemp = commentAll.get(i);
			commentTemp.setDelFlag("1");
			commentService.updateById(commentTemp);
			sb.append(commentTemp.getId()+",");
		}
		String commentAllStr = sb.deleteCharAt(sb.length() - 1).toString();
		Boolean aBoolean = commentPraiseService.deleteCommentPraise(commentAllStr);
		return new R<>(aBoolean);
	}

  /**
   * 查看该评论是否已被该公共用户点过赞
   * @param commentId
   * @param userId
   * @return R
   */
  @Inner
  @PostMapping("/isPraise")
  public Boolean isPraise(@RequestParam(value = "commentId")Integer commentId,@RequestParam(value = "userId")Integer userId) {
      CommentPraise commentPraise = commentPraiseService.getOne(Wrappers.<CommentPraise>query().lambda()
        .eq(CommentPraise::getCommentId, commentId)
        .eq(CommentPraise::getPubId, userId));
      if(commentPraise==null){
        return false;
      }
      return true;
  }

  /**
	 * 评论点赞
	 * @param commentPraise
	 * @return R
	 */
	@Inner
	@PostMapping("/savePraise")
	public R savePraise(@RequestBody CommentPraise commentPraise) {
		return new R<>(commentPraiseService.save(commentPraise));
	}

	/**
	 * 评论取消赞(该接口保留)
	 * @param commentPraise
	 * @return R
	 */
	@Inner
	@PostMapping("/delPraise")
	public R save2(@RequestBody CommentPraise commentPraise) {
		return new R<>(commentPraiseService.save(commentPraise));
	}


  @Inner
  @GetMapping("/getCommentCount")
  public Integer getCommentCount(@RequestParam(value = "schoolId")Integer schoolId,
                                 @RequestParam(value = "commentCategory")String commentCategory) {
    Map commentCountMap = commentService.getCommentCount(schoolId, commentCategory);
    int comentCount = Integer.parseInt(String.valueOf(commentCountMap.get("commentCount")));
    return comentCount;
  }

  @Inner
  @GetMapping("/getAppComment")
  public R getAppComment(@RequestParam(value = "size") Integer size,
                         @RequestParam(value = "current") Integer current,
                         @RequestParam(value = "userId")Integer userId,
                         @RequestParam(value = "schoolId")Integer schoolId,
                         @RequestParam(value = "commentCategory")String commentCategory) {
    // 根据输入参数组装分页类
    Page<Object> page = new Page<>();
    page.setSize(new Long((long)size));
    page.setCurrent(new Long((long)current));
    IPage<Map> listIPage = commentService.getAppComment(page, userId,schoolId,commentCategory);
    List<Map> records = listIPage.getRecords();
    if (records != null && records.size()>0){
      for(int i =0;i<records.size();i++){
        records.get(i).put("reply",commentService.getReplyByCommentId(Integer.valueOf(records.get(i).get("id").toString()),userId));
      }
      return new R<>(listIPage);
    }else {
      return new R(null,"暂无评论");
    }
  }

  /**
   * app-person 收到的回复
   * @param size
   * @param current
   * @param userId
   * @return
   */
  @Inner
  @GetMapping("/getAppReceiveComment")
  public R<Object> getAppReceiveComment(@RequestParam(value = "size")Integer size,
                                  @RequestParam(value = "current")Integer current,
                                  @RequestParam(value = "userId")Integer userId) {
    // 根据输入参数组装分页类
    Page<Object> page = new Page<>();
    page.setSize(new Long((long)size));
    page.setCurrent(new Long((long)current));
    return new R<>(commentService.getAppReceiveComment(page,userId));
  }

  /**
   * app-comment 评论详情（包含该条评论的所有回复）
   * @param size
   * @param current
   * @param userId
   * @param commentId
   * @return
   */
  @Inner
  @GetMapping("/getAppCommentDetails")
  public R<Object> getAppCommentDetails(@RequestParam(value = "size")Integer size,
                                        @RequestParam(value = "current")Integer current,
                                        @RequestParam(value = "userId")Integer userId,
                                        @RequestParam(value = "commentId")Integer commentId) {
    // 根据输入参数组装分页类
    Page<Object> page = new Page<>();
    page.setSize(new Long((long)size));
    page.setCurrent(new Long((long)current));
    return new R(commentService.getAppCommentDetails(page,userId,commentId));
  }

}
