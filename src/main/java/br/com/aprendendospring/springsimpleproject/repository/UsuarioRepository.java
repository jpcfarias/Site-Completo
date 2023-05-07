package br.com.aprendendospring.springsimpleproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.aprendendospring.springsimpleproject.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    @Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1% order by id")
    List<Usuario> buscarNome(String nome);
}
