package com.pig4cloud.pig.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.portal.api.vo.live.LiveSchoolVO;
import com.pig4cloud.pig.portal.api.vo.live.LiveVO;
import com.pig4cloud.pig.portal.api.entity.live.Live;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 直播管理
 *
 * @author pig code generator
 * @date 2019-08-28 00:35:12
 */
public interface LiveMapper extends BaseMapper<Live> {
  /**
    * 直播管理简单分页查询
    * @param live 直播管理
    * @return
    */
  IPage<LiveVO> getLivePage(Page page, @Param("live") Live live);
  /**
    * 直播管理简单分页查询
    * @param live 直播管理
    * @return
    */
  IPage<LiveSchoolVO> getLiveSchoolPage(Page page, @Param("live") Live live);

  /**
   * app-live 获取直播首页
   * @param list
   * @return
   */
  List<Map> getLiveMain(List<Integer> list);

  /**
   * app-live 获取直播列表
   * @param schoolId
   * @return
   */
  List<Map> getLiveList(@Param("schoolId") Integer schoolId);

  /**
   * app-live 获取直播信息
   * @param liveId
   * @return
   */
  List<Map> getLiveInfo(@Param("liveId") Integer liveId);
  /**
   * app-live 获取直播信息
   * @param liveId
   * @return
   */
  List<LiveVO> getLiveListWatch(@Param("schoolId") Integer schoolId);
}
