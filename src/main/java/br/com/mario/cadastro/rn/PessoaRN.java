package br.com.mario.cadastro.rn;

import java.util.Date;
import java.util.List;

import br.com.mario.cadastro.dao.PessoaDAO;
import br.com.mario.cadastro.modelo.Pessoa;
import br.com.mario.cadastro.util.DAOFactory;


public class PessoaRN {
	private PessoaDAO pessoaDAO;

	public PessoaRN() {
		this.pessoaDAO = DAOFactory.criarPessoaDAO();
	}

	public Pessoa carregar(Integer pesId) {
		return this.pessoaDAO.carregar(pesId);

	}

	public Pessoa buscarPorRegiao(String pessoa) {
		return this.pessoaDAO.buscarPorPessoa(pessoa);
	}

	public void salvar(Pessoa pessoa) {
		Integer codigo = pessoa.getPesId();
		if(pessoa.getPesDataCadastro()==null)
			pessoa.setPesDataCadastro(new Date());
		if (codigo == null || codigo == 0) {
			this.pessoaDAO.salvar(pessoa);
		} else {
			this.pessoaDAO.atualizar(pessoa);
		}

	}

	public void excluir(Pessoa pessoa) {		
			this.pessoaDAO.excluir(pessoa);
	}

	public List<Pessoa> listar() {
		return this.pessoaDAO.listar();
	}
}
