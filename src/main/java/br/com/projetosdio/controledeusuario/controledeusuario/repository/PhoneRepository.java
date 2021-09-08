package br.com.projetosdio.controledeusuario.controledeusuario.repository;

import br.com.projetosdio.controledeusuario.controledeusuario.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
