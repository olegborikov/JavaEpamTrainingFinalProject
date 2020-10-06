package com.borikov.bullfinch.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadController extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "C:\\uploads";
    private static final String END_PHOTO_NAME = ".jpg";
    private static final String DISPATCHER_PATH_CONTROLLER = "/controller";
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dispatcherPath = PagePath.TATTOO_OFFER;
        Path path = Paths.get(UPLOAD_DIRECTORY);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        for (Part part : request.getParts()) {
            String fileName = part.getSubmittedFileName();
            if (fileName != null) {
                if (fileName.endsWith(END_PHOTO_NAME)) {
                    String newFileName = UUID.randomUUID().toString();
                    part.write(UPLOAD_DIRECTORY + File.separator + newFileName + END_PHOTO_NAME);
                    LOGGER.log(Level.INFO, "Upload successful. File: {}", fileName);
                    request.setAttribute(RequestParameter.PHOTO_NAME, newFileName);
                    dispatcherPath = DISPATCHER_PATH_CONTROLLER;
                } else {
                    LOGGER.log(Level.ERROR, "Upload failed. File: {}", fileName);
                    request.setAttribute(RequestParameter.ERROR_IMAGE_MESSAGE, true);
                }
            }
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(dispatcherPath);
        dispatcher.forward(request, response);
    }
}

