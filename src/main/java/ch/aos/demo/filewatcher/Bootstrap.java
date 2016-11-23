package ch.aos.demo.filewatcher;

import org.springframework.boot.loader.JarLauncher;
import org.springframework.boot.loader.jar.JarFile;

/**
 * Prun service using the spring {@link JarFile}
 */
public class Bootstrap extends JarLauncher {

	private static ClassLoader classLoader = null;
	private static Bootstrap bootstrap = null;

	protected void launch(String[] args, String mainClass,
			ClassLoader classLoader, boolean wait) throws Exception {
		Runnable runner = createMainMethodRunner(mainClass, args, classLoader);
		Thread runnerThread = new Thread(runner);
		runnerThread.setContextClassLoader(classLoader);
		runnerThread.setName(Thread.currentThread().getName());
		runnerThread.start();
		if (wait == true) {
			// Thread.sleep(1000);
			runnerThread.join();
		}
	}

	public static void start(String[] args) {

		bootstrap = new Bootstrap();
		try {
			JarFile.registerUrlProtocolHandler();
			classLoader = bootstrap.createClassLoader(bootstrap
					.getClassPathArchives());
			bootstrap.launch(args, bootstrap.getMainClass(), classLoader, true);
		} catch (Exception ex) {
			System.out.println("EXCEPTION IN START");
			ex.printStackTrace();
			System.exit(1);
		}
	}

	public static void stop(String[] args) {
		try {
			if (bootstrap != null) {
				bootstrap.launch(args, bootstrap.getMainClass(), classLoader,
						true);
				bootstrap = null;
				classLoader = null;
			}
		} catch (Exception ex) {
			System.out.println("EXCEPTION IN STOP");
			ex.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		String mode = args != null && args.length > 0 ? args[0] : null;
		System.out.println("START MODE IS " + mode);
		if ("start".equals(mode)) {
			Bootstrap.start(args);
		} else if ("stop".equals(mode)) {
			Bootstrap.stop(args);
		}
	}
}