package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProductDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;
import br.com.casadocodigo.loja.models.ShoppingCart;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private FileSaver fileSaver;
	
//	@Autowired
//	private ShoppingCart shoppingCart;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
//binder.setValidator(new ProductValidator());		
	}
	//Part summary
	//@ModelAttribute("objetoAtual")
	@RequestMapping(method=RequestMethod.POST,name="saveProduct")
	@CacheEvict(value="books", allEntries=true)
	public ModelAndView save(MultipartFile summary,@ModelAttribute("product") @Valid  Product product,BindingResult bindingResult,
			RedirectAttributes redirectAttributes){
		
//		System.out.println(summary.getName() + ";"
//				+summary.getHeader("content-disposition"));
		
		System.out.println(summary.getName() + ";"
				+summary.getOriginalFilename());
		
	if(bindingResult.hasErrors()){
		//return "produtos/form";
		System.out.println("Encontre algun error");
		return form(product);
	}
	
	String webPath =fileSaver.write("uploaded-images",summary);
			product.setSummaryPath(webPath);
		
		
		productDao.save(product);
		redirectAttributes.addFlashAttribute("sucesso","Producto cadastrado con exito");
		//return "redirect:produtos";
		return new ModelAndView("redirect:produtos");
	}
	
	
//	@RequestMapping(method=RequestMethod.POST)
//	public ModelAndView save(Product product){
//	productDao.save(product);
//	ModelAndView modelAndView=new ModelAndView("redirect:produtos");
//	modelAndView.addObject("sucesso","Producto cadastrado con suceso");
//	return modelAndView;
//	}
	
//	@RequestMapping("/produtos/form")
//	public String form(){
//		return "products/form";
//	}
//	
	
	@RequestMapping("/form")
	public ModelAndView  form(Product product){
ModelAndView modelAndView =
	new ModelAndView("products/form");
	modelAndView.addObject("types", BookType.values());
	return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@Cacheable(value="books")
	public ModelAndView list(){
	ModelAndView modelAndView =new ModelAndView("products/list");
	//System.out.println("Lista "+productDao.list().get(0));
	modelAndView.addObject("products", productDao.list());
	return modelAndView;
	}
	
	
	@RequestMapping("/{id}")
	public ModelAndView show(@PathVariable("id") Integer id){
		ModelAndView modelAndView =
				new ModelAndView("products/show");
				Product product = productDao.find(id);
				modelAndView.addObject("product", product);
				return modelAndView;
				}
//	
//	
//	@RequestMapping(method=RequestMethod.POST)
//			public ModelAndView add(Integer productId,BookType bookType){
//			ShoppingItem item = createItem(productId, bookType);
//			shoppingCart.add(item);
//			return new ModelAndView("redirect:/produtos");
//			}
//	
//	
//			private ShoppingItem createItem(Integer productId,
//			BookType bookType) {
//			Product product = productDao.find(productId);
//			ShoppingItem item = new ShoppingItem(product,bookType);
//			return item;
//			}
	
	
	
//	@RequestMapping(method=RequestMethod.GET,value="/show")
//	public ModelAndView show(Integer id){
//	ModelAndView modelAndView =
//	new ModelAndView("products/show");
//	Product product = productDao.find(id);
//	modelAndView.addObject("product", product);
//	return modelAndView;
//	}
	
//	USADO PARA OBTENER UNA RESPUESTA EN JSON
//	@RequestMapping(method = RequestMethod.GET,value="json")
//	@ResponseBody
//	public List<Product> listJson() {
//	return productDao.list();
//	}

}
