package br.com.casadocodigo.loja.controllers;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.casadocodigo.loja.models.PaymentData;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.models.User;


@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private MailSender mailer;
	
	
	@Autowired
	private ShoppingCart shoppingCart;
	@Autowired
	private RestTemplate restTemplate;



	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public Callable<String> checkout(@AuthenticationPrincipal User user) {
	return () -> {
	BigDecimal total = shoppingCart.getTotal();
	String uriToPay ="http://book-payment.herokuapp.com/payment"; //"http://localhost:9000/payment";
	try {
	String response =
	restTemplate.postForObject(uriToPay,
	new PaymentData(total), String.class);
	//enviando email
	sendNewPurchaseMail(user);
	return "redirect:/payment/success";
	} catch (HttpClientErrorException exception) {
	return "redirect:/payment/error";
	}
	};
	}

	
	private void sendNewPurchaseMail(User user) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("magzoaa@gmail.com");//compras@casadocodigo.com.br
		email.setTo(user.getLogin());
		email.setSubject("Nova compra");
		email.setText("corpo do email");
		mailer.send(email);
		}


}
