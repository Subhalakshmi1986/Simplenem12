package com.redenergy.simplenem12.service;

import com.redenergy.simplenem12.model.EnergyUnit;
import com.redenergy.simplenem12.model.MeterRead;
import com.redenergy.simplenem12.model.MeterVolume;
import com.redenergy.simplenem12.model.Quality;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import static com.redenergy.simplenem12.utility.SimpleNem12Constants.COMMA_DELIMITER;
import static com.redenergy.simplenem12.utility.SimpleNem12Formatter.formatDate;
import static com.redenergy.simplenem12.utility.SimpleNem12Validator.isValidMeterReadRec;
import static com.redenergy.simplenem12.utility.SimpleNem12Validator.isValidMeterVolumeRec;

public class SimpleNem12ParserImpl implements SimpleNem12Parser {

    private final List<MeterRead> meterReadList = new ArrayList<>();

    public Collection<MeterRead> parseSimpleNem12(File simpleNem12File) {
        try (InputStream inputFileStream = new FileInputStream(simpleNem12File);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputFileStream))) {
            //parses each line from the csv
            br.lines().forEach(this::parseLine);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return meterReadList;
    }

    private void parseLine(String line) {
        if (!StringUtils.isBlank(line)) {
            String[] record = line.split(COMMA_DELIMITER);
            //Validates meter read rec
            if (isValidMeterReadRec(record)) {
                //If valid meter read rec is added to Collection<MeterRead>
                addMeterReadRec(record);
            }
            //Validates meter volume rec
            if (isValidMeterVolumeRec(record)) {
                if (meterReadList.size() > 0) {
                    //If valid adds meter volume record to the collection
                    addMeterVolumeRec(record);
                }
            }
        }
    }

    private void addMeterVolumeRec(String[] record) {
        MeterVolume meterVolume = new MeterVolume(new BigDecimal(record[2].trim()),
                Quality.valueOf(record[3].trim()));
        //add Meter Volume record to lastly added MeterRead record from the Collection<MeterRead> - 1
        meterReadList.get(meterReadList.size() - 1).getVolumes().put(formatDate(record[1].trim()), meterVolume);
    }

    private void addMeterReadRec(String[] record) {
        MeterRead meterReadRec = new MeterRead(record[1].trim(), EnergyUnit.valueOf(record[2].trim()));
        //add new Meter read record to the collection
        meterReadList.add(meterReadRec);
    }

}