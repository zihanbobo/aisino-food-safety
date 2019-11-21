package com.pig4cloud.pig.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.portal.api.entity.Feedback;

import java.util.List;
import java.util.Map;


/**
 * 意见反馈信息
 *
 * @author xiesongzhe
 * @date 2019-09-03 15:07:52
 */
public interface FeedbackService extends IService<Feedback> {

  /**
   * 意见反馈信息简单分页查询
   * @param feedback 意见反馈信息
   * @return
   */
  IPage<Feedback> getFeedbackPage(Page<Feedback> page, Feedback feedback);

  /**
   * 意见反馈信息简单分页查询（个人中心查询）
   * @return
   */
  IPage<List<Map>> getFeedbackPageForP(Page page, Integer userId);


}
