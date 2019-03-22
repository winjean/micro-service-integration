package com.winjean.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;


public class IPUtils {

    private static Logger logger = LoggerFactory.getLogger(IPUtils.class);
    private static final String USE_CDN = "useCDN";

    /**
     * 获取请求的ip地址
     *
     * @param request
     * @param userCdn 是否使用cdn
     * @return
     */
    private static String getIp(HttpServletRequest request, boolean userCdn) {
        //1: get ip address from http_cip
        String ipAddress = request.getHeader("HTTP_CIP");
        if (verifyIp(ipAddress)) {

            logger.warn("return from HTTP_CIP " + ipAddress);

            return ipAddress;
        }
        //2: get ip address from x-forwarded-for
        ipAddress = request.getHeader("x-forwarded-for");
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            String[] ipAddressArray = ipAddress.split(",");
            if (ipAddressArray.length > 0) {
                if (userCdn) {
                    ipAddress = ipAddressArray[0];
                } else {
                    ipAddress = ipAddressArray[ipAddressArray.length - 1].trim();
                }
            }
        }
        if (verifyIp(ipAddress)) {
            return ipAddress;
        }

        //3: get ip address from REMOTE_ADDR
        ipAddress = request.getHeader("REMOTE_ADDR");

        if (verifyIp(ipAddress)) {
            return ipAddress;
        }

        //4: get ip address from X-Real-IP
        ipAddress = request.getHeader("X-Real-IP");
        if (verifyIp(ipAddress)) {
            return ipAddress;
        }

        ipAddress = request.getRemoteAddr();
        if (ipAddress.equals("127.0.0.1")) {
            //根据网卡取本机配置的IP
            InetAddress inet = null;
            try {
                inet = InetAddress.getLocalHost();
                ipAddress = inet.getHostAddress();
            } catch (UnknownHostException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return ipAddress;
    }

    /**
     * 适用于 scs或者nginx 前端为cdn等可信任的代理时
     *
     * @param request
     * @return
     */
    public static String getHttpClientIp(HttpServletRequest request) {
        return getIp(request, true);
    }

    /**
     * 适用于客户端直接访问 scs或者nginx，即前面不存在cdn等反向代理
     *
     * @return
     */
    public static String getTcpClientIp(HttpServletRequest request) {
        return getIp(request, false);
    }

    private final static Pattern ipPattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");

    public static boolean verifyIp(String ip) {
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip))
            return false;

        return ipPattern.matcher(ip).matches();
    }

    public static long ipToLong(String ip) {
        long[] array = new long[4];
        int position1 = ip.indexOf(".");
        int position2 = ip.indexOf(".", position1 + 1);
        int position3 = ip.indexOf(".", position2 + 1);
        array[0] = Long.parseLong(ip.substring(0, position1));
        array[1] = Long.parseLong(ip.substring(position1 + 1, position2));
        array[2] = Long.parseLong(ip.substring(position2 + 1, position3));
        array[3] = Long.parseLong(ip.substring(position3 + 1));
        return (array[0] << 24) + (array[1] << 16) + (array[2] << 8) + array[3];
    }

    public static String ipToString(long ip) {
        StringBuffer sb = new StringBuffer("");
        sb.append(String.valueOf((ip >>> 24)));
        sb.append(".");
        sb.append(String.valueOf((ip & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((ip & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf((ip & 0x000000FF)));
        return sb.toString();
    }
}
