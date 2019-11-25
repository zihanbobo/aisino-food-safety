/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_t_region")
public class SysRegion extends Model<SysRegion> {

	private static final long serialVersionUID = 1L;

	/**
	 * 区划代码
	 */
	@TableId(value = "code", type = IdType.AUTO)
	private String code;
	/**
	 * 区划名称
	 */
	private String name;
	/**
	 * 显示名称
	 */
	private String displayName;
	/**
	 * 父区划
	 */
	private String pCode;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 修改人
	 */
	private String modifyUser;

}
