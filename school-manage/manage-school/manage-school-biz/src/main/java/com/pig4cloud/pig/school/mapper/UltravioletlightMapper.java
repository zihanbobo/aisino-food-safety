package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.entity.account.Ultravioletlight;
import org.apache.ibatis.annotations.Param;

/**
 * 紫外线灯使用信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 15:35:57
 */
public interface UltravioletlightMapper extends BaseMapper<Ultravioletlight> {
  /**
    * 紫外线灯使用信息简单分页查询
    * @param ultravioletlight 紫外线灯使用信息
    * @return
    */
  IPage<Ultravioletlight> getUltravioletlightPage(Page page, @Param("ultravioletlight") Ultravioletlight ultravioletlight);


}
