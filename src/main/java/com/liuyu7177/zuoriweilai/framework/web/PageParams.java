package com.liuyu7177.zuoriweilai.framework.web;

import java.io.Serializable;

/**
 * Created by liuyu7177 On 2019/5/17
 */
public class PageParams implements Serializable {
    private int start;
    private int limit;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PageParams{" +
                "start=" + start +
                ", limit=" + limit +
                '}';
    }
}
