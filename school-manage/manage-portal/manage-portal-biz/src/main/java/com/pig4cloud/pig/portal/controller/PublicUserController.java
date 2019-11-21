package com.pig4cloud.pig.portal.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.SysDept;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.entity.PublicUserSchool;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;
import com.pig4cloud.pig.portal.service.PublicUserSchoolService;
import com.pig4cloud.pig.portal.service.PublicUserService;
import com.pig4cloud.pig.school.api.entity.School;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 公共用户信息
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/publicuser")
public class PublicUserController {

  private final PublicUserService publicUserService;
  private final PublicUserSchoolService publicUserSchoolService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param publicUser 公共用户信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<PublicUserVO>> getPublicUserPage(Page<PublicUser> page, PublicUser publicUser) {
    return  new R<>(publicUserService.getPublicUserPage(page,publicUser));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @Inner
  @GetMapping("/{id}")
  public R<PublicUser> getById(@PathVariable("id") Integer id){
    return new R<>(publicUserService.getById(id));
  }

  /**
   * 新增记录
   * @param publicUser
   * @return R
   */
//  @SysLog("新增公共用户信息")
//  @PostMapping
//  @PreAuthorize("@pms.hasPermission('portal_publicuser_add')")
//  public R save(@RequestBody PublicUser publicUser){
//    return new R<>(publicUserService.save(publicUser));
//  }

  /**
   * 修改记录
   * @param publicUser
   * @return R
   */
  @SysLog("修改公共用户信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('portal_publicuser_edit')")
  public R update(@RequestBody PublicUser publicUser){
    return new R<>(publicUserService.updateById(publicUser));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除公共用户信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('portal_publicuser_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(publicUserService.removeById(id));
  }



	/**
	 * 根据公共查询是否绑定该学校
	 *
	 * @return 学校列表
	 */
	@Inner
	@PostMapping("/isBindSchool")
	public Boolean isBindSchool(@RequestParam(value = "schoolId")Integer schoolId,
								@RequestParam(value = "type")String type,
								@RequestParam(value = "userId")Integer userId) {
		PublicUserSchool publicUserSchool = publicUserSchoolService.getOne(Wrappers.<PublicUserSchool>query()
			.lambda()
			.eq(PublicUserSchool::getSchoolId, schoolId)
			.eq(PublicUserSchool::getPublicId, userId)
			.eq(PublicUserSchool::getType, type));
		if(publicUserSchool==null){
			return false;
		}
		return true;
	}


	/**
	 * 用户绑定学校
	 *
	 * @return 学校列表
	 */
	@Inner
	@PostMapping("/bindSchool")
	public R bindSchool(@RequestParam(value = "schoolId")Integer schoolId,
						@RequestParam(value = "type")String type,
						@RequestParam(value = "userId")Integer userId) {
		R<Object> objectR = new R<>();

		PublicUserSchool publicUserSchool = new PublicUserSchool();
		publicUserSchool.setPublicId(userId);
		publicUserSchool.setSchoolId(schoolId);
		publicUserSchool.setType(type);

		return new R<>(publicUserSchoolService.save(publicUserSchool));
	}
	/**
	 * 用户解除绑定学校
	 *
	 * @return 学校列表
	 */
	@Inner
	@PostMapping("/unBindSchool")
	public R unBindSchool(@RequestParam(value = "schoolId")Integer schoolId,
						  @RequestParam(value = "type")String type,
						  @RequestParam(value = "userId")Integer userId) {
		return new R<>(publicUserSchoolService.remove(Wrappers.<PublicUserSchool>query()
			.lambda()
			.eq(PublicUserSchool::getSchoolId, schoolId)
			.eq(PublicUserSchool::getPublicId, userId)
			.eq(PublicUserSchool::getType, type))); //根据这三个条件查询 然后热rmove
	}

	// 验证用户手机号是否注册
	@Inner
	@PostMapping("/registerPubuser")
	public R registerPubuser(@RequestParam(value = "pubPhone")String pubPhone) {
		PublicUser publicUser = new PublicUser();
		publicUser.setPubPhone(pubPhone);
		publicUser.setDelFlag("0");
		return new R<>(publicUserService.getOne(new QueryWrapper<>(publicUser)));
	}


	/**
	 * 插入公共用户
	 *
	 * @param publicUser 公共用户实体
	 * @return success/false
	 */
	@Inner
	@PostMapping
	public R save(@RequestBody PublicUser publicUser) {
		return new R<>(publicUserService.save(publicUser));
	}


	/**
	 * 根据手机号查询公共用户信息
	 *
	 * @param pubPhone 手机号
	 * @return
	 */
	@Inner
	@GetMapping("/details/{pubPhone}")
	public PublicUser queryInfo(@PathVariable String pubPhone) {
		PublicUser condition = new PublicUser();
		condition.setPubPhone(pubPhone);
		condition.setDelFlag(CommonConstants.STATUS_NORMAL);
//		PublicUser publicUser = publicUserService.getOne(Wrappers.<PublicUser>query().lambda()
//			.eq(PublicUser::getPubPhone, pubPhone)
//			.eq(PublicUser::getDelFlag, CommonConstants.STATUS_NORMAL));
		PublicUser publicUser = null;
		publicUser = publicUserService.getOne(new QueryWrapper<>(condition));
		return publicUser;
	}


	/**
	 * 修改公共用户(外接)
	 *
	 * @param publicUser 公共用户实体
	 * @return success/false
	 */
	@Inner
	@PostMapping("/updatePublicuser")
	public R updatePublicuser(@RequestBody PublicUser publicUser) {
		return new R<>(publicUserService.updateById(publicUser));
	}

	/**
	 * 根据公共用户id查询公共用户基本信息与所绑定的学校信息
	 * @param userId
	 * @return
	 */
	@Inner
	@GetMapping("/getSchoolByPubId")
	public PublicUserVO getSchoolByPubId(@RequestParam("userId") Integer userId){
		PublicUser publicUser = publicUserService.getById(userId);	//实体类

		PublicUserVO publicUserVO = new PublicUserVO();
		BeanUtils.copyProperties(publicUser, publicUserVO);	//复制属性

		List schoolList = publicUserSchoolService.getPublicSchool(publicUser);
		publicUserVO.setSchoolList(schoolList);
		return publicUserVO;
	}

  /**
   * 根据公共用户id及type查询公共用户基本信息与所绑定的学校信息-门户端使用
   * @param userId
   * @return
   */
  @Inner
  @GetMapping("/checkSchoolByPubId")
  public List checkSchoolByPubId(@RequestParam("userId") Integer userId,@RequestParam("type") String type){
    PublicUserSchool publicUserSchool = new PublicUserSchool();
    publicUserSchool.setPublicId(userId);
    publicUserSchool.setType(type);
    return publicUserSchoolService.getSchoolByIdType(publicUserSchool);
  }



	/**
	 * 通过id查询单条记录
	 * @param id
	 * @return R
	 */
	@Inner
	@GetMapping("/pubUser/{id}")
	public PublicUser getByPubId(@PathVariable("id") Integer id){
		return publicUserService.getById(id);
	}



//--------------------------------------------------------------------------------------------------------

  /**
   * 家长信息管理查询
   * @param page 分页对象
   * @param publicUser 公共用户信息
   * @return
   */
  @GetMapping("/pageSchool")
  public R<IPage<PublicUserVO>> getPublicUserSchoolPage(Page<PublicUser> page, PublicUser publicUser) {
    return  new R<>(publicUserService.getPublicUserPage(page,publicUser));
  }

}
