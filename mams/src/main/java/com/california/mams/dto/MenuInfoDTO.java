package com.california.common.dto;

import com.california.mams.orm.model.MamsMenu;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ffn on 8/11/2018.
 */
public class MenuInfoDTO {
    public class MenuItem {
        String kid;
        String url;
        String name;
        String comment;

        public MenuItem(String hostUrl, MamsMenu menu) {
            this.kid = menu.getKid();
            this.url = hostUrl + menu.getUri();
            this.name = menu.getName();
            this.comment = menu.getComment();
        }

        public String getKid() {
            return kid;
        }

        public void setKid(String kid) {
            this.kid = kid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private List<MenuItem> menuList;

    public MenuInfoDTO(String hostUrl, List<MamsMenu> mamsMenuList) {
        this.menuList = new ArrayList<>();
        menuList.sort(Comparator.comparing(MamsMenu::getWeight));
        List<MamsMenu> tmpMenuList = new ArrayList<>();
        for (MamsMenu menu : mamsMenuList) {
            if (StringUtils.isEmpty(menu.getP1Kid())) {
                tmpMenuList.add(menu);
                menuList.add(new MenuItem(hostUrl, menu));
            }
        }
        mamsMenuList.removeAll(tmpMenuList);

    }

    public List<MenuItem> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuItem> menuList) {
        this.menuList = menuList;
    }
}
