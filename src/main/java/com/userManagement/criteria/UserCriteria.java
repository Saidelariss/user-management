package com.userManagement.criteria;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserCriteria {
    private String search;
    private String email;
    private String role;
    private String username;
}
