package com.fire.Pojo;

import java.io.File;
import java.util.Date;

public class FileData {
//    文件大小
    private Integer fileSize;
//    文件类型 即后缀名
    private String fileType;
//    原始文件名
    private String oldFileName;
//    文件创建日期
    private Date createDate;
//    文件目录
    private String filePath;
//    数字信封
    private String digitalEnvelope;

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDigitalEnvelope() {
        return digitalEnvelope;
    }

    public void setDigitalEnvelope(String digitalEnvelope) {
        this.digitalEnvelope = digitalEnvelope;
    }

    public FileData(Integer fileSize, String fileType, String oldFileName, Date createDate, String filePath, String digitalEnvelope) {
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.oldFileName = oldFileName;
        this.createDate = createDate;
        this.filePath = filePath;
        this.digitalEnvelope = digitalEnvelope;
    }

    public FileData(){}
}
