package br.com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springboot.bo.NotaEntradaBO;
import br.com.springboot.bo.NotaEntradaItemBO;
import br.com.springboot.bo.ProdutoBO;
import br.com.springboot.model.NotaEntrada;
import br.com.springboot.model.NotaEntradaItem;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/nota-entrada-item")
public class NotaEntradaItemController {
	
	//classes de negocios
	@Autowired
	private ProdutoBO produtoBO;
	
	@Autowired
	private NotaEntradaBO notaEntradaBO;
	
	@Autowired
	private NotaEntradaItemBO notaEntradaItemBO;
	
	
	//metodos
	@RequestMapping(value="", method=RequestMethod.POST)
	public String salva(@Valid @ModelAttribute NotaEntradaItem notaEntradaItem,
			BindingResult result,
			RedirectAttributes attr,
			ModelMap model) {
		
		Long produtoId = notaEntradaItem.getProduto().getId();
		if ( produtoId == null) {
			result.rejectValue("produto", "field.required");
		}
		//validacao do produto
		
		
		//validacao duplicidade
		
		if (result.hasErrors()) {
			model.addAttribute("produtos", produtoBO.lista());
				return "/nota-entrada-item/formulario";
			
		}
		 NotaEntrada notaEntrada = notaEntradaBO.pesquisaPeloId(notaEntradaItem.getNotaEntrada().getId());
		 notaEntradaItem.setNotaEntrada(notaEntrada);
		 
		 if (notaEntradaItem.getId() == null) {
			 notaEntradaItemBO.insere(notaEntradaItem);
			 attr.addFlashAttribute("feedback", "Produto adicionado com sucesso!");
		 }else {
			 notaEntradaItemBO.atualiza(notaEntradaItem);
			 attr.addFlashAttribute("feedback", "Produto atualizado com sucesso!");
		 }
		 
		 Long notaEntradaId = notaEntradaItem.getNotaEntrada().getId();
		 return "redirect:/nota-entrada-edita/" + notaEntradaId;
		
	}

}
