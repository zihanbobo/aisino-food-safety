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

package com.pig4cloud.pig.admin.api.feign.fallback;

import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.admin.api.vo.SysUserVO;
import com.pig4cloud.pig.common.core.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {
	@Setter
	private Throwable cause;

	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     内外标志
	 * @return R
	 */
	@Override
	public R<UserInfo> info(String username, String from) {
		log.error("feign 查询用户信息失败:{}", username, cause);
		return null;
	}

	/**
	 * 通过社交账号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @return
	 */
	@Override
	public R<UserInfo> social(String inStr) {
		log.error("feign 查询用户信息失败:{}", inStr, cause);
		return null;
	}

  /**
   * h5数据采集登录
   * @param pubPhone
   * @param from
   * @return
   */
  @Override
  public SysUserVO queryInfo(String pubPhone, String from) {
    log.error("feign 根据手机号查询学校用户失败", cause);
    return null;
  }

  @Override
  public SysUserVO queryInfo2(String pubPhone, String from) {
    log.error("feign 根据手机号查询学校用户失败", cause);
    return null;
  }

  @Override
  public R<Object> getAllHuman(Integer size, Integer current, Integer schoolId,String realName,String position,String from) {
    log.error("feign 查询所有用户信息失败(h5数据采集)", cause);
    return null;
  }

  @Override
  public R<Object> getDictByTypeW(String dicType,String from) {
    log.error("feign 查询字典表信息(h5数据采集)", cause);
    return null;
  }

  @Override
  public R<Boolean> addHuman(SysUser sysUser, String from) {
    log.error("feign 新增用户信息失败", cause);
    return null;
  }

  @Override
  public R<Boolean> updateHuman(UserDTO userDTO,String from) {
    log.error("feign 修改用户信息失败", cause);
    return null;
  }

  @Override
  public R<Boolean> DelHumanById(Integer id,String from) {
    log.error("feign 删除用户信息失败", cause);
    return null;
  }

  @Override
  public R getHumanById(Integer userId, String from) {
    log.error("feign 获取用户信息失败", cause);
    return null;
  }

  @Override
  public Boolean isAisinoSub(Integer userId, String from) {
    log.error("feign 判断用户是否为航信子公司管理信息失败", cause);
    return null;
  }

}
