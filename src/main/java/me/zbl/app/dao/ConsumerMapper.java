package me.zbl.app.dao;

import me.zbl.app.domain.Consumer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ConsumerMapper {
    @Delete({
        "delete from app_consumer",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into app_consumer (id, name, ",
        "tel)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{tel,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="select replace(uuid(),'-','') from dual", keyProperty="id", before=true, resultType=String.class)
    int insert(Consumer record);

    int insertSelective(Consumer record);

    @Select({
        "select",
        "id, name, tel",
        "from app_consumer",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("me.zbl.app.dao.ConsumerMapper.BaseResultMap")
    Consumer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Consumer record);

    @Update({
        "update app_consumer",
        "set name = #{name,jdbcType=VARCHAR},",
          "tel = #{tel,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Consumer record);
}