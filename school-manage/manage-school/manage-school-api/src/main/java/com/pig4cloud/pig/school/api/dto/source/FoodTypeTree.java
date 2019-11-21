
package com.pig4cloud.pig.school.api.dto.source;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lengleng
 * @date 2019/2/1
 * 食材类别树
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FoodTypeTree extends TreeNode {
	private String name;
}
