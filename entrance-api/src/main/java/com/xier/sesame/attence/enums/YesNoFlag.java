package com.xier.sesame.attence.enums;

public enum YesNoFlag {
	
	YES("是",1),
	
	NO("否",0);
	
	private String desc;
	
	private int value;

	YesNoFlag(String desc ,int value){
		this.value = value;
		this.desc =desc;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public static String fromValue(int value) {
		YesNoFlag entry = getEntry(value);
		if(entry == null) return null;
		return entry.getDesc();
	}
	
	public static int fromDesc(String desc) {
		YesNoFlag entry = getEntry(desc);
		if(entry == null) return 0;
		return entry.getValue();
	}

	private static YesNoFlag getEntry(int value) {
		for (YesNoFlag type : YesNoFlag.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
	
	private static YesNoFlag getEntry(String desc) {
		for (YesNoFlag type : YesNoFlag.values()) {
			if (type.getDesc().equals(desc)) {
				return type;
			}
		}
		return null;
	}
	
	
}
