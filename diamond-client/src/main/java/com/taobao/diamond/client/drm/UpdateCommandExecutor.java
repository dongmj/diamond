package com.taobao.diamond.client.drm;

import static com.taobao.diamond.client.drm.ConfregDataBuilder.buildDataId;
import static com.taobao.diamond.client.drm.DistributedResourceConstant.DRM_SUFFIX;

import org.apache.commons.lang.StringUtils;

import com.taobao.diamond.utils.DrmClientLogger;

public class UpdateCommandExecutor {
    final static String LOCAL_STRATEGY = "L";
    final static String REMOTE_STRATEGY = "R";
    final static String LOCAL_SUFFIX = ":" + LOCAL_STRATEGY + DRM_SUFFIX;
    final static String REMOTE_SUFFIX = ":" + REMOTE_STRATEGY + DRM_SUFFIX;
    
    public static void execute(DistributedResourceAttribute attribute, String originalCommand) throws Exception {
        String value = null;
        if(StringUtils.endsWith(originalCommand, LOCAL_SUFFIX)) {
            value = StringUtils.substring(originalCommand, 0, originalCommand.length() - LOCAL_SUFFIX.length());
        } else if(StringUtils.endsWith(originalCommand, REMOTE_SUFFIX)) {
            String room = StringUtils.substring(originalCommand, 0, originalCommand.length() - REMOTE_SUFFIX.length());
            String remoteValue = RemoteResourceDataLoader.loadValue(buildDataId(attribute), room);
            DrmClientLogger.log_monitor.info("��Զ�̶�ȡdrm����: " + remoteValue);
            if(StringUtils.endsWith(remoteValue, DRM_SUFFIX)) {
                value = StringUtils.substring(remoteValue, 0, remoteValue.length() - DRM_SUFFIX.length());
            } else {
                DrmClientLogger.log_monitor.warn("��Զ�̶�ȡ��drm���ݲ�����Լ����������");
                return;
            }
        } else {
            DrmClientLogger.log_monitor.warn("����ָ����󣬲�����");
        }
        
        ResourceAttributeUpdater.updateResource(attribute, value);
    }
}
