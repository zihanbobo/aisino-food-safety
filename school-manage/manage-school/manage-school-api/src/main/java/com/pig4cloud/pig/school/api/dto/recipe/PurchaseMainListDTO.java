package com.pig4cloud.pig.school.api.dto.recipe;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.pig4cloud.pig.school.api.entity.recipe.PurchaseFiling;
import com.pig4cloud.pig.school.api.entity.recipe.PurchaseMainList;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 采购食材关联信息
 *
 * @author xiesongzhe
 * @date 2019-09-11 15:23:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseMainListDTO extends PurchaseMainList {
private static final long serialVersionUID = 1L;

  private List purchasesList;// 采购清单
}
