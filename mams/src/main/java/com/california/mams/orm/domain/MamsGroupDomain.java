package com.california.mams.orm.domain;

import com.california.mams.orm.dao.MamsGroupJsonMapper;
import com.california.mams.orm.dao.MamsGroupMapper;
import com.california.mams.orm.model.MamsGroup;
import com.california.mams.orm.model.MamsGroupExample;
import com.california.mams.service.MamsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Robert on 17/11/20.
 */
@Component
public class MamsGroupDomain implements AbstractDomainInterface<MamsGroup, MamsGroupExample> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private MamsGroupMapper mamsGroupMapper;

    private MamsGroupJsonMapper mamsGroupJsonMapper;

    @Autowired
    public MamsGroupDomain(MamsGroupMapper mamsGroupMapper, MamsGroupJsonMapper mamsGroupJsonMapper) {
        this.mamsGroupMapper = mamsGroupMapper;
        this.mamsGroupJsonMapper = mamsGroupJsonMapper;
    }

    /**
     * 获取通过主键
     *
     * @param id       默认自增id
     * @param useCache 是否从Redis 缓存中获取
     * @return
     */
    @Override
    public MamsGroup get(Integer id, boolean useCache) {
        if (null == id) {
            return null;
        }
        return mamsGroupMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过脱敏主键kid获取对象
     *
     * @param kid
     * @param useCache 是否从Redis 缓存中获取
     * @return
     */
    @Override
    public MamsGroup get(String kid, boolean useCache) {
        if (null == kid) {
            return null;
        }
        MamsGroupExample example = new MamsGroupExample();
        example.createCriteria().andKidEqualTo(kid);
        return this.getByExample(example);
    }

    /**
     * 添加
     *
     * @param obj
     * @return
     */
    @Override
    public Integer add(MamsGroup obj) {
        return mamsGroupMapper.insertSelective(obj);
    }

    /**
     * 保存
     *
     * @param obj
     * @return
     */
    @Override
    public Integer save(MamsGroup obj) {
        return mamsGroupMapper.updateByPrimaryKeySelective(obj);
    }

    /**
     * 删除
     *
     * @param obj
     * @return
     */
    @Override
    public Integer delete(MamsGroup obj) {
        return mamsGroupMapper.deleteByPrimaryKey(obj.getId());
    }

    /**
     * 获取通过example (取List的第0个)
     *
     * @param example
     * @return
     */
    @Override
    public MamsGroup getByExample(MamsGroupExample example) {
        List<MamsGroup> list = mamsGroupMapper.selectByExample(example);
        return (list.size() != 0) ? list.get(0) : null;
    }

    /**
     * 分页获取
     *
     * @param example
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<MamsGroup> getsByExample(MamsGroupExample example, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(mamsGroupMapper.selectByExample(example));
    }

    /**
     * 获取所有通过example
     *
     * @param example
     * @return
     */
    @Override
    public List<MamsGroup> getAllByExample(MamsGroupExample example) {
        return mamsGroupMapper.selectByExample(example);
    }

    /**
     * 删除通过条件
     *
     * @param example
     * @return
     */
    @Override
    public Integer deleteByExample(MamsGroupExample example) {
        return mamsGroupMapper.deleteByExample(example);
    }

    /**
     * 获取包含指定成员的组
     */
    public List<MamsGroup> getAllContainMember(String memberKid, String type) {
        Map<String, Object> argsMap = new HashMap<>();
        Gson gson = new Gson();
        argsMap.put("type", type);
        argsMap.put("memberKid", gson.toJson(memberKid));
        return mamsGroupJsonMapper.selectGroupsContainMember(argsMap);
    }

    /**
     * 获取包含指定权限的组
     */
    public List<MamsGroup> getAllContainAuth(String authKid, String type) {
        Map<String, Object> argsMap = new HashMap<>();
        Gson gson = new Gson();
        argsMap.put("type", type);
        argsMap.put("authKid", gson.toJson(authKid));
        return mamsGroupJsonMapper.selectGroupsContainAuth(argsMap);
    }

    /**
     * 获取包含指定菜单的组
     */
    public List<MamsGroup> getAllContainMenu(String menuKid, String type) {
        Map<String, Object> argsMap = new HashMap<>();
        Gson gson = new Gson();
        argsMap.put("type", type);
        argsMap.put("menuKid", gson.toJson(menuKid));
        return mamsGroupJsonMapper.selectGroupsContainMenu(argsMap);
    }
}
