package com.liuyu7177.zuoriweilai.web.springMvcDemo.model;

/**
 * Created by liuyu7177 On 2019/5/16
 */
public class RoleModel {
    private String roleName;
    private String roleNote;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleNote() {
        return roleNote;
    }

    public void setRoleNote(String roleNote) {
        this.roleNote = roleNote;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "roleName='" + roleName + '\'' +
                ", roleNote='" + roleNote + '\'' +
                '}';
    }
}
