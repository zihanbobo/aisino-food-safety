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

package com.pig4cloud.pig.school.api.vo.check;

import com.pig4cloud.pig.school.api.entity.check.EarlyWarning;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 谢松哲
 * @date 2019/10/16
 */
@Data
public class EarlyWarningVO extends EarlyWarning implements Serializable {
  private static final long serialVersionUID = 1L;

  private String schoolName; //学校名称

  private String description;  // 违规行为描述




}
