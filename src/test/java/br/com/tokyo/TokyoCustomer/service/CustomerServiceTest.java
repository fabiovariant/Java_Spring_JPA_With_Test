package br.com.tokyo.TokyoCustomer.service;

import static br.com.tokyo.TokyoCustomer.TestUtils.getValidNewCustomer;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.tokyo.TokyoCustomer.model.Customer;
import br.com.tokyo.TokyoCustomer.repository.CustomerRepository;
import br.com.tokyo.TokyoCustomer.utils.exceptions.CustomerDataException;
import br.com.tokyo.TokyoCustomer.utils.exceptions.CustomerNotFoundException;
import br.com.tokyo.TokyoCustomer.utils.exceptions.DatabaseException;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
		
	@TestConfiguration
	static class CustomerServiceTestContextConfiguration {
		
		@Bean
		public CustomerService customerService() {
			return new CustomerServiceImpl();
		}
		
	}

	@Test
	public void mustToSaveCustomer() throws CustomerDataException {
		Customer customer = getValidNewCustomer();
		Mockito.doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				((Customer) args[0]).setId(10L);
				return null;
			}}).when(customerRepository).save(customer);
		customerService.save(customer);
	}
	
	@Test(expected=CustomerDataException.class)
	public void mustToThrowExceptionOnNoID() throws CustomerDataException {
		Customer customer = getValidNewCustomer();
		Mockito.doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				((Customer) args[0]).setId(null);
				return null;
			}}).when(customerRepository).save(customer);
		customerService.save(customer);
	}
	
	@Test(expected=DatabaseException.class)
	public void mustToGetRepositoryError() throws CustomerDataException {
		Customer customer = getValidNewCustomer();
		Mockito.doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				throw new DatabaseException("Oh no!!");
			}}).when(customerRepository).save(customer);
		customerService.save(customer);
	}
	
	@Test
	public void mustToNotSave_ifNoName() {
		try {
			Customer customer = getValidNewCustomer();
			customer.setName("");
			customerService.save(customer);
		} catch (CustomerDataException e) {
			final String msg = "The name must to have min size of 1 character.";
			assertEquals(msg, e.getMessage());
		}
	}
	
	@Test
	public void mustToListAllCustomer() {
		List<Customer> customerList = new ArrayList<>();
		customerList.add(getValidNewCustomer());
		Mockito.when(customerRepository.allFinalCustomers())
			.thenReturn(customerList);
		List<Customer> returnedList = customerService.allFinalCustomers();
		assertEquals(customerList, returnedList);
	}

	@Test
	public void mustToGetByID() throws CustomerNotFoundException {
		Customer customer = getValidNewCustomer();
		Mockito.when(customerRepository.getByID(customer.getId()))
			.thenReturn(customer);
		Customer rCustomer = customerService.getByID(customer.getId());
		assertEquals(customer, rCustomer);
	}
	
	@Test(expected=CustomerNotFoundException.class)
	public void mustToThrowsIfNotFoundByID() throws CustomerNotFoundException {
		final Long id = 10L;
		Mockito.when(customerRepository.getByID(id))
			.thenReturn(null);
		customerService.getByID(id);
	}
	
	@Test
	public void mustToGetByEmail() throws CustomerNotFoundException {
		Customer customer = getValidNewCustomer();
		Mockito.when(customerRepository.getByEmail(customer.getEmail()))
			.thenReturn(customer);
		Customer rCustomer = customerService.getByEmail(customer.getEmail());
		assertEquals(customer, rCustomer);
	}
	
	@Test(expected=CustomerNotFoundException.class)
	public void mustToThrowsIfNotFoundByEmail() throws CustomerNotFoundException {
		final String email = "fabiovariant@hotmail.com";
		Mockito.when(customerRepository.getByEmail(email))
			.thenReturn(null);
		customerService.getByEmail(email);
	}
	
	@Test
	public void mustToUpdate() throws CustomerDataException, CustomerNotFoundException {
		Customer customer = getValidNewCustomer();
		Mockito.doNothing()
			.when(customerRepository)
			.update(customer);;
		customerService.update(customer);
	}
	
	@Test(expected=CustomerDataException.class)
	public void mustToNotUpdate_andGetNotFoundException() throws CustomerDataException {
		Customer customer = getValidNewCustomer();
		customer.setName("");
		Mockito.verify(customerRepository, Mockito.never())
			.update(customer);
		customerService.update(customer);
	}
	
	@Test(expected=CustomerDataException.class)
	public void mustToNotUpdate_ifNoID_andGetNotFoundException() throws CustomerDataException {
		Customer customer = getValidNewCustomer();
		customer.setId(null);
		Mockito.verify(customerRepository, Mockito.never())
			.update(customer);
		customerService.update(customer);
	}

}
