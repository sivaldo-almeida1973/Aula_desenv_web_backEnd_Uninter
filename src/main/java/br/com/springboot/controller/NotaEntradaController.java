package br.com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springboot.bo.FornecedorBO;
import br.com.springboot.bo.NotaEntradaBO;
import br.com.springboot.model.NotaEntrada;
import jakarta.validation.Valid;

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
	
	//salvar no banco de dados
	@RequestMapping(value="", method=RequestMethod.POST)
	public String salva(@Valid @ModelAttribute NotaEntrada notaEntrada,
			BindingResult result, RedirectAttributes attr) {
		//se existir algum erro , retorna para formulario nota-entrada
		if (result.hasErrors()) {
			return "/nota-entrada/formulario";
		}//regra para salvar usuario
		if (notaEntrada.getId() == null) {//se for nulo, isere na notaEntrada
			notaEntradaBO.insere(notaEntrada);
			attr.addFlashAttribute("feedback", "Os notas de entrada foram cadastradas com sucesso!");

		}else {//se não for nula, atulaizar notaEntrada
			notaEntradaBO.atualiza(notaEntrada);
			attr.addFlashAttribute("feedback", "Os da nota de entrada foram atualizados com sucesso!");
		}
		
		return "redirect:/nota-entrada";//assim que savar, volta para url nota-entrada
	
		
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("notas", notaEntradaBO.lista());
		return new ModelAndView("/nota-entrada/lista", model);
	}
	
	
	

}
