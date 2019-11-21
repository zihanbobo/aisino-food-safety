package com.pig4cloud.pig.school.service.regulator;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.dto.regulator.EducationTree;
import com.pig4cloud.pig.school.api.dto.regulator.RegulatorTree;
import com.pig4cloud.pig.school.api.entity.regulator.Education;

import java.util.List;

/**
 * 教育局信息
 *
 * @author xiesongzhe
 * @date 2019-10-20 18:31:16
 */
public interface EducationService extends IService<Education> {

  /**
   * 教育局信息简单分页查询
   * @param education 教育局信息
   * @return
   */
  IPage<Education> getEducationPage(Page<Education> page, Education education);



  /**
   * 查询部门树菜单
   *
   * @return 树
   */
  List<EducationTree> listEducationTrees();
}
