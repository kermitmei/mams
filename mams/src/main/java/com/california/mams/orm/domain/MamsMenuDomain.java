package com.california.mams.orm.domain;

import com.california.mams.orm.dao.MamsMenuMapper;
import com.california.mams.orm.model.MamsMenu;
import com.california.mams.orm.model.MamsMenuExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Robert on 17/11/20.
 */
@Component
public class MamsMenuDomain implements AbstractDomainInterface<MamsMenu, MamsMenuExample> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MamsMenuMapper mamsMenuMapper;

    /**
     * 获取通过主键
     *
     * @param id 默认自增id
     * @param useCache 是否从Redis 缓存中获取
     * @return
     */
    @Override
    public MamsMenu get(Integer id, boolean useCache) {
        if(null == id) {
            return null;
        }
        return mamsMenuMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过脱敏主键kid获取对象
     *
     * @param kid
     * @param useCache 是否从Redis 缓存中获取
     * @return
     */
    @Override
    public MamsMenu get(String kid, boolean useCache) {
        if(null == kid) {
            return null;
        }
        MamsMenuExample example = new MamsMenuExample();
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
    public Integer add(MamsMenu obj) {
        return mamsMenuMapper.insertSelective(obj);
    }

    /**
     * 保存
     *
     * @param obj
     * @return
     */
    @Override
    public Integer save(MamsMenu obj) {
        return mamsMenuMapper.updateByPrimaryKeySelective(obj);
    }

    /**
     * 删除
     *
     * @param obj
     * @return
     */
    @Override
    public Integer delete(MamsMenu obj) {
        return mamsMenuMapper.deleteByPrimaryKey(obj.getId());
    }

    /**
     * 获取通过example (取List的第0个)
     *
     * @param example
     * @return
     */
    @Override
    public MamsMenu getByExample(MamsMenuExample example) {
        List<MamsMenu> list = mamsMenuMapper.selectByExample(example);
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
    public PageInfo<MamsMenu> getsByExample(MamsMenuExample example, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(mamsMenuMapper.selectByExample(example));
    }

    /**
     * 获取所有通过example
     *
     * @param example
     * @return
     */
    @Override
    public List<MamsMenu> getAllByExample(MamsMenuExample example) {
        return mamsMenuMapper.selectByExample(example);
    }

    /**
     * 删除通过条件
     *
     * @param example
     * @return
     */
    @Override
    public Integer deleteByExample(MamsMenuExample example) {
        return mamsMenuMapper.deleteByExample(example);
    }
}
