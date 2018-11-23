package com.herman.security.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.herman.security.validator.ConstraintTest;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author hsh
 * @create 2018-11-15 16:05
 **/
public class User implements Serializable{
    private static final long serialVersionUID = 3599638653254177520L;

    public interface UserSimpleView{};

    public interface UserDetailView extends UserSimpleView{};

    private String id;

    @ConstraintTest(message = "这是一个测试注解")
    @ApiModelProperty(value = "用户名称")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Past(message = "生日必须是过去时间")
    private Date    birthday;

    public String getId() {
        return id;
    }

    @JsonView(UserSimpleView.class)
    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
