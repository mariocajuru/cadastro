package br.com.mario.cadastro.rn;

import java.util.List;

import br.com.mario.cadastro.dao.EMailDAO;
import br.com.mario.cadastro.modelo.EMail;
import br.com.mario.cadastro.modelo.Pessoa;
import br.com.mario.cadastro.util.DAOFactory;


public class EMailRN {
	private EMailDAO eMailDAO;

	public EMailRN() {
		this.eMailDAO = DAOFactory.criarEMailDAO();
	}

	public EMail carregar(Integer eMail) {
		return this.eMailDAO.carregar(eMail);

	}

	public EMail buscarPorEMail(String eMail) {
		return this.eMailDAO.buscarPorEMail(eMail);
	}

	public void salvar(EMail eMail) {
		Integer codigo = eMail.getMaiId();
		if (codigo == null || codigo == 0) {
			this.eMailDAO.salvar(eMail);
		} else {
			this.eMailDAO.atualizar(eMail);
		}

	}

	public void excluir(EMail eMail) {		
		this.eMailDAO.excluir(eMail);		
	}

	public List<EMail> listar() {
		return this.eMailDAO.listar();
	}

	public List<EMail> carregarEmailsPorPessoa(Pessoa pessoa) {
		return this.eMailDAO.carregarEmailsPorPessoa(pessoa);
	}

	public void excluirTodosEmailsPorPessoa(Pessoa pessoa){
		if(pessoa.getPesId() != 0){
			for(EMail eMail: carregarEmailsPorPessoa(pessoa)){
				excluir(eMail);
			}
		}
	}
}
