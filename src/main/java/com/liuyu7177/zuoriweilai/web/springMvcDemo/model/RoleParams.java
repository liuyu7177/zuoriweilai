package com.liuyu7177.zuoriweilai.web.springMvcDemo.model;

import com.liuyu7177.zuoriweilai.framework.web.PageParams;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuyu7177 On 2019/5/17
 */
public class RoleParams extends RoleModel implements Serializable {
    public RoleParams() {

    }

    private List<PageParams> pageParamsList=null;

    public List<PageParams> getPageParamsList() {
        return pageParamsList;
    }

    public void setPageParamsList(List<PageParams> pageParamsList) {
        this.pageParamsList = pageParamsList;
    }

    private PageParams pageParams = null;

    public PageParams getPageParams() {
        return pageParams;
    }

    public void setPageParams(PageParams pageParams) {
        this.pageParams = pageParams;
    }

    @Override
    public String toString() {
        return "RoleParams{" +
                "pageParams=" + pageParams +
                '}';
    }
}
