package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.dto.check.MorningCheckDTO;
import com.pig4cloud.pig.school.api.entity.check.MorningCheck;
import org.apache.ibatis.annotations.Param;

/**
 * 人员晨检管理
 *
 * @author xiesongzhe
 * @date 2019-10-30 20:14:51
 */
public interface MorningCheckMapper extends BaseMapper<MorningCheck> {
  /**
    * 人员晨检管理简单分页查询
    * @param morningCheck 人员晨检管理
    * @return
    */
  IPage<MorningCheck> getMorningCheckPage(Page page, @Param("morningCheck") MorningCheckDTO morningCheckDto);


}
