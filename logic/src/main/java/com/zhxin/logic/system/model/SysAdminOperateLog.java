package com.zhxin.logic.system.model;

/**
 * @ClassName SysAdminOperateLog
 * @Description //TODO
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/24 0024 下午 2:59
 **/
public class SysAdminOperateLog extends BaseModel{
    private static final long serialVersionUID = 5030109365997137258L;
    private Long logId;
    private String title;
    private Integer operateType;
    private Long userId;
    private String userName;
    private String operateIp;
    private String operateLocation;
    private String method;
    private String requestType;
    private String requestUrl;
    private String requestParam;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    public String getOperateLocation() {
        return operateLocation;
    }

    public void setOperateLocation(String operateLocation) {
        this.operateLocation = operateLocation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }
}
