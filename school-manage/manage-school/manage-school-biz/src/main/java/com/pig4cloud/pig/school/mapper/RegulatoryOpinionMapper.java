package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.entity.message.RegulatoryOpinion;
import org.apache.ibatis.annotations.Param;

/**
 * 监管意见信息
 *
 * @author xiesongzhe
 * @date 2019-10-16 17:06:21
 */
public interface RegulatoryOpinionMapper extends BaseMapper<RegulatoryOpinion> {
  /**
    * 监管意见信息简单分页查询
    * @param regulatoryOpinion 监管意见信息
    * @return
    */
  IPage<RegulatoryOpinion> getRegulatoryOpinionPage(Page page, @Param("regulatoryOpinion") RegulatoryOpinion regulatoryOpinion);


}
