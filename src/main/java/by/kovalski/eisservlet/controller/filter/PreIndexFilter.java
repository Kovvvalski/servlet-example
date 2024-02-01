package by.kovalski.eisservlet.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter(filterName = "PreIndexFilter", value = "/index.jsp")
public class PreIndexFilter implements Filter {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpSession session = httpServletRequest.getSession(false);
    logger.info("Session in PreIndexFilter:" + (session != null ? session.getId() : "session not created"));
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }

}
