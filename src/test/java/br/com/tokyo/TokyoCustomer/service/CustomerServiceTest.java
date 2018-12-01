package br.com.tokyo.TokyoCustomer.service;

import static br.com.tokyo.TokyoCustomer.TestUtils.getValidNewCustomer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.tokyo.TokyoCustomer.model.Customer;
import br.com.tokyo.TokyoCustomer.repository.CustomerRepository;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@TestConfiguration
	static class CustomerServiceTestContextConfiguration {
		
		@Bean
		public CustomerService customerService() {
			return new CustomerServiceImpl();
		}
		
	}

	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@Test
	public void mustToSaveCustomer() {
		Customer customer = getValidNewCustomer();
		Mockito.doNothing().when(customerRepository).save(customer);
		
		customerService.save(customer);
		
		
	}
}
