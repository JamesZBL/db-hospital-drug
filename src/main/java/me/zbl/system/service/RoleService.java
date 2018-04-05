package me.zbl.system.service;

import me.zbl.system.domain.RoleDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

  RoleDO get(Long id);

  List<RoleDO> list();

  int save(RoleDO role);

  int update(RoleDO role);

  int remove(Long id);

  List<RoleDO> list(Long userId);

  int batchremove(Long[] ids);
}
