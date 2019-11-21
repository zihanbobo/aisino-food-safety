package com.pig4cloud.pig.school.service.regulator.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.dto.regulator.EducationTree;
import com.pig4cloud.pig.school.api.entity.regulator.Education;
import com.pig4cloud.pig.school.api.vo.source.TreeUtil;
import com.pig4cloud.pig.school.mapper.EducationMapper;

import com.pig4cloud.pig.school.service.regulator.EducationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 教育局信息
 *
 * @author xiesongzhe
 * @date 2019-10-20 18:31:16
 */
@Service("educationService")
public class EducationServiceImpl extends ServiceImpl<EducationMapper, Education> implements EducationService {

  /**
   * 教育局信息简单分页查询
   * @param education 教育局信息
   * @return
   */
  @Override
  public IPage<Education> getEducationPage(Page<Education> page, Education education){
    return baseMapper.getEducationPage(page,education);
  }


  /**
   * 查询全部部门树
   *
   * @return 树
   */
  @Override
  public List<EducationTree> listEducationTrees() {
    return getEducationTree(this.list(Wrappers.emptyWrapper()));
  }


  /**
   * 构建食材类别树
   *
   * @param educations 食材类别
   * @return
   */
  private List<EducationTree> getEducationTree(List<Education> educations) {
    List<EducationTree> treeList = educations.stream()
      .filter(education -> !education.getId().equals(education.getParentId()))
      .map(education -> {
        EducationTree node = new EducationTree();
        node.setId(education.getId());
        node.setParentId(education.getParentId());
        node.setEduName(education.getEduName());
        node.setEduArea(education.getEduArea());
        node.setEduTel(education.getEduTel());
        node.setEduAddress(education.getEduAddress());
        return node;
      }).collect(Collectors.toList());
    return TreeUtil.buildByLoop(treeList, 0);
  }

}
