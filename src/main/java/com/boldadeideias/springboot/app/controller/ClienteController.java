package com.boldadeideias.springboot.app.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boldadeideias.springboot.app.models.entities.Cliente;
import com.boldadeideias.springboot.app.service.IClienteService;
import com.boldadeideias.springboot.app.service.IUploadFileService;
import com.boldadeideias.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IUploadFileService uploadFileService;

	// Metodo que carrega uma foto de outra maneira
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			flash.addAttribute("error", "O cliente nao existe");
			return "redirect:/listar";
		}

		model.put("cliente", cliente);
		model.put("titulo", "Detalhe Cliente " + cliente.getNome());

		return "ver";
	}
	

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);

		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	
	@RequestMapping(value = "/form")
	public String criar(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}

	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteService.findOne(id);

			if (cliente == null) {
				flash.addFlashAttribute("error", "O ID do cliente não existe no BD");
				return "redirect:/listar";
			}

		} else {
			flash.addFlashAttribute("error", "O ID do cliente não pode ser zero");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}
	

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String salvar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status, @RequestParam("file") MultipartFile foto) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}

		if (!foto.isEmpty()) {
			// Path diretorioRecursos = Paths.get("src//main//resources//static/uploads");
			// String rootPath = diretorioRecursos.toFile().getAbsolutePath();
			// String rootPath = "C://Spring5//uploads";

			// Exclui a foto antiga antes de colocar a nova
			if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null
					&& cliente.getFoto().length() > 0) {

				uploadFileService.delete(cliente.getFoto());
			}

			String uniqueFileName = null;
			try {
				uniqueFileName = uploadFileService.copy(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Foto Carregada '" + uniqueFileName + "'");
			cliente.setFoto(uniqueFileName);
		}

		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", "Cliente criado com sucesso");
		return "redirect:listar";
	}

	@RequestMapping(value = "/deletar/{id}")
	public String deletar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		
		if (id > 0) {

			Cliente cliente = clienteService.findOne(id);
			
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente excluido com sucesso");
			
			

			if (uploadFileService.delete(cliente.getFoto())) {
				flash.addFlashAttribute("info", cliente.getFoto()+" excluida");
			}
		}
		return "redirect:/listar";
	}
}
