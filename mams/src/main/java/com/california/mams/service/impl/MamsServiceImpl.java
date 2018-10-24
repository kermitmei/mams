package com.california.mams.service.impl;

import com.california.mams.orm.domain.MamsAuthDomain;
import com.california.mams.orm.domain.MamsGroupDomain;
import com.california.mams.orm.domain.MamsMenuDomain;
import com.california.mams.orm.model.*;
import com.california.mams.service.MamsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/**
 * Created by ffn on 21/10/18.
 */
@Service
public class MamsServiceImpl implements MamsService {

    private MamsAuthDomain mamsAuthDomain;
    private MamsGroupDomain mamsGroupDomain;
    private MamsMenuDomain mamsMenuDomain;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * md5快捷方法
     *
     * @param seed 种子数据
     * @return （32位md5随机码）
     */
    private String md5(String seed) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes;
            md5Bytes = md5.digest(seed.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexValue = new StringBuilder();
            for (byte md5Val : md5Bytes) {
                int val = ((int) md5Val) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString().toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 创建权限系统的kid
     *
     * @return kid字符串（默认：8字节，大写，16进制数）
     */
    private String createKid() {
        String randomCode = null;
        while (null == randomCode) {
            String randomText = UUID.randomUUID().toString();
            randomCode = md5(randomText);
            if (null == randomCode || randomCode.length() != 32) {
                randomCode = null;
            } else {
                int length = 8;
                int startIndex = (randomCode.length() - length) / 2;
                logger.info("randomCode:{} in [{}, {}]"
                        , randomCode
                        , startIndex
                        , length);
                randomCode = randomCode.substring(startIndex, startIndex + length);
            }
        }
        return randomCode;
    }

    private List<String> getKidListFromJson(String kidJsonArray) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(kidJsonArray, listType);
    }

    @Autowired
    public MamsServiceImpl(MamsAuthDomain mamsAuthDomain, MamsGroupDomain mamsGroupDomain, MamsMenuDomain mamsMenuDomain) {
        this.mamsAuthDomain = mamsAuthDomain;
        this.mamsGroupDomain = mamsGroupDomain;
        this.mamsMenuDomain = mamsMenuDomain;
    }

    @Override
    public MamsGroup createGroup(String name, String comment, String type) {
        MamsGroup mamsGroup = new MamsGroup();
        mamsGroup.setName(name);
        mamsGroup.setComment(comment);
        mamsGroup.setType(type);
        Gson gson = new Gson();
        String emptyKidList = gson.toJson(new ArrayList<>());
        mamsGroup.setKid(createKid());
        mamsGroup.setAuths(emptyKidList);
        mamsGroup.setMembers(emptyKidList);
        mamsGroup.setMenus(emptyKidList);
        if (mamsGroupDomain.add(mamsGroup) > 0) {
            return mamsGroup;
        } else {
            return null;
        }
    }

    @Override
    public MamsAuth createAuth(String name, String uri, String comment, String type) {
        MamsAuth mamsAuth = new MamsAuth();
        mamsAuth.setKid(createKid());
        mamsAuth.setName(name);
        mamsAuth.setUri(uri);
        mamsAuth.setComment(comment);
        mamsAuth.setType(type);
        if (mamsAuthDomain.add(mamsAuth) > 0) {
            return mamsAuth;
        } else {
            return null;
        }
    }

    @Override
    public MamsMenu createMenu(String name, String uri, String p1Kid, String comment, String type) {
        MamsMenu mamsMenu = new MamsMenu();
        mamsMenu.setKid(createKid());
        mamsMenu.setName(name);
        mamsMenu.setUri(uri);
        mamsMenu.setP1Kid(p1Kid);
        mamsMenu.setComment(comment);
        mamsMenu.setType(type);
        if (mamsMenuDomain.add(mamsMenu) > 0) {
            return mamsMenu;
        } else {
            return null;
        }
    }

    @Override
    public MamsGroup updateGroup(String groupKid, String name, String comment) {
        MamsGroup mamsGroup = mamsGroupDomain.get(groupKid, true);
        if (null == mamsGroup) {
            return null;
        }
        mamsGroup.setName(name);
        mamsGroup.setComment(comment);
        if (mamsGroupDomain.save(mamsGroup) > 0) {
            return mamsGroup;
        } else {
            return null;
        }
    }

    @Override
    public MamsGroup addMembersToGroup(String groupKid, List<String> memberKids) {
        MamsGroup mamsGroup = mamsGroupDomain.get(groupKid, true);
        if (null == mamsGroup) {
            return null;
        }
        if (null == memberKids || memberKids.isEmpty()) {
            return mamsGroup;
        }

        Set<String> memberKidSet = new HashSet<>();
        memberKidSet.addAll(memberKids);
        memberKidSet.addAll(getKidListFromJson(mamsGroup.getMembers()));
        List<String> newMemberKidList = new ArrayList<>(memberKidSet);
        Gson gson = new Gson();
        mamsGroup.setMembers(gson.toJson(newMemberKidList));
        if (mamsGroupDomain.save(mamsGroup) > 0) {
            return mamsGroup;
        } else {
            return null;
        }
    }

    @Override
    public MamsGroup addAuthsToGroup(String groupKid, List<String> authKids) {
        MamsGroup mamsGroup = mamsGroupDomain.get(groupKid, true);
        if (null == mamsGroup) {
            return null;
        }
        if (null == authKids || authKids.isEmpty()) {
            return mamsGroup;
        }
        Set<String> authKidSet = new HashSet<>();
        authKidSet.addAll(authKids);
        authKidSet.addAll(getKidListFromJson(mamsGroup.getAuths()));
        List<String> newAuthKidList = new ArrayList<>(authKidSet);
        Gson gson = new Gson();
        mamsGroup.setAuths(gson.toJson(newAuthKidList));
        if (mamsGroupDomain.save(mamsGroup) > 0) {
            return mamsGroup;
        } else {
            return null;
        }
    }

    @Override
    public MamsGroup addMenusToGroup(String groupKid, List<String> menuKids) {
        MamsGroup mamsGroup = mamsGroupDomain.get(groupKid, true);
        if (null == mamsGroup) {
            return null;
        }
        if (null == menuKids || menuKids.isEmpty()) {
            return mamsGroup;
        }
        Set<String> menuKidSet = new HashSet<>();
        menuKidSet.addAll(menuKids);
        menuKidSet.addAll(getKidListFromJson(mamsGroup.getMenus()));
        List<String> newMenuKidList = new ArrayList<>(menuKidSet);
        Gson gson = new Gson();
        mamsGroup.setMenus(gson.toJson(newMenuKidList));
        if (mamsGroupDomain.save(mamsGroup) > 0) {
            return mamsGroup;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteGroup(String groupKid) {
        MamsGroup mamsGroup = mamsGroupDomain.get(groupKid, true);
        if (null == mamsGroup) {
            return true;
        }
        return (mamsGroupDomain.delete(mamsGroup) > 0);
    }

    @Override
    @Transactional
    public boolean deleteAuth(String authKid) {
        MamsAuth mamsAuth = mamsAuthDomain.get(authKid, true);
        if (null == mamsAuth) {
            return true;
        }

        //删除掉包含这个权限的组中的authKid
        List<MamsGroup> groupList = mamsGroupDomain.getAllContainAuth(mamsAuth.getKid(), mamsAuth.getType());
        for (MamsGroup group : groupList) {
            List<String> authKidList = getKidListFromJson(group.getAuths());
            logger.info("authKidList:{}", authKidList);
            authKidList.remove(authKid);
            Gson gson = new Gson();
            group.setAuths(gson.toJson(authKidList));
            mamsGroupDomain.save(group);
        }
        return (mamsAuthDomain.delete(mamsAuth) > 0);
    }

    @Override
    @Transactional
    public boolean deleteMenu(String menuKid) {
        MamsMenu mamsMenu = mamsMenuDomain.get(menuKid, true);
        if (null == mamsMenu) {
            return true;
        }

        //删除掉包含这个权限的组中的authKid
        List<MamsGroup> groupList = mamsGroupDomain.getAllContainMenu(mamsMenu.getKid(), mamsMenu.getType());
        logger.info("groupContainsMenuSize:{}", groupList.size());
        for (MamsGroup group : groupList) {
            List<String> menuKidList = getKidListFromJson(group.getMenus());
            menuKidList.remove(menuKid);
            Gson gson = new Gson();
            group.setMenus(gson.toJson(menuKidList));
            mamsGroupDomain.save(group);
        }
        return (mamsMenuDomain.delete(mamsMenu) > 0);
    }

    @Override
    public boolean authenticate(String memberKid
            , String type
            , String authUri) {
        //传入的authUri不能为空字符串
        if (StringUtils.isEmpty(authUri)) {
            return false;
        }
        List<MamsAuth> authList = getAuthsOfMember(memberKid, type);
        for (MamsAuth auth : authList) {
            if (authUri.equals(auth.getUri())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<MamsGroup> getGroupsContainMember(String memberKid, String type) {
        return mamsGroupDomain.getAllContainMember(memberKid, type);
    }

    @Override
    public List<MamsMenu> getMenusOfMember(String memberKid, String type) {
        if (StringUtils.isEmpty(memberKid)) {
            return null;
        }
        List<MamsGroup> groupList = getGroupsContainMember(memberKid, type);
        if (null == groupList) {
            return null;
        }

        HashSet<String> menuSet = new HashSet<>();
        for (MamsGroup group : groupList) {
            Gson gson = new Gson();
            List<String> menuList = getKidListFromJson(group.getMenus());
            menuSet.addAll(menuList);
        }

        List<String> menuKidList = new ArrayList<>(menuSet);
        for (String menuKid : menuKidList) {
            logger.info("menuKid:{}", menuKid);
        }

        MamsMenuExample example = new MamsMenuExample();
        MamsMenuExample.Criteria criteria = example.createCriteria();
        criteria.andKidIn(menuKidList);
        return mamsMenuDomain.getAllByExample(example);
    }

    @Override
    public List<MamsAuth> getAuthsOfMember(String memberKid, String type) {
        if (StringUtils.isEmpty(memberKid)) {
            return null;
        }
        List<MamsGroup> groupList = getGroupsContainMember(memberKid, type);
        if (null == groupList) {
            return null;
        }

        HashSet<String> authSet = new HashSet<>();
        for (MamsGroup group : groupList) {
            List<String> menuList = getKidListFromJson(group.getAuths());
            authSet.addAll(menuList);
        }

        List<String> authKidList = new ArrayList<>(authSet);
        for (String authKid : authKidList) {
            logger.info("authKid:{}", authKid);
        }

        MamsAuthExample example = new MamsAuthExample();
        MamsAuthExample.Criteria criteria = example.createCriteria();
        criteria.andKidIn(authKidList);
        return mamsAuthDomain.getAllByExample(example);
    }
}
