package com.pig4cloud.pig.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.entity.PublicUserSchool;
import com.pig4cloud.pig.portal.api.vo.CommentVO;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论信息
 *
 * @author -
 * @date 2019-08-28 14:12:21
 */
public interface PublicUserSchoolMapper extends BaseMapper<PublicUserSchool> {

	/**
	 * 公共用户信息简单分页查询
	 * @param publicUser 公共用户信息
	 * @return
	 */
	List getPublicSchool(@Param("publicUser") PublicUser publicUser);
	/**
	 * 公共用户信息简单查询
	 * @param publicUserSchool 公共用户信息
	 * @return
	 */
	List getSchoolByIdType(@Param("publicUserSchool") PublicUserSchool publicUserSchool);

}
