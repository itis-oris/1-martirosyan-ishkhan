package ru.itis.oris.semestrwork.controller.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.itis.oris.semestrwork.repository.DBWork;

@WebListener
public class ContextListener implements ServletContextListener {

    final static Logger logger = LogManager.getLogger(ContextListener.class);

    public void contextInitialized(ServletContextEvent sce) {
        logger.info("contextInitialized");
        DBWork.getInstance();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        DBWork.getInstance().destroy();
    }
}