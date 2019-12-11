package com.tuniu.ord.core.Logging;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;

/**
 * 日志工厂用于创建集合日志实例，并初始化。
 * 
 * @author fangzhongwei
 * 
 */
public class LogFactory {

    private static final Map<Class<?>, Log4jLogger> manageLoggers = new HashMap<Class<?>, Log4jLogger>();

    private static boolean switchToFileAppender = false;

    private static final String LOG_DEBUG_LEVEL = "DEBUG";
    private static final String LOG_INFO_LEVEL = "INFO";
    private static final String LOG_WARN_LEVEL = "WARN";
    private static final String LOG_ERROR_LEVEL = "ERROR";

    private static final int MAX_BACKUP_INDEX = 5;
    private static final String MAX_FILE_SIZE = "10MB";
    private static final String SYSTEM_LOG_APPENDER_NAME = "sysLogAppender";
    private static final String ACCESS_LOG_APPENDER_NAME = "accLogAppender";
    private static RollingFileAppender sysRollingFileAppender;
    private static RollingFileAppender accRollingFileAppender;

    private static Appender consoleAppender;

    private static String level;
    private static String logFilePath;
    private static String logConfigPath;

    public static Log4jLogger getLogger(Class<?> category) {
        return getLogger(category, Locale.getDefault());
    }

    public static Log4jLogger getLogger(Class<?> category, Locale locale) {
        synchronized (manageLoggers) {
            Log4jLogger logger = new Log4jLogger(category, locale);
            if (switchToFileAppender) {
                setFileAppender(logger);
            } else {
                setConsoleAppender(logger);
            }
            if (!manageLoggers.containsKey(category)) {
                manageLoggers.put(category, logger);
            }

            return logger;
        }
    }

    public static void activateFileAppender(String levelParam, String logFilePathParam, String logConfigPathParam) {
        synchronized (manageLoggers) {
            LogFactory.level = levelParam;
            LogFactory.logFilePath = logFilePathParam;
            LogFactory.logConfigPath = logConfigPathParam;

            try {
                initAppenders();

                Iterator<Class<?>> iterator = manageLoggers.keySet().iterator();
                while (iterator.hasNext()) {
                    Class<?> category = iterator.next();
                    setFileAppender(manageLoggers.get(category));
                }
                switchToFileAppender = true;
            } catch (IOException e) {
                System.err.print("Initialize MiaoMu log failed!");
            }
        }
    }

    private static void setConsoleAppender(Log4jLogger logger) {
        if (null == consoleAppender) {
            consoleAppender = new ConsoleAppender(getLayout());
            consoleAppender.setName("MiaoMu Console Log");
        }

        Level currentLevel = determineLogLevel();

        logger.systemLog.removeAllAppenders();
        logger.systemLog.addAppender(new ConsoleAppender(getLayout()));

        logger.accessLog.removeAllAppenders();
        logger.accessLog.addAppender(new ConsoleAppender(getLayout()));

        setLogLevel(logger, currentLevel);
    }

    private static void setFileAppender(Log4jLogger logger) {
        Level currentLevel = determineLogLevel();

        changeToFileAppenderIfNew(logger.systemLog, SYSTEM_LOG_APPENDER_NAME, sysRollingFileAppender);

        changeToFileAppenderIfNew(logger.accessLog, ACCESS_LOG_APPENDER_NAME, accRollingFileAppender);

        setLogLevel(logger, currentLevel);
    }

    private static void changeToFileAppenderIfNew(Logger logger, String appenderName, RollingFileAppender rollingFileAppender) {
        Appender existingAppender = logger.getAppender(appenderName);
        if (null == existingAppender) {
            logger.removeAppender(consoleAppender);
            logger.addAppender(rollingFileAppender);
        } else if (existingAppender != rollingFileAppender) {
            logger.removeAppender(existingAppender);
            logger.addAppender(rollingFileAppender);
        }
    }

    private static void initAppenders() throws IOException {
        sysRollingFileAppender = new RollingFileAppender(getLayout(), LogFactory.logFilePath + File.separatorChar
                + "system.log");
        sysRollingFileAppender.setMaxBackupIndex(MAX_BACKUP_INDEX);
        sysRollingFileAppender.setMaxFileSize(MAX_FILE_SIZE);
        sysRollingFileAppender.setName(SYSTEM_LOG_APPENDER_NAME);

        accRollingFileAppender = new RollingFileAppender(getLayout(), LogFactory.logFilePath + File.separatorChar
                + "access.log");
        accRollingFileAppender.setMaxBackupIndex(MAX_BACKUP_INDEX);
        accRollingFileAppender.setMaxFileSize(MAX_FILE_SIZE);
        accRollingFileAppender.setName(ACCESS_LOG_APPENDER_NAME);
    }

    private static Level determineLogLevel() {
        Level currentLevel = Level.DEBUG;
        if (LOG_DEBUG_LEVEL.equals(level)) {
            currentLevel = Level.DEBUG;
        } else if (LOG_INFO_LEVEL.equals(level)) {
            currentLevel = Level.INFO;
        } else if (LOG_WARN_LEVEL.equals(level)) {
            currentLevel = Level.WARN;
        } else if (LOG_ERROR_LEVEL.equals(level)) {
            currentLevel = Level.ERROR;
        }
        return currentLevel;
    }

    private static void setLogLevel(Log4jLogger logger, Level currentLevel) {
        if (null != LogFactory.logConfigPath && (new File(LogFactory.logConfigPath).exists())) {
            PropertyConfigurator.configureAndWatch(LogFactory.logConfigPath, 60000);
        } else {
            logger.systemLog.setLevel(currentLevel);
            logger.accessLog.setLevel(currentLevel);
        }
        return;
    }

    private static Layout getLayout() {
        return new PatternLayout("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{32} - %msg%n");
    }

}
