package com.pig4cloud.pig.school.service.recipe;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.school.api.dto.recipe.IngredientsFilingDTO;
import com.pig4cloud.pig.school.api.entity.recipe.IngredientsFiling;
import com.pig4cloud.pig.school.api.vo.recipe.IngredientsFilingVO;


/**
 * 食材备案信息
 *
 * @author xiesongzhe
 * @date 2019-09-06 19:46:33
 */
public interface IngredientsFilingService extends IService<IngredientsFiling> {

  /**
   * 食材备案信息简单分页查询
   * @param ingredientsFilingDto 食材备案信息
   * @return
   */
  IPage<IngredientsFilingVO> getIngredientsFilingPage(Page<IngredientsFiling> page, IngredientsFilingDTO ingredientsFilingDto);

  // 存储食材备案信息
//  Boolean saveIngredientsFiling(IngredientsFilingDTO ingredientsFilingDto);

}
