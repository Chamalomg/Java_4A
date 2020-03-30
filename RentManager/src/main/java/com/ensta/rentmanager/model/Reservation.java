package com.ensta.rentmanager.model;

import java.sql.Date;

public class Reservation {
	private int id;	//Private : seule cette class y a accès
	private int Id_client;
	private int Id_vehicule;
	private Date debut;
	private Date fin;
	
	public Reservation () {
		
	}
	
	public Reservation (int id, int Id_client, int Id_vehicule, Date debut, Date fin) {
		this.id = id;
		this.Id_client = Id_client;
		this.Id_vehicule = Id_vehicule;
		this.debut = debut;
		this.fin = fin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_client() {
		return Id_client;
	}

	public void setId_client(int id_client) {
		Id_client = id_client;
	}

	public int getId_vehicule() {
		return Id_vehicule;
	}

	public void setId_vehicule(int id_vehicule) {
		Id_vehicule = id_vehicule;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", Id_client=" + Id_client + ", Id_vehicule=" + Id_vehicule + ", debut="
				+ debut + ", fin=" + fin + "]";
	}
}
