package me.zbl.blog.service.impl;

import me.zbl.blog.dao.ContentDao;
import me.zbl.blog.domain.ContentDO;
import me.zbl.blog.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ContentServiceImpl implements ContentService {

  @Autowired
  private ContentDao bContentMapper;

  @Override
  public ContentDO get(Long cid) {
    return bContentMapper.get(cid);
  }

  @Override
  public List<ContentDO> list(Map<String, Object> map) {
    return bContentMapper.list(map);
  }

  @Override
  public int count(Map<String, Object> map) {
    return bContentMapper.count(map);
  }

  @Override
  public int save(ContentDO bContent) {
    return bContentMapper.save(bContent);
  }

  @Override
  public int update(ContentDO bContent) {
    return bContentMapper.update(bContent);
  }

  @Override
  public int remove(Long cid) {
    return bContentMapper.remove(cid);
  }

  @Override
  public int batchRemove(Long[] cids) {
    return bContentMapper.batchRemove(cids);
  }

}
