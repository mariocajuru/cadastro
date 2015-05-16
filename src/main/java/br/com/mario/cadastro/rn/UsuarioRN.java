package br.com.mario.cadastro.rn;

import java.util.List;

import br.com.mario.cadastro.dao.UsuarioDAO;
import br.com.mario.cadastro.modelo.Pessoa;
import br.com.mario.cadastro.modelo.Usuario;
import br.com.mario.cadastro.util.DAOFactory;

public class UsuarioRN {
	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}

	public Usuario carregar(Integer pesId) {
		return this.usuarioDAO.carregar(pesId);

	}

	public Usuario buscarPorUsuario(String loguin) {
		return this.usuarioDAO.buscarPorUsuario(loguin);
	}

	public void salvar(Usuario Funcionario) {
		Integer codigo = Funcionario.getPesId();
		if (codigo == null || codigo == 0) {
			this.usuarioDAO.salvar(Funcionario);
		} else {
			this.usuarioDAO.atualizar(Funcionario);
		}

	}

	public void excluir(Usuario usuario) {		
			this.usuarioDAO.excluir(usuario);
	}

	public List<Usuario> listar() {
		return this.usuarioDAO.listar();
	}
	
	public List<Pessoa> listarUsuarioPessoas() {
		return this.usuarioDAO.listarUsuarioPessoas();
	}
	
	public List<Pessoa> listaPessoasNaoUsuario() {
		return this.usuarioDAO.listaPessoasNaoUsuario();
	}
}
