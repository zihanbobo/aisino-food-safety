package com.pig4cloud.pig.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.vo.CommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 评论信息
 *
 * @author -
 * @date 2019-08-28 14:12:21
 */
public interface CommentMapper extends BaseMapper<Comment> {
  /**
    * 评论信息简单分页查询(门户端)
    * @param comment 评论信息
    * @return
    */
  IPage<CommentVO> getCommentPage(Page page, @Param("comment") Comment comment);

  /**
   * 评论信息简单分页查询(管理端)
   * @return
   */
  IPage<CommentVO> getCommentPageForManage(Page page, @Param("comment") Comment comment);


  /**
    * 评论信息简单分页查询
    * @param parentId 父级评论id
    * @return
    */
  List<Map> getCommentChild(@Param("parentId") Integer parentId,@Param("userId") Integer userId);

  /**
   * 评论信息简单分页查询
   * @param parentId 父级评论id
   * @return
   */
  List<Map> getCommentChildForManage(@Param("parentId") Integer parentId);


  /**
    * 公共用户发出的评论信息
    * @param page 分页对象
    * @param userId 公共用户id
    * @return
    */
  IPage<List<Map>> personalComment(Page page,@Param("userId") Integer userId);

  /**
    * 公共用户发出被回复的评论信息
    * @param page 分页对象
    * @param userId 公共用户id
    * @return
    */
  IPage<List<Map>> receiveComment(Page page,@Param("userId") Integer userId);


	/**
	 * 评论信息查询所有子节点(包括当前节点)
	 * @param id 评论信息
	 * @return
	 */
	List<Comment> getCommentByIdAll(@Param("id") Integer id);

	/**
	 * 评论查询学校有多少人参与评论 分类型（1直播2食谱）
	 * @return
	 */
	Map getCommentCount(@Param("schoolId") Integer schoolId,@Param("commentCategory") String commentCategory);




  /**
   * 个人中心查询被回复的评论，返回一个id集合
   * @return
   */
  List<Map> gerReplyComment(@Param("userId") Integer userId);
  /**
   * 个人中心查询被回复的评论id,返回父级id
   * @return
   */
  Map getReplyCommentParentId(@Param("id") Integer id);
  /**
   * 所有评论
   * @return
   */
  IPage<CommentVO> getReplyCommentPage(Page page,@Param("idList") String idList);

  /**
   * app 获取评论列表
   * @param page
   * @param schoolId
   * @param commentCategory
   * @return
   */
  IPage<Map> getAppComment(Page<Object> page,@Param("userId") Integer userId, @Param("schoolId") Integer schoolId, @Param("commentCategory") String commentCategory);

  /**
   * 根据评论id获取回复内容
   * @param commentId
   * @return
   */
  List<Map> getReplyByCommentId(@Param("commentId") Integer commentId,@Param("userId") Integer userId);

  /**
   * app-comment  评论回显
   *
   * @param id
   * @return
   */
  Map findCommentById(@Param("id") Integer id);

  /**
   * app-comment  评论总数
   * @param schoolId
   * @param commentCategory
   * @return
   */
  Integer getAppCommentCount(@Param("schoolId") Integer schoolId, @Param("commentCategory") String commentCategory);

  /**
   * app-person 收到的回复
   * @param page
   * @param userId
   * @return
   */
  IPage<List<Map>> getAppReceiveComment(Page<Object> page, @Param("userId") Integer userId);

  /**
   * app-comment 评论详情（包含该条评论的所有回复）
   * @param page
   * @param userId
   * @param commentId
   * @return
   */
  IPage<List<Map>> getAppCommentDetails(Page<Object> page, @Param("userId") Integer userId, @Param("commentId") Integer commentId);
}
