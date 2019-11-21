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

package com.pig4cloud.pig.school.api.feign.fallback;

import com.pig4cloud.pig.school.api.entity.School;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.api.entity.message.RegulatoryOpinion;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.pig4cloud.pig.common.core.util.R;

import java.util.List;
import java.util.Map;


/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Component
public class RemoteSchoolServiceFallbackImpl implements RemoteSchoolService {
	@Setter
	private Throwable cause;

	@Override
	public R getById(Integer id, String from) {
		log.error("feign 查询学校详情信息失败:{}", id, cause);
		return null;
	}

	@Override
	public R update(School school, String from) {
		log.error("feign 更新学校详情信息失败:{}", school, cause);
		return null;
	}
	@Override
	public R schoolList(String from) {
		log.error("feign 查询学校列表失败:{}", cause);
		return null;
	}

	@Override
	public R setIsadmin(Map<String, Object> params, String from) {
		log.error("feign 更新学校信息失败:{}",params, cause);
		return null;
	}
	@Override
	public R setIsadmin2(Integer userId,Integer schoolId,String from) {
		log.error("feign 更新学校信息失败:{}",userId,schoolId, cause);
		return null;
	}

	@Override
	public R verify(String serviceCode,String type,String from) {
		log.error("feign 验证服务码失败:{}",serviceCode, cause);
		return null;
	}

  @Override
  public R getSchoolEasyInfo(Integer id, String from) {
    log.error("app-live 获取学校信息失败:{}",id, cause);
    return null;
  }

  @Override
  public List getNoSchoolUserid(String from) {
    log.error("feign 获取学校用户管理-获取学校信息（无超级管理员的学校）失败:", cause);
    return null;
  }
  @Override
  public R<Boolean> insertAnnouncement(Announcement announcement, String from) {
    log.error("feign 新增通知中心信息失败", cause);
    return null;
  }
  @Override
  public R findPage(Integer size,Integer current,Integer userId,String from) {
    log.error("feign 查看通知中心信息失败", cause);
    return null;
  }
  @Override
  public R findForStatus(Integer size,Integer current,Integer userId,String supStatus,String from) {
    log.error("feign 查看通知中心信息失败", cause);
    return null;
  }
  @Override
  public R<Boolean> updateAnnouncement(Announcement announcement, String from) {
    log.error("feign 修改通知中心信息失败", cause);
    return null;
  }

  @Override
  public  R<Boolean> schoolUserId(Integer userId,Integer schoolId,String type,String from) {
    log.error("feign 查看通知中心信息失败", cause);
    return null;
  }
  @Override
  public R<Boolean> insertRegulatoryOpinion(RegulatoryOpinion regulatoryOpinion, String from) {
    log.error("feign 下发监管意见失败", cause);
    return null;
  }

  @Override
  public R findPageForOp(Integer size, Integer current, Integer userId, String from) {
    log.error("feign 查询监管信息失败", cause);
	  return null;
  }

}
