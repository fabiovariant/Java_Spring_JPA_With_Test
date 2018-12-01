package br.com.tokyo.TokyoCustomer.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.tokyo.TokyoCustomer.model.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void mustToSaveCustomer_thenFindByID() {
		Customer customer = getValidNewCustomer();
		customerRepository.save(customer);
		Customer repoCustomer = entityManager.find(Customer.class, customer.getId());
		assertEquals(customer, repoCustomer);
		entityManager.detach(customer);
	}
	
	@Test
	public void mustToListAllCustomers() {
		Customer customer = getValidNewCustomer();
		entityManager.persist(customer);
		
		List<Customer> customerList = customerRepository.allFinalCustomers();
		assertThat(customerList, contains(hasProperty("name", is(customer.getName()))));
		entityManager.detach(customer);
	}
	
	@Test
	public void mustToFindByID() {
		Customer customer = getValidNewCustomer();
		entityManager.persist(customer);
		entityManager.flush();
		Customer repoCustomer = customerRepository.getByID(customer.getId());
		assertEquals(customer, repoCustomer);
		entityManager.detach(customer);
	}
	
	@Test
	public void mustToFindByEmail() {
		Customer customer = getValidNewCustomer();
		entityManager.persist(customer);
		Customer repoCustomer = customerRepository.getByEmail(customer.getEmail());
		assertEquals(customer, repoCustomer);
		entityManager.detach(customer);
	}
	
	@Test
	public void mustToUpdateCustomer() {
		Customer customer = getValidNewCustomer();
		entityManager.persist(customer);
		entityManager.flush();
		
		customer.setName("New Fantastic Name");
		customerRepository.update(customer);
		
		Customer repoCustomer = entityManager.find(Customer.class, customer.getId());
		assertEquals(customer.getName(), repoCustomer.getName());
		entityManager.detach(customer);
	}
	
 	private Customer getValidNewCustomer() {
		return new Customer(
				"Alex", 
				LocalDateTime.now().minusYears(18), 
				"fabiovariant@hotmail.com", 
				true);
	}
}
