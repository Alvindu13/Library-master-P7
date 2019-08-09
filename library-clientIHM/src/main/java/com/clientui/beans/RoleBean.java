package com.clientui.beans;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
public class RoleBean {


    private Integer id;
    private String name;
    private List<UserBean> users;

}
