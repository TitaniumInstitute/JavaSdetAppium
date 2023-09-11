package org.baseiumobile.model;

import lombok.Getter;

@Getter
public class DriverOptions {
    private String appName;

    private String deviceName;

    private String appActivity;

    private String port;

    private String udid;

    public DriverOptions setAppName(String appName) {
        this.appName = System.getProperty("user.dir")+"/src/test/resources/apps/"+appName;
        return this;
    }

    public DriverOptions setDeviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    public DriverOptions setAppActivity(String appActivity) {
        this.appActivity = appActivity;
        return this;
    }

    public DriverOptions setPort(String port) {
        this.port = port;
        return this;
    }

    public DriverOptions setUdid(String udid) {
        this.udid = udid;
        return this;
    }
}
