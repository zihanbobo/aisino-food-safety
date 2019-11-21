package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import org.apache.ibatis.annotations.Param;

/**
 * 通知中心信息
 *
 * @author xiesongzhe
 * @date 2019-10-15 09:58:12
 */
public interface FamilyMapper extends BaseMapper<PublicUser> {
  /**
    * 通知中心信息简单分页查询
    * @param publicUser 通知中心信息
    * @return
    */
  IPage<PublicUserVO> getFamilyPage(Page page, @Param("publicUser") PublicUser publicUser);


}
