package br.com.tokyo.TokyoCustomer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.tokyo.TokyoCustomer.model.Customer;
import br.com.tokyo.TokyoCustomer.utils.exceptions.CustomerDataException;
import br.com.tokyo.TokyoCustomer.utils.exceptions.CustomerNotFoundException;

@Service
public interface CustomerService {

	/**
	 * Must to validate the customer data, then persist it.
	 * 
	 * @param customer
	 */
	void save(Customer customer) throws CustomerDataException;

	/**
	 * Must to retrieve all existing customers.
	 * @return
	 */
	List<Customer> allFinalCustomers();

	/**
	 * Must to retrieve a customer by the given ID.
	 * @param id
	 * @return
	 */
	Customer getByID(Long id) throws CustomerNotFoundException;

	/**
	 * Must to retrieve a customer by the given email.
	 * @param email
	 * @return
	 */
	Customer getByEmail(String email) throws CustomerNotFoundException;

	/**
	 * Must to validate the customer data then, if valid update it.
	 * @param customer
	 */
	void update(Customer customer) throws CustomerDataException;

}
