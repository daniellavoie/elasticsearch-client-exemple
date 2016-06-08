package com.daniellavoie.elk.model;

public class Voiture {
	private String marque;
	private String model;
	private int annee;
	private String couleur;
	private int consommation;
	
	public Voiture(){
		
	}

	public Voiture(String marque, String model, int annee, String couleur, int consommation) {
		this.marque = marque;
		this.model = model;
		this.annee = annee;
		this.couleur = couleur;
		this.consommation = consommation;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getConsommation() {
		return consommation;
	}

	public void setConsommation(int consommation) {
		this.consommation = consommation;
	}
}
