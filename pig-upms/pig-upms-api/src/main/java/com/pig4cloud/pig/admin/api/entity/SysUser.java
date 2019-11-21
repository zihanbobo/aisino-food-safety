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
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
public class SysUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "user_id", type = IdType.AUTO)
	private Integer userId;
	/**
	 * 用户名
	 */
	private String username;

	private String password;
	/**
	 * 随机盐
	 */
	@JsonIgnore
	private String salt;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 0-正常，1-删除
	 */
	@TableLogic
	private String delFlag;

	/**
	 * 锁定标记
	 */
	private String lockFlag;

	/**
	 * 简介
	 */
	private String phone;
	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 部门ID
	 */
	private Integer deptId;

	/**
	 * 微信openid
	 */
	private String wxOpenid;

	/**
	 * QQ openid
	 */
	private String qqOpenid;

	// 增加字段

	/**
	 * 关联到学校表或监管表信息
	 */
	private Integer unionId;
	/**
	 * 用户类型（字典表：1航信2学校3监管4公众）
	 */
	private String userType;
	/**
	 * 是否是超级管理员（1是）
	 */
	private String isAdmin;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 健康证
	 */
	private String healthNumber;
	/**
	 * 晨检情况（字典表：1正常 2异常）
	 */
	private String morningCheck;
	//新加字段
  /**
   * 身份证上传照片url
   */
  private String identityPic;
  /**
   * 身份证号
   */
  private String identityNumber;
  /**
   * 健康证上传图片url
   */
  private String healthPic;
  /**
   * 健康证开始时间
   */
  private LocalDate healthStarttime;
  /**
   * 健康证截止时间
   */
  private LocalDate healthEndtime;
  /**
   * 体检单位
   */
  private String medicalAddress;
  /**
   * 真实姓名
   */
  private String realName;

}
