package com.taobao.diamond.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

public class HttpUtil {
    public static String query(String url) {
        return query(url, null);
    }
    
    public static String query(String url, Map<String, String> params) {
        StringBuffer sb = new StringBuffer(url);
        if(params != null) {
            sb.append("?");
            for(Entry<String, String> param : params.entrySet()) {
                sb.append(param.getKey());
                sb.append("=");
                sb.append(param.getValue());
                sb.append("&");
            }
            if(sb.charAt(sb.length() - 1) == '&')
                sb.deleteCharAt(sb.length() - 1);
        }
        
        return getContent(sb.toString().replaceAll(" ", "%20"));
    }
    
    private static String getContent(String urlString) {
        if(!StringUtils.startsWithIgnoreCase(urlString, "http://"))
            return null;
        
        StringBuffer sb = new StringBuffer();
        HttpURLConnection httpConnection = null;
        URL url;
        int code = -1;
        try {
            url = new URL(urlString);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);
            code = httpConnection.getResponseCode();
        } catch(IOException e) {
            DrmClientLogger.log_monitor.error("ÇëÇó" + urlString + "Ê§°Ü", e);
        }
        
        if(code == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = null;
            try  {
                String aLine;
                reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                while((aLine = reader.readLine()) != null) {
                    sb.append(aLine).append("\n");
                }
            } catch(IOException e) {
                DrmClientLogger.log_monitor.error("¶ÁÈ¡Á÷Ê§°Ü", e);
                return null;
            } finally {
                if(reader != null) {
                    try {
                        reader.close();
                    } catch(IOException e) {
                        DrmClientLogger.log_monitor.error("¹Ø±ÕÁ÷Ê§°Ü", e);
                    }
                }
            }
        }
        
        return sb.toString();
    }
}
