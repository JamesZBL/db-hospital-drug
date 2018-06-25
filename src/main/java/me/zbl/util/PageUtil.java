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
package me.zbl.util;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.Map;
import java.util.Optional;

/**
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-06-24
 */
public class PageUtil {

  public static <E> Page<E> page(Map<String, Object> params, ISelect select) {
    Optional<String> orderby = Optional.ofNullable((String) (params.get("orderby")));
    Page<Object> page = PageHelper.offsetPage(
            (Integer) params.get("offset"),
            (Integer) params.get("limit"));
    orderby.ifPresent(page::setOrderBy);
    return page.doSelectPage(select);
  }
}
