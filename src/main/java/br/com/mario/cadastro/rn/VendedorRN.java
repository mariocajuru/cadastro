package br.com.mario.cadastro.rn;

import java.util.List;

import br.com.mario.cadastro.dao.VendedorDAO;
import br.com.mario.cadastro.modelo.Cliente;
import br.com.mario.cadastro.modelo.Vendedor;
import br.com.mario.cadastro.util.DAOFactory;

public class VendedorRN {
	private VendedorDAO vendedorDAO;

	public VendedorRN() {
		this.vendedorDAO = DAOFactory.criarVendedorDAO();
	}

	public Vendedor carregar(Integer pesId) {
		return this.vendedorDAO.carregar(pesId);

	}

	public Vendedor buscarPorVendedor(String venDescricao) {
		return this.vendedorDAO.buscarPorVendedor(venDescricao);
	}

	public void salvar(Vendedor vendedor) {
		Integer codigo = vendedor.getPesId();
		if (codigo == null || codigo == 0) {
			this.vendedorDAO.salvar(vendedor);
		} else {
			this.vendedorDAO.atualizar(vendedor);
		}

	}

	public void excluir(Vendedor vendedor) {		
		this.vendedorDAO.excluir(vendedor);
	}

	public List<Vendedor> listar() {
		return this.vendedorDAO.listar();
	}

	public List<Cliente> listarClientesPorVendedor(Vendedor vendedor) {
		return this.vendedorDAO.listarClientesPorVendedor(vendedor);
	}
}
