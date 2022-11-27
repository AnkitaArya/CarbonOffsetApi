package org.project.carbonoffset.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.project.carbonoffset.exception.InvalidInputException;
import org.project.carbonoffset.model.CarbonOffset;
import org.project.carbonoffset.model.response.CarbonOffsetResponse;
import org.project.carbonoffset.validator.CarbonOffsetValidator;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarbonOffsetServiceTest {
    @InjectMocks
    CarbonOffsetService service = new CarbonOffsetService();

    CarbonOffsetValidator validator = new CarbonOffsetValidator();

    @MockBean
    Map<String, Double> carbonOffsetMap;


    @Before
    public void setup() {
        Map<String, Double> data = prepareTestObject();
        ReflectionTestUtils.setField(service, "carbonOffsetMap", data);
        ReflectionTestUtils.setField(service, "validator", validator);

    }

    @Test
    public void updateProjectOffsetDetailsPositiveTest() {
        CarbonOffset carbonOffset = new CarbonOffset();
        carbonOffset.setProjectName("test");
        carbonOffset.setOffsetAmount(1.2);
        CarbonOffsetResponse resp = service.updateProjectOffsetDetails(carbonOffset);
        Assert.assertNotNull(resp);
        Assert.assertEquals(resp.getProjectName(), "test");
        Assert.assertEquals(resp.getOffsetAmount(), new Double(1.2));
    }

    @Test(expected = InvalidInputException.class)
    public void updateProjectOffsetDetailsNegativeTest() {
        CarbonOffset carbonOffset = new CarbonOffset();
        carbonOffset.setProjectName("test");
        carbonOffset.setOffsetAmount(-1.2);
        service.updateProjectOffsetDetails(carbonOffset);
    }

    @Test
    public void getTop3ProjectsTest() {
        Map<String, Double> data = prepareTestObject();
        ReflectionTestUtils.setField(service, "carbonOffsetMap", data);
        List<CarbonOffsetResponse> resp = service.getTop3Projects();
        Assert.assertNotNull(resp);
        Assert.assertEquals(resp.get(0).getOffsetSum(), new Double(12.0));
        Assert.assertEquals(resp.get(0).getProjectName(), "abc");
    }

    private Map<String, Double> prepareTestObject() {
        Map<String, Double> carbonOffsetMap = new HashMap<String, Double>();
        carbonOffsetMap.put("abc", 12.0);
        carbonOffsetMap.put("def", 10.0);
        carbonOffsetMap.put("ijk", 11.0);
        return carbonOffsetMap;

    }

}
