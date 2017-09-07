package com.xier.sesame.attence.domain;

public class RecognizeRecordView extends RecognizeRecord {
	
	private static final long serialVersionUID = -1972048771367410909L;
	
	private Long searchBeginDate;
	private Long searchEndDate;
	
	public Long getSearchBeginDate() {
		return searchBeginDate;
	}
	public void setSearchBeginDate(Long searchBeginDate) {
		this.searchBeginDate = searchBeginDate;
	}
	public Long getSearchEndDate() {
		return searchEndDate;
	}
	public void setSearchEndDate(Long searchEndDate) {
		this.searchEndDate = searchEndDate;
	}
	

}
