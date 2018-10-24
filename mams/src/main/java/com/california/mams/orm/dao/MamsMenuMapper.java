package com.california.mams.orm.dao;

import com.california.mams.orm.model.MamsMenu;
import com.california.mams.orm.model.MamsMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MamsMenuMapper {
    long countByExample(MamsMenuExample example);

    int deleteByExample(MamsMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MamsMenu record);

    int insertSelective(MamsMenu record);

    List<MamsMenu> selectByExample(MamsMenuExample example);

    MamsMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MamsMenu record, @Param("example") MamsMenuExample example);

    int updateByExample(@Param("record") MamsMenu record, @Param("example") MamsMenuExample example);

    int updateByPrimaryKeySelective(MamsMenu record);

    int updateByPrimaryKey(MamsMenu record);
}