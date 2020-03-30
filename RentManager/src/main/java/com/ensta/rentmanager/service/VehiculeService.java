/*
 * Etape avant la base de donnée, où sont effectuées les vérifications des données entrées par l'utilisateur
 * */
package com.ensta.rentmanager.service;

import java.util.List;

import com.ensta.rentmanager.dao.VehiculeDao;
import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;
import com.ensta.rentmanager.model.Vehicule;

public class VehiculeService {
	
	private static VehiculeService instance = null;
	private VehiculeDao vehiculeDao;
	
	private VehiculeService() {
		this.vehiculeDao = VehiculeDao.getInstance();
	}
	
	public static VehiculeService getInstance() {
		if (instance == null) {
			instance = new VehiculeService();
		}
		
		return instance;
	}
	
	public long create(Vehicule vehicule) throws ServiceException {
		try {
			return vehiculeDao.create(vehicule);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	public long delete(int Id) throws ServiceException {
		try {
			return vehiculeDao.delete(Id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	 
	}


	public List<Vehicule> findById(int id) throws ServiceException {
		try {
			return vehiculeDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Vehicule> findAll() throws ServiceException {
		try {
			return vehiculeDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
