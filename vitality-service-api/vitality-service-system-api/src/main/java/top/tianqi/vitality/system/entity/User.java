package top.tianqi.vitality.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @Author wkh
 * @Date 2020/8/19 11:17
 */
@TableName(value = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 5700660322326020641L;

    /** 用户状态：有效*/
    public static final String STATUS_VALID = "1";
    /** 用户状态：锁定*/
    public static final String STATUS_LOCK = "0";
    /** 默认头像*/
    public static final String DEFAULT_AVATAR = "default.jpg";
    /** 默认密码*/
    public static final String DEFAULT_PASSWORD = "1234qwer";
    /** 性别男*/
    public static final String SEX_MALE = "0";
    /** 性别女*/
    public static final String SEX_FEMALE = "1";
    /** 性别保密*/
    public static final String SEX_UNKNOW = "2";

    /**用户ID*/
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;

    /**用户名*/
    @TableField(value = "USERNAME")
    private String username;

    /**密码*/
    @TableField(value = "PASSWORD")
    private String password;

    /**部门 ID*/
    @TableField(value = "DEPT_ID")
    private Long deptId;

    /**邮箱*/
    @TableField(value = "EMAIL")
    private String email;

    /**联系电话*/
    @TableField(value = "MOBILE")
    private String mobile;

    /**状态 0锁定 1有效*/
    @TableField(value = "STATUS")
    private String status;

    /**创建时间*/
    @TableField(value = "CREATE_TIME")
    private Date createTime;

    /**修改时间*/
    @TableField(value = "MODIFY_TIME")
    private Date modifyTime;

    /** 最近访问时间*/
    @TableField(value = "LAST_LOGIN_TIME")
    private Date lastLoginTime;

    /**性别 0男 1女 2 保密*/
    @TableField(value = "gender")
    private String sex;

    /**头像*/
    @TableField(value = "AVATAR")
    private String avatar;

    /**描述*/
    @TableField(value = "DESCRIPTION")
    private String description;

    /**部门名称*/
    @TableField(exist = false)
    private String deptName;

    @TableField(exist = false)
    private String createTimeFrom;
    @TableField(exist = false)
    private String createTimeTo;
    /**角色 ID*/
    @TableField(exist = false)
    private String roleId;
    /** 角色名称*/
    @TableField(exist = false)
    private String roleName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
