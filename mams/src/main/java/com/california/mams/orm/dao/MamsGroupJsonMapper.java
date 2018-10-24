package com.california.mams.orm.dao;

import com.california.mams.orm.model.MamsGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by ffn on 21/10/18.
 */
public interface MamsGroupJsonMapper {
    List<MamsGroup> selectGroupsContainMember(@Param("argsMap") Map<String, Object> argsMap);

    List<MamsGroup> selectGroupsContainAuth(@Param("argsMap") Map<String, Object> argsMap);

    List<MamsGroup> selectGroupsContainMenu(@Param("argsMap") Map<String, Object> argsMap);
}
