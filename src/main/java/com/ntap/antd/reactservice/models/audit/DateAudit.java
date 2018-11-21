package com.ntap.antd.reactservice.models.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package: com.ntap.antd.reactservice.models
 * @auther: YuAn
 * @Date: 2018/11/20
 * @Time: 17:19
 * @Project_name: React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description:
 */

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createAt", "updatedAt"},
allowGetters = true)
public abstract class DateAudit implements Serializable {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updateAt;
}
