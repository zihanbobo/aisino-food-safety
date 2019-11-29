package com.pig4cloud.pig.portal.controller.live;

import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailResponse;
import com.aliyuncs.iot.model.v20180120.QueryDeviceResponse;
import com.aliyuncs.linkvisual.model.v20180120.QueryLiveStreamingRequest;
import com.aliyuncs.linkvisual.model.v20180120.QueryLiveStreamingResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.DefaultAcsClientUtil;
import com.pig4cloud.pig.common.core.util.LinkvisualUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.portal.api.entity.live.Equipment;
import com.pig4cloud.pig.portal.api.entity.live.Live;
import com.pig4cloud.pig.portal.api.vo.live.LiveSchoolVO;
import com.pig4cloud.pig.portal.api.vo.live.LiveVO;
import com.pig4cloud.pig.portal.service.live.EquipmentService;
import com.pig4cloud.pig.portal.service.live.LiveService;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 直播管理
 *
 * @author pig code generator
 * @date 2019-08-28 00:35:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/live")
public class LiveController {

  private final EquipmentService equipmentService;
  private final RemoteUserService remoteUserService;
  private final RemoteSchoolService remoteSchoolService;
  private final LiveService liveService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param live 直播管理
   * @return
   */
  @GetMapping("/page")
  public R<IPage<LiveVO>> getLivePage(Page<Live> page, Live live) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      live.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return new R<>(liveService.getLivePage(page,live));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Live> getById(@PathVariable("id") Integer id){
    return new R<>(liveService.getById(id));
  }

  /**
   * 新增记录
   * @param live
   * @return R
   */
  @SysLog("新增直播管理")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('live_live_add')")
  public R save(@RequestBody Live live){
    return new R<>(liveService.save(live));
  }

  /**
   * 修改记录
   * @param live
   * @return R
   */
  @SysLog("修改直播管理")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('live_live_edit')")
  public R update(@RequestBody Live live){
    Equipment equipment = equipmentService.getById(live.getEqId());
    equipment.setEqStatus(live.getIsStart());
    equipmentService.updateById(equipment);
    return new R<>(liveService.updateById(live));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除直播管理")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('live_live_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(liveService.removeById(id));
  }



  /*	*//**
   * 简单分页查询
   * @param page 分页对象
   * @param live 直播管理
   * @return
   *//*
//	@Inner	 现有的直播查询接口根据学校id
	@GetMapping("/livePage")
	public R getLiveSchoolPage(Page<Live> page, Live live) {
		int schoolId = live.getSchoolId();
		R byId = remoteSchoolService.getById(schoolId,SecurityConstants.FROM_IN);
//		ObjectMapper mapper = new ObjectMapper();
		// 转对象
//		School school = mapper.convertValue(byId.getData(), School.class);
//		School school = (School)byId.getData();

		IPage<LiveSchoolVO> liveSchoolPage = liveService.getLiveSchoolPage(page, live);
		R<Object> objectR = new R<>();
		objectR.setData(liveSchoolPage);
		return objectR;
	}*/

  /**
   * 现有的直播查询接口根据学校id
   * @param size
   * @param current
   * @param schoolId
   * @return
   */
  @Inner
  @GetMapping("/livePage")
  public R getLiveSchoolPage(Integer size,Integer current,Integer schoolId) {
    Live live = new Live();
    live.setSchoolId(schoolId);
    /*int schoolId = live.getSchoolId();*/
    Page<Live> page = new Page<>();
    page.setSize(new Long((long) size));
    page.setCurrent(new Long((long) current));
    R byId = remoteSchoolService.getById(schoolId,SecurityConstants.FROM_IN);
    IPage<LiveSchoolVO> liveSchoolPage = liveService.getLiveSchoolPage(page, live);
    R<Object> objectR = new R<>();
    objectR.setData(liveSchoolPage);
    return objectR;
  }

  /**
   * app-live 获取直播首页
   * @param schoolIds
   * @return
   */
  @Inner
  @GetMapping("/getLiveMain")
  public R getLiveMain(@RequestParam(value ="schoolIds") List<Integer> schoolIds) {
    List<Map> list = liveService.getLiveMain(schoolIds);
    return new R(list);
  }

  /**
   * app-live 获取直播列表
   * @param schoolId
   * @return
   */
  @Inner
  @GetMapping("/getLiveList")
  public R getLiveList(@RequestParam(value ="schoolId") Integer schoolId) {
    List<Map> list = liveService.getLiveList(schoolId);
    return new R(list);
  }

