package co.edu.usbcali.cart.service;

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

import co.edu.usbcali.cart.domain.Customer;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTest {
	
	@Autowired
	CustomerService customerService;
	
	//Llave del Customer
	private final static String email="hjsimpson@gmail.com";	
	private final static Logger log=LoggerFactory.getLogger(CustomerServiceTest.class);
	
	@Test
	@Order(5)
	void count() {
		Long count=customerService.count();
		assertNotEquals(count, 0);
		log.info("Count:"+count);
	}
	
	@Test
	@Order(6)
	void findAll() {		
		customerService.findAll().forEach(customer->log.info(customer.getEmail()));		
	}

	@Test
	@Order(1)
	void save()throws Exception {
		assertNotNull(customerService, "El customerService es nulo");
		
		Customer customer=new Customer();
		customer.setEmail(email);
		customer.setAddress("Avenida Siempre Viva 123");
		customer.setEnable("Y");
		customer.setName("Homero J Simpson");
		customer.setPhone("555 555 5555");
		customer.setToken("382741809234kjhsdfjhasgfjhsd3");
		
		customerService.save(customer);		
	}
	
	@Test
	@Order(2)
	void findById() {
		Optional<Customer> customerOptional=customerService.findById(email);
		assertTrue(customerOptional.isPresent(),"El customer con email:"+email+" no existe");
	}
	
	@Test
	@Order(3)
	void update()throws Exception {
		Optional<Customer> customerOptional=customerService.findById(email);
		assertTrue(customerOptional.isPresent(),"El customer con email:"+email+" no existe");
		
		Customer customer=customerOptional.get();
		customer.setEnable("N");
		customerService.update(customer);
	}
	
	@Test
	@Order(4)
	void delete()throws Exception {
		Optional<Customer> customerOptional=customerService.findById(email);
		assertTrue(customerOptional.isPresent(),"El customer con email:"+email+" no existe");
		
		Customer customer=customerOptional.get();
		
		customerService.delete(customer);
	}
	
	
}
