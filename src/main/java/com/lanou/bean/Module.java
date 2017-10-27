package com.lanou.bean;

import java.util.List;

public class Module {
    private Integer moduleId;

    private String name;

    private List<Role> roleList;

    @Override
    public String toString() {
        return "Module{" +
                "moduleId=" + moduleId +
                ", name='" + name + '\'' +
                ", roleList=" + roleList +
                '}';
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}