
package loja.online.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import loja.online.domain.Cliente;

// O MongoRepository já possui todos os métodos de CRUD tradicionais.
// Mas é possível aqui incluir mais métodos. No caso, de busca pelo CPF do Cliente.

@Repository
public interface IClienteRepository extends MongoRepository<Cliente, String>{

	Optional<Cliente> findByCpf(Long cpf); // Usar "findBy" mesmo, que adiciona automaticamente com base no campo.
	Optional<Cliente> deleteByCpf(Long cpf);
}
