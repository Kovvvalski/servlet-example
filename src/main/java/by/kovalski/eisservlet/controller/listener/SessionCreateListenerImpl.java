package by.kovalski.eisservlet.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionCreateListenerImpl implements HttpSessionListener {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public void sessionCreated(HttpSessionEvent se) {
    logger.info("Session " + se.getSession().getId()+ " created");
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    logger.info("Session " + se.getSession().getId()+ " destroyed");
  }
}
