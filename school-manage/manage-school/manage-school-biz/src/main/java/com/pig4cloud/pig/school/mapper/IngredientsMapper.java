package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.entity.source.Ingredients;
import org.apache.ibatis.annotations.Param;

/**
 * 食材信息
 *
 * @author xiesongzhe
 * @date 2019-09-06 14:31:18
 */
public interface IngredientsMapper extends BaseMapper<Ingredients> {
  /**
    * 食材信息简单分页查询
    * @param ingredients 食材信息
    * @return
    */
  IPage<Ingredients> getIngredientsPage(Page page, @Param("ingredients") Ingredients ingredients);


}
