package com.borikov.bullfinch.tag;

import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.entity.Tattoo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PaginationTattoosCatalogTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TATTOOS_INFO = "tattoosAdmin.info";
    private int pageNumber;
    private int tattoosAmountOnPage;

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setTattoosAmountOnPage(int tattoosAmountOnPage) {
        this.tattoosAmountOnPage = tattoosAmountOnPage;
    }

    @Override
    public int doStartTag() throws JspException {
        List<Tattoo> tattoos = (List<Tattoo>) pageContext.getRequest().getAttribute(RequestParameter.TATTOOS);
        int currentTattoo = pageNumber * tattoosAmountOnPage - tattoosAmountOnPage;
        int lastTattoo = pageNumber * tattoosAmountOnPage - 1;
        String localeName = (String) pageContext.getSession().getAttribute(RequestParameter.CURRENT_LOCALE);
        String language = localeName.split("_")[0];
        String country = localeName.split("_")[1];
        Locale locale = new Locale(language, country);
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.application_message", locale);
        while (currentTattoo < tattoos.size() && currentTattoo <= lastTattoo) {
            try {
                pageContext.getOut().write("<div class=\"col-lg-4\">\n" +
                        "<div class=\"image-wrap-2\">\n" +
                        "<div class=\"image-info\">\n" +
                        "<h2 class=\"mb-3\">" + tattoos.get(currentTattoo).getName() + "</h2>\n" +
                        "<form name=\"tattooInfoForm\" method=\"post\" action=\"controller\">\n" +
                        "<input type=\"hidden\" name=\"commandName\"\n" +
                        "        value=\"browse_tattoo_page_command\">\n" +
                        "<button class=\"btn btn-outline-white py-2 px-4\"\n" +
                        "        name=\"tattooId\" value=\"" + tattoos.get(currentTattoo).getTattooId() + "\">\n" + bundle.getString(TATTOOS_INFO) +
                        "</button>\n" +
                        "</form>\n" +
                        "</div>\n" +
                        "<img src=\"/images/" + tattoos.get(currentTattoo).getImage().getName() + ".jpg\"\n" +
                        "        alt=\"Image\" class=\"img-fluid\">\n" +
                        "</div>\n" +
                        "</div>\n");
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Error while writing to out stream");
            }
            currentTattoo++;
        }
        return SKIP_BODY;
    }
}
