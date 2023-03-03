package com.fabric.wms.model;

import java.time.LocalDate;

public class WaterAllotmentDetails {

    private String ratio;
    private LocalDate allotmentDate;

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public LocalDate getAllotmentDate() {
        return allotmentDate;
    }

    public void setAllotmentDate(LocalDate allotmentDate) {
        this.allotmentDate = allotmentDate;
    }
}
