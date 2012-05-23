/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.cdi.impl.el;

import org.activiti.cdi.test.CdiActivitiTestCase;
import org.activiti.cdi.test.beans.MessageBean;
import org.activiti.engine.test.Deployment;

/**
 * @author Daniel Meyer
 */
public class ElTest extends CdiActivitiTestCase {

  @Deployment
  public void testSetBeanProperty() throws Exception {
    MessageBean messageBean = getBeanInstance(MessageBean.class);
    runtimeService.startProcessInstanceByKey("setBeanProperty");
    assertEquals("Greetings from Activiti", messageBean.getMessage());
  }

}