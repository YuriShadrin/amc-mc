package com.exadel.amc.mc.war;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exadel.amc.mc.engine.Engine;
import com.exadel.amc.mc.engine.impl.EngineInstance;

public class EngineLauncher implements ServletContextListener {

    public static final String MCENGINE = "MC$ENGINE";
    public static final String MCENGINEID = "MC$ENGINE$ID";
    
    private Engine engine;
    private Logger log = LoggerFactory.getLogger(EngineLauncher.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String engineId = getEngineId(sce.getServletContext());
        Thread.currentThread().setName(engineId + "-engine-thread");
        engine = new EngineInstance();
        try {
            log.info("Starting '{}' engine...", engineId);
            engine.init(engineId);
            engine.start();
            sce.getServletContext().setAttribute(MCENGINE, engine);
            sce.getServletContext().setAttribute(MCENGINEID, engineId);
            log.info("Engine '{}' has been started successfully.", engineId);
        } catch (Exception e) {
            log.error("Error occurred diring engine '" + engineId + "' initialization.", e);
            throw new RuntimeException("Error occurred diring engine '" + engineId + "' initialization.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        String engineId = getEngineId(sce.getServletContext());
        try {
            log.info("Stopping '{}' engine...", engineId);
            sce.getServletContext().removeAttribute(MCENGINE);
            sce.getServletContext().removeAttribute(MCENGINEID);
            engine.stop();
            log.info("Engine '{}' has been stopped successfully.", engineId);
        } catch (Exception e) {
            log.error("Error occurred diring engine '" + engineId + "' stopping.", e);
            throw new RuntimeException("Error occurred diring engine '" + engineId + "' stopping.", e);
        }
    }

    private String getEngineId(ServletContext sc) {
        String engineId = sc.getContextPath();
        return engineId.replaceAll("/", "");
    }

}
