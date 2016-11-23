package ch.aos.demo.filewatcher.config;

import java.io.FileNotFoundException;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.aos.demo.filewatcher.common.AppPropertyValues;

/**
 * Configuration class for an embedded TomCat startup parameters. This class
 * uses two property files: <br>
 * 1). appliation-live.properties.<br>
 * 
 * @author aos
 *
 */
@Configuration
public class AppContainerConfig {

	@Value(AppPropertyValues.TOMCAT_SERVER_PORT)
	private int port;

	@Value(AppPropertyValues.TOMCAT_SERVER_CONTEXT_PATH)
	private String serverContextPath;

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer()
			throws FileNotFoundException {

		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer factory) {
				if (factory instanceof TomcatEmbeddedServletContainerFactory) {
					TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) factory;
					factory.setContextPath(serverContextPath);

					containerFactory
							.addConnectorCustomizers(new TomcatConnectorCustomizer() {
								@Override
								public void customize(Connector connector) {
									connector.setPort(port);
									connector.setSecure(false);
									connector.setScheme("http");
								}
							});
				}
			}
		};
	}
}