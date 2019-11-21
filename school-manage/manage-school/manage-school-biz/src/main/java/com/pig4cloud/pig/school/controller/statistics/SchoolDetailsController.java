package com.pig4cloud.pig.school.controller.statistics;


import com.pig4cloud.pig.school.service.statistics.SchoolDetailsService;
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
@RequestMapping("/schooldetails")
public class SchoolDetailsController {

  private final SchoolDetailsService schoolDetailsService;

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
}
