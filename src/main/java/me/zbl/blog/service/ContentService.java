package me.zbl.blog.service;

import me.zbl.blog.domain.ContentDO;

import java.util.List;
import java.util.Map;

/**
 * 文章内容
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-09-09 10:03:34
 */
public interface ContentService {

  ContentDO get(Long cid);

  List<ContentDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(ContentDO bContent);

  int update(ContentDO bContent);

  int remove(Long cid);

  int batchRemove(Long[] cids);
}
