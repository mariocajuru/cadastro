package br.com.mario.cadastro.rn;

import java.util.List;

import br.com.mario.cadastro.dao.TelefoneDAO;
import br.com.mario.cadastro.modelo.Pessoa;
import br.com.mario.cadastro.modelo.Telefone;
import br.com.mario.cadastro.util.DAOFactory;


public class TelefoneRN {
	private TelefoneDAO telefoneDAO;

	public TelefoneRN() {
		this.telefoneDAO = DAOFactory.criarTelefoneDAO();
	}

	public Telefone carregar(Integer telefone) {
		return this.telefoneDAO.carregar(telefone);

	}

	public Telefone buscarPorTelefone(String telefone) {
		return this.telefoneDAO.buscarPorTelefone(telefone);
	}

	public void salvar(Telefone telefone) {
		Integer codigo = telefone.getTelId();
		if (codigo == null || codigo == 0) {
			this.telefoneDAO.salvar(telefone);
		} else {
			this.telefoneDAO.atualizar(telefone);
		}

	}

	public void excluir(Telefone telefone) {		
		this.telefoneDAO.excluir(telefone);		
	}

	public List<Telefone> listar() {
		return this.telefoneDAO.listar();
	}

	public List<Telefone> carregarTelefonesPorPessoa(Pessoa pessoa) {
		return this.telefoneDAO.carregarTelefonesPorPessoa(pessoa);
	}

	public void excluirTodosTelefonesPorPessoa(Pessoa pessoa){
		if(pessoa.getPesId() != 0){
			for(Telefone telefone: carregarTelefonesPorPessoa(pessoa)){
				excluir(telefone);
			}
		}
	}
}
