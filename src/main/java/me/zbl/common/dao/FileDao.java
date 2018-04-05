package me.zbl.common.dao;

import me.zbl.common.domain.FileDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface FileDao {

  FileDO get(Long id);

  List<FileDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(FileDO file);

  int update(FileDO file);

  int remove(Long id);

  int batchRemove(Long[] ids);
}
