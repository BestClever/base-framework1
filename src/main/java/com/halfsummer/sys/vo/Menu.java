package com.halfsummer.sys.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BestClever
 * @title: Menu
 * @projectName base-framework
 * @description: TODO
 * @date 2020-05-25 13:36
 */
@Data
public class Menu {

    private String title;//菜单名字
    private String href;//连接地址
    private String fontFamily;//字体
    private String icon;//图标
    private boolean spread;//是否展开
    private boolean isCheck;//是否选中

    private List<Menu> children = new ArrayList<>();
}
