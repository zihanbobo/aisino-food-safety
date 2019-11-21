package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.entity.regulator.Education;
import org.apache.ibatis.annotations.Param;

/**
 * 教育局信息
 *
 * @author xiesongzhe
 * @date 2019-10-20 18:31:16
 */
public interface EducationMapper extends BaseMapper<Education> {
  /**
    * 教育局信息简单分页查询
    * @param education 教育局信息
    * @return
    */
  IPage<Education> getEducationPage(Page page, @Param("education") Education education);


}
