package com.herman.security.code;

import com.herman.security.core.validate.code.image.ImageCode;
import com.herman.security.core.validate.code.base.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 自定义图形验证码生成器
 * @author hsh
 * @create 2018-11-20 14:42
 **/
//@Component("imageValidateCodeGenerator")
public class DemoImageCodeGenerator /*implements ValidateCodeGenerator*/{

//    @Override
    public ImageCode createCode(ServletWebRequest request) {
        System.out.println("更高级图形验证配置");
        return null;
    }
}
