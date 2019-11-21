package com.pig4cloud.pig.school.controller.source;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.school.api.entity.source.Ingredients;
import com.pig4cloud.pig.school.api.entity.source.SchoolCustom;
import com.pig4cloud.pig.school.service.source.IngredientsService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 食材信息
 *
 * @author xiesongzhe
 * @date 2019-09-06 14:31:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ingredients")
public class IngredientsController {

  private final IngredientsService ingredientsService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param ingredients 食材信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Ingredients>> getIngredientsPage(Page<Ingredients> page, Ingredients ingredients) {
    return  new R<>(ingredientsService.getIngredientsPage(page,ingredients));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Ingredients> getById(@PathVariable("id") Integer id){
    return new R<>(ingredientsService.getById(id));
  }

  /**
   * 新增记录
   * @param ingredients
   * @return R
   */
  @SysLog("新增食材信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_ingredients_add')")
  public R save(@RequestBody Ingredients ingredients){
    return new R<>(ingredientsService.save(ingredients));
  }

  /**
   * 修改记录
   * @param ingredients
   * @return R
   */
  @SysLog("修改食材信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_ingredients_edit')")
  public R update(@RequestBody Ingredients ingredients){
    return new R<>(ingredientsService.updateById(ingredients));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除食材信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('schoolMan_ingredients_del')")
  public R removeById(@PathVariable Integer id){
    Ingredients ingredients = ingredientsService.getById(id);
    ingredients.setDelFlag("1");
    return new R<>(ingredientsService.updateById(ingredients));
//    return new R<>(ingredientsService.removeById(id));
  }

}
