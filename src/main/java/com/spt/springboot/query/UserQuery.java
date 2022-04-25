package com.spt.springboot.query;

public class UserQuery {

    /**
     *  分页参数
     */
    private Integer pageNum = 1; // 当前页（未传递页面时，默认查询第一页）
    private Integer pageSize = 10;// 每页显示的记录数量（当未传递数量时，默认每页显示10条）

    /**
     *  条件参数
     */
    private String userName; // 查询参数: 用户名

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
