package com.pig4cloud.pig.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.portal.api.entity.CommentPraise;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;
import org.apache.ibatis.annotations.Param;

/**
 * 公共用户信息
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
public interface CommentPraiseMapper extends BaseMapper<CommentPraise> {
  /**
    * 公共用户信息简单分页查询
    * @param publicUser 公共用户信息
    * @return
    */
  IPage<PublicUserVO> getPublicUserPage(Page page, @Param("publicUser") PublicUser publicUser);


  Boolean deleteCommentPraise(@Param("commentAllStr") String commentAllStr);

  Boolean deleteCommentPraise2(@Param("commentAllStr") String commentAllStr);


}
