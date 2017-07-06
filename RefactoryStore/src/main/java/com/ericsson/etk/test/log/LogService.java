package com.ericsson.etk.test.log;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;


/**
 * Created by eadrdam on 05.07.17..
 */
public class LogService {

    private Map<String,Logger> logMap;

    public LogService() {
        this.logMap = new HashMap<>();
    }

    private Logger getLoggerForName(String logName) {
        if(!this.logMap.containsKey(logName)){
            logMap.put(logName,Logger.getLogger(logName));
        }
        return  logMap.get(logName);
    }

    public void debug(String logName, String message){
        getLoggerForName(logName).debug("[" +logName+"]    " + message);
    }
    public void info(String logName, String message){
        getLoggerForName(logName).info("[" +logName+"]    " + message);
    }
    public void warn(String logName, String message){
        getLoggerForName(logName).warn("[" +logName+"]    " + message);
    }
    public void warn(String logName, String message, Throwable t){
        getLoggerForName(logName).warn("[" +logName+"]    " + message,t);
    }
    public void error(String logName, String message){
        getLoggerForName(logName).error("[" +logName+"]    " + message);
    }
    public void error(String logName, String message, Throwable t){
        getLoggerForName(logName).error("[" +logName+"]    " + message,t);
    }
}
