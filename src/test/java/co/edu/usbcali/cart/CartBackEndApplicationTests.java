package co.edu.usbcali.cart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class CartBackEndApplicationTests {

	@Test
	void contextLoads() {
		
		String vacia=" ";
		
		System.out.println(vacia.isBlank());
		System.out.println(vacia.trim().isEmpty());
	
	}

}
