package top.tianqi.vitality.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单实体类
 *
 * @Author wkh
 * @Date 2020/8/19 11:24
 */
@TableName(value = "menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = -467007697523076674L;

    /** 菜单*/
    public static final String TYPE_MENU = "0";
    /** 按钮*/
    public static final String TYPE_BUTTON = "1";

    /** 菜单/按钮ID*/
    @TableId(value = "MENU_ID", type = IdType.AUTO)
    private Long menuId;

    /**上级菜单ID*/
    @TableField("PARENT_ID")
    private Long parentId;

    /** 菜单/按钮名称*/
    @TableField("MENU_NAME")
    private String menuName;

    /**菜单URL*/
    @TableField("PATH")
    private String path;

    /** 对应 Vue组件*/
    @TableField("COMPONENT")
    private String component;

    /** 权限标识*/
    @TableField("PERMS")
    private String perms;

    /** 图标*/
    @TableField("ICON")
    private String icon;

    /** 类型 0菜单 1按钮*/
    @TableField("TYPE")
    private String type;

    /** 排序*/
    @TableField("ORDER_NUM")
    private Integer orderNum;

    /** 创建时间*/
    @TableField("CREATE_TIME")
    private Date createTime;

    /**修改时间*/
    @TableField("MODIFY_TIME")
    private Date modifyTime;

    @TableField(exist = false)
    private String createTimeFrom;
    @TableField(exist = false)
    private String createTimeTo;


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(String createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public String getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(String createTimeTo) {
        this.createTimeTo = createTimeTo;
    }
}
