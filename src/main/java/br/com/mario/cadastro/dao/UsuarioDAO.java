package br.com.mario.cadastro.dao;

import java.util.List;

import br.com.mario.cadastro.modelo.Pessoa;
import br.com.mario.cadastro.modelo.Usuario;

public interface UsuarioDAO {
	public void salvar(Usuario usuario);

	public void atualizar(Usuario usuario);

	public void excluir(Usuario usuario);

	public Usuario carregar(Integer pesId);

	public Usuario buscarPorUsuario(String usuario);

	public List<Usuario> listar();
	
	public List<Pessoa> listarUsuarioPessoas();
	
	public List<Pessoa> listaPessoasNaoUsuario();
	
	public boolean dependecias(Usuario usuario);
}
