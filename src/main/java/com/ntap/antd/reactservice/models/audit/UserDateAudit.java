package com.ntap.antd.reactservice.models.audit;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.ntap.antd.reactservice.models.audit
 * @auther YuAn
 * @Date 2018/11/27
 * @Time 11:12
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */

@Data
@AllArgsConstructor
public abstract class UserDateAudit extends DateAudit {

    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;


}
