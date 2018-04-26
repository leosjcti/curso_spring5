package com.boldadeideias.springboot.app.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boldadeideias.springboot.app.models.entities.Cliente;
import com.boldadeideias.springboot.app.models.entities.Fatura;
import com.boldadeideias.springboot.app.models.entities.ItemFatura;
import com.boldadeideias.springboot.app.models.entities.Produto;
import com.boldadeideias.springboot.app.service.IClienteService;

@Controller
@RequestMapping("/fatura")
@SessionAttributes("fatura")
public class FaturaController {

	@Autowired
	IClienteService clienteService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/form/{clienteId}")
	public String criar(@PathVariable(value="clienteId") Long clienteId, 
			Map<String, Object> model,
			RedirectAttributes flash,
			SessionStatus status) {
		
		Cliente cliente = clienteService.findOne(clienteId);
		
		if(cliente == null) {
			flash.addAttribute("error", "Esse cliente nao existe");
			return "redirect:/listar";
		}
		
		//Relaciona um cliente a uma fatura.
		Fatura fatura = new Fatura();
		fatura.setCliente(cliente);
		
		//Coloca o objeto fatura no modelo para enviar para a view
		model.put("fatura", fatura);
		model.put("titulo", "Criar Fatura");
		
		return "fatura/form";
	}
	
	
	
	@GetMapping(value="/carregar_produtos/{term}", produces= {"application/json"})
	public @ResponseBody List<Produto> carregarProduto(@PathVariable String term){
		return clienteService.buscarPorNome(term);
	}
	
	
	@PostMapping("/form")
	public String guardar(Fatura fatura,
			@RequestParam(name="item_id[]", required=false) Long[] itemId,
			@RequestParam(name="item_id[]", required=false) Integer[] quantidade,
			RedirectAttributes flash,
			SessionStatus status) {
			
			for (int i = 0; i < itemId.length; i++) {
				Produto produto = clienteService.findProdutoById(itemId[i]);
				
				ItemFatura itemFatura = new ItemFatura();
				itemFatura.setQtd(quantidade[i]);
				itemFatura.setProduto(produto);
				
				fatura.addItemFatura(itemFatura);
				
				log.info("ID: " +itemId[i].toString() + " Qtd:"+ itemId[i].toString());
				
			}
			
			clienteService.saveFatura(fatura);
			status.setComplete();
			
			log.info("CHEGGGGGUUUUUEEEEEIIIII");
			
			flash.addAttribute("success", "Fatura Criada");
			return "redirect:/ver/" + fatura.getCliente().getId();
		
	}
	
}
