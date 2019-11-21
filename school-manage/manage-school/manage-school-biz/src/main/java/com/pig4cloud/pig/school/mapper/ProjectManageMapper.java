package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.entity.project.ProjectManage;
import com.pig4cloud.pig.school.api.vo.project.ProjectManageVO;
import org.apache.ibatis.annotations.Param;

/**
 * 管理端-项目管理
 *
 * @author 沈凝
 * @date 2019-10-17 15:30:11
 */
public interface ProjectManageMapper extends BaseMapper<ProjectManage> {
  /**
    * 管理端-项目管理简单分页查询
    * @param projectManage 管理端-项目管理
    * @return
    */
  IPage<ProjectManageVO> getProjectManagePage(Page page, @Param("projectManage") ProjectManage projectManage);


}
