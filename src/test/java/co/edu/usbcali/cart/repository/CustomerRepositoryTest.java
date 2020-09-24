package co.edu.usbcali.cart.repository;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.cart.domain.Customer;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepository;
	
	//Llave del Customer
	private final static String email="hjsimpson@gmail.com";
	
	private final static Logger log=LoggerFactory.getLogger(CustomerRepositoryTest.class);
	
	@Test
	@Transactional
	@Order(5)
	void count() {
		Long count=customerRepository.count();
		assertNotEquals(count, 0);
		log.info("Count:"+count);
	}
	
	@Test
	@Transactional
	@Order(6)
	void findAll() {
		
		customerRepository.findAll().forEach(customer->log.info(customer.getEmail()));
		
	}

	@Test
	@Transactional
	@Order(1)
	void save() {
		assertNotNull(customerRepository, "El customerRepository es nulo");
		
		Customer customer=new Customer();
		customer.setEmail(email);
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setEnable("Y");
		customer.setName("Homero J Simpson");
		customer.setPhone("555 555 5555");
		customer.setToken("382741809234kjhsdfjhasgfjhsd3");
		
		customerRepository.save(customer);		
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		Optional<Customer> customerOptional=customerRepository.findById(email);
		assertTrue(customerOptional.isPresent(),"El customer con email:"+email+" no existe");
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		Optional<Customer> customerOptional=customerRepository.findById(email);
		assertTrue(customerOptional.isPresent(),"El customer con email:"+email+" no existe");
		
		Customer customer=customerOptional.get();
		customer.setEnable("N");
		customerRepository.save(customer);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		Optional<Customer> customerOptional=customerRepository.findById(email);
		assertTrue(customerOptional.isPresent(),"El customer con email:"+email+" no existe");
		
		Customer customer=customerOptional.get();
		
		customerRepository.delete(customer);
	}
	
	
}
