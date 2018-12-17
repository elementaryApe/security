package com.herman.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.herman.security.core.properties.SecurityProperties;
import com.herman.security.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户请求api
 *
 * @author hsh
 * @create 2018-11-15 16:04
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;//基于session获取第三方用户信息

//    @Autowired
//    private AppSignUpUtils appSignUpUtils;//app依赖，基于redis获取第三方用户信息,AppSecurityController


    @PostMapping(value = "/register")
    public void register(User user, HttpServletRequest request) {
        //注册用户 不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
        String userName = user.getUserName();
        providerSignInUtils.doPostSignUp(userName, new ServletWebRequest(request));
//        appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userName);
        //注册成功即登录逻辑
    }

    /**
     * 获取当前用户
     */
    @GetMapping(value = "/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails authentication) {
//        SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    /**
     * 获取当前用户
     */
    @GetMapping(value = "/jwt/me")
    public Object getAppCurrentUser(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {
//        SecurityContextHolder.getContext().getAuthentication();
        String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");

        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
                .parseClaimsJws(token).getBody();

        String company = (String) claims.get("author");

        logger.info(company);

        return authentication;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @PutMapping(value = "/{id:\\d+}")
    public User update(@PathVariable(value = "id") String id, @Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
//                FieldError fieldError = (FieldError) error;
//                String message = fieldError.getField() + " " + error.getDefaultMessage();
                System.out.println(error.getDefaultMessage());
            });
        }
        System.out.println("id===>" + id);
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId(id);
        return user;
    }

    @DeleteMapping(value = "/{id:\\d+}")
    public void delete(@PathVariable(value = "id") String id) {
        System.out.println(id);
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "size", value = "每页数量", defaultValue = "10", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页", defaultValue = "10", dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序条件", defaultValue = "username", dataType = "String")
    })
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(@PageableDefault(page = 2, size = 10, sort = "username") Pageable pageable) {
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());

        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }


    @GetMapping(value = "/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable(value = "id") String id) {
//        throw new UserNotExistException("user not exist");
        logger.info("进入 getInfo");
        User user = new User();
        user.setUserName("tom");
        return user;
    }
}
