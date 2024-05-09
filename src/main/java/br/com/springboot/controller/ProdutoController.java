package br.com.springboot.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springboot.bo.ProdutoBO;
import br.com.springboot.model.Categoria;
import br.com.springboot.model.Produto;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	//declarar obj de negocio
	private ProdutoBO produtoBO;
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("produto", new Produto());
		model.addAttribute("categorias", Arrays.asList(Categoria.values()));
		return new ModelAndView("/produto/formulario", model);
	}
	
	@RequestMapping(value = "", method=RequestMethod.POST)
	public String salva(@Valid @ModelAttribute Produto produto, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors())
			return "produto/formulario";
		
		if (produto.getId() == null) {
			produtoBO.insere(produto);
			attr.addFlashAttribute("feedback", "Produto foi cadastrado com sucesso");
		}
		else { 
			produtoBO.atualiza(produto);
			attr.addFlashAttribute("feedback", "Produto foi atualizado com sucesso");
		}
		return "redirect:/produtos";
	}
	
	@RequestMapping(value = "", method=RequestMethod.GET)
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("produtos", produtoBO.lista());
		return new ModelAndView("/produto/lista", model);		
	}

	@RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
		try {
			model.addAttribute("produto", produtoBO.pesquisaPeloId(id));
			model.addAttribute("categorias", Arrays.asList(Categoria.values()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/produto/formulario", model);
	}
	
	@RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
	public String inativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		System.out.println(id);
		try {
			Produto produto = produtoBO.pesquisaPeloId(id); 
			produtoBO.inativa(produto);
			attr.addFlashAttribute("feedback", "Produto foi inativado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/produtos";
	}
	
	@RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
	public String ativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		System.out.println(id);
		try {
			Produto produto = produtoBO.pesquisaPeloId(id); 
			produtoBO.ativa(produto);
			attr.addFlashAttribute("feedback", "Produto foi ativado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/produtos";
	}

}
