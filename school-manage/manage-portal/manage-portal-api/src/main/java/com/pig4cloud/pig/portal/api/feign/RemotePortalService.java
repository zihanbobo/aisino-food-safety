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

package com.pig4cloud.pig.portal.api.feign;

import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.entity.Comment;
import com.pig4cloud.pig.portal.api.entity.CommentPraise;
import com.pig4cloud.pig.portal.api.entity.Feedback;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.feign.factory.RemotePortalServiceFallbackFactory;
import com.pig4cloud.pig.portal.api.vo.PublicUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@FeignClient(value = ServiceNameConstants.PORTAL_SERVICE, fallbackFactory = RemotePortalServiceFallbackFactory.class)
public interface RemotePortalService {

  /*// 根据学校ID查询评论信息
  @GetMapping("/comment/getComment")
  /*R getComment(@RequestBody Page<Comment> page,@RequestParam(value = "schoolId")Integer schoolId,@RequestHeader(SecurityConstants.FROM)String from);*/
  @GetMapping("/comment/getComment")
  R getComment(@RequestParam(value = "size") Integer size, @RequestParam(value = "current") Integer current,
               @RequestParam(value = "schoolId") Integer schoolId, @RequestParam(value = "userId") Integer userId,
               @RequestParam(value = "commentCategory") String commentCategory, @RequestHeader(SecurityConstants.FROM) String from);

  // 新增评论
  @PostMapping("/comment")
  R<Boolean> saveComment(@RequestBody Comment comment, @RequestHeader(SecurityConstants.FROM) String from);

  // 根据userId获取用户信息
  @GetMapping("/publicuser/{id}")
  R getById(@PathVariable("id") Integer id, @RequestHeader(SecurityConstants.FROM) String from);

  // 根据userId获取用户信息
  @GetMapping("/publicuser/pubUser/{id}")
  PublicUser getByPubId(@PathVariable("id") Integer id, @RequestHeader(SecurityConstants.FROM) String from);


  // 验证学校是否已经绑定该学校
  @PostMapping("/publicuser/isBindSchool")
  Boolean isBindSchool(@RequestParam(value = "schoolId") Integer schoolId,
                       @RequestParam(value = "type") String type,
                       @RequestParam(value = "userId") Integer userId, @RequestHeader(SecurityConstants.FROM) String from);

  // 公众用户绑定学校
  @PostMapping("/publicuser/bindSchool")
  R bindSchool(@RequestParam(value = "schoolId") Integer schoolId,
               @RequestParam(value = "type") String type,
               @RequestParam(value = "userId") Integer userId, @RequestHeader(SecurityConstants.FROM) String from);

  // 公众用户解除绑定学校
  @PostMapping("/publicuser/unBindSchool")
  R unBindSchool(@RequestParam(value = "schoolId") Integer schoolId,
                 @RequestParam(value = "type") String type,
                 @RequestParam(value = "userId") Integer userId, @RequestHeader(SecurityConstants.FROM) String from);

  // 公共用户注册
  // 验证公共用户手机号
  @PostMapping("/publicuser/registerPubuser")
  R registerPubuser(@RequestParam(value = "pubPhone") String pubPhone, @RequestHeader(SecurityConstants.FROM) String from);

  // 新增公共用户
  @PostMapping("/publicuser")
  R<Boolean> savePublicuser(@RequestBody PublicUser publicUser, @RequestHeader(SecurityConstants.FROM) String from);

  // 通过手机号查询公众用户信息
  @GetMapping("/publicuser/details/{pubPhone}")
  PublicUser queryInfo(@PathVariable("pubPhone") String pubPhone, @RequestHeader(SecurityConstants.FROM) String from);

  // 更新用户信息
  @PostMapping("/publicuser/updatePublicuser")
  R updatePublicuser(@RequestBody PublicUser publicUser, @RequestHeader(SecurityConstants.FROM) String from);

  // 根据公共用户id查绑定学校
  @GetMapping("/publicuser/getSchoolByPubId")
  PublicUserVO getSchoolByPubId(@RequestParam(value = "userId") Integer userId, @RequestHeader(SecurityConstants.FROM) String from);

  // 新增意见反馈
  @PostMapping("/feedback")
  R<Boolean> saveFeedback(@RequestBody Feedback feedback, @RequestHeader(SecurityConstants.FROM) String from);

  // 查询意见反馈信息(门户个人中心)
  @GetMapping("/feedback/getFeedback")
  R getFeedback(@RequestParam(value = "size") Integer size,
                @RequestParam(value = "current") Integer current,
                @RequestParam(value = "userId") Integer userId,
                @RequestHeader(SecurityConstants.FROM) String from);

  // 管理端回复意见反馈
  @PostMapping("/feedback/updateFeedback")
  R<Boolean> updateFeedback(@RequestBody Feedback feedback, @RequestHeader(SecurityConstants.FROM) String from);

  // 门户端获取评论
//	@GetMapping("/comment/getComment")
//	R<Object> getComment(@RequestBody Page<Comment> page,@RequestHeader(SecurityConstants.FROM) String from);


