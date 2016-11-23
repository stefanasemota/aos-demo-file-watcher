package ch.aos.demo.filewatcher.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Configuration class which initializes application specific beans and services
 * 
 * @author aos
 *
 */
@Configuration
@EnableScheduling
// @formatter:off
@Import({ AppContainerConfig.class, AppBeansConfig.class, AppFileCleanupDaemonConfig.class })
// @formatter:on
public class LiveConfig {

}