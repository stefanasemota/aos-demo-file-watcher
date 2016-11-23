package ch.aos.demo.filewatcher.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import ch.aos.demo.filewatcher.myfile.MyFileUtils;
import ch.aos.demo.filewatcher.service.impl.DocxFileWatcher;
import ch.aos.demo.filewatcher.service.impl.XMLFileWatcher;

/**
 * Creates a bunch of file observers and fire them up.
 * @author aos
 *
 */
public class MyFileMonitor implements InitializingBean {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyFileMonitor.class);

	@Value(AppPropertyValues.FILE_DIRECTORY_PATH)
	private String dirPath;

	@Value(AppPropertyValues.FILE_WATCHER_INTERVAL)
	private int interval;

	private List<FileAlterationObserver> observerList = new ArrayList<FileAlterationObserver>();

	private XMLFileWatcher xmlFileWatcher;

	private DocxFileWatcher docxFileWatcher;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(interval);
		Assert.notNull(dirPath);
	}

	@PostConstruct
	public void setupFileMonitor() {
		File folder = new File(dirPath);
		/* Does monitored folder exists */
		/* TODO AOS Add check for "folder must be empty" */
		if (!folder.exists()) {
			throw new RuntimeException("Directory not found: " + dirPath);
		}
		try {

			/* Create File observers */
			this.xmlFileWatcher = new XMLFileWatcher(folder,
					MyFileUtils.getAllFilesWithExtention(FileExtentionType.XML
							.getExtention()), interval);
			this.docxFileWatcher = new DocxFileWatcher(folder,
					MyFileUtils.getAllFilesWithExtention(FileExtentionType.DOCX
							.getExtention()), interval);

			/* Add File observers to list */
			this.observerList.add(xmlFileWatcher.getObserver());
			this.observerList.add(docxFileWatcher.getObserver());

			/* Start file monitors */
			this.xmlFileWatcher.start();
			this.docxFileWatcher.start();

		} catch (Exception e) {
			LOGGER.error("ERROR.: ", e);
		}
	}

	public String getDirPath() {
		return dirPath;
	}

	public List<FileAlterationObserver> getObserverList() {
		return observerList;
	}

	public XMLFileWatcher getXmlFileWatcher() {
		return xmlFileWatcher;
	}

	public DocxFileWatcher getDocxFileWatcher() {
		return docxFileWatcher;
	}
}