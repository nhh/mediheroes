package com.mediheroes.mediheroes.dto;

public class SummaryMetricResponse {

    private long userCount;
    private long companyCount;
    private long jobOfferCount;

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public long getCompanyCount() {
        return companyCount;
    }

    public void setCompanyCount(long companyCount) {
        this.companyCount = companyCount;
    }

    public long getJobOfferCount() {
        return jobOfferCount;
    }

    public void setJobOfferCount(long jobOfferCount) {
        this.jobOfferCount = jobOfferCount;
    }
}
