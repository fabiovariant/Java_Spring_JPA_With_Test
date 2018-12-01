package br.com.tokyo.TokyoCustomer.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="tb_customer_data")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime bday;
	
	@Column(unique=true)
	private String email;

	@Column(name="is_active")
	private Boolean isActive;
	
	@ManyToMany
	@JoinTable(name="tb_join_customer_event", 
		joinColumns=@JoinColumn(name="id_customer"), 
		inverseJoinColumns=@JoinColumn(name="id_event"))
	private List<Event> customerEvents = new ArrayList<>();

	public Customer() {
		
	}

	public Customer(Long id, String name, LocalDateTime bday, String email, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.bday = bday;
		this.email = email;
		this.isActive = isActive;
	}
	
	public Customer(String name, LocalDateTime bday, String email, Boolean isActive) {
		super();
		this.name = name;
		this.bday = bday;
		this.email = email;
		this.isActive = isActive;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getBday() {
		return bday;
	}
	public void setBday(LocalDateTime bday) {
		this.bday = bday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public List<Event> getCustomerEvents() {
		return customerEvents;
	}
	public void setCustomerEvents(List<Event> customerEvents) {
		this.customerEvents = customerEvents;
	}
	
}
