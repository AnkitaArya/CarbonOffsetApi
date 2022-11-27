package org.project.carbonoffset.validator;

import org.project.carbonoffset.exception.InvalidInputException;
import org.project.carbonoffset.model.CarbonOffset;
import org.springframework.stereotype.Component;

@Component
public class CarbonOffsetValidator {

    public void validateInput(CarbonOffset carbonOffset){
        if(carbonOffset == null){
            throw new InvalidInputException("carbon offset should be not null value");
        }
        else if(carbonOffset.getOffsetAmount()<0) {
            throw new InvalidInputException("the carbon offset should be positive value");
        }
    };

}
