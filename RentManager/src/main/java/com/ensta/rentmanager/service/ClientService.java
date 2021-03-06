/*
 * Etape avant la base de donnée, où sont effectuées les vérifications des données entrées par l'utilisateur
 * */

package com.ensta.rentmanager.service;

import java.util.List;

import com.ensta.rentmanager.dao.ClientDao;
import com.ensta.rentmanager.exception.DaoException;
import com.ensta.rentmanager.exception.ServiceException;
import com.ensta.rentmanager.model.Client;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ClientService {
	
	private static ClientService instance = null;
	private ClientDao clientDao;
	
	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		return instance;
	}

	
	public long create(Client client) throws ServiceException {
		checkAge(client);
		checkNom(client);
		checkPrenom(client);	
		//checkMail(client);
		try {
			return clientDao.create(client);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	
	}
	private void checkAge(Client client) throws ServiceException {
		long age = ChronoUnit.YEARS.between(client.getNaissance().toLocalDate(), LocalDate.now());
		System.out.println(age);
		if (age<18) {
			throw new ServiceException("Le client doit avoir 18 ans : il n'a que " + age);
		}
	}
	
	/*private void checkMail(Client client) throws ServiceException {
		String mail = client.getEmail();	
		System.out.println(mail);
		if (!mail.contains("@")) {
			throw new ServiceException("Le mail doit contenir un @ !");
		}
		/*Mail déjà uilisé ? 
	}*/
	public long delete(int Id) throws ServiceException {
		try {
			return clientDao.delete(Id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}	 
	}

	private void checkPrenom(Client client) throws ServiceException {
		String prenom = client.getPrenom();
		System.out.println(prenom);
		if (prenom.length() == 0) {
			throw new ServiceException("Le client doit avoir un prenom non vide");
		} else if (prenom.length() <= 3) {
			throw new ServiceException("Un prenom ne peut pas être si court !");
		}
	}

	private void checkNom(Client client) throws ServiceException {
		String nom = client.getNom();
		System.out.println(nom);
		if (nom.length() == 0) {
			throw new ServiceException("Le client doit avoir un nom non vide");
		} else if (nom.length() <= 3) {
			throw new ServiceException("Un nom ne peut pas être si court !");
		}
	}


	public Client findById(int id) throws ServiceException {
		try {
			return clientDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return clientDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
