package me.zbl.system.dao;

import me.zbl.system.domain.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-03 09:45:09
 */
@Mapper
public interface MenuDao {

  MenuDO get(Long menuId);

  List<MenuDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(MenuDO menu);

  int update(MenuDO menu);

  int remove(Long menuId);

  int batchRemove(Long[] menuIds);

  List<MenuDO> listMenuByUserId(Long id);

  List<String> listUserPerms(Long id);
}
