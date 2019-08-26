package com.napier.sem;

public class Country extends Report {
    @Override
    ReportType getReportType() {
        return ReportType.CountryReport;
    }

}
