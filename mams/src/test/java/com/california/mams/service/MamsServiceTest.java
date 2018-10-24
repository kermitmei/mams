package com.california.mams.service;

import com.california.mams.BaseTest;
import com.california.mams.orm.model.MamsAuth;
import com.california.mams.orm.model.MamsGroup;
import com.california.mams.orm.model.MamsMenu;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ffn on 21/10/18.
 */
public class MamsServiceTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(MamsServiceTest.class);

    @Autowired
    MamsService mamsService;

    @Test
    public void createGroup() {
        try {
            MamsGroup mamsGroup = mamsService.createGroup("高级管理员", "用户系统最高权限的管理员", "admin");
            logger.info("创建完成 mamsGroup:{}", mamsGroup);
        } catch (Exception e) {
            logger.error("异常 e:{}", e);
        }
    }

    @Test
    public void createAuth() {
        try {
            MamsAuth mamsAuth = mamsService.createAuth("添加管理员", "/mams/auth/home", "添加管理员权限", "admin");
            logger.info("创建完成 mamsAuth:{}", mamsAuth);
        } catch (Exception e) {
            logger.error("异常 e:{}", e);
        }
    }

    @Test
    public void createMenu() {
        try {
            MamsMenu mamsMenu = mamsService.createMenu("系统管理"
                    , "/mams/menu/system-managers-1"
                    , null
                    , "系统管理页面"
                    , "admin");
            logger.info("创建完成 mamsMenu:{}", mamsMenu);
        } catch (Exception e) {
            logger.error("异常 e:{}", e);
        }
    }

    @Test
    public void updateGroup() {
        try {
            MamsGroup mamsGroup = mamsService.updateGroup("1F30CCFA"
                    , "超级管理员"
                    , "拥有系统所有管理权限");
            logger.info("修改完成 mamsGroup:{}", mamsGroup);
        } catch (Exception e) {
            logger.error("异常 e:{}", e);
        }
    }

    @Test
    public void addMembersToGroup() {
        try {
            List<String> memberList = new ArrayList<>();
            memberList.add("Admin1");
            memberList.add("Admin2");
            memberList.add("Admin3");
            MamsGroup mamsGroup = mamsService.addMembersToGroup("1F30CCFA", memberList);
            logger.info("添加完成 mamsGroup:{}", mamsGroup);
        } catch (Exception e) {
            logger.error("异常 e:{}", e);
        }
    }

    @Test
    public void addAuthsToGroup() {
        try {
            List<String> authList = new ArrayList<>();
            authList.add("07D0FAF9");
            MamsGroup mamsGroup = mamsService.addAuthsToGroup("1F30CCFA", authList);
            logger.info("添加完成 mamsGroup:{}", mamsGroup);
        } catch (Exception e) {
            logger.error("异常 e:{}", e);
        }
    }

    @Test
    public void addMenusToGroup() {
        try {
            List<String> menuList = new ArrayList<>();
            menuList.add("18C8E30F");
            menuList.add("1DD9AEAF");
            MamsGroup mamsGroup = mamsService.addMenusToGroup("1F30CCFA", menuList);
            logger.info("添加完成 mamsGroup:{}", mamsGroup);
        } catch (Exception e) {
            logger.error("异常 e:{}", e);
        }
    }

    @Test
    public void deleteGroup() {
        try {
            boolean result = mamsService.deleteGroup("18C8E30F");
            logger.info("删除完成 result:{}", result);
        } catch (Exception e) {
            logger.error("异常 e:{}", e);
        }
    }

    @Test
    public void deleteAuth() {
        try {
            boolean result = mamsService.deleteAuth("D2B06AE6");
            logger.info("删除完成 result:{}", result);
        } catch (Exception e) {
            logger.error("异常 e:{}", e);
        }
    }

    @Test
    public void deleteMenu() {
        try {
            boolean result = mamsService.deleteMenu("18C8E30F");
            logger.info("删除完成 result:{}", result);
        } catch (Exception e) {
            logger.error("异常 e:{}", e);
        }
    }

    @Test
    public void authenticate() {
        try {
            boolean result = mamsService.authenticate("1"
                    , "admin"
                    , "/auth/test");
            logger.info("authenticate result={}", result);
        } catch (Exception e) {
            logger.error("出现异常 e:{}", e);
        }
    }

    @Test
    public void getGroupsContainsMember() {
        try {
            List<MamsGroup> groupList = mamsService.getGroupsContainMember("hdGhdKqt"
                    , "admin");
            logger.info("getGroupsContainsMembers result={}", groupList.size());
        } catch (Exception e) {
            logger.error("出现异常 e:{}", e);
        }
    }

    @Test
    public void getMenusOfMember() {
        try {
            List<MamsMenu> mamsMenuList = mamsService.getMenusOfMember("", "admin");
            logger.info("getMenusOfMember mamsMenuList:{}", mamsMenuList);
        } catch (Exception e) {
            logger.error("异常 e:{}", e);
        }
    }

    @Test
    public void getAuthsOfMember() {
        try {
            List<MamsAuth> mamsAuthList = mamsService.getAuthsOfMember("", "admin");
            logger.info("getAuthsOfMember mamsAuthList:{}", mamsAuthList);
        } catch (Exception e) {
            logger.error("异常 e:{}", e);
        }
    }
}
