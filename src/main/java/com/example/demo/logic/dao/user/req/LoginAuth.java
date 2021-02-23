package com.example.demo.logic.dao.user.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luwl
 * @date 2021/2/23 12:33
 */
@Data
@ApiModel("用户登录实体")
public class LoginAuth implements Serializable {
    @ApiModelProperty("用户名称")
    private String userName;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("密码")
    private String password;

}
