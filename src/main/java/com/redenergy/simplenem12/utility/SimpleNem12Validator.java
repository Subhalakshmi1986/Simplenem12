package com.redenergy.simplenem12.utility;

import com.redenergy.simplenem12.model.EnergyUnit;
import com.redenergy.simplenem12.model.Quality;
import com.redenergy.simplenem12.model.RecordType;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;

import static com.redenergy.simplenem12.utility.SimpleNem12Formatter.formatDate;

public class SimpleNem12Validator {

    //Check if each value is empty or present 
    public static boolean isValid(String[] record) {
        if (!StringUtils.isAllBlank(record)) {
            for (String col : record) {
                if (StringUtils.isBlank(col)) {
                    System.err.println("Mandatory value is blank");
                    return false;
                }
            }
        } else {
            System.err.println("Line is blank");
            return false;
        }
        return true;
    }

    public static boolean isValidMeterReadRec(String[] record) {
        //If Meter read record is not empty and begins with 200 then consider valid
        if (isValid(record) && RecordType.METER_READ_REC.getValue().equals(record[0].trim())) {
            if (record[1].trim().length() != 10) {
                System.err.println("NMI should be 10 characters long");
                return false;
            }
            if (!EnergyUnit.KWH.name().equals(record[2].trim())) {
                System.err.println("EnergyUnit must be KWH");
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public static boolean isValidMeterVolumeRec(String[] record) {
        //If Meter volume record is not empty and begins with 300 then consider valid
        if (isValid(record) && RecordType.METER_VOLUME_REC.getValue().equals(record[0].trim())) {

            if (!isValidDate(record[1].trim())) {
                System.err.println("Date must be in yyyyMMdd format");
                return false;
            }

            if (!isValidVolume(record[2].trim())) {
                System.err.println("Volume must be a valid number");
                return false;
            }
            if (!isValidQuality(record[3].trim())) {
                System.err.println("Quantity must be A or E");
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private static boolean isValidVolume(String col) {
        try {
            new BigDecimal(col);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private static boolean isValidDate(String col) {
        try {
            formatDate(col);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isValidQuality(String col) {
        try {
            Quality.valueOf(col);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
