package com.herman.security.core.validate.code.base;

import com.herman.security.core.enums.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码保存接口
 * @author hsh
 * @create 2018-12-01 15:58
 **/
public interface ValidateCodeRepository {


    /**
     * 保存验证码
     * @param request
     * @param code
     * @param codeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType codeType);

    /**
     * 获取验证码
     * @param request
     * @param validateCodeType
     * @return
     */
    ValidateCode get(ServletWebRequest request,ValidateCodeType validateCodeType);

    /**
     * 移除验证码
     * @param request
     * @param codeType
     */
    void remove(ServletWebRequest request,ValidateCodeType codeType);
}
