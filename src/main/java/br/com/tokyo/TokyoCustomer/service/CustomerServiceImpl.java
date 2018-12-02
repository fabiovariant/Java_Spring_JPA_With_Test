package br.com.tokyo.TokyoCustomer.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tokyo.TokyoCustomer.model.Customer;
import br.com.tokyo.TokyoCustomer.repository.CustomerRepository;
import br.com.tokyo.TokyoCustomer.utils.exceptions.CustomerDataException;
import br.com.tokyo.TokyoCustomer.utils.exceptions.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@Override
	public void save(Customer customer) throws CustomerDataException {
		validateCustomer(customer);
		customerRepository.save(customer);
		if (customer.getId() == null) {
			throw new CustomerDataException("Error saving customer. No ID returned.");
		}
	}

	private void validateCustomer(Customer customer) throws CustomerDataException {
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		for (ConstraintViolation<Customer> violation : violations) {
			throw new CustomerDataException(violation.getMessage());
		}
	}

	@Override
	public List<Customer> allFinalCustomers() {
		return customerRepository.allFinalCustomers();
	}

	@Override
	public Customer getByID(Long id) throws CustomerNotFoundException {
		Customer customer = customerRepository.getByID(id);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found with ID: " + id);
		}
		return customer;
	}

	@Override
	public Customer getByEmail(String email) throws CustomerNotFoundException {
		Customer customer = customerRepository.getByEmail(email);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found with e-mail: " + email);
		}
		return customer;
	}

	@Override
	public void update(Customer customer) throws CustomerDataException {
		validateCustomer(customer);
		if(customer.getId() == null) {
			throw new CustomerDataException("Invalid ID to search. ID: " + customer.getId());
		}
		customerRepository.update(customer);
	}

}
