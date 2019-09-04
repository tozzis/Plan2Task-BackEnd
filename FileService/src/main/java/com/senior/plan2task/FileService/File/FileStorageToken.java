package com.senior.plan2task.FileService.File;

public class FileStorageToken {
    
    private String write;
    
    private String id;
    
    private String from;

    public FileStorageToken() {
    }

    public FileStorageToken(String write, String id, String from) {
        this.write = write;
        this.id = id;
        this.from = from;
    }

    public String getWrite() {
        return write;
    }

    public void setWrite(String write) {
        this.write = write;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}