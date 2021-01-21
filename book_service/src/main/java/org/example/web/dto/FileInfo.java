package org.example.web.dto;

public class FileInfo {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileInfo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
            "name='" + name + '\'' +
            '}';
    }
}
