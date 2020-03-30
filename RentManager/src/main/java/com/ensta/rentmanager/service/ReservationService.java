package com.ensta.rentmanager.service;

import java.util.List;

import com.ensta.rentmanager.dao.ReservationDao;
import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Reservation;

public class ReservationService {
	private static ReservationService instance = null;
	private ReservationDao reservationDao;
	
	private ReservationService() {
		this.reservationDao = ReservationDao.getInstance();
	}
	
	public static ReservationService getInstance() {
		if (instance == null) {
			instance = new ReservationService();
		}
		
		return instance;
	}
	public long create(Reservation reservation) throws ServiceException {
		try {
			return reservationDao.create(reservation);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	public long delete(int Id) throws ServiceException {
		try {
			return reservationDao.delete(Id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	 
	}
	public List<Reservation> findAll() throws ServiceException {
		try {
			return reservationDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<Reservation> findResaByVehiculeId(int Id_vehicule) throws ServiceException {
		try {
			return reservationDao.findResaByVehiculeId(Id_vehicule);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public List<Reservation> findResaByClientId(int Id_client) throws ServiceException {
		try {
			return reservationDao.findResaByClientId(Id_client);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
