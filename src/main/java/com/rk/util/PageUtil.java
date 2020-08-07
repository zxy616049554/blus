package com.rk.util;

import com.github.pagehelper.PageInfo;
import com.rk.report.PagingInfo;


public class PageUtil {
    public static PagingInfo pageTrans(@SuppressWarnings("rawtypes") PageInfo page){
        PagingInfo pagingInfo = new PagingInfo();
        pagingInfo.setPage(page.getPageNum());
        pagingInfo.setPageSize(page.getPageSize());
        pagingInfo.setTotalSize((int)page.getTotal());
        pagingInfo.setPages(page.getPages());
        return pagingInfo;
    }
}
