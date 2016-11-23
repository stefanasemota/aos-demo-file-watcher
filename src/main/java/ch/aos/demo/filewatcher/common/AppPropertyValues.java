package ch.aos.demo.filewatcher.common;

public interface AppPropertyValues {

    /* Tomcat */
    public final static String TOMCAT_SERVER_PORT = "${tomcatServerPort}";

    public final static String TOMCAT_SERVER_CONTEXT_PATH = "${server.contextPath}";

    /* File cleanup */
    public final static String FILE_CLEANUP_SCHEDULING_CRON = "${fileCleanup.schedulingCron}";

    /* File watcher */
    public final static String FILE_DIRECTORY_PATH = "${dir.path}";

    public final static String FILE_WATCHER_INTERVAL = "${fileWatcher.interval}";

}