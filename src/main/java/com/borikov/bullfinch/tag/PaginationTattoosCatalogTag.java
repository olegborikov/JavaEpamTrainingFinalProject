package com.borikov.bullfinch.tag;

import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.model.entity.Tattoo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The {@code PaginationUsersAdminTag} class represents catalog pagination tag for tattoos.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class PaginationTattoosCatalogTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String BUNDLE_PATH = "i18n.application_message";
    private static final String TATTOOS_INFO = "tattoosAdmin.info";
    private static final String SPLIT_SYMBOL = "_";
    private int pageNumber;
    private int tattoosAmountOnPage;

    /**
     * Sets page number.
     *
     * @param pageNumber the page number
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Sets tattoos amount on page.
     *
     * @param tattoosAmountOnPage the tattoos amount on page
     */
    public void setTattoosAmountOnPage(int tattoosAmountOnPage) {
        this.tattoosAmountOnPage = tattoosAmountOnPage;
    }

    @Override
    public int doStartTag() throws JspException {
        ServletRequest request = pageContext.getRequest();
        List<Tattoo> tattoos = (List<Tattoo>) request.getAttribute(RequestParameter.TATTOOS);
        int currentIndex = pageNumber * tattoosAmountOnPage - tattoosAmountOnPage;
        int lastIndex = pageNumber * tattoosAmountOnPage - 1;
        HttpSession session = pageContext.getSession();
        String localeName = (String) session.getAttribute(RequestParameter.CURRENT_LOCALE);
        String language = localeName.split(SPLIT_SYMBOL)[0];
        String country = localeName.split(SPLIT_SYMBOL)[1];
        Locale locale = new Locale(language, country);
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PATH, locale);
        while (currentIndex < tattoos.size() && currentIndex <= lastIndex) {
            try {
                pageContext.getOut().write("<div class=\"col-lg-4\">\n"
                        + "<div class=\"image-wrap-2\">\n"
                        + "<div class=\"image-info\">\n"
                        + "<h2 class=\"mb-3\">"
                        + tattoos.get(currentIndex).getName() + "</h2>\n"
                        + "<form name=\"tattooInfoForm\" method=\"post\" action=\"/bullfinch\">\n"
                        + "<input type=\"hidden\" name=\"commandName\"\n"
                        + "value=\"browse_tattoo_page_command\">\n"
                        + "<button class=\"btn btn-outline-white py-2 px-4\"\n"
                        + "name=\"tattooId\" value=\""
                        + tattoos.get(currentIndex).getTattooId()
                        + "\">\n" + bundle.getString(TATTOOS_INFO)
                        + "</button>\n"
                        + "</form>\n"
                        + "</div>\n"
                        + "<img src=\"/images/"
                        + tattoos.get(currentIndex).getImage().getName() + ".jpg\"\n"
                        + "alt=\"Image\" class=\"img-fluid\">\n"
                        + "</div>\n"
                        + "</div>\n");
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Error while writing to out stream", e);
                throw new JspException("Error while writing to out stream", e);
            }
            currentIndex++;
        }
        return SKIP_BODY;
    }
}
