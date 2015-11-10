package com.exadel.amc.mc.engine.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.PropertySource;

import com.exadel.amc.mc.engine.Engine;
import com.exadel.amc.mc.engine.EngineConstants;
import com.exadel.amc.mc.engine.Scheduler;
import com.exadel.amc.mc.engine.SchedulerState;
import com.exadel.amc.mc.engine.conf.InstanceConfiguration;
import com.exadel.amc.mc.engine.conf.Source;
import com.exadel.amc.mc.engine.exception.EngineException;
import com.exadel.amc.mc.engine.exception.InitializationException;
import com.exadel.amc.mc.engine.impl.log.LogInitializer;

public class EngineInstance implements Engine {

    @Autowired
    private InstanceConfiguration config;

    @Autowired
    private LogInitializer logInitializer;

    private List<Scheduler>schedulers = new ArrayList<>();;
    private Logger log = LoggerFactory.getLogger(EngineInstance.class);

    @Override
    public void init(String instanceId) throws InitializationException {

        File instanceDir = getInstanceDir(instanceId);
        AbstractApplicationContext appContext = loadApplicationContext(instanceId, instanceDir.getAbsolutePath());

        log.info("Initializing instance '{}'...", instanceId);

        config.init(instanceId);
        schedulers = initSchedulers(appContext, config.getSources());

        log.info("Instance '{}' has been initialized.", instanceId);
    }

    @Override
    public void start() throws EngineException {
        for (Scheduler scheduler  : schedulers) {
            if (scheduler.getStatus().getSchedulerState() == SchedulerState.STOPPED) {
                scheduler.start();
            }
        }
    }

    @Override
    public void stop() throws EngineException {
        for (Scheduler scheduler  : schedulers) {
            scheduler.stop();
        }
    }

    @Override
    public void suspend() throws EngineException {
        for (Scheduler scheduler  : schedulers) {
            if (scheduler.getStatus().getSchedulerState() == SchedulerState.RUNNING) {
                scheduler.suspend();
            }
        }
    }

    @Override
    public void resume() throws EngineException {
        for (Scheduler scheduler  : schedulers) {
            if (scheduler.getStatus().getSchedulerState() == SchedulerState.SUSPENDED) {
                scheduler.resume();
            }
        }
    }

    private File getInstanceDir(String instanceId) throws InitializationException {
        String dir = System.getProperty(EngineConstants.MC_ROOT_DIR);
        if (dir == null) {
            throw new InitializationException("System property " + EngineConstants.MC_ROOT_DIR + " is not set, can not instantiate collector!");
        }
        File rootDir = new File(dir);
        if (!rootDir.exists() || !rootDir.isDirectory() ) {
            throw new InitializationException("Root directory " + rootDir.getAbsolutePath() + " does not exist.");
        }
        File instanceDir = new File(rootDir, instanceId);
        if (!instanceDir.exists() || !instanceDir.isDirectory() ) {
            throw new InitializationException("Instance directory " + instanceDir.getAbsolutePath() + " does not exist.");
        }
        return instanceDir;
    }

    private class CustomPropertySource extends PropertySource<String> {
        String instanceId, instanceDir;
        public CustomPropertySource(String instanceId, String instanceDir) {
            super("custom");
            this.instanceId = instanceId;
            this.instanceDir = instanceDir;
        }
        @Override
        public String getProperty(String name) {
            if (name.equals(EngineConstants.MC_INSTANCE_ID)) {
                return instanceId;
            }
            if (name.equals(EngineConstants.MC_INSTANCE_DIR)) {
                return instanceDir;
            }
            return null;
        }
    }

    private AbstractApplicationContext loadApplicationContext(String instanceId, String instanceDir) {
        AbstractApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] {"engine-context.xml"}, false);
        appContext.getEnvironment().getPropertySources().addLast(new CustomPropertySource(instanceId, instanceDir));
        appContext.refresh();
        appContext.getAutowireCapableBeanFactory().autowireBean(this);
        return appContext;
    }

    private List<Scheduler> initSchedulers(AbstractApplicationContext appContext, List<Source>sources) throws InitializationException {
        List<Scheduler>schedulers = new ArrayList<>();
        for (Source source : sources) {
            if (source.isDisabled()) {
                log.debug("Source '{}' ({}) disabled.", source.getSourceName(), source.getSourceId());
            } else {
                log.debug("Initializing scheduler '{}' ({})...", source.getSourceName(), source.getSourceId());
                Scheduler scheduler = appContext.getBean(source.getSourceId(), Scheduler.class);
                logInitializer.addLogger(source.getSourceId(), scheduler.getClass());
                scheduler.init(source);
                schedulers.add(scheduler);
            }
        } 
        return schedulers;
    }

    public static void main(String[] args) throws Exception {
        EngineInstance ei = new EngineInstance();
        ei.init("instance-id");
        ei.start();

//        System.err.println("0");
//        
//        Thread.sleep(3000);
//        ei.suspend();
//
//        System.err.println("1");
//        
//        Thread.sleep(10000);
//        ei.resume();
//
//        System.err.println("2");
//        
//        Thread.sleep(5000);
//        ei.stop();
//        
//        
//        System.err.println("3");
        
    }

}
