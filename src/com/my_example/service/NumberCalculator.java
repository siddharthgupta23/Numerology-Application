package com.my_example.service;

import java.util.Calendar;
import java.util.Date;

/**
 * Single Responsibility: Calculates numerology numbers from dates and names
 */
public class NumberCalculator {
    
    public int calculateLifePathNumber(Date dateOfBirth) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOfBirth);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1; // Month is 0-indexed
        int year = cal.get(Calendar.YEAR);
        
        int daySum = reduceToSingleDigit(day);
        int monthSum = reduceToSingleDigit(month);
        int yearSum = reduceToSingleDigit(year);
        
        int total = daySum + monthSum + yearSum;
        return reduceToSingleDigit(total);
    }
    
    public int calculateDestinyNumber(String name) {
        String upperName = name.toUpperCase().replaceAll("\\s+", "");
        int sum = 0;
        for (char c : upperName.toCharArray()) {
            sum += getLetterValue(c);
        }
        return reduceToSingleDigit(sum);
    }
    
    public int calculateSoulNumber(String name) {
        String upperName = name.toUpperCase().replaceAll("\\s+", "");
        int sum = 0;
        for (char c : upperName.toCharArray()) {
            if (isVowel(c)) {
                sum += getLetterValue(c);
            }
        }
        return reduceToSingleDigit(sum);
    }
    
    public int calculatePersonalityNumber(String name) {
        String upperName = name.toUpperCase().replaceAll("\\s+", "");
        int sum = 0;
        for (char c : upperName.toCharArray()) {
            if (!isVowel(c)) {
                sum += getLetterValue(c);
            }
        }
        return reduceToSingleDigit(sum);
    }
    
    private int reduceToSingleDigit(int number) {
        while (number > 9 && number != 11 && number != 22 && number != 33) {
            int sum = 0;
            while (number > 0) {
                sum += number % 10;
                number /= 10;
            }
            number = sum;
        }
        return number;
    }
    
    private int getLetterValue(char c) {
        if (c >= 'A' && c <= 'Z') {
            int value = (c - 'A' + 1) % 9;
            return value == 0 ? 9 : value;
        }
        return 0;
    }
    
    private boolean isVowel(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}



