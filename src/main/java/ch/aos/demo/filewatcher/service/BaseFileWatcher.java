package ch.aos.demo.filewatcher.service;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.aos.demo.filewatcher.common.FileExtentionType;
import ch.aos.demo.filewatcher.myfile.MyFile;

/**
 * Base class for a file monitor
 * 
 * @author aos
 *
 */
public abstract class BaseFileWatcher {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BaseFileWatcher.class);

	public enum MonitorEvent {
		STARTUP, FCREATE, FDELETE, FCHANGE
	};

	private IOFileFilter ioFileFilter = null;

	private Map<String, MyFile> threadQueue = new ConcurrentHashMap<String, MyFile>();

	private FileAlterationObserver observer;

	private FileAlterationMonitor monitor;

	/**
	 * 
	 * @param dir
	 *            watch directory
	 * @param files
	 *            {@link IOFileFilter}
	 * @param interval
	 *            in miliseconds
	 */
	public BaseFileWatcher(File dir, IOFileFilter files, int interval) {
		this.observer = new FileAlterationObserver(dir, files);
		this.monitor = new FileAlterationMonitor(interval, observer);

		observer.addListener(new FileAlterationListenerAdaptor() {

			@Override
			public void onFileCreate(File file) {
				onUpdated(MonitorEvent.FCREATE, file);
			}

			@Override
			public void onFileChange(File file) {
				onUpdated(MonitorEvent.FCHANGE, file);
			}

		});
	}

	public void start() throws Exception {
		monitor.start();
	}

	public void stop() {
		try {
			monitor.stop();
		} catch (Exception exc) {
			LOGGER.error("Error:: ", exc);
		}
	}

	public IOFileFilter getFiles() {
		return this.ioFileFilter;
	}

	public String getFName(String path) {
		return FilenameUtils.removeExtension(path);
	}

	protected void addItem(String fname, MonitorEvent event, File file,
			FileExtentionType extention) {
		this.threadQueue.put(fname,
				new MyFile(Long.valueOf(System.currentTimeMillis()), file,
						event, extention));
		LOGGER.info(extention + " File modification type [" + event.name()
				+ "] File name [" + fname + "]");
	}

	public Map<String, MyFile> getThreadQueue() {
		return threadQueue;
	}

	public FileAlterationObserver getObserver() {
		return observer;
	}

	public abstract void onUpdated(MonitorEvent event, File file);
}
