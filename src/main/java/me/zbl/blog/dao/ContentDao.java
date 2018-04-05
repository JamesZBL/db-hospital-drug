package me.zbl.blog.dao;

import me.zbl.blog.domain.ContentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 文章内容
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-03 16:17:48
 */
@Mapper
public interface ContentDao {

  ContentDO get(Long cid);

  List<ContentDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(ContentDO content);

  int update(ContentDO content);

  int remove(Long cid);

  int batchRemove(Long[] cids);
}
