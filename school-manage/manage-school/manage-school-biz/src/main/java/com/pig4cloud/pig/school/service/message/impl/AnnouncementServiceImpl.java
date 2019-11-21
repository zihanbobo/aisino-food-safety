package com.pig4cloud.pig.school.service.message.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.mapper.AnnouncementMapper;

import com.pig4cloud.pig.school.service.message.AnnouncementService;
import org.springframework.stereotype.Service;

/**
 * 通知中心信息
 *
 * @author xiesongzhe
 * @date 2019-10-15 09:58:12
 */
@Service("announcementService")
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

  /**
   * 通知中心信息简单分页查询
   * @param announcement 通知中心信息
   * @return
   */
  @Override
  public IPage<Announcement> getAnnouncementPage(Page<Announcement> page, Announcement announcement){
      return baseMapper.getAnnouncementPage(page,announcement);
  }

}
