package org.featx.templet.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.featx.templet.entity.ServiceCategoryTypeEntity;
import org.featx.templet.model.ServiceCategoryTypePageRequest;

import java.util.List;

/**
 * @author Excepts
 * @since 2020/4/4 17:06
 */
@Mapper
public interface ServiceCategoryTypeMapper {

    String COLUMNS = "id, name, code, type, deleted, created_at, updated_at";

    @Insert({"insert into t_component_category_type(`name`, `code`, `type`)",
            "values(#{entity.name}, #{entity.code}, #{entity.type})"})
    @SelectKey(keyProperty = "entity.id", keyColumn = "id", before = false, statement = "SELECT LAST_INSERT_ID()",
            resultType = Long.class)
    int insert(@Param("entity") ServiceCategoryTypeEntity entity);

    @Update({"<script>update t_component_category_type set deleted = 0,",
            "<if test=\"entity.name!=null and entity.name!='' \">`name`=#{entity.name},</if>",
            "<if test=\"entity.type!=null\">`type`=#{entity.type},</if>",
            " updated_at = now() ",
            "where code = #{interest.code} limit 1</script>"})
    int update(@Param("entity") ServiceCategoryTypeEntity entity);

    @Update("update t_component_category_type set deleted = 1 where code = #{code}")
    int delete(@Param("code") String code);

    @Select({"select ", COLUMNS, " from t_component_category_type where code = #{code} and deleted = 0"})
    ServiceCategoryTypeEntity selectByCode(@Param("code") String code);

    @Select({"<script>select ", COLUMNS, " from t_component_category_type ",
            "order by id ",
            "limit #{request.offset}, #{request.size} </script>"})
    List<ServiceCategoryTypeEntity> listByPage(@Param("request") ServiceCategoryTypePageRequest pageRequest);

    @Select({"select count(*) from t_component_category_type where "})
    long countByPage(@Param("request") ServiceCategoryTypePageRequest pageRequest);
}
