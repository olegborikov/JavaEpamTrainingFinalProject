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

/**
 * The {@code PaginationUsersAdminTag} class represents pagination users admin.
 *
 * @author Oleg Borikov
 * @version 1.0
 * @since 2020-10-16
 */
public class PaginationUsersAdminTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();
    private int pageNumber;
    private int usersAmountOnPage;

    /**
     * Sets page number.
     *
     * @param pageNumber the page number
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Sets users amount on page.
     *
     * @param usersAmountOnPage the users amount on page
     */
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
                pageContext.getOut().write("<div class=\"col-md-12\">\n"
                        + "<form method=\"post\" action=\"controller\">\n"
                        + "<input type=\"hidden\" name=\"commandName\"\n"
                        + "value=\"browse_profile_admin_page_command\">\n"
                        + "<button style=\"background-color: black; text-align: left\"\n"
                        + "class=\"form-control text-white\"\n"
                        + "name=\"login\"\n"
                        + "value=\"" + users.get(currentIndex).getLogin() + "\">\n"
                        + users.get(currentIndex).getLogin() + ", "
                        + users.get(currentIndex).getEmail() + ", "
                        + users.get(currentIndex).getFirstName() + " "
                        + users.get(currentIndex).getSecondName()
                        + "</button>\n"
                        + "</form>\n"
                        + "</div>");
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Error while writing to out stream", e);
                throw new JspException("Error while writing to out stream", e);
            }
            currentIndex++;
        }
        return SKIP_BODY;
    }
}
