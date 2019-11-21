package com.pig4cloud.pig.school.controller.statistics;


import com.pig4cloud.pig.school.service.statistics.SchoolDetailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/schooldeta")
public class SchoolDetailController {

  private final SchoolDetailService schoolDetailsService;

  //学校资质信息
  @GetMapping("/getSchoolQualification")
  public Map getAlarmListYear(@RequestParam(value="schoolid")String schoolid) {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolid",schoolid);//年

    return schoolDetailsService.getSchoolQualification(map);
  }

  //根据学校id获得供应商信息
  @GetMapping("/getSupplierInformation")
  public List<Map> getSupplierInformation(@RequestParam(value="schoolid")String schoolid){
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolid",schoolid);
    return schoolDetailsService.getSupplierInformation( map );
  }

  //根据学校id获得供应商数量
  @GetMapping("/getSuppliersum")
  public List<Map> getSuppliersum(@RequestParam(value="schoolid")String schoolid){
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolid",schoolid);
    return schoolDetailsService.getSuppliersum( map );
  }

  //根据学校id获得台账信息
  @GetMapping("/getAccount")
  public List<Map> getAccount(@RequestParam(value="schoolId")String schoolid){
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolid);
    return schoolDetailsService.getAccount( map );
  }

  //根据学校id获得设备信息
  @GetMapping("/getSchoolVdDevice")
  public List<Map> getSchoolVdDevice(@RequestParam(value="schoolId")String schoolid){
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolid);
    return schoolDetailsService.getSchoolVdDevice( map );
  }

  //设备检查合格比率
  @GetMapping("/getEqOperationRatio")
  public Map getEqOperationRatio(@RequestParam(value="schoolId")String schoolid){
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("schoolId",schoolid);
    return schoolDetailsService.getEqOperationRatio( map );
  }
}
