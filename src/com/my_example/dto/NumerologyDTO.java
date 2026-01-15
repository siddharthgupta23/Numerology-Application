package com.my_example.dto;

import java.util.Date;

public class NumerologyDTO {
    private String username;
    private Date dateOfBirth;
    private int lifePathNumber;
    private int destinyNumber;
    private int soulNumber;
    private int personalityNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getLifePathNumber() {
        return lifePathNumber;
    }

    public void setLifePathNumber(int lifePathNumber) {
        this.lifePathNumber = lifePathNumber;
    }

    public int getDestinyNumber() {
        return destinyNumber;
    }

    public void setDestinyNumber(int destinyNumber) {
        this.destinyNumber = destinyNumber;
    }

    public int getSoulNumber() {
        return soulNumber;
    }

    public void setSoulNumber(int soulNumber) {
        this.soulNumber = soulNumber;
    }

    public int getPersonalityNumber() {
        return personalityNumber;
    }

    public void setPersonalityNumber(int personalityNumber) {
        this.personalityNumber = personalityNumber;
    }
}



