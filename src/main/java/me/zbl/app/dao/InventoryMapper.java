package me.zbl.app.dao;

import me.zbl.app.domain.Inventory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface InventoryMapper {
    @Delete({
        "delete from app_inventory_record",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into app_inventory_record (id, supplier_id, ",
        "consumer_id, drug_id, ",
        "type, quantity, ",
        "ammount, comment, ",
        "manager, gmt_created, ",
        "gmt_modified)",
        "values (#{id,jdbcType=VARCHAR}, #{supplierId,jdbcType=VARCHAR}, ",
        "#{consumerId,jdbcType=VARCHAR}, #{drugId,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{quantity,jdbcType=INTEGER}, ",
        "#{ammount,jdbcType=DECIMAL}, #{comment,jdbcType=VARCHAR}, ",
        "#{manager,jdbcType=VARCHAR}, #{gmtCreated,jdbcType=TIMESTAMP}, ",
        "#{gmtModified,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="select replace(uuid(),'-','') from dual", keyProperty="id", before=true, resultType=String.class)
    int insert(Inventory record);

    int insertSelective(Inventory record);

    @Select({
        "select",
        "id, supplier_id, consumer_id, drug_id, type, quantity, ammount, comment, manager, ",
        "gmt_created, gmt_modified",
        "from app_inventory_record",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("me.zbl.app.dao.InventoryMapper.BaseResultMap")
    Inventory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Inventory record);

    @Update({
        "update app_inventory_record",
        "set supplier_id = #{supplierId,jdbcType=VARCHAR},",
          "consumer_id = #{consumerId,jdbcType=VARCHAR},",
          "drug_id = #{drugId,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "quantity = #{quantity,jdbcType=INTEGER},",
          "ammount = #{ammount,jdbcType=DECIMAL},",
          "comment = #{comment,jdbcType=VARCHAR},",
          "manager = #{manager,jdbcType=VARCHAR},",
          "gmt_created = #{gmtCreated,jdbcType=TIMESTAMP},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Inventory record);
}