package com.zhxin.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName OssProperties
 * @Description //OssProperties
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/25 0025 下午 2:09
 **/
@Component
@ConfigurationProperties(prefix ="aliyun-oss")
public class OssProperties {
    private  String endPoint;
    private  String keyId;
    private  String keySecret;
    private  String bucketName;
    private  String fileDir;

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeySecret() {
        return keySecret;
    }

    public void setKeySecret(String keySecret) {
        this.keySecret = keySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }
}
