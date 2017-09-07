package com.xier.sesame.attence.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 有关url工具类
 * @author xiongchao
 *
 */
public class UrlUtil {
	
	
	/**
	 * 根据request返回该request对应的url
	 * @param request
	 * @return
	 */
    public static String buildFullRequestUrl(HttpServletRequest request) {
        return buildFullRequestUrl(request.getScheme(), request.getServerName(), request.getServerPort(), request.getRequestURI(),
        		request.getQueryString());
    }
    
    

    /**
     * 根据参数返回url
     * @param scheme 协议
     * @param serverName 服务地址
     * @param serverPort 服务端口
     * @param requestURI 请求路径
     * @param queryString 请求参数
     * @return
     */
    public static String buildFullRequestUrl(String scheme, String serverName, int serverPort, String requestURI,
            String queryString) {

        scheme = scheme.toLowerCase();

        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        if ("http".equals(scheme)) {
            if (serverPort != 80) {
                url.append(":").append(serverPort);
            }
        } else if ("https".equals(scheme)) {
            if (serverPort != 443) {
                url.append(":").append(serverPort);
            }
        }

        url.append(requestURI);

        if (queryString != null) {
            url.append("?").append(queryString);
        }

        return url.toString();
    }
}
