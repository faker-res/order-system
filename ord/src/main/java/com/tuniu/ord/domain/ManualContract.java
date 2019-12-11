package com.tuniu.ord.domain;

public class ManualContract extends BaseManualDomain {
    /**
     * 
     */
    private static final long serialVersionUID = 7710952523307916596L;

    private String fileName;

    private String fileSuffix;

    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix == null ? null : fileSuffix.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }
}