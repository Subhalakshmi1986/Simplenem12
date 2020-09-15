# Simplenem12
  System to parse simplenem12 file and print meter reads and meter volume
 
## Requirements
  Requirements given in [README-task-domain-provided.md](https://github.com/Subhalakshmi1986/Simplenem12/blob/master/README-task-domain-provided.md)
  
## Project Structure 
  Restructured the project structure as below and moved components to relevant directories.
  New files added are marked in bold
  * Simplenem12
    * src
        * main
            * java
                * com.redenergy.simplenem12
                    * app
                        * TestHarness.java
                    * model
                        * EnergyUnit.java
                        * MeterRead.java
                        * MeterVolume.java
                        * Quality.java
                        * **RecordType.java**
                    * service
                        * SimpleNem12Parser.java
                        * **SimpleNem12ParserImpl.java**
                    * utility
                        * **SimpleNem12Constants.java**
                        * **SimpleNem12Formatter.java**
                        * **SimpleNem12Validator.java**
        * test
            * java
                * com.redenergy.simplenem12.service
                    * **SimpleNem12ParserImplTest**
            
## New Files added
   * SimpleNem12ParserImpl.java - File which parses the nem file
   * RecordType.java - Enum describing the record type
   * SimpleNem12Constants.java  - File with project constants
   * SimpleNem12Formatter.java  - File with format methods
   * SimpleNem12Validator.java  - File with validation
   * SimpleNem12ParserImplTest.java  - Test to prove expected behaviour of code
   * SimpleNem12.csv packaged with the project
   
## Assumptions considered 
    * No quotes or extraneous commas appear in the CSV data
    * Each file does not contain more than one RecordType 200 with the same NMI. 
    * Meter Volume records (300)  are associated with the last preceded  valid Meter Read record(200)
    * File name and location given is valid

 
## Design
    1.Ignore the lines in csv beginning with 100 or 900
    2.Add records beginning with  - 200  - MeterRead record to Collection<MeterRead>
    4.Additional validator added to validate most validations
       
## Project build and run
   ```
    1. Download Simplenem12-master.zip  from - https://github.com/Subhalakshmi1986/Simplenem12/archive/master.zip)
    2. Unzip Simplenem12-master.zip
    3. Navigate to Simplenem12-master folder
    4. Execute ./gradlew clean build
    5. ./gradlew run --args="SimpleNem12.csv"

```
## Console Output:
```
    Total volume for NMI 6123456789 is -36.840000
    Total volume for NMI 6987654321 is 14.330000

``` 
## Test and Code coverage report after project build
   ```
    1.Test report availabel in ./build/reports/tests/test/index.html
    2.Code coverage report availabel after project build  ./build/jacocoHtml/index.html
   ```        
      
## Further enhancements
   1.Proper exception handling 
   2.Logging implementation      