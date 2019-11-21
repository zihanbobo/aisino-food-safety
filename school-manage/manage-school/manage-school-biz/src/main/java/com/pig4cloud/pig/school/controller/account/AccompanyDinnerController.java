package com.pig4cloud.pig.school.controller.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.entity.account.AccompanyDinner;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import com.pig4cloud.pig.school.api.vo.account.AccompanyDinnerVO;
import com.pig4cloud.pig.school.service.account.AccompanyDinnerService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.util.Date;
import java.util.List;


/**
 * 陪餐记录表
 *
 * @author 沈凝
 * @date 2019-07-04 11:30:54
 */
@RestController
@AllArgsConstructor
@RequestMapping("/accompanydinner")
public class AccompanyDinnerController {

  private final RemoteUserService remoteUserService;
  private final AccompanyDinnerService accompanyDinnerService;

	/**
	 * 简单分页查询
	 * @param page 分页对象
	 * @param accompanyDinner 陪餐记录表
	 * @return
	 */
	@GetMapping("/page")
	public R<IPage<AccompanyDinnerVO>> getAccompanyDinnerPage(Page<AccompanyDinner> page, AccompanyDinner accompanyDinner) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId = userInfo.getSysUser().getUserId();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    accompanyDinner.setSchoolId(schoolId);
	  return  new R<>(accompanyDinnerService.getAccompanyDinnerPage(page,accompanyDinner));
	}


	/**
	 * 通过id查询单条记录
	 * @param id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R<AccompanyDinner> getById(@PathVariable("id") Integer id){
		return new R<>(accompanyDinnerService.getById(id));
	}

	/**
	 * 新增记录
	 * @param accompanyDinner
	 * @return R
	 */
	@SysLog("新增陪餐记录表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sl_accompanydinner_add')")
	public R save(@RequestBody AccompanyDinner accompanyDinner){
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId = userInfo.getSysUser().getUserId();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    accompanyDinner.setSchoolId(schoolId);
		return new R<>(accompanyDinnerService.save(accompanyDinner));
	}

	/**
	 * 修改记录
	 * @param accompanyDinner
	 * @return R
	 */
	@SysLog("修改陪餐记录表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sl_accompanydinner_edit')")
	public R update(@RequestBody AccompanyDinner accompanyDinner){
		return new R<>(accompanyDinnerService.updateById(accompanyDinner));
	}

	/**
	 * 通过id删除一条记录
	 * @param id
	 * @return R
	 */
	@SysLog("删除陪餐记录表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sl_accompanydinner_del')")
	public R removeById(@PathVariable Integer id){
    AccompanyDinner accompanyDinner = accompanyDinnerService.getById(id);
    accompanyDinner.setDelFlag("1");
    return new R<>(accompanyDinnerService.updateById(accompanyDinner));
	}

  /**
   * h5数据采集新增陪餐记录
   * @param accompanyDinner
   * @return R
   */
  @Inner
  @PostMapping("/addAccompanyDinner")
  public R addAccompanyDinner(@RequestBody AccompanyDinner accompanyDinner){
    return new R<>(accompanyDinnerService.save(accompanyDinner));
  }


  /**
   * h5数据采集查询所有陪餐记录
   * @param size
   * @param current
   * @param schoolId
   * @return
   */
  @Inner
  @GetMapping("/getAllAccompanyDinner")
  public R<Object> getAllAccompanyDinner(@RequestParam(value = "size")Integer size,
                                         @RequestParam(value = "current")Integer current,
                                         @RequestParam(value = "schoolId")Integer schoolId,
                                         @RequestParam(value = "adTime",required = false)String adTime) {
    // 根据输入参数组装分页类,
    Page page = new Page<>();
    page.setSize(new Long((long)size));
    page.setCurrent(new Long((long)current));
    AccompanyDinner accompanyDinner = new AccompanyDinner();
    accompanyDinner.setSchoolId(schoolId);
    accompanyDinner.setAdTime(adTime);
    return new R<>(accompanyDinnerService.getAccompanyDinnerPage(page,accompanyDinner));
  }

  /**
   * h5数据采集通过id查询单条陪餐记录
   *
   * @param id
   * @return R
   */
  @Inner
  @GetMapping("/getAccompanyDinnerById")
  public R getAccompanyDinnerById(@RequestParam("id") Integer id) {
    /*AccompanyDinner accompanyDinner = accompanyDinnerService.getById(id);
    Integer schoolId = accompanyDinner.getSchoolId();
    Page page = new Page<>();
    page.setSize(1);
    page.setCurrent(10);
    IPage accompanyDinnerPage = accompanyDinnerService.getAccompanyDinnerPage(page, accompanyDinner);
    List records = accompanyDinnerPage.getRecords();
    AccompanyDinnerVO AccompanyDinner2 = (AccompanyDinnerVO)records.get(0);*/
    AccompanyDinnerVO accompanyDinnerById = accompanyDinnerService.getAccompanyDinnerById(id);
    return new R<>(accompanyDinnerById);
  }

  /**
   * h5数据采集修改陪餐记录
   *
   * @param accompanyDinner 陪餐实体
   * @return success/false
   */
  @Inner
  @PostMapping("/updateAccompanyDinner")
  public R updateAccompanyDinner(@RequestBody AccompanyDinner accompanyDinner) {
    return new R<>(accompanyDinnerService.updateById(accompanyDinner));
  }

  /**
   * h5数据采集通过id删除一条记录
   *
   * @param id
   * @return R
   */
  @Inner
  @GetMapping("/delAccompanyDinner")
  public R delAccompanyDinner(@RequestParam(value = "id")Integer id) {
    AccompanyDinner accompanyDinner = accompanyDinnerService.getById(id);
    accompanyDinner.setDelFlag("1");
    return new R<>(accompanyDinnerService.updateById(accompanyDinner));
//    return new R<>(mainFilingService.removeById(id));
  }
}
