package com.california.mams.orm.dao;

import com.california.mams.orm.model.MamsGroup;
import com.california.mams.orm.model.MamsGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MamsGroupMapper {
    long countByExample(MamsGroupExample example);

    int deleteByExample(MamsGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MamsGroup record);

    int insertSelective(MamsGroup record);

    List<MamsGroup> selectByExample(MamsGroupExample example);

    MamsGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MamsGroup record, @Param("example") MamsGroupExample example);

    int updateByExample(@Param("record") MamsGroup record, @Param("example") MamsGroupExample example);

    int updateByPrimaryKeySelective(MamsGroup record);

    int updateByPrimaryKey(MamsGroup record);
}