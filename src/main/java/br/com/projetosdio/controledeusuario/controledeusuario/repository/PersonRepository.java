package br.com.projetosdio.controledeusuario.controledeusuario.repository;

import br.com.projetosdio.controledeusuario.controledeusuario.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
