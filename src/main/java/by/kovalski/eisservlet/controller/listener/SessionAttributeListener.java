package by.kovalski.eisservlet.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public void attributeAdded(HttpSessionBindingEvent event) {
    logger.info("Session attribute added " + event.getSession().getAttribute("user_name"));
    logger.info("Session attribute added " + event.getSession().getAttribute("current_page"));
  }

  @Override
  public void attributeRemoved(HttpSessionBindingEvent event) {
    HttpSessionAttributeListener.super.attributeRemoved(event);
  }

  @Override
  public void attributeReplaced(HttpSessionBindingEvent event) {
    logger.info("Session attribute replaced " + event.getSession().getAttribute("user_name"));
    logger.info("Session attribute replaced " + event.getSession().getAttribute("current_page"));
  }
}
