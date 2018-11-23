package com.herman.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.herman.security.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
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

    /**
     * 获取当前用户
     */
    @GetMapping(value = "/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails   authentication){
//        SecurityContextHolder.getContext().getAuthentication();
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
            @ApiImplicitParam(name = "page", value = "当前页",defaultValue = "10", dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序条件",defaultValue = "username", dataType = "String")
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
        System.out.println("进入 getInfo");
        User user = new User();
        user.setUserName("tom");
        return user;
    }
}