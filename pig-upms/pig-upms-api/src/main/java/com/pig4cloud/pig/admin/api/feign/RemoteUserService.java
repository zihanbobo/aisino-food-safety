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

package com.pig4cloud.pig.admin.api.feign;

import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.feign.factory.RemoteUserServiceFallbackFactory;
import com.pig4cloud.pig.admin.api.vo.SysUserVO;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE, fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {
	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
	R<UserInfo> info(@PathVariable("username") String username
		, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @return
	 */
	@GetMapping("/social/info/{inStr}")
	R<UserInfo> social(@PathVariable("inStr") String inStr);

  // 通过手机号查询学校用户信息(h5数据采集登录)
  @GetMapping("/user/details2/{pubPhone}")
  SysUserVO queryInfo(@PathVariable("pubPhone") String pubPhone, @RequestHeader(SecurityConstants.FROM) String from);

  // 通过手机号查询学校用户信息(h5数据采集登录)
  @GetMapping("/user/details3")
  SysUserVO queryInfo2(@RequestParam(value = "pubPhone")String pubPhone, @RequestHeader(SecurityConstants.FROM) String from);

  // 获取学校人员用户信息(h5数据采集)
  @GetMapping("/user/getAllHuman")
  R<Object> getAllHuman(@RequestParam(value = "size")Integer size,
                        @RequestParam(value = "current")Integer current,
                        @RequestParam(value = "schoolId")Integer schoolId,
                        @RequestParam(value = "realName")String realName,
                        @RequestParam(value = "position")String position,
                        @RequestHeader(SecurityConstants.FROM) String from);

  // 获取字典表信息
  @GetMapping("/dict/getDictByTypeW")
  R<Object> getDictByTypeW(@RequestParam(value = "dicType")String dicType,
                        @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集 新增用户信息
  @PostMapping("/user/addHuman")
  R<Boolean> addHuman(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集更新用户信息
  @PostMapping("/user/updateHuman")
  R<Boolean>  updateHuman(@RequestBody UserDTO userDTO, @RequestHeader(SecurityConstants.FROM) String from);

  // h5删除用户信息
  @GetMapping("/user/DelHumanById")
  R<Boolean>  DelHumanById(@RequestParam(value = "id")Integer id, @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集获取单个用户信息
  @GetMapping("/user/getHumanById")
  R getHumanById(@RequestParam(value = "userId")Integer userId,
                 @RequestHeader(SecurityConstants.FROM) String from);

  // 查询是否是航信子用户
  @GetMapping("/user/isAisinoSub")
  Boolean isAisinoSub(@RequestParam(value = "userId")Integer userId,
                 @RequestHeader(SecurityConstants.FROM) String from);

  // 获取省信息
  @GetMapping("/region/getProvinceForCol")
  R<Object> getProvinceForCol(@RequestHeader(SecurityConstants.FROM) String from);

  // 获取市信息
  @GetMapping("/region/getCityForCol")
  R<Object> getCityForCol(@RequestParam(value = "provice")String provice,
                           @RequestHeader(SecurityConstants.FROM) String from);

  // 获取区信息
  @GetMapping("/region/getDistrictForCol")
  R<Object> getDistrictForCol(@RequestParam(value = "district")String district,
                           @RequestHeader(SecurityConstants.FROM) String from);


}
