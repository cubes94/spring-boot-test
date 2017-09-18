package com.whc.log;

import java.util.Date;

/**
 * Created by whc on 2017/9/18.
 */
public class BaseLog<T extends Object> {

    /**
     * 主键
     */
    private String id;

    /**
     * 状态码
     */
    private String code;

    /**
     * 处理信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 域名
     */
    private String domain;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
