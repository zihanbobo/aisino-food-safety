package com.pig4cloud.pig.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.entity.PublicUserSchool;
import com.pig4cloud.pig.portal.api.vo.CommentVO;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;
import com.pig4cloud.pig.portal.mapper.CommentMapper;
import com.pig4cloud.pig.portal.mapper.PublicUserMapper;
import com.pig4cloud.pig.portal.mapper.PublicUserSchoolMapper;
import com.pig4cloud.pig.portal.service.CommentService;
import com.pig4cloud.pig.portal.service.PublicUserSchoolService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论信息
 *
 * @author -
 * @date 2019-08-28 14:12:21
 */
@Service("publicUserSchoolService")
public class PublicUserSchoolServiceImpl extends ServiceImpl<PublicUserSchoolMapper, PublicUserSchool> implements PublicUserSchoolService {

	@Override
	public List getPublicSchool(PublicUser publicUser){
		return baseMapper.getPublicSchool(publicUser);
	}
	@Override
	public List getSchoolByIdType(PublicUserSchool publicUserSchool){
		return baseMapper.getSchoolByIdType(publicUserSchool);
	}

}
