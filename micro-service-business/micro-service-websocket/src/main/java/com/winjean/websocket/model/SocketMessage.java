package com.winjean.websocket.model;

import lombok.Data;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/15 10:50
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Data
public class SocketMessage {

    private String message;

    private String data;
}
