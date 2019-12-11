package com.tuniu.ord.core.Logging;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tuniu.ord.core.init.SystemInitParameter;

/**
 * 日志初始化类，在web容器启动时设置日志输出到文件中和日志级别
 * 
 * @author fangzhongwei
 * 
 */
public class LogInitListener implements ServletContextListener {

    private String level;
    private String logFilePath;
    private String logConfigPath;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        level = SystemInitParameter.getLevel();
        logFilePath = SystemInitParameter.getLogFilePath();
        logConfigPath = SystemInitParameter.getLogConfigPath();
        LogFactory.activateFileAppender(level, logFilePath, logConfigPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        level = null;
        logFilePath = null;
        logConfigPath = null;
    }

}
