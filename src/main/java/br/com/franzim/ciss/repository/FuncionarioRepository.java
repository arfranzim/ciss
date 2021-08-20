package br.com.franzim.ciss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.franzim.ciss.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
