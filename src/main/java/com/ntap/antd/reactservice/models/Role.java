package com.ntap.antd.reactservice.models;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author YuAn
 * @Package: com.ntap.antd.reactservice.models
 * @auther: YuAn
 * @Date: 2018/11/20
 * @Time: 16:05
 * @Project_name: React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description:
 */

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public Role(){
    }

    public Role(RoleName name) {
        this.name = name;
    }
}
