package br.com.mario.cadastro.dao;

import java.util.List;

import br.com.mario.cadastro.modelo.Pessoa;
import br.com.mario.cadastro.modelo.Telefone;


public interface TelefoneDAO {
	public void salvar(Telefone telefone);

	public void atualizar(Telefone telefone);

	public void excluir(Telefone telefone);

	public Telefone carregar(Integer telefone);

	public Telefone buscarPorTelefone(String telefone);
	
	public List<Telefone> carregarTelefonesPorPessoa(Pessoa pessoa);

	public List<Telefone> listar();
	
	public boolean dependecias(Telefone telefone);
}
