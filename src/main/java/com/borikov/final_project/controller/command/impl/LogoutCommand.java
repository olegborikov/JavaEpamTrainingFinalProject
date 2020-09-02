package com.borikov.final_project.controller.command.impl;

import com.borikov.final_project.controller.PagePath;
import com.borikov.final_project.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.LOGIN;
    }
}
