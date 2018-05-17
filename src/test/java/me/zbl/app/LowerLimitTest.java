/*
 * Copyright 2018 JamesZBL
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package me.zbl.app;

import me.zbl.app.service.DrugOutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LowerLimitTest {

  @Autowired
  DrugOutService drugOutService;

  /**
   * 测试库存下限提醒
   */
  @Test
  public void doTest() {
    drugOutService.checkLowerLimit();
  }
}
