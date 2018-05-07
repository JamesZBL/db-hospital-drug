package me.zbl.app.dao;

import me.zbl.app.domain.Supplier;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface SupplierMapper {
    @Delete({
        "delete from app_supplier",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into app_supplier (id, name)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="select replace(uuid(),'-','') from dual", keyProperty="id", before=true, resultType=String.class)
    int insert(Supplier record);

    int insertSelective(Supplier record);

    @Select({
        "select",
        "id, name",
        "from app_supplier",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("me.zbl.app.dao.SupplierMapper.BaseResultMap")
    Supplier selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Supplier record);

    @Update({
        "update app_supplier",
        "set name = #{name,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Supplier record);
}