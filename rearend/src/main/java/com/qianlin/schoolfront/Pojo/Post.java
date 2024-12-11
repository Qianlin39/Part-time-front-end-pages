package com.qianlin.schoolfront.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer Pid;
    private String Pname;
    private String Pcontent;
    private String Ptype;
    private Integer Puid;
    private String Pstatuse;
    private String Pupdatetime;
    private Integer Psalary;
}
