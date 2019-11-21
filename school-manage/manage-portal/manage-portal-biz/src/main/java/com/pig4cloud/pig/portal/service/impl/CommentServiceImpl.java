package com.pig4cloud.pig.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.portal.mapper.CommentMapper;
import com.pig4cloud.pig.portal.service.CommentService;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.vo.CommentVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 评论信息
 *
 * @author -
 * @date 2019-08-28 14:12:21
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

  /**
   * 评论信息简单分页查询(门户端)
   * @param comment 评论信息
   * @return
   */
  @Override
  public IPage<CommentVO> getCommentPage(Page<Comment> page, Comment comment){
      return baseMapper.getCommentPage(page,comment);
  }

  /**
   * 评论信息分页查询(管理端)
   * @param page
   * @return
   */
  @Override
  public IPage<CommentVO> getCommentPageForManage(Page<Comment> page,Comment comment) {
    return baseMapper.getCommentPageForManage(page,comment);
  }


  /**
   * 评论信息简单分页查询
   * @param parentId 评论信息
   * @return
   */
  @Override
  public List<Map> getCommentChild(Integer parentId,Integer userId){
      return baseMapper.getCommentChild(parentId,userId);
  }

  /**
   * 查询所有回复信息(管理端)
   * @param parentId
   * @return
   */
  @Override
  public List<Map> getCommentChildForManage(Integer parentId){
    return baseMapper.getCommentChildForManage(parentId);
  }

  /**
   * 根据公共用户id查询已发出的所有评论信息
   * @param userId 公共用户id
   * @return
   */
  @Override
  public IPage<List<Map>> personalComment(Page page,Integer userId){
      return baseMapper.personalComment(page,userId);
  }

  /**
   * 根据公共用户id查询已回复的所有评论信息
   * @param userId 公共用户id
   * @return
   */
  @Override
  public IPage<List<Map>> receiveComment(Page page,Integer userId){
      return baseMapper.receiveComment(page,userId);
  }

	/**
	 * 评论信息查询所有子节点(包括当前节点)
	 * @param id 评论信息
	 * @return
	 */
	@Override
	public List<Comment> getCommentByIdAll(Integer id){
		return baseMapper.getCommentByIdAll(id);
	}

	/**
   * 评论查询学校有多少人参与评论 分类型（1直播2食谱）
	 * @return
	 */
	@Override
	public Map getCommentCount(Integer schoolId,String commentCategory){
		return baseMapper.getCommentCount(schoolId,commentCategory);
	}



  /**
   * 查询所有回复信息(管理端)
   * @param userId
   * @return
   */
  @Override
  public List<Map> gerReplyComment(Integer userId){
    return baseMapper.gerReplyComment(userId);
  }

  /**
   * 评论查询学校有多少人参与评论 分类型（1直播2食谱）
   * @return
   */
  @Override
  public Map getReplyCommentParentId(Integer id){
    return baseMapper.getReplyCommentParentId(id);
  }

  /**
   * 评论信息分页查询(管理端)
   * @param page
   * @return
   */
  @Override
  public IPage<CommentVO> getReplyCommentPage(Page page,String idList) {
    return baseMapper.getReplyCommentPage(page,idList);
  }

  @Override
  public IPage<Map> getAppComment(Page<Object> page,Integer userId, Integer schoolId, String commentCategory) {
    return baseMapper.getAppComment(page,userId,schoolId,commentCategory);
  }

  @Override
  public List<Map> getReplyByCommentId(Integer commentId,Integer userId) {
    return baseMapper.getReplyByCommentId(commentId,userId);
  }

  @Override
  public Map findCommentById(Integer id) {
    return baseMapper.findCommentById(id);
  }

  /**
   * app-comment  评论总数
   * @param schoolId
   * @param commentCategory
   * @return
   */
  @Override
  public Integer getAppCommentCount(Integer schoolId, String commentCategory) {
    return baseMapper.getAppCommentCount(schoolId,commentCategory);
  }

  @Override
  public IPage<List<Map>> getAppReceiveComment(Page<Object> page, Integer userId) {
    return baseMapper.getAppReceiveComment(page,userId);
  }

  @Override
  public IPage<List<Map>> getAppCommentDetails(Page<Object> page, Integer userId, Integer commentId) {
    return baseMapper.getAppCommentDetails(page,userId,commentId);
  }

}
