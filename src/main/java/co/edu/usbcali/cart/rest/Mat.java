package co.edu.usbcali.cart.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mat")
public class Mat {

	@GetMapping("/sum/{n1}/{n2}")
	public Resultado sum(@PathVariable("n1") Integer n1, @PathVariable("n2") Integer n2) {
		return new Resultado(n1 + n2);
	}

	@PostMapping("/res")
	public Resultado res(@RequestBody Operador operador) {
		return new Resultado(operador.getN1() - operador.getN2());
	}

}

class Operador {

	Integer n1;
	Integer n2;

	public Integer getN1() {
		return n1;
	}

	public void setN1(Integer n1) {
		this.n1 = n1;
	}

	public Integer getN2() {
		return n2;
	}

	public void setN2(Integer n2) {
		this.n2 = n2;
	}
}