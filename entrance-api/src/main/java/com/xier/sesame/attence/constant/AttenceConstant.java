package com.xier.sesame.attence.constant;

public class AttenceConstant {

	/**
	 * 考勤记录同步状态，表：attence_recognize_record 字段：sync_status
	 */
	public static class RecordSyncStatus {
		public static final int INIT = 1;// 默认，待同步
		public static final int OK = 100;// 同步成功
		public static final int QUIT = -100;// 放弃同步，重试失败超过最大值
	}
	
	/**
	 * 机构授权信息表状态，表：attence_auth 字段：status
	 */
	public static class AuthStatus {
		public static final int USABLE = 1;// 启用
		public static final int DISABLED = -1;// 停用
	}
	
}