  //	门户端个人中心获取评论
  @GetMapping("/comment/personalComment")
  R<Object> personalComment(@RequestParam(value = "size") Integer size,
                            @RequestParam(value = "current") Integer current,
                            @RequestParam(value = "userId") Integer userId,
                            @RequestHeader(SecurityConstants.FROM) String from);

  //	门户端个人中心获取回复的评论
  @GetMapping("/comment/receiveComment")
  R<Object> receiveComment(@RequestParam(value = "size") Integer size,
                           @RequestParam(value = "current") Integer current,
                           @RequestParam(value = "userId") Integer userId,
                           @RequestHeader(SecurityConstants.FROM) String from);

  //	门户端个人中心获取回复的评论(新)
  @GetMapping("/comment/getReplyComment")
  R<Object> getReplyComment(@RequestParam(value = "size") Integer size,
                            @RequestParam(value = "current") Integer current,
                            @RequestParam(value = "userId") Integer userId,
                            @RequestHeader(SecurityConstants.FROM) String from);

  //	门户端个人中心删除评论
  @DeleteMapping("/comment/logicDelete/{commentId}")
  R<Object> removeLogicById(@PathVariable("commentId") Integer commentId,
                            @RequestHeader(SecurityConstants.FROM) String from);

  // 管理端回复意见反馈
  @PostMapping("/comment/isPraise")
  Boolean isPraise(@RequestParam(value = "commentId") Integer commentId,
                   @RequestParam(value = "userId") Integer userId,
                   @RequestHeader(SecurityConstants.FROM) String from);

  // 新增点赞信息
  @PostMapping("/comment/savePraise")
  R<Boolean> savePraise(@RequestBody CommentPraise commentPraise, @RequestHeader(SecurityConstants.FROM) String from);


  /*@GetMapping("/comment/getComment")
  R getComment(@RequestParam(value = "size")Integer size,@RequestParam(value = "current")Integer current,
               @RequestParam(value = "schoolId")Integer schoolId,@RequestParam(value = "userId")Integer userId,
               @RequestHeader(SecurityConstants.FROM)String from);*/
  // 获取用户绑定学校信息
  @GetMapping("/publicuser/checkSchoolByPubId")
  List checkSchoolByPubId(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "type") String type,
                          @RequestHeader(SecurityConstants.FROM) String from);

  // 评论查询学校有多少人参与评论 分类型（1直播3食谱）
  @GetMapping("/comment/getCommentCount")
  Integer getCommentCount(@RequestParam(value = "schoolId") Integer schoolId, @RequestParam(value = "commentCategory") String commentCategory,
                          @RequestHeader(SecurityConstants.FROM) String from);

  /**
   * app 查询评论列表
   *
   * @param size
   * @param current
   * @param schoolId
   * @param commentCategory
   * @param from
   * @return
   */
  @GetMapping("/comment/getAppComment")
  R getAppComment(@RequestParam(value = "size") Integer size,
                  @RequestParam(value = "current") Integer current,
                  @RequestParam(value = "userId")Integer userId,
                  @RequestParam(value = "schoolId") Integer schoolId,
                  @RequestParam(value = "commentCategory") String commentCategory,
                  @RequestHeader(SecurityConstants.FROM) String from);

  /**
   * app-comment 添加评论
   *
   * @param comment
   * @param from
   * @return
   */
  @PostMapping("/comment/addComment")
  Comment addComment(Comment comment, @RequestHeader(SecurityConstants.FROM) String from);

  /**
   * app-comment 评论回显
   *
   * @param id
   * @return
   */
  @GetMapping("/comment/findCommentById")
  R findCommentById(@RequestParam(value = "id") Integer id, @RequestHeader(SecurityConstants.FROM) String from);

  /**
   * app-comment 查询学校评论总数
   * @param schoolId
   * @param commentCategory 1 直播  3 食谱
   * @param from
   * @return
   */
  @GetMapping("/comment/getAppCommentCount")
  Integer getAppCommentCount(@RequestParam(value = "schoolId") Integer schoolId,
                       @RequestParam(value = "commentCategory") String commentCategory,
                       @RequestHeader(SecurityConstants.FROM) String from);

  /**
   * app-person 收到的回复
   * @param size
   * @param current
   * @param userId
   * @param from
   * @return
   */
  @GetMapping("/comment/getAppReceiveComment")
  R<Object> getAppReceiveComment(@RequestParam(value = "size") Integer size,
                           @RequestParam(value = "current") Integer current,
                           @RequestParam(value = "userId") Integer userId,
                           @RequestHeader(SecurityConstants.FROM) String from);

  /**
   *
   * @param size
   * @param current
   * @param userId
   * @param commentId
   * @param from
   * @return
   */
  @GetMapping("/comment/getAppCommentDetails")
  R getAppCommentDetails(@RequestParam(value = "size") Integer size,
                         @RequestParam(value = "current") Integer current,
                         @RequestParam(value = "userId") Integer userId,
                         @RequestParam(value = "commentId") Integer commentId,
                         @RequestHeader(SecurityConstants.FROM) String from);
}
