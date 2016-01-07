package com.taobao.diamond.utils;

import java.io.File;

public class DrmDynamicLogManager {
    public final static String USER_HOME = "user.home";
    public final static String LOG_HOME = "logs" + File.separator + "sofa";
    public final static String DAY_DATE_PATTERN = "'.'yyyy-MM-dd";
    public final static String LAYOUT_PATTERN = "%d [%t] - %m%n";
    public final static String DAILY_APPENDER_NAME = "_DAILY_APPENDER_NAME";
    public final static String CONVERSION_PATTERN = "%d [%X{loginUserEmail}/%X{remoteAddr}/%X{clientId} - %X{requestURIWithQueryString}] %-5p %c{2} - %m%n";
    
    public static String getLogFilePath() {
        String userHome = System.getProperty(USER_HOME);
        if(!userHome.endsWith(File.separator)) {
            userHome += File.separator;
        }
        String path = userHome + LOG_HOME + File.separator;
        
        return path;
    }
}
