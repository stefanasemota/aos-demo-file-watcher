package ch.aos.demo.filewatcher.daemon;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.annotation.Validated;

/**
 * Provides a contract for the cleaning of watched file directory
 * 
 * @author aos
 *
 */
@Validated
public interface FileCleanupDaemon extends InitializingBean {

    final String COMPONENT_NAME = "fileCleanupDaemon";

    void cleanup();
}
