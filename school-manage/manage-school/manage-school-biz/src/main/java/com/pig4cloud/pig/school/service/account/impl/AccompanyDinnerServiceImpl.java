package com.pig4cloud.pig.school.service.account.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.account.AccompanyDinner;
import com.pig4cloud.pig.school.api.vo.account.AccompanyDinnerVO;
import com.pig4cloud.pig.school.mapper.AccompanyDinnerMapper;
import com.pig4cloud.pig.school.service.account.AccompanyDinnerService;
import org.springframework.stereotype.Service;

/**
 * 陪餐记录表
 *
 * @author 沈凝
 * @date 2019-07-04 11:30:54
 */
@Service("accompanyDinnerService")
public class AccompanyDinnerServiceImpl extends ServiceImpl<AccompanyDinnerMapper, AccompanyDinner> implements AccompanyDinnerService {

	/**
	 * 陪餐记录表简单分页查询
	 * @param accompanyDinner 陪餐记录表
	 * @return
	 */
	@Override
	public IPage<AccompanyDinnerVO> getAccompanyDinnerPage(Page<AccompanyDinner> page, AccompanyDinner accompanyDinner){
		return baseMapper.getAccompanyDinnerPage(page,accompanyDinner);
	}

  @Override
  public AccompanyDinnerVO getAccompanyDinnerById(Integer id) {
    return baseMapper.getAccompanyDinnerById(id);
  }

}
