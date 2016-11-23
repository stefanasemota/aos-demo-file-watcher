package ch.aos.demo.filewatcher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import ch.aos.demo.filewatcher.common.MyFileMonitor;
import ch.aos.demo.filewatcher.service.impl.JaxbFileServiceImpl;

/**
 * Declaration of Application Beans and Services.
 * 
 * @author aos
 *
 */
@Configuration
public class AppBeansConfig {

    @Bean
    public MyFileMonitor EdocsFileMonitor() {
        return new MyFileMonitor();
    }

    /**
     * Helps To resolve ${} in @Value from properties in *.properties
     * 
     * @return PropertySourcesPlaceholderConfigurer
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JaxbFileServiceImpl jaxbFileService() {
        return new JaxbFileServiceImpl();
    }
}