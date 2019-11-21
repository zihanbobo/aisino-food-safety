package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.dto.recipe.IngredientsFilingDTO;
import com.pig4cloud.pig.school.api.entity.recipe.FoodFiling;
import com.pig4cloud.pig.school.api.entity.recipe.IngredientsFiling;
import com.pig4cloud.pig.school.api.vo.recipe.IngredientsFilingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 食材备案信息
 *
 * @author xiesongzhe
 * @date 2019-09-06 19:46:33
 */
public interface IngredientsFilingMapper extends BaseMapper<IngredientsFiling> {
  /**
    * 食材备案信息简单分页查询
    * @param ingredientsFilingDto 食材备案信息
    * @return
    */
  IPage<IngredientsFilingVO> getIngredientsFilingPage(Page page, @Param("ingredientsFiling") IngredientsFilingDTO ingredientsFilingDto);



  /**
   * 通过食谱ID，查询食材信息
   *
   * @param id
   * @return
   */
  List<IngredientsFiling> ingredientBySourceId(Integer id);
  /**
   * 通过食谱ID，查询食材信息
   *
   * @param id
   * @return
   */
  List<IngredientsFiling> materialListBySourceId(Integer id);
  /**
   * 通过采购ID，查询已备案食材信息
   *
   * @param id
   * @return
   */
  List<IngredientsFiling> materialListByPurchaseId(Integer id);



}
