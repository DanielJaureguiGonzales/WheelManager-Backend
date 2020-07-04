package com.acme.wheelmanager.testing;

import com.acme.wheelmanager.AbstractTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.acme.wheelmanager.model.Advertising;
import com.acme.wheelmanager.resource.AdvertisingResource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.*;

public class AdvertisingControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getAllAdvertisingsList() throws Exception {
        String uri = "http://localhost:8081/api/advertisings";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        AdvertisingResource[] advertisinglist = super.mapFromJson(content, AdvertisingResource[].class);
        assertTrue(advertisinglist.length > 0);
    }

    @Test
    public void getAdvertisingsById() throws Exception {
        String uri = "http://localhost:8081/api/advertisings/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        AdvertisingResource advertisinglist = super.mapFromJson(content, AdvertisingResource.class);
        assertTrue(advertisinglist != null);
    }
}