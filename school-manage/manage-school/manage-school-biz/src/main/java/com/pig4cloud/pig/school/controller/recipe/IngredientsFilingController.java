package com.pig4cloud.pig.school.controller.recipe;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysUserRole;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.portal.api.entity.live.Live;
import com.pig4cloud.pig.school.api.dto.recipe.IngredientsFilingDTO;
import com.pig4cloud.pig.school.api.entity.recipe.FoodMain;
import com.pig4cloud.pig.school.api.entity.recipe.IngredientsFiling;
import com.pig4cloud.pig.school.api.entity.recipe.Source;
import com.pig4cloud.pig.school.api.vo.recipe.IngredientsFilingVO;
import com.pig4cloud.pig.school.service.recipe.IngredientsFilingService;
import com.pig4cloud.pig.school.service.source.FoodMainService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 食材备案信息
 *
 * @author xiesongzhe
 * @date 2019-09-06 19:46:33
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ingredientsfiling")
public class IngredientsFilingController {

  private final RemoteUserService remoteUserService;
  private final IngredientsFilingService ingredientsFilingService;
  private final FoodMainService foodMainService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param ingredientsFilingDto 食材备案信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<IngredientsFilingVO>> getIngredientsFilingPage(Page<IngredientsFiling> page, IngredientsFilingDTO ingredientsFilingDto) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      ingredientsFilingDto.setSchoolId(userInfo.getSysUser().getUnionId());
    }

    return new R<>(ingredientsFilingService.getIngredientsFilingPage(page,ingredientsFilingDto));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<IngredientsFiling> getById(@PathVariable("id") Integer id){
    return new R<>(ingredientsFilingService.getById(id));
  }

  /**
   * 新增记录
   * @param ingredientsFilingDto
   * @return R
   */
  @SysLog("新增食材备案信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_ingredientsfiling_add')")
  public R save(@RequestBody IngredientsFilingDTO ingredientsFilingDto){
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      ingredientsFilingDto.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    // 处理备案信息
    IngredientsFiling ingredientsFiling = new IngredientsFiling();
    BeanUtils.copyProperties(ingredientsFilingDto,ingredientsFiling);
    ingredientsFiling.setDelFlag(CommonConstants.STATUS_NORMAL);
    ingredientsFilingService.save(ingredientsFiling);

    // 处理食材内的供应商信息
    List<FoodMain> foodMainList = ingredientsFilingDto.getMainFiling()
      .stream().map(id -> {
        FoodMain foodMain = new FoodMain();
        foodMain.setFoodId(ingredientsFiling.getId());
        foodMain.setMainId(id);
        return foodMain;
      }).collect(Collectors.toList());
    return new R<>(foodMainService.saveBatch(foodMainList));
  }

  /**
   * 修改记录
   * @param ingredientsFilingDto
   * @return R
   */
  @SysLog("修改食材备案信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_ingredientsfiling_edit')")
  public R update(@RequestBody IngredientsFilingDTO ingredientsFilingDto){
    // 处理备案信息
    IngredientsFiling ingredientsFiling = new IngredientsFiling();
    BeanUtils.copyProperties(ingredientsFilingDto,ingredientsFiling);
    ingredientsFilingService.updateById(ingredientsFiling);

    // lamdba删除旧的逻辑关系
    foodMainService.remove(Wrappers.<FoodMain>update().lambda()
      .eq(FoodMain::getFoodId, ingredientsFilingDto.getId()));
    // 添加食材备案与供应商的逻辑关系
    ingredientsFilingDto.getMainFiling().forEach(mainFilingId -> {
      FoodMain foodMain = new FoodMain();
      foodMain.setFoodId(ingredientsFiling.getId());
      foodMain.setMainId(mainFilingId);
      foodMain.insert();
    });
    return new R<>(Boolean.TRUE);
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除食材备案信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('schoolMan_ingredientsfiling_del')")
  public R removeById(@PathVariable Integer id){
    // 先清除食材备案供应商信息
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("food_id",id);
    foodMainService.removeByMap(map);
    // 逻辑删除食材备案信息
    IngredientsFiling ingredientsFiling = ingredientsFilingService.getById(id);
    ingredientsFiling.setDelFlag("1");
    return new R<>(ingredientsFilingService.updateById(ingredientsFiling));
  }

}
