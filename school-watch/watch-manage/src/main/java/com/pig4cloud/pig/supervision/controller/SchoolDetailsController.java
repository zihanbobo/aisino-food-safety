package com.pig4cloud.pig.supervision.controller;


import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.school.api.feign.SchoolDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 监管端学校详情接口
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/schooldetails")
public class SchoolDetailsController {

	private final SchoolDetailsService schooldetailsService;
  /**
   * 监管端-供应商信息 根据学校id获得供应商数量
   * @return
   */
  @GetMapping("/getSuppliersum")
  public List<Map> getSuppliersum(@RequestParam(value="schoolid")String schoolid) {
    List<Map> rList = schooldetailsService.getSuppliersum(schoolid, SecurityConstants.FROM_IN);
    return rList;
  }


  //学校资质信息
  @GetMapping("/getSchoolQualification")
  public Map getAlarmListYear(@RequestParam(value="schoolid")String schoolid) {
    return schooldetailsService.getSchoolQualification(schoolid, SecurityConstants.FROM_IN);
  }

  //根据学校id获得供应商信息
  @GetMapping("/getSupplierInformation")
  public List<Map> getSupplierInformation(@RequestParam(value="schoolid")String schoolid){

    return schooldetailsService.getSupplierInformation( schoolid, SecurityConstants.FROM_IN );
  }
  //根据学校台账信息
  @GetMapping("/getAccount")
  public List<Map> getAccount(@RequestParam(value="schoolId")String schoolid){

    return schooldetailsService.getAccount( schoolid, SecurityConstants.FROM_IN );
  }

  //根据学校设备信息
  @GetMapping("/getSchoolVdDevice")
  public List<Map> getSchoolVdDevice(@RequestParam(value="schoolId")String schoolid){

    return schooldetailsService.getSchoolVdDevice( schoolid, SecurityConstants.FROM_IN );
  }

  //设备检查合格比率
  @GetMapping("/getEqOperationRatio")
  public Map getEqOperationRatio(@RequestParam(value="schoolId")String schoolid){

    return schooldetailsService.getEqOperationRatio( schoolid, SecurityConstants.FROM_IN );
  }
}
