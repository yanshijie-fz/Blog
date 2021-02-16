package com.shijie.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户实体类")
public class User {
    @ApiModelProperty("用户id")
    private int user_id;
    @ApiModelProperty("用户名")
    private String user_name;
    @ApiModelProperty("用户密码")
    private String user_password;
    @ApiModelProperty("用户权限级别")
    private int user_grade;
    @ApiModelProperty("用户邮箱")
    private String user_email;
    @ApiModelProperty("用户性别")
    private int user_gender;
}
