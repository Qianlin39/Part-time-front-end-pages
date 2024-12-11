package com.qianlin.schoolfront.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userid;
    private String username;
    private String userpassword;
    private String useremail;
    private String userrole;
    private String userstatus;
}
