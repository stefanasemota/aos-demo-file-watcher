package ch.aos.demo.filewatcher.common;

/* Enum file extension */
public enum FileExtentionType {
    XML("*.xml"), DOCX("*.docx");

    private String extention;

    FileExtentionType(String x) {
        extention = x;
    }

    public String getExtention() {
        return extention;
    }
};
