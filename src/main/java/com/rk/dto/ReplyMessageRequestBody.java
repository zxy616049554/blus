package com.rk.dto;

import com.rk.report.PagingInfo;

public class ReplyMessageRequestBody {
	private static final long serialVersionUID = 5764302081061943804L;
	private PagingInfo pagingInfo;

	private ReplyMessageDTO data;

	public PagingInfo getPagingInfo() {
		return pagingInfo;
	}

	public void setPagingInfo(PagingInfo pagingInfo) {
		this.pagingInfo = pagingInfo;
	}

	public ReplyMessageDTO getData() {
		return data;
	}

	public void setData(ReplyMessageDTO data) {
		this.data = data;
	}

}
