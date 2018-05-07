package me.zbl.app.dao;

import me.zbl.app.domain.Expire;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ExpireMapper {
    @Delete({
        "delete from app_expire",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into app_expire (id, drug_id, ",
        "expired_date)",
        "values (#{id,jdbcType=VARCHAR}, #{drugId,jdbcType=VARCHAR}, ",
        "#{expiredDate,jdbcType=DATE})"
    })
    @SelectKey(statement="select replace(uuid(),'-','') from dual", keyProperty="id", before=true, resultType=String.class)
    int insert(Expire record);

    int insertSelective(Expire record);

    @Select({
        "select",
        "id, drug_id, expired_date",
        "from app_expire",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("me.zbl.app.dao.ExpireMapper.BaseResultMap")
    Expire selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Expire record);

    @Update({
        "update app_expire",
        "set drug_id = #{drugId,jdbcType=VARCHAR},",
          "expired_date = #{expiredDate,jdbcType=DATE}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Expire record);
}