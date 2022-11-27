package org.project.carbonoffset.service;

import org.apache.commons.math3.util.Precision;
import org.project.carbonoffset.model.CarbonOffset;
import org.project.carbonoffset.model.response.CarbonOffsetResponse;
import org.project.carbonoffset.validator.CarbonOffsetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarbonOffsetService {
    @Autowired
    CarbonOffsetValidator validator;
    @Autowired
    private Map<String, Double> carbonOffsetMap;

    public CarbonOffsetResponse updateProjectOffsetDetails(CarbonOffset carbonOffset){
        validator.validateInput(carbonOffset);
        CarbonOffsetResponse createdCarbonOffset = new CarbonOffsetResponse();
        if(carbonOffsetMap.get(carbonOffset.getProjectName())!=null){
            Double updatedTotalOffset = (carbonOffset.getOffsetAmount()+carbonOffsetMap.get(carbonOffset.getProjectName()));
            Double carbonOffsetSum =round(updatedTotalOffset, 1);
            carbonOffsetMap.put(carbonOffset.getProjectName(), carbonOffsetSum);
        }else{
            Double updatedOffset = carbonOffset.getOffsetAmount();
            Double carbonOffsetSum =round(updatedOffset, 1);
            carbonOffsetMap.put(carbonOffset.getProjectName(), carbonOffsetSum);

        }
        createdCarbonOffset.setOffsetAmount(carbonOffset.getOffsetAmount());
        createdCarbonOffset.setProjectName(carbonOffset.getProjectName());
        return createdCarbonOffset;
    }

    public List<CarbonOffsetResponse> getTop3Projects(){
        List<String> keys = carbonOffsetMap.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
        List<CarbonOffsetResponse> responseList = new ArrayList<CarbonOffsetResponse>();
        for(String projectName: keys){
            CarbonOffsetResponse response = new CarbonOffsetResponse();
            response.setProjectName(projectName);
            response.setOffsetSum(carbonOffsetMap.get(projectName));
            responseList.add(response);
        }
        responseList.sort(Comparator.comparing(CarbonOffsetResponse::getOffsetSum).thenComparing(CarbonOffsetResponse::getProjectName).reversed());
    return responseList;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
