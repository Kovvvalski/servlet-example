package by.kovalski.eisservlet.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter(urlPatterns = {"/pages/main.jsp"}, initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class PageRedirectSecurityFilter implements Filter {
  private String indexPath;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    indexPath = filterConfig.getInitParameter("INDEX_PATH");
  }


  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + indexPath);
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }

}
