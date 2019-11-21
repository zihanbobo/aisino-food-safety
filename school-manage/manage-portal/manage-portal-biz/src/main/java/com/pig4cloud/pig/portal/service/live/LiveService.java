package com.pig4cloud.pig.portal.service.live;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.portal.api.entity.live.Live;
import com.pig4cloud.pig.portal.api.vo.live.LiveSchoolVO;
import com.pig4cloud.pig.portal.api.vo.live.LiveVO;

import java.util.List;
import java.util.Map;

/**
 * 直播管理
 *
 * @author pig code generator
 * @date 2019-08-28 00:35:12
 */
public interface LiveService extends IService<Live> {

  /**
   * 直播管理简单分页查询
   * @param live 直播管理
   * @return
   */
  IPage<LiveVO> getLivePage(Page<Live> page, Live live);

  /**
   * 直播管理简单分页查询
   * @param live 直播管理
   * @return
   */
  IPage<LiveSchoolVO> getLiveSchoolPage(Page<Live> page, Live live);

  /**
   * app-live 获取直播首页
   * @param schoolIds
   * @return
   */
  List<Map> getLiveMain(List<Integer> schoolIds);

  /**
   * app-live 获取直播列表
   * @param schoolId
   * @return
   */
  List<Map> getLiveList(Integer schoolId);

  /**
   * app-live 获取直播信息
   * @param liveId
   * @return
   */
  List<Map> getLiveInfo(Integer liveId);


  /**
   * 监管-live 获取直播列表
   * @param schoolId
   * @return
   */
  List<LiveVO> getLiveListWatch(Integer schoolId);
}
