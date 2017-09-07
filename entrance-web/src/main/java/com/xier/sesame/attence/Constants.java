package com.xier.sesame.attence;

/**
 * 
 * @author libinsong1204@gmail.com
 *
 */
public abstract class Constants {
	
	/**
	 * 系统profile：development、test、production
	 */
	public static final String PROFILE_DEVELOPMENT = "development";
	
	public static final String PROFILE_TEST = "test";
	
	public static final String PROFILE_PRODUCTION = "production";

	public static void main(String[] args){
		String mark = ""+(long)((Math.random()*9+1)*100000000000l);

		System.out.println(mark);
		System.out.println(mark.substring(4,8));
		//String pmark = mark.substring(0,4)+"-"+mark.substring(4,4)+"-"+mark.substring(7,4);
	}
}
