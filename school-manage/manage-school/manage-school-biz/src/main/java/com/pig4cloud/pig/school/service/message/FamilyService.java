package com.pig4cloud.pig.school.service.message;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;
import com.pig4cloud.pig.school.api.entity.message.Announcement;


/**
 * 通知中心信息
 *
 * @author xiesongzhe
 * @date 2019-10-15 09:58:12
 */
public interface FamilyService extends IService<PublicUser> {

  /**
   * 通知中心信息简单分页查询
   * @param publicUser 通知中心信息
   * @return
   */
  IPage<PublicUserVO> getFamilyPage(Page<PublicUser> page, PublicUser publicUser);


}
