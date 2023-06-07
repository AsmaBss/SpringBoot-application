package com.springboot.models;

public enum MunitionReference {
	OBUS_20_MM("Obus de 20 mm"),
    GRENADE("Grenade"),
    OBUS_40_MM("Obus de 40 mm"),
    MORTIER_60_MM("Mortier de 60 mm"),
    MORTIER_81_MM("Mortier de 81 mm"),
    OBUS_75_MM("Obus de 75 mm"),
    OBUS_105_MM("Obus de 105 mm"),
    BOMBE_200_LBS("Bombe de 200 lbs"),
    BOMBE_500_LBS("Bombe de 500 lbs"),
    BOMBE_1000_LBS("Bombe de 1000 lbs");
	
	private final String sentence;

    MunitionReference(String sentence) {
        this.sentence = sentence;
    }

    public String getSentence() {
        return sentence;
    }
}