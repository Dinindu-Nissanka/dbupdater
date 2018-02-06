package com.wso2telco.atum.demo.dbupdater;

import io.dropwizard.Configuration;

public class AppConfiguration extends Configuration {

    private String apiName;
    private String apiVersion;
    private String apiProvider;
    private String operatorList;
    private MysqlConfiguration mysql;

    public MysqlConfiguration getMysql() {
        return mysql;
    }

    public void setMysql(MysqlConfiguration mysql) {
        this.mysql = mysql;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getApiProvider() {
        return apiProvider;
    }

    public void setApiProvider(String apiProvider) {
        this.apiProvider = apiProvider;
    }

    public String getOperatorList() {
        return operatorList;
    }

    public void setOperatorList(String operatorList) {
        this.operatorList = operatorList;
    }
}
