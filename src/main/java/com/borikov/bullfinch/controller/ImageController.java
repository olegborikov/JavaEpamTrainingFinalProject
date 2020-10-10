package com.borikov.bullfinch.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/images/*")
public class ImageController extends HttpServlet {
    private static final int BEGIN_INDEX = 1;
    private static final String UPLOAD_DIRECTORY = "C:\\uploads";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {// TODO: 10.10.2020 refactor
        String filename = request.getPathInfo().substring(BEGIN_INDEX);
        File file = new File(UPLOAD_DIRECTORY, filename);
        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
    }
}