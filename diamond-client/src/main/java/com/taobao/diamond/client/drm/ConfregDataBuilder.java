package com.taobao.diamond.client.drm;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

public class ConfregDataBuilder implements DistributedResourceConstant {
    public static String buildDataId(DistributedResourceAttribute attribute) throws MalformedObjectNameException {
        ObjectName objectName;
        objectName = ObjectNameBuilder.build(attribute);
        
        return objectName.toString() + DRM_SUFFIX;
    }
}
