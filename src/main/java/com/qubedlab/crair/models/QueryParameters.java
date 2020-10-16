package com.qubedlab.crair.models;

public class QueryParameters {

    private String queryId;
    private String deltaDate;
    private String qparamInvCompany;
    private String dealerId;

    public QueryParameters() {

    }

    public QueryParameters(String queryId, String deltaDate, String qparamInvCompany, String dealerId) {
        this.queryId = queryId;
        this.deltaDate = deltaDate;
        this.qparamInvCompany = qparamInvCompany;
        this.dealerId = dealerId;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getDeltaDate() {
        return deltaDate;
    }

    public void setDeltaDate(String deltaDate) {
        this.deltaDate = deltaDate;
    }

    public String getQparamInvCompany() {
        return qparamInvCompany;
    }

    public void setQparamInvCompany(String qparamInvCompany) {
        this.qparamInvCompany = qparamInvCompany;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }
}
