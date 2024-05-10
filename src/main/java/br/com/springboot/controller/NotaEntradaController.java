package br.com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.springboot.bo.FornecedorBO;
import br.com.springboot.bo.NotaEntradaBO;
import br.com.springboot.model.NotaEntrada;

@Controller
@RequestMapping("/nota-entrada")
public class NotaEntradaController {
	
	@Autowired
	private NotaEntradaBO notaEntradaBO;
	
	@Autowired
	private FornecedorBO fornecedorBO;
	
	@RequestMapping(value="/novo", method=RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		//Long fornecedorId = null;
		//model.addAttribute("fornecedorId", fornecedorId);
		model.addAttribute("notaEntrada", new NotaEntrada());
		model.addAttribute("fornecedores", fornecedorBO.lista());
		return new ModelAndView("/nota-entrada/formulario", model);
		
	}

}
