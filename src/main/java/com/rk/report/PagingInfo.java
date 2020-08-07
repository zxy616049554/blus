package com.rk.report;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class PagingInfo implements Serializable{
 
    /** 
    * @Fields serialVersionUID : TODO
    */
    private static final long serialVersionUID = -5530830078646733441L;
    
    /**
     * 请求数据的分页起始页码  从0开始计数
     */
    @NotNull
    @Digits(integer = 10000, fraction = 0)
    private int page = 0;
    
    /**
     * 每页多少条数据  可以由客户端指定，默认为20
     */
    @NotNull
    @Digits(integer = 10, fraction = 0)
    private int pageSize = 10;
    
    /**总页数*/
    private int pages;
    
    
    /**总数据条数*/
    private int totalSize;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public String toString() {
        return "PagingInfo [page=" + page + ", pageSize=" + pageSize + ", pages=" + pages + ", totalSize=" + totalSize + "]";
    }
    
    
    public static PagingInfo newPagingInfo() {
        PagingInfo ret = new PagingInfo();
        ret.setPage(1);
        ret.setPageSize(10);
        return ret;
    }

}
