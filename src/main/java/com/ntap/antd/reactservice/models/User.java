package com.ntap.antd.reactservice.models;

import com.ntap.antd.reactservice.models.audit.DateAudit;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author YuAn
 * @Package: com.ntap.antd.reactservice.models
 * @auther: YuAn
 * @Date: 2018/11/20
 * @Time: 16:04
 * @Project_name: React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description:
 */

@Data
@Entity
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    private String email;

    @NotBlank
    @Size(max = 180)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User( ) {
    }

    public User(@NotBlank @Size(max = 40) String name, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 40) String email, @NotBlank @Size(max = 180) String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
