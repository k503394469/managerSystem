package com.liu.domain;

import java.util.List;

public class Pager <T> {
    private Integer page;//分页起始数
    private Integer size;//每页条数
    private List<T> rows;//记录集合
    private long total;//总记录条数

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
