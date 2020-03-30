package com.ensta.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Vehicule;
import com.ensta.rentmanager.persistence.ConnectionManager;

public class VehiculeDao {
	
	private static VehiculeDao instance = null;
	private VehiculeDao() {}
	public static VehiculeDao getInstance() {
		if(instance == null) {
			instance = new VehiculeDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	
	public long create(Vehicule vehicule) throws DaoException {
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(CREATE_VEHICLE_QUERY);) 
		{
			statement.setString(1, vehicule.getConstructeur());
			statement.setLong(2, vehicule.getNb_places());
			
			long result = statement.executeUpdate();
			return result;
			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
	}

	public long delete(int Id) throws DaoException {
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(DELETE_VEHICLE_QUERY);) 
		{
			statement.setInt(1, Id);
			long result = statement.executeUpdate();
			return result;
			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());
		}
	}

	/*public Optional<Vehicule> findById(long id) throws DaoException {
	}*/
	public List<Vehicule> findById(int id) throws DaoException {
		List<Vehicule> resultList = new ArrayList<Vehicule>();
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_VEHICLE_QUERY);)
		
		{	statement.setInt(1, id);
			ResultSet resultSet =  statement.executeQuery();
			while(resultSet.next()) {
				Vehicule vehicule = new Vehicule();
				vehicule.setId(resultSet.getInt(1));
				vehicule.setConstructeur(resultSet.getString(2));
				vehicule.setNb_places(resultSet.getByte(3));
				resultList.add(vehicule);
			} 
			return resultList;
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());			
		}	
	}
	
	public List<Vehicule> findAll() throws DaoException {
		List<Vehicule> resultList = new ArrayList<Vehicule>();
		
		try ( Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn.prepareStatement(FIND_VEHICLES_QUERY);) 
		{
			ResultSet resultSet =  statement.executeQuery();
			while(resultSet.next()) {
				
				Vehicule vehicule = new Vehicule();
				vehicule.setId(resultSet.getInt(1));
				vehicule.setConstructeur(resultSet.getString(2));
				vehicule.setNb_places(resultSet.getByte(3));
				resultList.add(vehicule);
		}
			return resultList;			
		} catch (SQLException e) { //e : nom de l'erreur (variable ?)
			throw new DaoException("Erreur lors de la création : " + e.getMessage());			
		}	 
	}
	

}
