package com.borikov.bullfinch.tag;

import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.model.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class PaginationUsersTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();
    private int pageNumber;
    private int usersAmountOnPage;

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setUsersAmountOnPage(int usersAmountOnPage) {
        this.usersAmountOnPage = usersAmountOnPage;
    }

    @Override
    public int doStartTag() throws JspException {
        ServletRequest request = pageContext.getRequest();
        List<User> users = (List<User>) request.getAttribute(RequestParameter.USERS);
        int currentIndex = pageNumber * usersAmountOnPage - usersAmountOnPage;
        int lastIndex = pageNumber * usersAmountOnPage - 1;
        while (currentIndex < users.size() && currentIndex <= lastIndex) {
            try {
                pageContext.getOut().write("<div class=\"col-md-12\">\n" +
                        "<form method=\"post\" action=\"controller\">\n" +
                        "<input type=\"hidden\" name=\"commandName\"\n" +
                        "value=\"browse_profile_admin_page_command\">\n" +
                        "<input style=\"background-color: black; text-align: left\"\n" +
                        "type=\"submit\"\n" +
                        "class=\"form-control text-white\"\n" +
                        "name=\"login\"\n" +
                        "value=\"" + users.get(currentIndex).getLogin() + "\">\n" +
                        "</form>\n" +
                        "</div>");
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Error while writing to out stream", e);
            }
            currentIndex++;
        }
        return SKIP_BODY;
    }
}
