package by.kovalski.eisservlet.controller.listener;

import by.kovalski.eisservlet.dbconnection.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ConnectionPool.getInstance();
    logger.info("Context initialized " + sce.getServletContext().getServerInfo());
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    try {
      ConnectionPool.deregisterDrivers();
      ConnectionPool.getInstance().destroyPool();
    } finally {
      logger.info("Context destroyed " + sce.getServletContext().getServerInfo());
    }
  }
}
