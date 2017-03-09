package com.taobao.diamond.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrmClientLogger {
	//import static com.taobao.diamond.utils.DrmDynamicLogManager.CONVERSION_PATTERN;
	//import static com.taobao.diamond.utils.DrmDynamicLogManager.DAILY_APPENDER_NAME;
	//import static com.taobao.diamond.utils.DrmDynamicLogManager.DAY_DATE_PATTERN;
	//import static com.taobao.diamond.utils.DrmDynamicLogManager.LAYOUT_PATTERN;
	//import static com.taobao.diamond.utils.DrmDynamicLogManager.getLogFilePath;	
//    public static final Logger log_boot = Logger.getLogger("SOFA-DRM-BOOT");
//    public static final Logger log_monitor = Logger.getLogger("SOFA-DRM-MONITOR");
//    static {
//        PatternLayout layout = new PatternLayout(LAYOUT_PATTERN);
//        layout.setConversionPattern(CONVERSION_PATTERN);
//        String path = getLogFilePath();
//        File dir = new File(path);
//        if(!dir.exists())
//            dir.mkdirs();
//        String logBootFileName = path + "sofa-drm-boot.log";
//        String logMonitorFileName = path + "sofa-drm-monitor.log";
//        DailyRollingFileAppender appenderBoot = null, appenderMonitor = null;
//        
//        try  {
//            String rollingDatePattern = DAY_DATE_PATTERN;
//            appenderBoot = new DailyRollingFileAppender(layout, logBootFileName, rollingDatePattern);
//            appenderBoot.setAppend(true);
//            appenderBoot.setEncoding("GBK");
//            appenderBoot.setName(DAILY_APPENDER_NAME);
//            
//            appenderMonitor = new DailyRollingFileAppender(layout, logMonitorFileName, rollingDatePattern);
//            appenderMonitor.setAppend(true);
//            appenderMonitor.setEncoding("GBK");
//            appenderMonitor.setName(DAILY_APPENDER_NAME);
//        } catch(IOException e) {
//            throw new RuntimeException(e);
//        }
//        if(appenderBoot != null) {
//            log_boot.addAppender(appenderBoot);
//        }
//        if(appenderMonitor != null) {
//            log_monitor.addAppender(appenderMonitor);
//        }
//        log_boot.setLevel(Level.INFO);
//        log_boot.setAdditivity(false);
//        log_monitor.setLevel(Level.INFO);
//        log_monitor.setAdditivity(false);
//    }
	public static final Logger log_boot = LoggerFactory.getLogger("SOFA-DRM-BOOT");
	public static final Logger log_monitor = LoggerFactory.getLogger("SOFA-DRM-MONITOR");
}
