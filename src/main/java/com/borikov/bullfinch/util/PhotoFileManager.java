package com.borikov.bullfinch.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class PhotoFileManager {
    private static final String UPLOAD_DIRECTORY = "C:\\uploads";
    private static final String END_PHOTO_NAME = ".jpg";
    private static final Logger LOGGER = LogManager.getLogger();

    public Optional<String> add(Collection<Part> photoParts) {
        Path path = Paths.get(UPLOAD_DIRECTORY);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Error while creating directory {}", UPLOAD_DIRECTORY, e);
            }
        }
        Optional<String> photoName = Optional.empty();
        for (Part part : photoParts) {
            String fileName = part.getSubmittedFileName();
            if (fileName != null) {
                if (fileName.endsWith(END_PHOTO_NAME)) {
                    try {
                        String newFileName = UUID.randomUUID().toString();
                        part.write(UPLOAD_DIRECTORY + File.separator + newFileName + END_PHOTO_NAME);
                        LOGGER.log(Level.INFO, "Upload successful. File: {}", fileName);
                        photoName = Optional.of(newFileName);
                    } catch (IOException e) {
                        LOGGER.log(Level.ERROR, "Error while writing file {}", fileName, e);
                    }
                } else {
                    LOGGER.log(Level.ERROR, "Upload failed. File: {}", fileName);
                }
            }
        }
        return photoName;
    }

    public void delete(String photoName) {
        File file = new File(UPLOAD_DIRECTORY + File.separator + photoName + END_PHOTO_NAME);
        if (file.delete()) {
            LOGGER.log(Level.INFO, "File {} was deleted", photoName + END_PHOTO_NAME);
        }
    }
}
