package com.winjean.zuul.model.response;

import lombok.Data;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/26 9:51
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Data
public class ResponseZuulRouteQuery {

    private String id;

    private String path;

    private String serviceId;

    private String url;

    private boolean stripPrefix;

    private Boolean retryable;

}
