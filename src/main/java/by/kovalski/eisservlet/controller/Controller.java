package by.kovalski.eisservlet.controller;

import java.io.*;

import by.kovalski.eisservlet.command.CommandType;
import by.kovalski.eisservlet.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.kovalski.eisservlet.util.RequestAndSessionData.*;

@WebServlet(name = "helloServlet", urlPatterns = "/controller")
public class Controller extends HttpServlet {

  private static final Logger logger = LogManager.getLogger();

  @Override
  public void init() {
    logger.info("Servlet created ff" + this.getServletName());
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/html");
    String commandStr = request.getParameter(COMMAND);
    String page = null;
    try {
      page = CommandType.defineCommand(commandStr).execute(request);
      request.getRequestDispatcher(page).forward(request, response);
      //response.sendRedirect(request.getContextPath() + "/" + page);
    } catch (CommandException e) {
      request.setAttribute("error_msg", e.getMessage());
      throw new ServletException(e);
    }
  }

  @Override
  public void destroy() {
    logger.info("Servlet destroyed " + this.getServletName());
  }
}