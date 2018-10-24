package com.california.mams.orm.domain;

import com.california.mams.orm.dao.MamsAuthMapper;
import com.california.mams.orm.model.MamsAuth;
import com.california.mams.orm.model.MamsAuthExample;
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
public class MamsAuthDomain implements AbstractDomainInterface<MamsAuth, MamsAuthExample> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MamsAuthMapper mamsAuthMapper;

    /**
     * 获取通过主键
     *
     * @param id 默认自增id
     * @param useCache 是否从Redis 缓存中获取
     * @return
     */
    @Override
    public MamsAuth get(Integer id, boolean useCache) {
        if(null == id) {
            return null;
        }
        return mamsAuthMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过脱敏主键kid获取对象
     *
     * @param kid
     * @param useCache 是否从Redis 缓存中获取
     * @return
     */
    @Override
    public MamsAuth get(String kid, boolean useCache) {
        if(null == kid) {
            return null;
        }
        MamsAuthExample example = new MamsAuthExample();
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
    public Integer add(MamsAuth obj) {
        return mamsAuthMapper.insertSelective(obj);
    }

    /**
     * 保存
     *
     * @param obj
     * @return
     */
    @Override
    public Integer save(MamsAuth obj) {
        return mamsAuthMapper.updateByPrimaryKeySelective(obj);
    }

    /**
     * 删除
     *
     * @param obj
     * @return
     */
    @Override
    public Integer delete(MamsAuth obj) {
        return mamsAuthMapper.deleteByPrimaryKey(obj.getId());
    }

    /**
     * 获取通过example (取List的第0个)
     *
     * @param example
     * @return
     */
    @Override
    public MamsAuth getByExample(MamsAuthExample example) {
        List<MamsAuth> list = mamsAuthMapper.selectByExample(example);
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
    public PageInfo<MamsAuth> getsByExample(MamsAuthExample example, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(mamsAuthMapper.selectByExample(example));
    }

    /**
     * 获取所有通过example
     *
     * @param example
     * @return
     */
    @Override
    public List<MamsAuth> getAllByExample(MamsAuthExample example) {
        return mamsAuthMapper.selectByExample(example);
    }

    /**
     * 删除通过条件
     *
     * @param example
     * @return
     */
    @Override
    public Integer deleteByExample(MamsAuthExample example) {
        return mamsAuthMapper.deleteByExample(example);
    }
}
