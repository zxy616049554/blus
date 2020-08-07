package com.rk.dto;

import java.util.List;

import com.rk.dao.ReplyMessageDao;
import com.rk.report.PagingInfo;

public class ReplyMessageResponseBody {
	private static final long serialVersionUID = -6337700603129309425L;


    private PagingInfo pagingInfo;
    
    private List<ReplyMessageDao> dataList;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

	public List<ReplyMessageDao> getDataList() {
		return dataList;
	}

	public void setDataList(List<ReplyMessageDao> dataList) {
		this.dataList = dataList;
	}

}
