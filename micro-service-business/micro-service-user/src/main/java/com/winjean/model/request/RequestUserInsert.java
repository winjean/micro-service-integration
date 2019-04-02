package com.winjean.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;


@Data
public class RequestUserInsert {

    private String id;

    @NotEmpty(message = "用户名信息不能为空")
    private String name;

    @Size(min = 6,max = 8,message = "密码长度为[6-8]")
    private String password;

    @Past(message = "出生日期必须为过去时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    @AssertTrue
    private boolean sex;

    @Email
    private String email;

    @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", message = "手机号格式错误")
    private String telephone;

    private int status;

    @Size(min = 2, message = "可以没有,有的话最少有2个")
    private List<String> role;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
}
