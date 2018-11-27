package com.ntap.antd.reactservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.ntap.antd.reactservice.config
 * @auther YuAn
 * @Date 2018/11/21
 * @Time 14:49
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    public AuditorAware<Long> auditorProvider() {
        return new SpringSecurityAuditAwareImpl();
    }
}



