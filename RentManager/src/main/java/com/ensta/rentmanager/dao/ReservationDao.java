package com.ensta.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.model.Reservation;
import com.ensta.rentmanager.persistence.ConnectionManager;

public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		
	public long create(Reservation reservation) throws DaoException {
		//Connection conn = test ? ConnectionManager.get
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(CREATE_RESERVATION_QUERY);) 
		{
			statement.setInt(1, reservation.getId_client());
			statement.setInt(2, reservation.getId_vehicule());
			statement.setDate(3, reservation.getDebut());
			statement.setDate(4, reservation.getFin());
			
			long result = statement.executeUpdate();
			return result;
			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
	}
	
	public long delete(int Id) throws DaoException {
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(DELETE_RESERVATION_QUERY);) 
		{
			statement.setInt(1, Id);
			long result = statement.executeUpdate();
			return result;
			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la suppression : " + e.getMessage());
		}
	}

	
	public List<Reservation> findResaByClientId(int clientId) throws DaoException {
		List<Reservation> resultList = new ArrayList<Reservation>();
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);)
		
		{	statement.setInt(1, clientId);
			ResultSet resultSet =  statement.executeQuery();
			while(resultSet.next()) {
				
				Reservation reservation = new Reservation();
				reservation.setId(resultSet.getInt(1));
				reservation.setId_client(clientId);
				reservation.setId_vehicule(resultSet.getInt(2));
				reservation.setDebut(resultSet.getDate(3));
				reservation.setFin(resultSet.getDate(4));
				resultList.add(reservation);
			} 
			return resultList;
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la recherche du client : " + e.getMessage());			
		}	
	}
	
	public List<Reservation> findResaByVehiculeId(int vehicleId) throws DaoException {
		List<Reservation> resultList = new ArrayList<Reservation>();
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);)
		
		{	statement.setInt(1, vehicleId);
			ResultSet resultSet =  statement.executeQuery();
			while(resultSet.next()) {
				
				Reservation reservation = new Reservation();
				reservation.setId(resultSet.getInt(1));
				reservation.setId_client(resultSet.getInt(2));
				reservation.setId_vehicule(resultSet.getInt(vehicleId));
				reservation.setDebut(resultSet.getDate(3));
				reservation.setFin(resultSet.getDate(4));
				resultList.add(reservation);
			} 
			return resultList;
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la recherche du véhicule : " + e.getMessage());			
		}	
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> resultList = new ArrayList<Reservation>();
		
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_RESERVATIONS_QUERY);) 
		{
			ResultSet resultSet =  statement.executeQuery();
			while(resultSet.next()) {
				
				Reservation reservation = new Reservation();
				reservation.setId(resultSet.getInt(1));
				reservation.setId_client(resultSet.getInt(2));
				reservation.setId_vehicule(resultSet.getInt(3));
				reservation.setDebut(resultSet.getDate(4));
				reservation.setFin(resultSet.getDate(5));
				resultList.add(reservation);
		}
			return resultList;			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la recherche : " + e.getMessage());			
		}	 
	}
}
