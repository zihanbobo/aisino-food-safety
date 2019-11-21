package com.pig4cloud.pig.school.service.source.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.source.Ingredients;
import com.pig4cloud.pig.school.mapper.IngredientsMapper;
import com.pig4cloud.pig.school.service.source.IngredientsService;
import org.springframework.stereotype.Service;

/**
 * 食材信息
 *
 * @author xiesongzhe
 * @date 2019-09-06 14:31:18
 */
@Service("ingredientsService")
public class IngredientsServiceImpl extends ServiceImpl<IngredientsMapper, Ingredients> implements IngredientsService {

  /**
   * 食材信息简单分页查询
   * @param ingredients 食材信息
   * @return
   */
  @Override
  public IPage<Ingredients> getIngredientsPage(Page<Ingredients> page, Ingredients ingredients){
      return baseMapper.getIngredientsPage(page,ingredients);
  }

}
