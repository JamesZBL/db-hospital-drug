package me.zbl.system.service.impl;

import me.zbl.common.domain.FileDO;
import me.zbl.common.domain.Tree;
import me.zbl.common.service.FileService;
import me.zbl.common.utils.BuildTree;
import me.zbl.system.dao.MenuDao;
import me.zbl.system.dao.RoleMenuDao;
import me.zbl.system.domain.MenuDO;
import me.zbl.system.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.*;

import static me.zbl.common.utils.ShiroUtils.getUser;
import static me.zbl.common.utils.ShiroUtils.getUserId;

@SuppressWarnings("AlibabaRemoveCommentedCode")
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

  @Autowired
  MenuDao menuMapper;
  @Autowired
  RoleMenuDao roleMenuMapper;

  @Autowired
  FileService fileService;

  /**
   * @param
   *
   * @return 树形菜单
   */
  @Cacheable
  @Override
  public Tree<MenuDO> getSysMenuTree(Long id) {
    List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
    List<MenuDO> menuDOs = menuMapper.listMenuByUserId(id);
    for (MenuDO sysMenuDO : menuDOs) {
      Tree<MenuDO> tree = new Tree<MenuDO>();
      tree.setId(sysMenuDO.getMenuId().toString());
      tree.setParentId(sysMenuDO.getParentId().toString());
      tree.setText(sysMenuDO.getName());
      Map<String, Object> attributes = new HashMap<>(16);
      attributes.put("url", sysMenuDO.getUrl());
      attributes.put("icon", sysMenuDO.getIcon());
      tree.setAttributes(attributes);
      trees.add(tree);
    }
    // 默认顶级菜单为０，根据数据库实际情况调整
    Tree<MenuDO> t = BuildTree.build(trees);
    return t;
  }

  @Override
  public List<MenuDO> list(Map<String, Object> params) {
    List<MenuDO> menus = menuMapper.list(params);
    return menus;
  }

  @Transactional(readOnly = false, rollbackFor = Exception.class)
  @Override
  public int remove(Long id) {
    int result = menuMapper.remove(id);
    return result;
  }

  @Transactional(readOnly = false, rollbackFor = Exception.class)
  @Override
  public int save(MenuDO menu) {
    int r = menuMapper.save(menu);
    return r;
  }

  @Transactional(readOnly = false, rollbackFor = Exception.class)
  @Override
  public int update(MenuDO menu) {
    int r = menuMapper.update(menu);
    return r;
  }

  @Override
  public MenuDO get(Long id) {
    MenuDO menuDO = menuMapper.get(id);
    return menuDO;
  }

  @Override
  public Tree<MenuDO> getTree() {
    List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
    List<MenuDO> menuDOs = menuMapper.list(new HashMap<>(16));
    for (MenuDO sysMenuDO : menuDOs) {
      Tree<MenuDO> tree = new Tree<MenuDO>();
      tree.setId(sysMenuDO.getMenuId().toString());
      tree.setParentId(sysMenuDO.getParentId().toString());
      tree.setText(sysMenuDO.getName());
      trees.add(tree);
    }
    // 默认顶级菜单为０，根据数据库实际情况调整
    Tree<MenuDO> t = BuildTree.build(trees);
    return t;
  }

  @Override
  public Tree<MenuDO> getTree(Long id) {
    // 根据roleId查询权限
    List<MenuDO> menus = menuMapper.list(new HashMap<String, Object>(16));
    List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(id);
    List<Long> temp = menuIds;
    for (MenuDO menu : menus) {
      if (temp.contains(menu.getParentId())) {
        menuIds.remove(menu.getParentId());
      }
    }
    List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
    List<MenuDO> menuDOs = menuMapper.list(new HashMap<String, Object>(16));
    for (MenuDO sysMenuDO : menuDOs) {
      Tree<MenuDO> tree = new Tree<MenuDO>();
      tree.setId(sysMenuDO.getMenuId().toString());
      tree.setParentId(sysMenuDO.getParentId().toString());
      tree.setText(sysMenuDO.getName());
      Map<String, Object> state = new HashMap<>(16);
      Long menuId = sysMenuDO.getMenuId();
      if (menuIds.contains(menuId)) {
        state.put("selected", true);
      } else {
        state.put("selected", false);
      }
      tree.setState(state);
      trees.add(tree);
    }
    // 默认顶级菜单为０，根据数据库实际情况调整
    Tree<MenuDO> t = BuildTree.build(trees);
    return t;
  }

  @Override
  public Set<String> listPerms(Long userId) {
    List<String> perms = menuMapper.listUserPerms(userId);
    Set<String> permsSet = new HashSet<>();
    for (String perm : perms) {
      if (StringUtils.isNotBlank(perm)) {
        permsSet.addAll(Arrays.asList(perm.trim().split(",")));
      }
    }
    return permsSet;
  }

  @Override
  public List<Tree<MenuDO>> listMenuTree(Long id) {
    List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
    List<MenuDO> menuDOs = menuMapper.listMenuByUserId(id);
    for (MenuDO sysMenuDO : menuDOs) {
      Tree<MenuDO> tree = new Tree<MenuDO>();
      tree.setId(sysMenuDO.getMenuId().toString());
      tree.setParentId(sysMenuDO.getParentId().toString());
      tree.setText(sysMenuDO.getName());
      Map<String, Object> attributes = new HashMap<>(16);
      attributes.put("url", sysMenuDO.getUrl());
      attributes.put("icon", sysMenuDO.getIcon());
      tree.setAttributes(attributes);
      trees.add(tree);
    }
    // 默认顶级菜单为０，根据数据库实际情况调整
    List<Tree<MenuDO>> list = BuildTree.buildList(trees, "0");
    return list;
  }

  @Override
  public String getMenus(Model model){

    List<Tree<MenuDO>> menus = listMenuTree(getUserId());

    model.addAttribute("menus", menus);
    model.addAttribute("name", getUser().getName());
    FileDO fileDO = fileService.get(getUser().getPicId());
    if (fileDO != null && fileDO.getUrl() != null) {
      if (fileService.isExist(fileDO.getUrl())) {
        model.addAttribute("picUrl", fileDO.getUrl());
      } else {
        model.addAttribute("picUrl", "/img/photo_s.jpg");
      }
    } else {
      model.addAttribute("picUrl", "/img/photo_s.jpg");
    }
    model.addAttribute("username", getUser().getUsername());
    return "index_v1";
  }

}
