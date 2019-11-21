package com.pig4cloud.pig.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.portal.api.entity.CommentPraise;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;
import com.pig4cloud.pig.portal.mapper.CommentPraiseMapper;
import com.pig4cloud.pig.portal.mapper.PublicUserMapper;
import com.pig4cloud.pig.portal.service.CommentPraiseService;
import com.pig4cloud.pig.portal.service.PublicUserService;
import org.springframework.stereotype.Service;

/**
 * 公共用户信息
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@Service("commentPraiseService")
public class CommentPraiseServiceImpl extends ServiceImpl<CommentPraiseMapper, CommentPraise> implements CommentPraiseService {

  /**
   * 公共用户信息简单分页查询
   * @param publicUser 公共用户信息
   * @return
   */
  @Override
  public IPage<PublicUserVO> getPublicUserPage(Page<PublicUser> page, PublicUser publicUser){
      return baseMapper.getPublicUserPage(page,publicUser);
  }

  @Override
  public Boolean deleteCommentPraise(String commentAllStr){
      return baseMapper.deleteCommentPraise(commentAllStr);
  }

  @Override
  public Boolean deleteCommentPraise2(String commentAllStr){
      return baseMapper.deleteCommentPraise2(commentAllStr);
  }

}
