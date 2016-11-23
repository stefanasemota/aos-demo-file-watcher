package ch.aos.demo.filewatcher.myfile;

import java.io.File;
import java.io.Serializable;

import ch.aos.demo.filewatcher.common.FileExtentionType;
import ch.aos.demo.filewatcher.service.BaseFileWatcher.MonitorEvent;

public class MyFile implements Serializable {

    /* default */
    private static final long serialVersionUID = -4010073433549221526L;

    private MonitorEvent monitorEvent;

    private File liveFile;

    private Long changedTimestamp;

    private FileExtentionType extention;

    public MyFile() {
        /* Default constructor */
    }

    public MyFile(Long changedTimestamp, File f, MonitorEvent mevent, FileExtentionType extention) {
        this.changedTimestamp = changedTimestamp;
        this.liveFile = f;
        this.monitorEvent = mevent;
        this.extention = extention;
    }

    public MonitorEvent getMonitorEvent() {
        return this.monitorEvent;
    }

    public File getLiveFile() {
        return this.liveFile;
    }

    public Long getChangedTimestamp() {
        return this.changedTimestamp;
    }

    public FileExtentionType getExtention() {
        return this.extention;
    }

    public String getFileName() {
        return MyFileUtils.getFilenameWithExtention(getLiveFile().getName());
    }
}