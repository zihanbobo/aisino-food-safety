package com.pig4cloud.pig.school.service.source;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.source.SchoolCustom;


/**
 * 学校自定义食材信息
 *
 * @author xiesongzhe
 * @date 2019-09-05 20:59:49
 */
public interface SchoolCustomService extends IService<SchoolCustom> {

  /**
   * 学校自定义食材信息简单分页查询
   * @param schoolCustom 学校自定义食材信息
   * @return
   */
  IPage<SchoolCustom> getSchoolCustomPage(Page<SchoolCustom> page, SchoolCustom schoolCustom);


}
