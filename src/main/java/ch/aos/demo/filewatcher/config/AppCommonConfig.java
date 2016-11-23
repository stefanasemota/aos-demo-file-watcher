package ch.aos.demo.filewatcher.config;

import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvidersConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration;
import org.springframework.boot.autoconfigure.security.BootGlobalAuthenticationConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpEncodingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration class which imports specific the Spring configuration classes
 * 
 * @author aos
 *
 */
@Configuration
// @formatter:off
@Import({
        AuthenticationManagerConfiguration.class,
        BootGlobalAuthenticationConfiguration.class,
        DataSourceAutoConfiguration.class,
        DataSourcePoolMetadataProvidersConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        DispatcherServletAutoConfiguration.class,
        EmbeddedServletContainerAutoConfiguration.class,
        ErrorMvcAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        HttpEncodingAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        JacksonAutoConfiguration.class,
        MultipartAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        ServerPropertiesAutoConfiguration.class,
        SpringBootWebSecurityConfiguration.class,
        ThymeleafAutoConfiguration.class,
        WebMvcAutoConfiguration.class
})
// @formatter:on
public class AppCommonConfig {
    // empty

}