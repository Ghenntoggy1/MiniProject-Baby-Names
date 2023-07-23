import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalMales = 0;
        int totalFemales = 0;
        int totalUniqueMales = 0;
        int totalUniqueFemales = 0;
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            if (record.get(1).equals("F")) {
                totalFemales += numBorn;
                totalUniqueFemales += 1;
            }
            else {
                totalMales += numBorn;
                totalUniqueMales += 1;
            }
        }
        int totalUniqueNames = totalUniqueFemales + totalUniqueMales;
        System.out.println("Total Births: " + totalBirths);
        System.out.println("Total Females: " + totalFemales);
        System.out.println("Total Unique Females Names: " + totalUniqueFemales);
        System.out.println("Total Males: " + totalMales);
        System.out.println("Total Unique Males Names: " + totalUniqueMales);
        System.out.println("Total Unique Names: " + totalUniqueNames);
    }
    
    public int getRank (int year, String name, String gender) {
        String nameFile = "C:\\Users\\511\\Desktop\\Coursera\\OOP\\MiniProject - BabyNames\\data\\us_babynames_by_year\\yob" + year + ".csv";
        FileResource fr = new FileResource(nameFile);
        CSVParser parser = fr.getCSVParser(false);
        long rank = -1;
        int index = 0;
        for (CSVRecord record : parser) {
            String currName = record.get(0);
            String currGender = record.get(1);
            if (currGender.equals("F")) {
                index += 1;
            }
            if (currName.equals(name) && currGender.equals(gender) && gender.equals("M")) {
                rank = parser.getCurrentLineNumber() - index;
                return (int) rank;
            }
            else if (currName.equals(name) && currGender.equals(gender) && gender.equals("F")){
                rank = index;
                return (int) rank;
            }
        }
        return (int) rank;
    }
    
    public String getName (int year, int rank, String gender) {
        String nameFile = "C:\\Users\\511\\Desktop\\Coursera\\OOP\\MiniProject - BabyNames\\data\\us_babynames_by_year\\yob" + year + ".csv";
        FileResource fr = new FileResource(nameFile);
        CSVParser parser = fr.getCSVParser(false);
        String name = "NO NAME";
        int index = 0;
        for (CSVRecord record : parser) {
            String currGender = record.get(1);
            long line = parser.getCurrentLineNumber();
            if (currGender.equals("F")) {
                index += 1;
                if (index == rank && currGender.equals(gender)) {
                    name = record.get(0);
                    return name;
                }
            }
            if (currGender.equals(gender) && (line - index) == rank) {
                name = record.get(0);
                return name;
            }
        }
        return name;
    }
    
    public void whatIsNameInYear (String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        if (gender.equals("F")) {
            System.out.println(name + " born in " + year + " would be " + newName  + " if she was born in " + newYear);
        }
        else {
            System.out.println(name + " born in " + year + " would be " + newName  + " if he was born in " + newYear);
        }
    }
    
    public int yearOfHighestRank (String name, String gender) {
        int highestRank = 999999999;
        int highestYear = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int currYear = Integer.parseInt(fileName.substring(3, 7));
            int currRank = getRank(currYear, name, gender);
            if (highestRank > currRank && currRank != -1) {
                highestRank = currRank;
                highestYear = currYear;
            }
        }
        if (highestYear == 0) {
            return -1;
        }
        else {
            return highestYear;
        }
    }
    
    public double getAverageRank (String name, String gender) {
        double sum = 0;
        int counter = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int currYear = Integer.parseInt(fileName.substring(3, 7));
            int currRank = getRank(currYear, name, gender);
            if (currRank != -1) {
                sum += currRank;
                counter += 1;
            }
        }
        if (counter == 0) {
            return -1;
        }
        else {
            return sum / counter;
        }
    }
    
    public int getTotalBirthsRankedHigher (int year, String name, String gender) {
        String nameFile = "C:\\Users\\511\\Desktop\\Coursera\\OOP\\MiniProject - BabyNames\\data\\us_babynames_by_year\\yob" + year + ".csv";
        FileResource fr = new FileResource(nameFile);
        int counter = 0;
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            String currGender = record.get(1);
            String currName = record.get(0);
            int currBirths = Integer.parseInt(record.get(2));
            if (currGender.equals("M") && !currName.equals("Drew")) {
                counter += currBirths;
            }
            if (currName.equals(name) && currGender.equals("M")) {
                break;
            }
        }
        return counter;
    }
    
    public void testTotalBirths () {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public void testGetRank () {
        int year = 1960;
        String name = "Emily";
        String gender = "F";
        long rank = getRank(year, name, gender);
        System.out.println("Rank of " + name + " gender " + gender + " in " + year + ": " + rank);
        
        year = 1971;
        name = "Frank";
        gender = "M";
        rank = getRank(year, name, gender);
        System.out.println("Rank of " + name + " gender " + gender + " in " + year + ": " + rank);
        
        year = 2012;
        name = "Emma";
        gender = "M";
        rank = getRank(year, name, gender);
        System.out.println("Rank of " + name + " gender " + gender + " in " + year + ": " + rank);
    }
    
    public void testGetName () {
        int year = 1980;
        int rank = 350;
        String gender = "F";
        String name = getName(year, rank, gender);
        System.out.println("Name with rank " + rank + " gender " + gender + " in " + year + ": " + name);
        
        year = 1982;
        rank = 450;
        gender = "M";
        name = getName(year, rank, gender);
        System.out.println("Name with rank " + rank + " gender " + gender + " in " + year + ": " + name);
        
        year = 2012;
        rank = 4;
        gender = "M";
        name = getName(year, rank, gender);
        System.out.println("Name with rank " + rank + " gender " + gender + " in " + year + ": " + name);
        
        year = 2012;
        rank = 7;
        gender = "F";
        name = getName(year, rank, gender);
        System.out.println("Name with rank " + rank + " gender " + gender + " in " + year + ": " + name);
    }
    
    public void testWhatIsNameInYear () {
        int year = 1972;
        String name = "Susan";
        int newYear = 2014;
        String gender = "F";
        whatIsNameInYear(name, year, newYear, gender);

        year = 1974;
        name = "Owen";
        newYear = 2014;
        gender = "M";
        whatIsNameInYear(name, year, newYear, gender);
    }
    
    public void testYearOfHighestRank () {
        String name = "Genevieve";
        String gender = "F";
        int yearHighestRank = yearOfHighestRank(name, gender);
        System.out.println("Name " + name + " gender " + gender + " had highest rank in " + yearHighestRank);
        name = "Mich";
        gender = "M";
        yearHighestRank = yearOfHighestRank(name, gender);
        System.out.println("Name " + name + " gender " + gender + " had highest rank in " + yearHighestRank);
    
    }
    
    public void testGetAverageRank () {
        String name = "Susan";
        String gender = "F";
        double averageRank = getAverageRank(name, gender);
        System.out.println("Name " + name + " gender " + gender + " had average rank " + averageRank);
        
        name = "Robert";
        gender = "M";
        averageRank = getAverageRank(name, gender);
        System.out.println("Name " + name + " gender " + gender + " had average rank " + averageRank);
        
        name = "Jacob";
        gender = "F";
        averageRank = getAverageRank(name, gender);
        System.out.println("Name " + name + " gender " + gender + " had average rank " + averageRank);
    }
    
    public void testGetTotalBirthsRankedHigher () {
        int year = 1990;
        String name = "Edgar";
        String gender = "M";
        int counter = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Total births of Names Ranked higher than name " + name + " gender " + gender + " in year " + year + " is " + counter);
    
        year = 1990;
        name = "Drew";
        gender = "M";
        counter = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Total births of Names Ranked higher than name " + name + " gender " + gender + " in year " + year + " is " + counter);
    
    }
}
