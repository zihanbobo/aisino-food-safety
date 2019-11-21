package com.pig4cloud.pig.school.service.account.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.account.Ultravioletlight;
import com.pig4cloud.pig.school.mapper.UltravioletlightMapper;
import com.pig4cloud.pig.school.service.account.UltravioletlightService;
import org.springframework.stereotype.Service;

/**
 * 紫外线灯使用信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 15:35:57
 */
@Service("ultravioletlightService")
public class UltravioletlightServiceImpl extends ServiceImpl<UltravioletlightMapper, Ultravioletlight> implements UltravioletlightService {

  /**
   * 紫外线灯使用信息简单分页查询
   * @param ultravioletlight 紫外线灯使用信息
   * @return
   */
  @Override
  public IPage<Ultravioletlight> getUltravioletlightPage(Page<Ultravioletlight> page, Ultravioletlight ultravioletlight){
      return baseMapper.getUltravioletlightPage(page,ultravioletlight);
  }

}
