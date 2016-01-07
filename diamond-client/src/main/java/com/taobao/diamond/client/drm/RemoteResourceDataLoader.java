package com.taobao.diamond.client.drm;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.taobao.diamond.utils.HttpUtil;

public class RemoteResourceDataLoader {
    private static String drmDataUrl;
    private static String fullUrl;
    
    public static String loadValue(String dataId, String room) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("dataId", dataId);
        map.put("room", room);
        String reponseString = HttpUtil.query(fullUrl, map);
        return StringUtils.trim(reponseString);
    }
    
    public static String getDrmDataUrl() {
        return drmDataUrl;
    }
    
    public static void setDrmDataUrl(String drmDataUrl) {
        RemoteResourceDataLoader.drmDataUrl = drmDataUrl;
        RemoteResourceDataLoader.fullUrl = drmDataUrl + "/queryData.htm";
    }
}
