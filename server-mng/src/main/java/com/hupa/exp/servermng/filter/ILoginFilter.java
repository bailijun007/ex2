package com.hupa.exp.servermng.filter;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Filter;

public interface ILoginFilter extends Filter {

    boolean existLogin(HttpServletRequest httpServletRequest);
}
