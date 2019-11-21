package com.pig4cloud.pig.school.service.message.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.mapper.AnnouncementMapper;
import com.pig4cloud.pig.school.mapper.FamilyMapper;
import com.pig4cloud.pig.school.service.message.AnnouncementService;
import com.pig4cloud.pig.school.service.message.FamilyService;
import org.springframework.stereotype.Service;

/**
 * 通知中心信息
 *
 * @author xiesongzhe
 * @date 2019-10-15 09:58:12
 */
@Service("familyService")
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, PublicUser> implements FamilyService {

  /**
   * 通知中心信息简单分页查询
   * @param publicUser 通知中心信息
   * @return
   */
  @Override
  public IPage<PublicUserVO> getFamilyPage(Page<PublicUser> page, PublicUser publicUser){
      return baseMapper.getFamilyPage(page,publicUser);
  }

}
