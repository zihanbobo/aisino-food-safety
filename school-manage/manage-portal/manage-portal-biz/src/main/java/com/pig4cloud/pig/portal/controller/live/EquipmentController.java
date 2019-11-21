package com.pig4cloud.pig.portal.controller.live;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.aliyuncs.iot.model.v20180120.QueryDeviceResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysDeptRelation;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.DefaultAcsClientUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.portal.api.entity.live.Equipment;
import com.pig4cloud.pig.portal.api.entity.live.Live;
import com.pig4cloud.pig.portal.service.live.EquipmentService;
import com.pig4cloud.pig.portal.service.live.LiveService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 设备表
 *
 * @author -
 * @date 2019-08-27 17:11:58
 */
@RestController
@AllArgsConstructor
@RequestMapping("/equipment")
public class EquipmentController {

	private final EquipmentService equipmentService;
	private final RemoteUserService remoteUserService;
	private final LiveService liveService;
	/**
	 * 简单分页查询
	 * @param page 分页对象
	 * @param equipment 设备表
	 * @return
	 */
	@GetMapping("/page")
	public R<IPage<Equipment>> getEquipmentPage(Page<Equipment> page, Equipment equipment) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      equipment.setSchoolId(userInfo.getSysUser().getUnionId());
    }
	  return  new R<>(equipmentService.getEquipmentPage(page,equipment));
	}


	/**
	 * 通过id查询单条记录
	 * @param id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R<Equipment> getById(@PathVariable("id") Integer id){
		return new R<>(equipmentService.getById(id));
	}

	/**
	 * 新增记录
	 * @param equipment
	 * @return R
	 */
	@SysLog("新增设备表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('managepor_equipment_add')")
	public R save(@RequestBody Equipment equipment){
		PigUser user = SecurityUtils.getUser();
		String username = user.getUsername();	// 当前登录用户昵称
		R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
		UserInfo userInfo = result.getData();
		Integer userId = userInfo.getSysUser().getUserId();
		equipment.setCreateById(userId);
    equipment.setSchoolId(userInfo.getSysUser().getUnionId());
		boolean save = equipmentService.save(equipment);

		// 新增直播信息
		Live live = new Live();
		live.setIsStart(equipment.getEqStatus());
		live.setSchoolId(equipment.getSchoolId());
		live.setEqId(equipment.getId());
		liveService.save(live);
		return new R<>(save);
	}

	/**
	 * 修改记录
	 * @param equipment
	 * @return R
	 */
	@SysLog("修改设备表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('managepor_equipment_edit')")
	public R update(@RequestBody Equipment equipment){
		Live live = liveService.getOne(Wrappers.<Live>query()
			.lambda().eq(Live::getEqId, equipment.getId()));
		live.setSchoolId(equipment.getSchoolId());
		live.setIsStart(equipment.getEqStatus());
		liveService.updateById(live);
		return new R<>(equipmentService.updateById(equipment));
	}

	/**
	 * 通过id删除一条记录
	 * @param id
	 * @return R
	 */
	@SysLog("删除设备表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('managepor_equipment_del')")
	public R removeById(@PathVariable Integer id){
		Live live = liveService.getOne(Wrappers.<Live>query()
			.lambda().eq(Live::getEqId, id));
		live.setDelFlag("1");
		liveService.updateById(live);

		Equipment equipment = equipmentService.getById(id);
		equipment.setDelFlag("1");
		return new R<>(equipmentService.updateById(equipment));
//		return new R<>(equipmentService.removeById(id));
	}

  /**
   * 批量导入接口
   * @param file
   * @param userId
   * @param schoolId
   * @return
   * @throws IOException
   */
  @PostMapping("/equipmentUpload")
  public Map<String,Object> equipmentUpload(@RequestParam("file") MultipartFile file,
                                      @RequestParam("userId") Integer userId,
                                      @RequestParam("schoolId") Integer schoolId
  ) throws IOException {
    ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
    List<Equipment> equipmentList = reader.readAll(Equipment.class);
    int count = 0;// 判断循环下标
    Equipment equipment = null;
//    equipmentList.stream().forEach(equipment ->{
    for(int i=0;i<equipmentList.size();i++){
      equipment = equipmentList.get(i);
      if(count==0){
        count++;
        continue;//跳出本次循环
      }
      equipment.setCreateById(userId);
      equipment.setSchoolId(schoolId);
      equipment.setEqStatus("1");//设备关闭
      equipmentService.save(equipment);
      // 新增直播信息
      Live live = new Live();
      live.setIsStart(equipment.getEqStatus());
      live.setSchoolId(equipment.getSchoolId());
      live.setEqId(equipment.getId());
      live.setIsStart("1");//直播关闭
      liveService.save(live);
      count++;
//    });
    }
    Map map = new HashMap();
    map.put("count",count-1);
    return map;
  }

  @GetMapping("/getIotList")
  public R getIotList(){
    Map map = new HashMap();
    List<String> list = new ArrayList<String>();
    List<QueryDeviceResponse.DeviceInfo> deviceInfo = null;
    List<QueryDeviceResponse.DeviceInfo> deviceInfoR = new ArrayList<QueryDeviceResponse.DeviceInfo>();
    QueryDeviceResponse a1ZQwIdRsw0 = DefaultAcsClientUtil.queryDevice("a1ZQwIdRsw0");
    if(a1ZQwIdRsw0.getSuccess()){
      deviceInfo = a1ZQwIdRsw0.getData();
      for(int i=0;i< deviceInfo.size();i++){
        Equipment equipment = equipmentService.getOne(Wrappers.<Equipment>query().lambda()
          .eq(Equipment::getDelFlag, "0")
          .eq(Equipment::getEqAdmin, String.valueOf(deviceInfo.get(i).getDeviceName())));
        if(equipment==null){
          deviceInfoR.add(deviceInfo.get(i));
        }
      }
    }
    return new R<>(deviceInfoR);
  }






}
