package br.com.springboot.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.springboot.dao.CRUD;
import br.com.springboot.dao.NotaEntradaItemDAO;
import br.com.springboot.model.NotaEntradaItem;

@Service
public class NotaEntradaItemBO implements  CRUD<NotaEntradaItem, Long>{

	private NotaEntradaItemDAO dao;
	
	@Override
	public NotaEntradaItem pesquisaPeloId(Long id) {
		return dao.pesquisaPeloId(id);
	}

	@Override
	public List<NotaEntradaItem> lista() {
		return dao.lista();
	}

	@Override
	public void insere(NotaEntradaItem notaEntradaItem) {
        dao.insere(notaEntradaItem);
	}

	@Override
	public void atualiza(NotaEntradaItem notaEntradaItem) {
        dao.atualiza(notaEntradaItem);		
	}

	@Override
	public void remove(NotaEntradaItem notaEntradaItem) {
		dao.remove(notaEntradaItem);	
	}

}
