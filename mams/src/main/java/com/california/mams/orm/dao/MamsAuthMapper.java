package com.california.mams.orm.dao;

import com.california.mams.orm.model.MamsAuth;
import com.california.mams.orm.model.MamsAuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MamsAuthMapper {
    long countByExample(MamsAuthExample example);

    int deleteByExample(MamsAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MamsAuth record);

    int insertSelective(MamsAuth record);

    List<MamsAuth> selectByExample(MamsAuthExample example);

    MamsAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MamsAuth record, @Param("example") MamsAuthExample example);

    int updateByExample(@Param("record") MamsAuth record, @Param("example") MamsAuthExample example);

    int updateByPrimaryKeySelective(MamsAuth record);

    int updateByPrimaryKey(MamsAuth record);
}