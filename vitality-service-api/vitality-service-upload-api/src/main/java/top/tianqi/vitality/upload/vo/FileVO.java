package top.tianqi.vitality.upload.vo;

/**
 * 文件信息VO
 * @Author wkh
 * @Date 2020/7/30 18:23
 */
public class FileVO {

    /** 文件名称 */
    private String fileName;

    /** 文件存储路径 */
    private String filePath;

    public FileVO(){}

    public FileVO(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
