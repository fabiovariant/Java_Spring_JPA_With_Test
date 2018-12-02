package br.com.tokyo.TokyoCustomer;

import java.time.LocalDateTime;

import br.com.tokyo.TokyoCustomer.model.Customer;

public class TestUtils {

	
 	public static Customer getValidNewCustomer() {
		return new Customer(
				10L,
				"Alex", 
				LocalDateTime.now().minusYears(18), 
				"fabiovariant@hotmail.com", 
				true);
	}
}
