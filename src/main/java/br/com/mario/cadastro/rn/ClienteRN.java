package br.com.mario.cadastro.rn;

import java.util.List;

import br.com.mario.cadastro.dao.ClienteDAO;
import br.com.mario.cadastro.modelo.Cliente;
import br.com.mario.cadastro.util.DAOFactory;


public class ClienteRN {
	private ClienteDAO clienteDAO;

	public ClienteRN() {
		this.clienteDAO = DAOFactory.criarClienteDAO();
	}

	public Cliente carregar(Integer codigo) {
		return this.clienteDAO.carregar(codigo);

	}

	public Cliente buscarPorCliente(String cliente) {
		return this.clienteDAO.buscarPorCliente(cliente);
	}

	public void salvar(Cliente cliente) {
		Integer codigo = cliente.getPesId();
		if (codigo == null || codigo == 0) {
			this.clienteDAO.salvar(cliente);
		} else {
			this.clienteDAO.atualizar(cliente);
		}

	}

	public void excluir(Cliente cliente) {
		this.clienteDAO.excluir(cliente);
	}

	public List<Cliente> listar() {
		return this.clienteDAO.listar();
	}
}
