package com.pig4cloud.pig.school.service.source;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.source.Ingredients;


/**
 * 食材信息
 *
 * @author xiesongzhe
 * @date 2019-09-06 14:31:18
 */
public interface IngredientsService extends IService<Ingredients> {

  /**
   * 食材信息简单分页查询
   * @param ingredients 食材信息
   * @return
   */
  IPage<Ingredients> getIngredientsPage(Page<Ingredients> page, Ingredients ingredients);


}
