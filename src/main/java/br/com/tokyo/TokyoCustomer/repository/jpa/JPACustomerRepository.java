package br.com.tokyo.TokyoCustomer.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.tokyo.TokyoCustomer.model.Customer;
import br.com.tokyo.TokyoCustomer.repository.CustomerRepository;

@Repository
public class JPACustomerRepository implements CustomerRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void save(Customer customer) {
		entityManager.persist(customer);
		entityManager.flush();
	}

	@Override
	public List<Customer> allFinalCustomers() {
		return entityManager.createQuery("select c from Customer c", 
				Customer.class).getResultList();
	}

	@Override
	public Customer getByID(Long id) {
		return entityManager.find(Customer.class, id);
	}

	@Override
	public Customer getByEmail(String email) {
		return entityManager
				.createQuery("select c from Customer c WHERE c.email = :email", 
						Customer.class)
				.setParameter("email", email)
				.getSingleResult();
	}

	@Override
	public void update(Customer customer) {
		entityManager.merge(customer);
	}

}
