package com.napier.sem;
/**
 * Create public class for the City
 */
public class City extends Report {
    @Override
    ReportType getReportType() {
        return ReportType.CityReport;
    }
}