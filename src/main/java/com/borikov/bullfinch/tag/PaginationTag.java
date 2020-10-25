package com.borikov.bullfinch.tag;

import com.borikov.bullfinch.controller.RequestParameter;
import com.borikov.bullfinch.entity.Tattoo;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

public class PaginationTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        List<Tattoo> tattoos = (List<Tattoo>) pageContext.getRequest().getAttribute(RequestParameter.TATTOOS);
        System.out.println(tattoos);
        return SKIP_BODY;
    }
}
