package com.zhxin.common.web;

import java.io.Serializable;
import java.lang.management.ManagementFactory;

/**
 * @ClassName SystemInfoVo
 * @Description //系统信息
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/14 0014 上午 11:39
 **/
public class SystemInfoVo implements Serializable {

    private static final long serialVersionUID = 803311870974236566L;
    /**
     * 当前JVM占用的内存总数(M)
     */
    private double jvmTotal;

    /**
     * JVM最大可用内存总数(M)
     */
    private double jvmMax;

    /**
     * JVM空闲内存(M)
     */
    private double jvmFree;

    /**
     * JDK版本
     */
    private String jdkVersion;

    /**
     * JDK路径
     */
    private String javaHome;

    /**服务器IP*/
    private String hostIp;
    /**项目路径*/
    private String projectDir;
    /**操作系统*/
    private String osName;
    private String jvmName;

    public String getJvmName() {
        return jvmName;
    }

    public void setJvmName(String jvmName) {
        this.jvmName = jvmName;
    }

    public double getJvmTotal() {
        return jvmTotal;
    }

    public void setJvmTotal(double jvmTotal) {
        this.jvmTotal = jvmTotal;
    }

    public double getJvmMax() {
        return jvmMax;
    }

    public void setJvmMax(double jvmMax) {
        this.jvmMax = jvmMax;
    }

    public double getJvmFree() {
        return jvmFree;
    }

    public void setJvmFree(double jvmFree) {
        this.jvmFree = jvmFree;
    }

    public String getJdkVersion() {
        return jdkVersion;
    }

    public void setJdkVersion(String jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public void setJavaHome(String javaHome) {
        this.javaHome = javaHome;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getProjectDir() {
        return projectDir;
    }

    public void setProjectDir(String projectDir) {
        this.projectDir = projectDir;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }
}
