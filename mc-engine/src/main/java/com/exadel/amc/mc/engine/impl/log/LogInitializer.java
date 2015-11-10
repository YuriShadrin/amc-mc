package com.exadel.amc.mc.engine.impl.log;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.annotation.PostConstruct;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.exadel.amc.mc.engine.EngineConstants;
import com.exadel.amc.mc.engine.Scheduler;

@Component("logInitializer")
public class LogInitializer {

    private static final String MC_INSTANCE_LOGGER_NAME = "mc.instance.logger";

    private static final String CONSOLE_APPENDER = "CONSOLE";
    private static final String INSTANCE_APPENDER = "INSTANCE";

    private static final String DATE_PATTERN = "-yyyy-MM-dd";

    @Value("${" + EngineConstants.MC_INSTANCE_ID + "}")
    private String instanceId;

    @Value("${" + EngineConstants.MC_LOG_DIR + "}")
    private String logDirectory;

    @Value("${" + EngineConstants.MC_LOG_DEBUG + "}")
    private boolean debug;

    public void addLogger(String sourceId, Class<? extends Scheduler>sourceSchedulerClass) {
        Logger ilogger = LogManager.getLogger(MC_INSTANCE_LOGGER_NAME);
        FileAppender fa;
        try {
            Layout layout = makeLayout(sourceId, ilogger.getAppender(INSTANCE_APPENDER).getLayout());
            fa = new DailyRollingFileAppender(
                    layout,
                    logDirectory + File.separator + instanceId + "-" + sourceId + ".log",
                    DATE_PATTERN);
            fa.setName(sourceId + "-file");
            fa.setAppend(true);
            
            Logger slogger = LogManager.getLogger(sourceSchedulerClass.getPackage().getName());
            slogger.addAppender(fa);
            
            ConsoleAppender ca = new ConsoleAppender();
            layout = makeLayout(sourceId, ilogger.getAppender(CONSOLE_APPENDER).getLayout());
            ca.setWriter(new OutputStreamWriter(System.out));
            ca.setLayout(layout);
            ca.setName(sourceId + "-console"); 
            
            slogger.addAppender(ca);
            if (debug) {
                slogger.setLevel(Level.DEBUG);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Layout makeLayout(String sourceId, Layout layout) {
        if (layout instanceof PatternLayout) {
            PatternLayout pl = (PatternLayout)layout;
            String cp = pl.getConversionPattern();
            cp = cp.replaceAll("#s", sourceId.toLowerCase()).replaceAll("#S", sourceId.toUpperCase());
            pl = new PatternLayout();
            pl.setConversionPattern(cp);
            layout = pl;
        }
        return layout;
    }
    
    @PostConstruct
    public void postConstruct() {
        addLogger("engine", Scheduler.class);
    }
}
