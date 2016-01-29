package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProductDao;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.models.ShoppingItem;



@Controller
@RequestMapping("/shopping")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ShoppingCartController {
	
	@Autowired
	private ProductDao productDAO;
	@Autowired
	private ShoppingCart shoppingCart;
	
	
	@RequestMapping(method=RequestMethod.POST)	
	public ModelAndView add(Integer productId,@RequestParam BookType bookType){
		ShoppingItem item = createItem(productId, bookType);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/shopping");
	}

	private ShoppingItem createItem(Integer productId, BookType bookType) {
		Product product = productDAO.find(productId);
		ShoppingItem item = new ShoppingItem(product,bookType);
		return item;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String items(){
		return "shoppingCart/items";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/{productId}")
	public String remove(@PathVariable("productId") Integer productId,BookType bookType){
		shoppingCart.remove(createItem(productId, bookType));
		return "redirect:/shopping";
	}

	

}
