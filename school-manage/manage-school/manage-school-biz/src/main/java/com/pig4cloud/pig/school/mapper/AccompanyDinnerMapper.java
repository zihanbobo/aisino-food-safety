package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.entity.account.AccompanyDinner;
import com.pig4cloud.pig.school.api.vo.account.AccompanyDinnerVO;
import org.apache.ibatis.annotations.Param;

/**
 * 陪餐记录表
 *
 * @author 沈凝
 * @date 2019-07-04 11:30:54
 */
public interface AccompanyDinnerMapper extends BaseMapper<AccompanyDinner> {
	/**
	 * 陪餐记录表简单分页查询
	 * @param accompanyDinner 陪餐记录表
	 * @return
	 */
	IPage<AccompanyDinnerVO> getAccompanyDinnerPage(Page page, @Param("accompanyDinner") AccompanyDinner accompanyDinner);

  AccompanyDinnerVO getAccompanyDinnerById(@Param("id") Integer id);
}
