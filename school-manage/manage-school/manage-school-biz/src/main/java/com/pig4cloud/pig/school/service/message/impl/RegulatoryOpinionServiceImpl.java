package com.pig4cloud.pig.school.service.message.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.message.RegulatoryOpinion;
import com.pig4cloud.pig.school.mapper.RegulatoryOpinionMapper;
import com.pig4cloud.pig.school.service.message.RegulatoryOpinionService;
import org.springframework.stereotype.Service;

/**
 * 监管意见信息
 *
 * @author xiesongzhe
 * @date 2019-10-16 17:06:21
 */
@Service("regulatoryOpinionService")
public class RegulatoryOpinionServiceImpl extends ServiceImpl<RegulatoryOpinionMapper, RegulatoryOpinion> implements RegulatoryOpinionService {

  /**
   * 监管意见信息简单分页查询
   * @param regulatoryOpinion 监管意见信息
   * @return
   */
  @Override
  public IPage<RegulatoryOpinion> getRegulatoryOpinionPage(Page<RegulatoryOpinion> page, RegulatoryOpinion regulatoryOpinion){
      return baseMapper.getRegulatoryOpinionPage(page,regulatoryOpinion);
  }

}
