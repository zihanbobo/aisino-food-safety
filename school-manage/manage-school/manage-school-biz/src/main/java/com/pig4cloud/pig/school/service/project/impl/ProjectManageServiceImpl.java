package com.pig4cloud.pig.school.service.project.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.project.ProjectManage;
import com.pig4cloud.pig.school.api.vo.project.ProjectManageVO;
import com.pig4cloud.pig.school.mapper.ProjectManageMapper;
import com.pig4cloud.pig.school.service.project.ProjectManageService;
import org.springframework.stereotype.Service;

/**
 * 管理端-项目管理
 *
 * @author 沈凝
 * @date 2019-10-17 15:30:11
 */
@Service("projectManageService")
public class ProjectManageServiceImpl extends ServiceImpl<ProjectManageMapper, ProjectManage> implements ProjectManageService {

  /**
   * 管理端-项目管理简单分页查询
   * @param projectManage 管理端-项目管理
   * @return
   */
  @Override
  public IPage<ProjectManageVO> getProjectManagePage(Page<ProjectManage> page, ProjectManage projectManage){
      return baseMapper.getProjectManagePage(page,projectManage);
  }

}