  /**
   * app-live 获取直播信息
   * @param liveId
   * @return
   */
  @Inner
  @GetMapping("/getLiveInfo")
  public R getLiveInfo(@RequestParam(value ="liveId") Integer liveId) {
    List<Map> list = liveService.getLiveInfo(liveId);
    return new R(list);
  }

  @PostMapping("/getLivePath")
  public Map<String,Object> getLivePath(@RequestParam(value="eqId", required = false) Integer eqId){
    Map map = new HashMap();
//    Live live = liveService.getById(id);
    Equipment equipment = equipmentService.getById(eqId);
    String lotId = equipment.getEqId();
    String path = findRtmpByIotId(lotId);
    map.put("path",path);
    return map;
  }


  @GetMapping("/getIotList")
  public R getIotList(){
    Map map = new HashMap();
    List<String> list = new ArrayList<String>();
    List<QueryDeviceResponse.DeviceInfo> deviceInfo = null;
    QueryDeviceResponse a1ZQwIdRsw0 = DefaultAcsClientUtil.queryDevice("a1ZQwIdRsw0");
    if(a1ZQwIdRsw0.getSuccess()){
      deviceInfo = a1ZQwIdRsw0.getData();
      for(int i =0;i<=deviceInfo.size();i++){
        Equipment equipment = equipmentService.getOne(Wrappers.<Equipment>query().lambda()
          .eq(Equipment::getDelFlag, "0")
          .eq(Equipment::getEqAdmin, String.valueOf(deviceInfo.get(i).getDeviceName())));
        if(equipment!=null){
          deviceInfo.remove(i);
        }else{
          list.add(deviceInfo.get(i).getDeviceName()+"-"+deviceInfo.get(i).getNickname());
        }
      }
    }
    return new R<>(deviceInfo);
  }



  @PostMapping("/getIoTLivePath")
  public Map<String,Object> getIoTLivePath(@RequestParam(value="eqId", required = false) Integer eqId){
    Map map = new HashMap();
//    Live live = liveService.getById(id);
    Equipment equipment = equipmentService.getById(eqId);
    String deviceName = equipment.getEqAdmin();
    QueryDeviceDetailResponse a1ZQwIdRsw0 = DefaultAcsClientUtil.queryDeviceDetail("a1ZQwIdRsw0", deviceName);
    if(a1ZQwIdRsw0.getSuccess()){
      String iotId = a1ZQwIdRsw0.getData().getIotId();
      String path = findRtmpByIotId(iotId);
      map.put("path",path);
    }else{
      map.put("path","");
    }
    return map;
  }


  // 监管端获取学校设备信息
  @Inner
  @GetMapping("/getLiveSchoolWatch")
  public R getLiveSchoolWatch(@RequestParam(value = "schoolId") Integer schoolId){
    return new R<>(liveService.getLiveListWatch(schoolId)
    );
  }


  @Inner
  @GetMapping("/getIoTLivePathWatch")
  public R getIoTLivePathWatch(@RequestParam(value="eqId", required = false) Integer eqId){
    Map map = new HashMap();
    //Live live = liveService.getById(id);
    Equipment equipment = equipmentService.getById(eqId);
    String deviceName = equipment.getEqAdmin();
    QueryDeviceDetailResponse a1ZQwIdRsw0 = DefaultAcsClientUtil.queryDeviceDetail("a1ZQwIdRsw0", deviceName);
    if(a1ZQwIdRsw0.getSuccess()){
      String iotId = a1ZQwIdRsw0.getData().getIotId();
      String path = findRtmpByIotId(iotId);
      map.put("path",path);
    }else{
      map.put("path","");
    }
    return new R<>(map);
  }



  public String findRtmpByIotId(String iotId) {
    String rtmp="";
    try{
      QueryLiveStreamingRequest qlst=new QueryLiveStreamingRequest();
      qlst.setIotId(iotId);
      qlst.setStreamType(0);
      QueryLiveStreamingResponse qlse= LinkvisualUtil.getSdkClient().getAcsResponse(qlst);

      //System.out.println(JSONObject.toJSONString(qlse));
      System.out.println(qlse.getRequestId());
      if(qlse.getSuccess()){
        rtmp=qlse.getData().getPath();
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return rtmp;
  }




}
