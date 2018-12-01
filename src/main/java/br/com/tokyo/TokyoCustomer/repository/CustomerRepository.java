package br.com.tokyo.TokyoCustomer.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.tokyo.TokyoCustomer.model.Customer;

@Repository
public interface CustomerRepository {

    /**
     * Must to persist a customer into database.
     * @param customer
     */
    void save(Customer customer);
	
    /**
     * Must to retrieve all customer from database.
	 * @return
	 */
	List<Customer> allFinalCustomers();
	
	/**
	 * Must to return a customer by the given ID.
	 * @param id
	 * @return
	 */
	Customer getByID(Long id);
	
	/**
	 * Must to returna  customer by the given e-mail.
	 * @param email
	 * @return
	 */
	Customer getByEmail(String email);
	
	/**
	 * Must to update a existent customer data.
	 * @param customer
	 */
	void update(Customer customer);
	
}
