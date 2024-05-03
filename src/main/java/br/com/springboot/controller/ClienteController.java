package br.com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.springboot.bo.ClienteBO;
import br.com.springboot.model.Cliente;

@Controller
@RequestMapping("clientes")
public class ClienteController {
	
	@Autowired
	private ClienteBO bo;
	
	//exibir form
	@RequestMapping(value = "/novo" , method = RequestMethod.GET)//reqisicao
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("cliente" ,new Cliente());
		return new ModelAndView("/cliente/formulario", model);
		
	}
	

}
