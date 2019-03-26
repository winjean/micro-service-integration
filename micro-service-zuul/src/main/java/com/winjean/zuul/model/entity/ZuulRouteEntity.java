package com.winjean.zuul.model.entity;

import com.winjean.common.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/26 9:21
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Data
@Entity(name = "t_zuul_route")
public class ZuulRouteEntity extends BaseEntity {
    /**
     * The ID of the route (the same as its map key by default).
     */
//    private String id;

    /**
     * The path (pattern) for the route, e.g. /foo/**.
     */
    private String path;

    /**
     * The service ID (if any) to map to this route. You can specify a physical URL or
     * a service, but not both.
     */
    private String serviceId;

    /**
     * A full physical URL to map to the route. An alternative is to use a service ID
     * and service discovery to find the physical address.
     */
    private String url;

    /**
     * Flag to determine whether the prefix for this route (the path, minus pattern
     * patcher) should be stripped before forwarding.
     */
    private boolean stripPrefix = true;

    /**
     * Flag to indicate that this route should be retryable (if supported). Generally
     * retry requires a service ID and ribbon.
     */
    private Boolean retryable;

    /**
     * List of sensitive headers that are not passed to downstream requests. Defaults
     * to a "safe" set of headers that commonly contain user credentials. It's OK to
     * remove those from the list if the downstream service is part of the same system
     * as the proxy, so they are sharing authentication data. If using a physical URL
     * outside your own domain, then generally it would be a bad idea to leak user
     * credentials.
     */
    @Transient
    private Set<String> sensitiveHeaders = new LinkedHashSet<>();

    @Transient
    private boolean customSensitiveHeaders = false;

}
