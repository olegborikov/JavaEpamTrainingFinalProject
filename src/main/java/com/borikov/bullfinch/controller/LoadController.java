package com.borikov.bullfinch.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * The {@code UploadController} class represents images load controller.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
@WebServlet("/images/*")
public class LoadController extends HttpServlet {
    private static final int BEGIN_INDEX = 1;
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIRECTORY = "C:\\uploads";
    private static final String CONTENT_DISPOSITION_VALUE = "inline; filename=\"%s\"";

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
        String filename = request.getPathInfo().substring(BEGIN_INDEX);
        File file = new File(UPLOAD_DIRECTORY, filename);
        response.setHeader(CONTENT_TYPE, getServletContext().getMimeType(filename));
        response.setHeader(CONTENT_LENGTH, String.valueOf(file.length()));
        response.setHeader(CONTENT_DISPOSITION, String.format(CONTENT_DISPOSITION_VALUE, filename));
        Files.copy(file.toPath(), response.getOutputStream());
    }
}
