package com.company.RESTApi;

import com.company.RESTApi.TmpObjects.GPSInfoPostTestObject;
import com.company.RESTApi.controllers.GPSInfoController;
import com.company.RESTApi.models.GPSInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

//@WebMvcTest(GPSInfoController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.MOCK)
public class GPSInfoControllerTests {
    @Autowired
    MockMvc mockMvc;


    @MockBean
    GPSInfoController controller;


    GPSInfo RECORD_1 = new GPSInfo(123,2222,3333);
    GPSInfo RECORD_2 = new GPSInfo(122, 444,3232);
    GPSInfo RECORD_3 = new GPSInfo(122, 55,3232);


    //-------------------ALL RECORDS--------------------
    @Test
    public void getAllRecords_success() throws Exception {
        List<GPSInfo> gps = new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));

        Mockito.when(
                controller.all()).
                thenReturn(gps);


        mockMvc.perform(MockMvcRequestBuilders
            .get("/gps")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].deviceId", is(122)));
    }

    //--------------------DEVICE ID---------------
    @Test
    public void getGPSInfoByDeviceId_200Success() throws Exception {
        List<GPSInfo> gps = new ArrayList<>(Arrays.asList( RECORD_2,RECORD_3));

        Mockito.when(
                controller.allOfDevice(122L)).
                thenReturn(gps);


        mockMvc.perform(MockMvcRequestBuilders
                .get("/gps?deviceId=122")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].deviceId", is(122)));
    }

    @Test
    public void getGPSInfoByDeviceId_200Success_DeviceIdNotOccure() throws Exception {
        List<GPSInfo> gps = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2,RECORD_3));

        Mockito.when(
                controller.all()).
                thenReturn(gps);


        mockMvc.perform(MockMvcRequestBuilders
                .get("/gps?deviceId=12662")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getGPSInfoByDeviceId_WrongDeviceIdParameterType_400BadRequest() throws Exception {
        List<GPSInfo> gps = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2,RECORD_3));

        Mockito.when(
                controller.allOfDevice(122L)).
                thenReturn(gps);


        mockMvc.perform(MockMvcRequestBuilders
                .get("/gps?deviceId=x")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    //--------------------DATE---------------
    @Test
    public void getGPSInfoByDate_WrongDateFormat_400BadRequest() throws Exception {
        List<GPSInfo> gps = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2,RECORD_3));

        Mockito.when(
                controller.all()).
                thenReturn(gps);


        mockMvc.perform(MockMvcRequestBuilders
                .get("/gps?date=123223223")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getGPSInfoByDate_200Success() throws Exception {
        List<GPSInfo> gps = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2,RECORD_3));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateFormatter.format(LocalDate.now(ZoneId.of("Europe/Paris")));

        Mockito.when(
                controller.allFromDate(LocalDate.now(ZoneId.of("Europe/Paris")))).
                thenReturn(gps);


        mockMvc.perform(MockMvcRequestBuilders
                .get("/gps?date="+date)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].deviceId", is(122)));
    }

    @Test
    public void getGPSInfoByDate_200Success_DateNotOccur() throws Exception {
        List<GPSInfo> gps = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2,RECORD_3));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateFormatter.format(LocalDate.now(ZoneId.of("Europe/Paris")));

        Mockito.when(
                controller.allFromDate(LocalDate.now(ZoneId.of("Europe/Paris")))).
                thenReturn(gps);


        mockMvc.perform(MockMvcRequestBuilders
                .get("/gps?date=1900-10-02")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }


    //INSERT NEW GPSInfo
    @Test
    public void insertNewGPSInfo_200OK() throws Exception {
        GPSInfoPostTestObject gpsInfo = new GPSInfoPostTestObject(111L, 222L, 333L);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(gpsInfo );

        mockMvc.perform(MockMvcRequestBuilders
            .post("/gps")
            .content(requestJson)
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void insertNewGPSInfo_NullVariable_400BadRequest() throws Exception {
        GPSInfoPostTestObject gpsInfo = new GPSInfoPostTestObject(111L, null, 333L);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(gpsInfo );

        mockMvc.perform(MockMvcRequestBuilders
                .post("/gps")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void insertNewGPSInfo_DeviceIdLessThan1_400BadRequest() throws Exception {
        GPSInfoPostTestObject gpsInfo = new GPSInfoPostTestObject(0L, 665L, 333L);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(gpsInfo );

        mockMvc.perform(MockMvcRequestBuilders
                .post("/gps")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    //DELETE GPSInfos data selected by deviceId
    @Test
    public void deleteGPSInfoByDeviceId_deviceIdFormatNotValid_400BadRequest() throws Exception {
        List<GPSInfo> gps = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2,RECORD_3));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateFormatter.format(LocalDate.now(ZoneId.of("Europe/Paris")));

        Mockito.when(
                controller.allFromDate(LocalDate.now(ZoneId.of("Europe/Paris")))).
                thenReturn(gps);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/gps?deviceId=x")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteGPSInfoByDeviceId_200Success() throws Exception {
        List<GPSInfo> gps = new ArrayList<>(Arrays.asList(RECORD_2, RECORD_3));

        Mockito.when(
                controller.deleteGPSInfoByDeviceId(122L)).
                thenReturn(gps);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/gps?deviceId=122")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void deleteGPSInfoByDeviceId_deviceIdNotOccur_200Success() throws Exception {
        List<GPSInfo> gps = new ArrayList<>(Collections.emptyList());

        Mockito.when(
                controller.deleteGPSInfoByDeviceId(1226L)).
                thenReturn(gps);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/gps?deviceId=126")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }


}
