package com.liyuan3210.demo.myBatisPlus.common;

import com.baomidou.mybatisplus.annotation.TableField;

public class BaseDto {
    /**
     * 当前页
     */
    @TableField(exist = false)
    private Integer page = 1;
    /**
     * 分页数量
     */
    @TableField(exist = false)
    private Integer pageSize = 10;
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
