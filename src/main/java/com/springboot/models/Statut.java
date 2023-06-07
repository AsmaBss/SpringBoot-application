package com.springboot.models;

public enum Statut {
	Securise("Sécurisé"), A_Verifier("A vérifier"), Non_Securise("Non sécurisé");

	private final String sentence;

	Statut(String sentence) {
		this.sentence = sentence;
	}

	public String getSentence() {
		return sentence;
	}
}
