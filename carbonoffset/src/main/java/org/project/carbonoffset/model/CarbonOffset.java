package org.project.carbonoffset.model;

public class CarbonOffset {
    private String projectName;

    private Double offsetAmount;
    private Double offsetSum;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public Double getOffsetAmount() {
        return offsetAmount;
    }
    public void setOffsetAmount(Double offsetAmount) {
        this.offsetAmount = offsetAmount;
    }
    public Double getOffsetSum() {
        return offsetSum;
    }
    public void setOffsetSum(Double offsetSum) {
        this.offsetSum = offsetSum;
    }
}
