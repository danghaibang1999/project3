package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TestData {
    public static Map<String, TestCase> loadTestData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("src/main/resources/createAssignmentData.json"), mapper.getTypeFactory().constructMapType(Map.class, String.class, TestCase.class));
    }

    public static class DateTime {
        public boolean enabled;
        public int day;
        public String month;
        public int year;
        public int hour;
        public int minute;
    }

    public static class Signal {
        public String id;
        public String message;
    }

    public static class ExpectedResult {
        public String result;
        public Signal signal;
    }

    public static class TestCase {
        public String description;
        public DateTime allowSubmissionsFrom;
        public DateTime dueDate;
        public DateTime cutOffDate;
        public DateTime remindMeToGradeBy;
        public ExpectedResult expectedResult;
    }
}