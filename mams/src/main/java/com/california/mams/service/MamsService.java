package com.california.mams.service;

import com.california.mams.orm.model.MamsAuth;
import com.california.mams.orm.model.MamsGroup;
import com.california.mams.orm.model.MamsMenu;

import java.util.List;

/**
 * Created by ffn on 21/10/18.
 */
public interface MamsService {

    /**
     * 创建组
     *
     * @param name    组名
     * @param comment 备注
     * @param type    类型
     * @return
     */
    MamsGroup createGroup(String name, String comment, String type);

    /**
     * 创建权限
     *
     * @param name    权限名称
     * @param uri     权限uri
     * @param comment 权限备注
     * @param type    权限类型
     * @return
     */
    MamsAuth createAuth(String name, String uri, String comment, String type);

    /**
     * 创建菜单
     *
     * @param name    菜单名称
     * @param uri     菜单uri
     * @param p1Kid   直接父菜单kid
     * @param comment 备注
     * @param type    类型
     * @return
     */
    MamsMenu createMenu(String name, String uri, String p1Kid, String comment, String type);

    /**
     * 更新组基本信息
     *
     * @param groupKid 组kid
     * @param name     组名
     * @param comment  备注
     * @return
     */
    MamsGroup updateGroup(String groupKid, String name, String comment);



    /**
     * 将列表中的成员加入组（不校验kid是否存在）
     *
     * @param groupKid   组kid
     * @param memberKids 成员kid列表
     * @return
     */
    MamsGroup addMembersToGroup(String groupKid, List<String> memberKids);

    /**
     * 将列表中的权限加入组（不校验kid是否存在）
     *
     * @param groupKid 组kid
     * @param authKids 权限kid列表
     * @return
     */
    MamsGroup addAuthsToGroup(String groupKid, List<String> authKids);

    /**
     * 将列表中的菜单加入组（不校验kid是否存在）
     *
     * @param groupKid 组kid
     * @param menuKids 权限kid列表
     * @return
     */
    MamsGroup addMenusToGroup(String groupKid, List<String> menuKids);


    /**
     * 删除指定组
     *
     * @param groupKid
     * @return 删除成功返回true；否则返回false。
     */
    boolean deleteGroup(String groupKid);

    /**
     * 删除指定权限
     *
     * @param authKid 权限kid
     * @return 对应authKid不存在或者成功删除，都返回true；否则返回false。
     */
    boolean deleteAuth(String authKid);

    /**
     * 删除指定菜单
     *
     * @param menuKid 菜单kid
     * @return 对应menuKid不存在或者成功删除，都返回true；否则返回false。
     */
    boolean deleteMenu(String menuKid);

    /**
     * 鉴定指定成员是否具有相应权限
     *
     * @param memberKid 成员kid
     * @param type      权限类型
     * @param authUri   成员Uri
     * @return 返回true表示成功，false表示失败
     */
    boolean authenticate(String memberKid
            , String type
            , String authUri);

    /**
     * 获取所有包含指定成员的组
     *
     * @param memberKid 成员kid
     * @param type      权限类型
     * @return
     */
    List<MamsGroup> getGroupsContainMember(String memberKid, String type);

    /**
     * 获取指定成员所有菜单
     *
     * @param memberKid 成员kid
     * @param type      权限类型
     * @return
     */
    List<MamsMenu> getMenusOfMember(String memberKid, String type);

    /**
     * 获取指定成员所有权限
     *
     * @param memberKid 成员kid
     * @param type      权限类型
     * @return
     */
    List<MamsAuth> getAuthsOfMember(String memberKid, String type);

}
