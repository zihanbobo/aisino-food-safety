package com.pig4cloud.pig.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.portal.api.entity.Feedback;
import com.pig4cloud.pig.portal.mapper.FeedbackMapper;

import com.pig4cloud.pig.portal.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 意见反馈信息
 *
 * @author xiesongzhe
 * @date 2019-09-03 15:07:52
 */
@Service("feedbackService")
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

  /**
   * 意见反馈信息简单分页查询
   * @param feedback 意见反馈信息
   * @return
   */
  @Override
  public IPage<Feedback> getFeedbackPage(Page<Feedback> page, Feedback feedback){
      return baseMapper.getFeedbackPage(page,feedback);
  }

  /**
   * 意见反馈信息简单分页查询
   * @return
   */
  @Override
  public IPage<List<Map>> getFeedbackPageForP(Page page, Integer userId){
    return baseMapper.getFeedbackForP(page,userId);
  }
}
