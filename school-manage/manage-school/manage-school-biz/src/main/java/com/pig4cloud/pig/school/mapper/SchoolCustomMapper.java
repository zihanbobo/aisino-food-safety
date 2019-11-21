package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.entity.source.SchoolCustom;
import org.apache.ibatis.annotations.Param;

/**
 * 学校自定义食材信息
 *
 * @author xiesongzhe
 * @date 2019-09-05 20:59:49
 */
public interface SchoolCustomMapper extends BaseMapper<SchoolCustom> {
  /**
    * 学校自定义食材信息简单分页查询
    * @param schoolCustom 学校自定义食材信息
    * @return
    */
  IPage<SchoolCustom> getSchoolCustomPage(Page page, @Param("schoolCustom") SchoolCustom schoolCustom);


}
