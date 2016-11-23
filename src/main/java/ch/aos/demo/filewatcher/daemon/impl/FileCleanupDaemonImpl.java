package ch.aos.demo.filewatcher.daemon.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import ch.aos.demo.filewatcher.common.AppPropertyValues;
import ch.aos.demo.filewatcher.common.MyFileMonitor;
import ch.aos.demo.filewatcher.daemon.FileCleanupDaemon;
import ch.aos.demo.filewatcher.myfile.MyFile;
import ch.aos.demo.filewatcher.service.JaxbFileService;

@Component(FileCleanupDaemon.COMPONENT_NAME)
public class FileCleanupDaemonImpl implements FileCleanupDaemon {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FileCleanupDaemonImpl.class);

	@Autowired
	private JaxbFileService jaxbFileService;

	@Autowired
	private MyFileMonitor fileMonitor;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(jaxbFileService);
		Assert.notNull(fileMonitor);
	}

	@Scheduled(cron = AppPropertyValues.FILE_CLEANUP_SCHEDULING_CRON)
	@Override
	public void cleanup() {
		try {
			/* Cleanup hashmap */
			synchronized (this) {
				/* Iterate over xml's */
				for (Iterator<Entry<String, MyFile>> iter = fileMonitor
						.getXmlFileWatcher().getThreadQueue().entrySet()
						.iterator(); iter.hasNext();) {

					Map.Entry<String, MyFile> fileXml = iter.next();

					String fileNameWithoutExtention = fileXml.getKey();
					if (fileMonitor.getDocxFileWatcher().getThreadQueue()
							.containsKey(fileNameWithoutExtention)) {

						/* GetFound corresponding DOCX File */
						MyFile fileDocx = fileMonitor.getDocxFileWatcher()
								.getThreadQueue().get(fileNameWithoutExtention);

						LOGGER.info("Current Thread ["
								+ Thread.currentThread().getName()
								+ "] - DOCX File [" + fileNameWithoutExtention
								+ "] , XML File ["
								+ fileDocx.getLiveFile().getName() + ']');
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("ERROR:: " + e);
		}
	}
}