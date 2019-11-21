package com.pig4cloud.pig.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.portal.api.entity.CommentPraise;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;

/**
 * 公共用户信息
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
public interface CommentPraiseService extends IService<CommentPraise> {

  /**
   * 公共用户信息简单分页查询
   * @param publicUser 公共用户信息
   * @return
   */
  IPage<PublicUserVO> getPublicUserPage(Page<PublicUser> page, PublicUser publicUser);


  Boolean deleteCommentPraise(String commentAllStr);

  Boolean deleteCommentPraise2(String commentAllStr);


}
