package me.zbl.app.dao;

import me.zbl.app.domain.Drug;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DrugMapper {
    @Delete({
        "delete from app_drug",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into app_drug (id, name, ",
        "quantity, price, ",
        "invalid, quality_guarantee_period, ",
        "lower_limit, supplier_id)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{quantity,jdbcType=INTEGER}, #{price,jdbcType=DECIMAL}, ",
        "#{invalid,jdbcType=VARCHAR}, #{qualityGuaranteePeriod,jdbcType=INTEGER}, ",
        "#{lowerLimit,jdbcType=INTEGER}, #{supplierId,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="select replace(uuid(),'-','') from dual", keyProperty="id", before=true, resultType=String.class)
    int insert(Drug record);

    int insertSelective(Drug record);

    @Select({
        "select",
        "id, name, quantity, price, invalid, quality_guarantee_period, lower_limit, supplier_id",
        "from app_drug",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("me.zbl.app.dao.DrugMapper.BaseResultMap")
    Drug selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Drug record);

    @Update({
        "update app_drug",
        "set name = #{name,jdbcType=VARCHAR},",
          "quantity = #{quantity,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "invalid = #{invalid,jdbcType=VARCHAR},",
          "quality_guarantee_period = #{qualityGuaranteePeriod,jdbcType=INTEGER},",
          "lower_limit = #{lowerLimit,jdbcType=INTEGER},",
          "supplier_id = #{supplierId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Drug record);
}