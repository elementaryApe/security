package com.herman.security.entity;

/**
 * 文件实体
 *
 * @author hsh
 * @create 2018-11-16 14:49
 **/
public class FileInfo {

    private String path;

    public FileInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
