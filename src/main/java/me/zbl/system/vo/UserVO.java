package me.zbl.system.vo;

import me.zbl.system.domain.UserDO;

/**
 * @author 郑保乐
 * @date 2017/12/15.
 */
public class UserVO {

  /**
   * 更新的用户对象
   */
  private UserDO userDO = new UserDO();
  /**
   * 旧密码
   */
  private String pwdOld;
  /**
   * 新密码
   */
  private String pwdNew;

  public UserDO getUserDO() {
    return userDO;
  }

  public void setUserDO(UserDO userDO) {
    this.userDO = userDO;
  }

  public String getPwdOld() {
    return pwdOld;
  }

  public void setPwdOld(String pwdOld) {
    this.pwdOld = pwdOld;
  }

  public String getPwdNew() {
    return pwdNew;
  }

  public void setPwdNew(String pwdNew) {
    this.pwdNew = pwdNew;
  }
}
