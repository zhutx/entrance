package com.xier.sesame.attence.config;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.util.HtmlUtils;

import com.xier.sesame.common.utils.StringUtil;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	
	HttpServletRequest originalRequest = null;

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		originalRequest = request;
	}
	
	@Override
	public String getParameter(String key) {
		String value = super.getParameter(key);
		if (value != null) {
			value = filter(value);
		}
		return value;
	}
	
	@Override
	public Map<String,String[]> getParameterMap() {
		Map<String,String[]> map = new HashMap<String, String[]>();
		Map<String,String[]> parameters = super.getParameterMap();
		for (String key : parameters.keySet()) { 
            String[] values = parameters.get(key); 
            for (int i = 0; i < values.length; i++) {  
        	    values[i] = filter(values[i]); 
            }  
            map.put(key, values); 
        }
		return map;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if(values==null) return values;
		for (int i = 0; i < values.length; i++) {  
    	    values[i] = filter(values[i]); 
        }
		return values;
	}
	
	// ------------------------------helper methods ----------------------------
	private String filter(String str){
		str = str.trim();
		str = HtmlUtils.htmlEscape(str);
		return str;
	}

	/**
	 * 转换为unicode  
	 */
	public static void encodeUnicode(final String gbString) {     
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]); 
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        System.out.println(unicodeBytes);
    }     
	
	private String removeInvalidWords(String str){
		Pattern pattern = Pattern.compile("([<>'\"]*)");
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "");
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	public HttpServletRequest getOriginalRequest() {
		return originalRequest;
	}

	public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
		if (req instanceof XssHttpServletRequestWrapper) {
			return ((XssHttpServletRequestWrapper) req).getOriginalRequest();
		}
		return req;
	}
	
}
