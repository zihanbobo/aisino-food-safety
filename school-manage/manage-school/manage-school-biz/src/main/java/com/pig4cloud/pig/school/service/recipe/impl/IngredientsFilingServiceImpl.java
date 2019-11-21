package com.pig4cloud.pig.school.service.recipe.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.entity.SysUserRole;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.school.api.dto.recipe.IngredientsFilingDTO;
import com.pig4cloud.pig.school.api.entity.recipe.FoodMain;
import com.pig4cloud.pig.school.api.entity.recipe.IngredientsFiling;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import com.pig4cloud.pig.school.api.vo.recipe.IngredientsFilingVO;
import com.pig4cloud.pig.school.mapper.IngredientsFilingMapper;
import com.pig4cloud.pig.school.service.recipe.IngredientsFilingService;
import com.pig4cloud.pig.school.service.source.FoodMainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 食材备案信息
 *
 * @author xiesongzhe
 * @date 2019-09-06 19:46:33
 */
@Slf4j
@Service("ingredientsFilingService")
@AllArgsConstructor
public class IngredientsFilingServiceImpl extends ServiceImpl<IngredientsFilingMapper, IngredientsFiling> implements IngredientsFilingService {

  private final FoodMainService foodMainService;

  /**
   * 食材备案信息简单分页查询
   * @param ingredientsFilingDto 食材备案信息
   * @return
   */
  @Override
  public IPage<IngredientsFilingVO> getIngredientsFilingPage(Page<IngredientsFiling> page, IngredientsFilingDTO ingredientsFilingDto){
    return baseMapper.getIngredientsFilingPage(page,ingredientsFilingDto);
  }


  /**
   * 保存食材备案信息
   *
   * @param ingredientsFilingDto DTO 对象
   * @return success/fail
   */
//  @Override
//  @Transactional(rollbackFor = Exception.class)
//  public Integer saveIngredientsFiling(IngredientsFilingDTO ingredientsFilingDto) {
//    IngredientsFiling ingredientsFiling = new IngredientsFiling();
//    BeanUtils.copyProperties(ingredientsFilingDto,ingredientsFiling);
//    ingredientsFiling.setDelFlag(CommonConstants.STATUS_NORMAL);
//    return baseMapper.insert(ingredientsFiling);
//  }
}
