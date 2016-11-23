package ch.aos.demo.filewatcher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import ch.aos.demo.filewatcher.daemon.impl.FileCleanupDaemonImpl;

/**
 * Daemon configuration
 * 
 * @author aos
 *
 */
@Configuration
@EnableScheduling
public class AppFileCleanupDaemonConfig implements SchedulingConfigurer {
	@Bean
	public FileCleanupDaemonImpl task() {
		return new FileCleanupDaemonImpl();
	}

	@Bean
	ThreadPoolTaskScheduler taskSchedulerPool() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(100);
		scheduler.setThreadNamePrefix("File-Cleanup-Daemon-");
		scheduler.setAwaitTerminationSeconds(10);
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		return scheduler;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setTaskScheduler(this.taskSchedulerPool());
	}
}