package com.team.house.utils;

public class Page {
    private Integer page=1;
    private  Integer rows=3;

    public Page() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
