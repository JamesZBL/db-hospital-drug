package me.zbl.common.utils;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {

  private static final long serialVersionUID = 1L;

  public R() {
    put("code", 0);
    put("msg", "操作成功");
  }

  public static R error() {
    return error(1, "操作失败");
  }

  public static R error(String msg) {
    return error(500, msg);
  }

  public static R error(int code, String msg) {
    R run = new R();
    run.put("code", code);
    run.put("msg", msg);
    return run;
  }

  public static R ok(String msg) {
    R run = new R();
    run.put("msg", msg);
    return run;
  }

  public static R ok(Map<String, Object> map) {
    R run = new R();
    run.putAll(map);
    return run;
  }

  public static R ok() {
    return new R();
  }

  @Override
  public R put(String key, Object value) {
    super.put(key, value);
    return this;
  }
}
