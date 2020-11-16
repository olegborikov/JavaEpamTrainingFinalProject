package com.borikov.bullfinch.tag;

import com.borikov.bullfinch.controller.RequestAttribute;
import com.borikov.bullfinch.controller.SessionAttribute;
import com.borikov.bullfinch.model.entity.Order;
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
 * The {@code PaginationOrdersAdminTag} class represents admin pagination tag for orders.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class PaginationOrdersAdminTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String BUNDLE_PATH = "i18n.application_message";
    private static final String ORDER_IS_CONFIRMED = "ordersAdmin.confirmed";
    private static final String ORDER_IS_NOT_CONFIRMED = "ordersAdmin.notConfirmed";
    private static final String SPLIT_SYMBOL = "_";
    private int pageNumber;
    private int ordersAmountOnPage;

    /**
     * Sets page number.
     *
     * @param pageNumber the page number
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Sets orders amount on page.
     *
     * @param ordersAmountOnPage the orders amount on page
     */
    public void setOrdersAmountOnPage(int ordersAmountOnPage) {
        this.ordersAmountOnPage = ordersAmountOnPage;
    }

    @Override
    public int doStartTag() throws JspException {
        ServletRequest request = pageContext.getRequest();
        List<Order> orders = (List<Order>) request.getAttribute(RequestAttribute.ORDERS);
        int currentIndex = pageNumber * ordersAmountOnPage - ordersAmountOnPage;
        int lastIndex = pageNumber * ordersAmountOnPage - 1;
        HttpSession session = pageContext.getSession();
        String localeName = (String) session.getAttribute(SessionAttribute.CURRENT_LOCALE);
        String language = localeName.split(SPLIT_SYMBOL)[0];
        String country = localeName.split(SPLIT_SYMBOL)[1];
        Locale locale = new Locale(language, country);
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PATH, locale);
        while (currentIndex < orders.size() && currentIndex <= lastIndex) {
            String confirmedMessage;
            if (orders.get(currentIndex).isConfirmed()) {
                confirmedMessage = bundle.getString(ORDER_IS_CONFIRMED);
            } else {
                confirmedMessage = bundle.getString(ORDER_IS_NOT_CONFIRMED);
            }
            try {
                pageContext.getOut().write(" <div class=\"col-md-12\">\n"
                        + "<form method=\"post\" action=\"/bullfinch\">\n"
                        + " <input type=\"hidden\" name=\"commandName\"\n"
                        + " value=\"browse_order_admin_page_command\">\n"
                        + " <button style=\"background-color: black; text-align: left\"\n"
                        + "class=\"form-control text-white\"\n"
                        + "name=\"orderId\"\n"
                        + "value=\"" + orders.get(currentIndex).getOrderId() + "\">\n"
                        + confirmedMessage + ", "
                        + orders.get(currentIndex).getTattoo().getName() + ", "
                        + orders.get(currentIndex).getPrice() + " "
                        + orders.get(currentIndex).getDate()
                        + " </button>\n"
                        + "</form>\n"
                        + " </div>");
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, "Error while writing to out stream", e);
                throw new JspException("Error while writing to out stream", e);
            }
            currentIndex++;
        }
        return SKIP_BODY;
    }
}
