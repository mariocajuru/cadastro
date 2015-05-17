package br.com.mario.cadastro.dao;

import java.util.List;

import br.com.mario.cadastro.modelo.PessoaFisica;


public interface PessoaFisicaDAO {
	public void salvar(PessoaFisica pessoaFisica);

	public void atualizar(PessoaFisica pessoaFisica);

	public void excluir(PessoaFisica pessoaFisica);

	public PessoaFisica carregar(Integer pesId);

	public PessoaFisica buscarPorPessoaFisica(String pessoaFisica);

	public List<PessoaFisica> listar();
	
	public boolean dependecias(PessoaFisica pessoaFisica);
}
