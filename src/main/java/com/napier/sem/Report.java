package com.napier.sem;

abstract public class Report {

    public enum ReportType{CountryReport, CityReport, CapitalCityReport, PopulationReport, TotalPopulationReport}
    abstract ReportType getReportType(); // abstract method which classes must override. Returns report type


    abstract class ReportItem {

        private String _name; // country name which appears on most reports
        private long _population; // population which appears on most reports

        /**
         * Method to return name
         *
         * @return name
         */
        public String get_name() {
            return _name;
        }

        /**
         * Method to set name
         *
         * @param _name
         */
        public void set_name(String _name) {
            this._name = _name;
        }
    }
}
