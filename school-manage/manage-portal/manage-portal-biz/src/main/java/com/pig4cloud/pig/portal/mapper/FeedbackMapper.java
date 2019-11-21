package com.pig4cloud.pig.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.pig4cloud.pig.portal.api.entity.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 意见反馈信息
 *
 * @author xiesongzhe
 * @date 2019-09-03 15:07:52
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {
  /**
    * 意见反馈信息简单分页查询
    * @param feedback 意见反馈信息
    * @return
    */
  IPage<Feedback> getFeedbackPage(Page page, @Param("feedback") Feedback feedback);

  IPage<List<Map>> getFeedbackForP(Page page, @Param("userId") Integer userId);
}
