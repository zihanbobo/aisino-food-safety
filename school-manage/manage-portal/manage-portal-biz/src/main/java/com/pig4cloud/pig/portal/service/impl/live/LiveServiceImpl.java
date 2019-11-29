package com.pig4cloud.pig.portal.service.impl.live;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.portal.api.entity.live.Live;
import com.pig4cloud.pig.portal.api.vo.live.LiveSchoolVO;
import com.pig4cloud.pig.portal.api.vo.live.LiveVO;
import com.pig4cloud.pig.portal.mapper.LiveMapper;
import com.pig4cloud.pig.portal.service.live.LiveService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 直播管理
 *
 * @author pig code generator
 * @date 2019-08-28 00:35:12
 */
@Service("liveService")
public class LiveServiceImpl extends ServiceImpl<LiveMapper, Live> implements LiveService {

  /**
   * 直播管理简单分页查询
   * @param live 直播管理
   * @return
   */
  @Override
  public IPage<LiveVO> getLivePage(Page<Live> page, Live live){
      return baseMapper.getLivePage(page,live);
  }
  /**
   * 直播管理简单分页查询
   * @param live 直播管理
   * @return
   */
  @Override
  public IPage<LiveSchoolVO> getLiveSchoolPage(Page<Live> page, Live live){
      return baseMapper.getLiveSchoolPage(page,live);
  }

  /**
   * app-live 获取直播首页
   * @param schoolIds
   * @return
   */
  @Override
  public List<Map> getLiveMain(List<Integer> schoolIds) {
    return baseMapper.getLiveMain(schoolIds);
  }

  /**
   * app-live 获取直播列表
   * @param schoolId
   * @return
   */
  @Override
  public List<Map> getLiveList(Integer schoolId) {
    return baseMapper.getLiveList(schoolId);
  }

  /**
   * app-live 获取直播信息
   * @param liveId
   * @return
   */
  @Override
  public List<Map> getLiveInfo(Integer liveId) {
    return baseMapper.getLiveInfo(liveId);
  }
  /**
   * app-live 获取直播信息
   * @param schoolId
   * @return
   */
  @Override
  public List<LiveVO> getLiveListWatch(Integer schoolId) {
    return baseMapper.getLiveListWatch(schoolId);
  }

}
