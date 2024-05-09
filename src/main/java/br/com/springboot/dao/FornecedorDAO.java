package br.com.springboot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.springboot.model.Fornecedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository          //classe de acesso a dados
@Transactional       //
public class FornecedorDAO implements CRUD<Fornecedor, Long>{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Fornecedor pesquisaPeloId(Long id) {
		return em.find(Fornecedor.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> lista() {
		jakarta.persistence.Query query = em.createQuery("select f Fornecedor f");
		return query.getResultList();
	}

	@Override
	public void insere(Fornecedor fornecedor) {
		em.persist(fornecedor);
		
	}

	@Override
	public void atualiza(Fornecedor fornecedor) {
		em.merge(fornecedor);
		
	}

	@Override
	public void remove(Fornecedor fornecedor) {
		em.remove(fornecedor);
		
	}

}
