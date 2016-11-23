package ch.aos.demo.filewatcher.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.filefilter.IOFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.aos.demo.filewatcher.App;
import ch.aos.demo.filewatcher.common.FileExtentionType;
import ch.aos.demo.filewatcher.myfile.MyFile;
import ch.aos.demo.filewatcher.service.BaseFileWatcher;

/**
 * File tracing of only *.xml file types
 * 
 * @author aos
 *
 */
public class XMLFileWatcher extends BaseFileWatcher {

	// private final BlockingQueue<Long> changeQueue = new
	// ArrayBlockingQueue<Long>(2);
	private static final Logger LOGGER = LoggerFactory
			.getLogger(XMLFileWatcher.class);

	private Map<String, MyFile> threadQueueModifiedFiles = new ConcurrentHashMap<String, MyFile>();

	/**
	 * Creates a WatchService and registers the given directory
	 */
	public XMLFileWatcher(File dir, IOFileFilter files, int interval)
			throws IOException {
		super(dir, files, interval);
	}

	@Override
	public void onUpdated(MonitorEvent event, File file) {
		String fname = getFName(file.getName());
		switch (event) {
		case FCREATE:
			addItem(fname, event, file, FileExtentionType.XML);
			break;

		case FCHANGE:
			threadQueueModifiedFiles.put(fname,
					new MyFile(Long.valueOf(System.currentTimeMillis()), file,
							event, FileExtentionType.XML));
			LOGGER.info("XML File modification type [" + event.name()
					+ "] File name [" + fname + "]");
			break;

		default:
			break;
		}
	}
}