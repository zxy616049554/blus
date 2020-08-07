package com.rk.dto;

import com.rk.report.Head;
import com.rk.report.PagingInfo;

public class ReplyMessageRequestJson {
	private static final long serialVersionUID = 363779540831681955L;

	private Head head;

	private ReplyMessageDTO requestBody;

	private PagingInfo pagingInfo;

	public PagingInfo getPagingInfo() {
		return pagingInfo;
	}

	public void setPagingInfo(PagingInfo pagingInfo) {
		this.pagingInfo = pagingInfo;
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public ReplyMessageDTO getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(ReplyMessageDTO requestBody) {
		this.requestBody = requestBody;
	}
}
