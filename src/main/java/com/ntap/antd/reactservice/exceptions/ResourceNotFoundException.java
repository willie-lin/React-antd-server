package com.ntap.antd.reactservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created with IntelliJ IDEA.
 *
 * @author YuAn
 * @Package com.ntap.antd.reactservice.exceptions
 * @auther YuAn
 * @Date 2018/11/22
 * @Time 14:57
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;

    private String filedName;

    private Object filedValue;

    /**
     * Constructs a new runtime exception with the specified detail
     * message, cause, suppression enabled or disabled, and writable
     * stack trace enabled or disabled.
     *
     *                           and indicates that the cause is nonexistent or unknown.)
     *                           or disabled
     *                           be writable
     * @since 1.7
     */
    public ResourceNotFoundException(String resourceName, String filedName, Object filedValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, filedName, filedValue));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.filedValue = filedValue;
    }
}
