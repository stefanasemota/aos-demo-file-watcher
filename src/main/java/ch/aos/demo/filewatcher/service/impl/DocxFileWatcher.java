package ch.aos.demo.filewatcher.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.filefilter.IOFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.aos.demo.filewatcher.common.FileExtentionType;
import ch.aos.demo.filewatcher.myfile.MyFile;
import ch.aos.demo.filewatcher.service.BaseFileWatcher;

/**
 * File tracing of only *docx file types
 * 
 * @author aos
 *
 */
public class DocxFileWatcher extends BaseFileWatcher {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DocxFileWatcher.class);

	private Map<String, MyFile> threadQueueModifiedFiles = new ConcurrentHashMap<String, MyFile>();

	/**
	 * Creates a WatchService and registers the given directory
	 */
	public DocxFileWatcher(File dir, IOFileFilter files, int interval)
			throws IOException {
		super(dir, files, interval);

	}

	@Override
	public void onUpdated(MonitorEvent event, File file) {
		String fname = getFName(file.getName());
		switch (event) {
		case FCREATE:
			addItem(fname, event, file, FileExtentionType.DOCX);
			break;

		case FCHANGE:
			threadQueueModifiedFiles.put(fname,
					new MyFile(Long.valueOf(System.currentTimeMillis()), file,
							event, FileExtentionType.DOCX));
			LOGGER.info("DOCX File modification type:: " + event.name() + " "
					+ fname);
			break;
		default:
			break;
		}
	}
}