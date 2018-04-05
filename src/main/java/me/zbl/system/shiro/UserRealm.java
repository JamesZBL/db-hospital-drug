package me.zbl.system.shiro;

import me.zbl.common.config.ApplicationContextRegister;
import me.zbl.common.utils.ShiroUtils;
import me.zbl.system.dao.UserDao;
import me.zbl.system.domain.UserDO;
import me.zbl.system.service.MenuService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
/*	@Autowired
	UserDao userMapper;
	@Autowired
	MenuService menuService;*/

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
    Long userId = ShiroUtils.getUserId();
    MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
    Set<String> perms = menuService.listPerms(userId);
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.setStringPermissions(perms);
    return info;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    String username = (String) token.getPrincipal();
    Map<String, Object> map = new HashMap<>(16);
    map.put("username", username);
    String password = new String((char[]) token.getCredentials());

    UserDao userMapper = ApplicationContextRegister.getBean(UserDao.class);
    // 查询用户信息
    UserDO user = userMapper.list(map).get(0);

    // 账号不存在
    if (user == null) {
      throw new UnknownAccountException("账号或密码不正确");
    }

    // 密码错误
    if (!password.equals(user.getPassword())) {
      throw new IncorrectCredentialsException("账号或密码不正确");
    }

    // 账号锁定
    if (user.getStatus() == 0) {
      throw new LockedAccountException("账号已被锁定,请联系管理员");
    }
    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
    return info;
  }

}
