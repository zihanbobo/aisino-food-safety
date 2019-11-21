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

package com.pig4cloud.pig.portal.api.feign.fallback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.entity.CommentPraise;
import com.pig4cloud.pig.portal.api.entity.Feedback;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Component
public class RemotePortalServiceFallbackImpl implements RemotePortalService {
	@Setter
	private Throwable cause;


  @Override
  public R getComment(Integer size,Integer current,Integer schoolId,Integer userId,String commentCategory,String from) {
    log.error("feign 根据学校ID查询评论信息失败", cause);
    return null;
  }

	/*@Override
	public R getComment(Integer size,Integer current,Integer schoolId,Integer userId,String from) {
		log.error("feign 根据学校ID查询评论信息失败", cause);
		return null;
	}*/

	@Override
	public R<Boolean> saveComment(Comment comment, String from) {
		log.error("feign 插入日志失败", cause);
		return null;
	}

	@Override
	public R getById(Integer id, String from) {
		log.error("feign 查询公共信息失败", cause);
		return null;
	}
	@Override
	public PublicUser getByPubId(Integer id, String from) {
		log.error("feign 查询公共用户信息失败", cause);
		return null;
	}
	@Override
	public Boolean isBindSchool(Integer schoolId,String type,Integer userId, String from) {
		log.error("feign 公共用户绑定学校失败", cause);
		return null;
	}
	@Override
	public R bindSchool(Integer schoolId,String type,Integer userId, String from) {
		log.error("feign 公共用户绑定学校失败", cause);
		return null;
	}
	@Override
	public R unBindSchool(Integer schoolId,String type,Integer userId, String from) {
		log.error("feign 公共用户解除绑定学校失败", cause);
		return null;
	}

	@Override
	public R registerPubuser(String pubPhone, String from) {
		log.error("feign 公共用户验证手机号是否存在", cause);
		return null;
	}
	@Override
	public R<Boolean> savePublicuser(PublicUser publicUser,String from) {
		log.error("feign 公共用户增加失败", cause);
		return null;
	}
	@Override
	public PublicUser queryInfo(String pubPhone,String from) {
		log.error("feign 根据手机号查询公共用户失败", cause);
		return null;
	}
	@Override
	public R updatePublicuser(PublicUser publicUser,String from) {
    log.error("feign 公众用户修改失败", cause);
    return null;
  }
	@Override
	public PublicUserVO getSchoolByPubId(Integer userId, String from) {
		log.error("feign 根据公共用户id查询所绑定的学校失败", cause);
		return null;
	}

	@Override
	public R<Boolean> saveFeedback(Feedback feedback, String from) {
		log.error("feign 公共用户意见反馈失败", cause);
		return null;
	}

	@Override
	public R<Boolean> updateFeedback(Feedback feedback, String from) {
		log.error("feign 回复公共用户意见反馈失败", cause);
		return null;
	}

  @Override
  public R getFeedback(Integer size,Integer current,Integer userId,String from) {
    log.error("feign 查询公共用户意见反馈失败", cause);
    return null;
  }
	@Override
	public R<Object> personalComment(Integer size,Integer current,Integer userId,String from) {
		log.error("feign 根据公共用户id查询已发出评论失败", cause);
		return null;
	}
	@Override
	public R<Object> receiveComment(Integer size,Integer current,Integer userId,String from) {
		log.error("feign 根据公共用户id查询已发出被回复的评论失败", cause);
		return null;
	}
	@Override
	public R<Object> getReplyComment(Integer size,Integer current,Integer userId,String from) {
		log.error("feign 根据公共用户id查询已发出被回复新的评论失败", cause);
		return null;
	}
	@Override
	public R<Object> removeLogicById(Integer commentId,String from) {
		log.error("feign 根据评论id删除评论失败", cause);
		return null;
	}
  @Override
  public Boolean isPraise(Integer commentId,Integer userId, String from) {
    log.error("feign 根据公共用户id与评论id查询该用户是否点赞失败", cause);
    return null;
  }
  @Override
  public R<Boolean> savePraise(CommentPraise commentPraise, String from) {
    log.error("feign 点赞增加失败", cause);
    return null;
  }

  @Override
  public List checkSchoolByPubId(Integer userId,String type,String from) {
    log.error("feign 根据用户id查询是否绑定学校列表失败", cause);
    return null;
  }
  @Override
  public Integer getCommentCount(Integer schoolId,String commentCategory,String from) {
    log.error("feign 根据学校查询有多少人参与评论 分类型（1直播3食谱）失败", cause);
    return null;
  }

  @Override
  public R getAppComment(Integer size,Integer userId, Integer current, Integer schoolId, String commentCategory, String fromIn) {
    log.error("feign 查询评论列表失败", cause);
	  return null;
  }

  @Override
  public Comment addComment(Comment comment, String fromIn) {
    log.error("feign app-comment 增加评论失败", cause);
    return null;
  }

  @Override
  public R findCommentById(Integer id, String from) {
    log.error("feign app-comment 评论回显", cause);
    return null;
  }

  @Override
  public Integer getAppCommentCount(Integer schoolId, String commentCategory, String from) {
    log.error("feign app-comment 评论总数", cause);
    return null;
  }

  @Override
  public R<Object> getAppReceiveComment(Integer size, Integer current, Integer userId, String from) {
    log.error("feign app-person 收到的回复", cause);
    return null;
  }

  @Override
  public R getAppCommentDetails(Integer size, Integer current, Integer userId, Integer commentId, String from) {
    log.error("feign app-comment 评论详情 ", cause);
    return null;
  }
}
