package com.borikov.bullfinch.controller;

import com.borikov.bullfinch.util.PhotoFileManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * The {@code UploadController} class represents file upload controller.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadController extends HttpServlet {
    private static final PhotoFileManager photoFileManager = new PhotoFileManager();
    private static final String DISPATCHER_PATH_CONTROLLER = "/bullfinch";

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
        String page = PagePath.TATTOO_OFFER;
        Optional<String> photoName = photoFileManager.add(request.getParts());
        if (photoName.isPresent()) {
            request.setAttribute(RequestParameter.PHOTO_NAME, photoName.get());
            page = DISPATCHER_PATH_CONTROLLER;
        } else {
            request.setAttribute(RequestParameter.INCORRECT_IMAGE_MESSAGE, true);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}

