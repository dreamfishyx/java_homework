package com.dreamfish.admin.util;

import com.github.pagehelper.PageInfo;

/**
 * @author: dreamfish
 * @Description: 主要用于记录一些页面参数
 * @date: Created in 2022/8/7-19:06
 * @version: v1.8
 **/
public class MyPage {
    public<T> MyPage(PageInfo<T> pageInfo, String searchName) {
        this.pageNow = pageInfo.getPageNum();
        this.pages = pageInfo.getPages();
        this.count = pageInfo.getTotal();
        this.isLastPage=pageInfo.isIsLastPage();
        this.searchName=searchName;
    }

    //每页数据条数
    public static final int PAGE_SIZE=7;
    //当前页
    private int pageNow;
    //总页数
    private int pages;
    //总记录数
    private long count;

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    //
    private String searchName;

    public Boolean getIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(Boolean lastPage) {
        isLastPage = lastPage;
    }

    //最后一页
    private Boolean isLastPage;
    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }


}
