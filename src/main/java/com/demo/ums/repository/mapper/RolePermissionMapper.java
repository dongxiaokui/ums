package com.demo.ums.repository.mapper;

import com.demo.ums.repository.model.RolePermissionPO;
import com.demo.ums.repository.model.RolePermissionPOKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RolePermissionMapper {
    @Delete({
        "delete from role_permission",
        "where roleId = #{roleId,jdbcType=INTEGER}",
          "and permissionId = #{permissionId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(RolePermissionPOKey key);

    @Insert({
        "insert into role_permission (roleId, permissionId, ",
        "createTime, updateTime)",
        "values (#{roleId,jdbcType=INTEGER}, #{permissionId,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(RolePermissionPO record);

    @InsertProvider(type=RolePermissionSqlProvider.class, method="insertSelective")
    int insertSelective(RolePermissionPO record);

    @Select({
        "select",
        "roleId, permissionId, createTime, updateTime",
        "from role_permission",
        "where roleId = #{roleId,jdbcType=INTEGER}",
          "and permissionId = #{permissionId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="roleId", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="permissionId", property="permissionId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="createTime", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updateTime", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    RolePermissionPO selectByPrimaryKey(RolePermissionPOKey key);

    @UpdateProvider(type=RolePermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RolePermissionPO record);

    @Update({
        "update role_permission",
        "set createTime = #{createTime,jdbcType=TIMESTAMP},",
          "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
        "where roleId = #{roleId,jdbcType=INTEGER}",
          "and permissionId = #{permissionId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RolePermissionPO record);
}