package com.pig4cloud.pig.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.entity.PublicUserSchool;
import com.pig4cloud.pig.portal.api.vo.CommentVO;

import java.util.List;

/**
 * 查询学校信息
 *
 * @author -
 * @date 2019-08-28 14:12:21
 */
public interface PublicUserSchoolService extends IService<PublicUserSchool> {

	// 根据用户id查询所绑定的学校
	List getPublicSchool(PublicUser publicUser);


	// 根据用户id查询所绑定的学校(门户端查询使用)
	List getSchoolByIdType(PublicUserSchool publicUserSchool);


}
