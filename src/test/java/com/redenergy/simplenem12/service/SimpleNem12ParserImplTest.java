package com.redenergy.simplenem12.service;

import com.redenergy.simplenem12.model.MeterRead;
import java.io.File;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleNem12ParserImplTest {
    private File file = null;


    @Before
    public void setUp() {
        String resourceName = "SimpleNem12.csv";
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(Objects.requireNonNull(classLoader.getResource(resourceName)).getFile());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testParseSimpleNem12() {
        SimpleNem12Parser simpleNem12Parser = new SimpleNem12ParserImpl();
        Collection<MeterRead> meterReadList = simpleNem12Parser.parseSimpleNem12(file);
        assertEquals(2, meterReadList.size());
        assertEquals(new BigDecimal("14.33"),
                meterReadList.stream().filter(mr -> mr.getNmi().equals("6987654321")).findFirst().get().getTotalVolume());
        assertEquals(new BigDecimal("-36.84"),
                meterReadList.stream().filter(mr -> mr.getNmi().equals("6123456789")).findFirst().get().getTotalVolume());

    }

    @Test
    public void testValidRecords() {
        String resourceName = "SimpleNem12_2.csv";
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(Objects.requireNonNull(classLoader.getResource(resourceName)).getFile());
        SimpleNem12Parser simpleNem12Parser = new SimpleNem12ParserImpl();
        Collection<MeterRead> meterReadList = simpleNem12Parser.parseSimpleNem12(file);
        assertEquals(1, meterReadList.size());
        assertEquals(new BigDecimal("-22.51"),
                meterReadList.stream().filter(mr -> mr.getNmi().equals("6123456789")).findFirst().get().getTotalVolume());
    }
}