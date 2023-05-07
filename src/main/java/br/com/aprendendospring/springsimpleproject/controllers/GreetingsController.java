package br.com.aprendendospring.springsimpleproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.aprendendospring.springsimpleproject.repository.UsuarioRepository;
import br.com.aprendendospring.springsimpleproject.model.Usuario;

@RestController
public class GreetingsController {
    
	@Autowired
	private UsuarioRepository usuarioRepository;

    /* @GetMapping("/")
	public String index() {
		return "Pagina inicial!";
	} */

    @GetMapping("/mostrarnome/{name}")
	public String mostrarNome(@PathVariable String name) {

		Usuario usuario = new Usuario();
		usuario.setNome(name);

		usuarioRepository.save(usuario);

		return "Hello " + name + "!";
	}

	@GetMapping(value = "listausuarios")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuarios(){
		List<Usuario> usuarios = usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@PostMapping(value = "salvar")
	@ResponseBody
	public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
		
		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario){
		
		if(usuario.getId() == null){
			return new ResponseEntity<String>("O id nao foi informado", HttpStatus.OK);
		}

		Usuario user = usuarioRepository.saveAndFlush(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> deletarUsuario(@RequestParam Long iduser){
		usuarioRepository.deleteById(iduser);
		return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
	}

	@GetMapping(value = "consultarid")
	@ResponseBody
	public ResponseEntity<Usuario> buscarUsuario(@RequestParam Long iduser){
		
		Usuario usuario = usuarioRepository.findById(iduser).get();

		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}

	@GetMapping(value = "consultar")
	@ResponseBody
	public ResponseEntity<List<Usuario>> buscarNome(@RequestParam(name = "nome") String nome){
		
		List<Usuario> usuarios = usuarioRepository.buscarNome(nome.trim().toUpperCase());

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.CREATED);
	}

}
