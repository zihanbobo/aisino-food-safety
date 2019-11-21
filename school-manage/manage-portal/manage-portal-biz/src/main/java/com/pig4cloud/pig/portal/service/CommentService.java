package com.pig4cloud.pig.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.vo.CommentVO;

import java.util.List;
import java.util.Map;

/**
 * 评论信息
 *
 * @author -
 * @date 2019-08-28 14:12:21
 */
public interface CommentService extends IService<Comment> {

  /**
   * 评论信息简单分页查询
   * @param comment 评论信息
   * @return
   */
  IPage<CommentVO> getCommentPage(Page<Comment> page, Comment comment);

  //getCommentPageForManage  管理端评论
  IPage<CommentVO> getCommentPageForManage(Page<Comment> page,Comment comment);

  List<Map> getCommentChild(Integer parentId,Integer userId);
  //管理端回复
  List<Map> getCommentChildForManage(Integer parentId);

  IPage<List<Map>> personalComment(Page page,Integer userId);


  IPage<List<Map>> receiveComment(Page page,Integer userId);


  List<Comment> getCommentByIdAll(Integer id);

  Map getCommentCount(Integer schoolId,String commentCategory);






  List<Map> gerReplyComment(Integer id);

  Map getReplyCommentParentId(Integer id);

  IPage<CommentVO> getReplyCommentPage(Page page,String idList);

  IPage<Map> getAppComment(Page<Object> page,Integer userId, Integer schoolId, String commentCategory);

  List<Map> getReplyByCommentId(Integer id,Integer userId);

  Map findCommentById(Integer id);

  Integer getAppCommentCount(Integer schoolId, String commentCategory);

  IPage<List<Map>> getAppReceiveComment(Page<Object> page, Integer userId);

  IPage<List<Map>> getAppCommentDetails(Page<Object> page, Integer userId, Integer commentId);
}
