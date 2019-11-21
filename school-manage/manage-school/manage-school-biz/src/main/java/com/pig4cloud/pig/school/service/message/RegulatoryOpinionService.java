package com.pig4cloud.pig.school.service.message;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.message.RegulatoryOpinion;

/**
 * 监管意见信息
 *
 * @author xiesongzhe
 * @date 2019-10-16 17:06:21
 */
public interface RegulatoryOpinionService extends IService<RegulatoryOpinion> {

  /**
   * 监管意见信息简单分页查询
   * @param regulatoryOpinion 监管意见信息
   * @return
   */
  IPage<RegulatoryOpinion> getRegulatoryOpinionPage(Page<RegulatoryOpinion> page, RegulatoryOpinion regulatoryOpinion);


}
