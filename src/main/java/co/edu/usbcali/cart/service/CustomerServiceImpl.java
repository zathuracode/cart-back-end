package co.edu.usbcali.cart.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.cart.domain.Customer;
import co.edu.usbcali.cart.repository.CustomerRepository;

@Service
@Scope("singleton")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	Validator validator;

	@Override
	@Transactional(readOnly = true)
	public Optional<Customer> findById(String id) {
		return customerRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return customerRepository.count();
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Customer save(Customer customer) throws Exception {
		
		if(customer==null) {
			throw new Exception("El customer no puede nulo");
		}
		
		validate(customer);
		
		if(customerRepository.existsById(customer.getEmail())==true) {
			throw new Exception("El customer con id:"+customer.getEmail()+" ya existe");
		}
		
		return customerRepository.save(customer);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Customer update(Customer customer) throws Exception {
		if(customer==null) {
			throw new Exception("El customer no puede nulo");
		}
		
		validate(customer);
		
		if(customerRepository.existsById(customer.getEmail())==false) {
			throw new Exception("El customer con id:"+customer.getEmail()+" no existe");
		}
		
		return customerRepository.save(customer);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(Customer customer) throws Exception {
		if(customer==null) {
			throw new Exception("El customer no puede nulo");
		}
		
		if(customer.getEmail()==null || customer.getEmail().isBlank()==true) {
			throw new Exception("El email es obligatorio ");
		}
		
		if(customerRepository.existsById(customer.getEmail())==false) {
			throw new Exception("El customer con id:"+customer.getEmail()+" no existe");
		}
		
		customerRepository.findById(customer.getEmail()).ifPresent(entity->{
			if(entity.getShoppingCarts()!=null && entity.getShoppingCarts().isEmpty()==false) {
				throw new RuntimeException("El customer tiene ShoppingCarts asociados");
			}
		});
		
		customerRepository.deleteById(customer.getEmail());

	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		if(id==null) {
			throw new Exception("El id no puede ser nulo");
		}
		if(customerRepository.existsById(id)==false) {
			throw new Exception("El customer con id:"+id+" no existe");
		}
		delete(customerRepository.findById(id).get());

	}

	@Override
	public void validate(Customer customer) throws ConstraintViolationException {
		
		Set<ConstraintViolation<Customer>> constraintViolations=validator.validate(customer);
		if(constraintViolations.isEmpty()==false) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}	

}