package ch.aos.demo.filewatcher;

import java.lang.management.ManagementFactory;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import ch.aos.demo.filewatcher.common.AppPropertyConstants;
import ch.aos.demo.filewatcher.config.AppCommonConfig;
import ch.aos.demo.filewatcher.config.LiveConfig;

/**
 * The {@link EnableAutoConfiguration} and the {@link ComponentScan} are not
 * used as it causes performance issues. Application beans are declared per
 * Profile Type. See {@link LiveConfig} .<br>
 *
 */
@PropertySources({ @PropertySource(value = AppPropertyConstants.APPLICATION_PROPERTY_SOURCE_LIVE, ignoreResourceNotFound = false) })
@Import({ AppCommonConfig.class, LiveConfig.class })
public class App {

	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	/** Application context */
	private static ApplicationContext applicationContext;

	/**
	 * Application entry point modified for the windows as service
	 * 
	 * @param args
	 *            optional
	 */
	public static void main(String[] args) {
		String mode = args != null && args.length > 0 ? args[0] : null;

		// Build file path to client properties
		String workingDirectory = System.getProperty("user.dir");
		StringBuilder sb = new StringBuilder();
		System.out.println(workingDirectory);
		sb.append(workingDirectory);
		LOGGER.info("Path to client property has be set to :: {}",
				sb.toString());
		if (StringUtils.isNoneEmpty(mode)) {
			LOGGER.info("PID:" + ManagementFactory.getRuntimeMXBean().getName()
					+ " Application mode:" + mode + " context:" + applicationContext);
		}

		if (applicationContext != null && mode != null && "stop".equals(mode)) {
			System.exit(SpringApplication.exit(applicationContext,
					new ExitCodeGenerator() {
						@Override
						public int getExitCode() {
							return 0;
						}
					}));
		} else {
			SpringApplication app = new SpringApplication(App.class);
			app.setBannerMode(Mode.OFF);
			applicationContext = app.run(args);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("PID:"
						+ ManagementFactory.getRuntimeMXBean().getName()
						+ " Application started context:" + applicationContext);
			}
		}
	}
}