package com.winjean.zuul.model.request;

import com.winjean.common.BaseEntity;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/26 9:50
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Data
public class RequestZuulRouteInsert extends BaseEntity {

    private String path;

    private String serviceId;

    private String url;

    private boolean stripPrefix;

    private Boolean retryable;

    private Set<String> sensitiveHeaders = new LinkedHashSet<>();

    private boolean customSensitiveHeaders;

}
