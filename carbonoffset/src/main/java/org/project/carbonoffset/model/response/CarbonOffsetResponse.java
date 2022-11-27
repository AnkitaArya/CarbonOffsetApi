package org.project.carbonoffset.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class CarbonOffsetResponse {
    private String projectName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double offsetAmount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
