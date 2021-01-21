package org.example.app.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.example.web.dto.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorage {

    private final Logger logger = Logger.getLogger(FileStorage.class);
    private final File storageDir;

    @Autowired
    FileStorage() {
        String rootPath = System.getProperty("catalina.home");
        storageDir = new File(rootPath + File.separator + "external_uploads");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
    }

    public List<FileInfo> getAllFiles () {
        return Arrays.stream(storageDir.listFiles())
            .filter(file -> !file.isDirectory())
            .map(file -> new FileInfo(file.getName()))
            .collect(Collectors.toList());
    }

    public void uploadFile(MultipartFile file) throws IOException {
        logger.info("new file: " + file.getOriginalFilename());

        String name = file.getOriginalFilename();
        byte[] bytes = file.getBytes();

        File serverFile = new File(storageDir.getAbsolutePath() + File.separator + name);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();

        logger.info("new file saved at: " + serverFile.getAbsolutePath());
    }

    public Resource loadFile(String filename) throws MalformedURLException {
        logger.info("download file: " + filename);

        File file = new File(storageDir.getAbsolutePath() + File.separator + filename);
        return new UrlResource(file.toURI());
    }
}
