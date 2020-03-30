package com.ensta.rentmanager.model;
public class Vehicule {
	private int id;	//Private : seule cette class y a accès
	private String constructeur;
	private byte nb_places;
	public Vehicule () {
		
	}
	public Vehicule (int id, String constructeur, String modele, byte nb_places ) {
		this.id = id;
		this.constructeur = constructeur;
		this.nb_places = nb_places;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConstructeur() {
		return constructeur;
	}
	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}
	public byte getNb_places() {
		return nb_places;
	}
	public void setNb_places(byte nb_place) {
		this.nb_places = nb_place;
	}	
	@Override
	public String toString() {
		return "vehicule [id=" + id + ", constructeur=" + constructeur + ", nb_places="
				+ nb_places + "]";
	}	
}
