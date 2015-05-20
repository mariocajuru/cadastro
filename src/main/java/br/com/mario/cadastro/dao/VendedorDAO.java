package br.com.mario.cadastro.dao;

import java.util.List;

import br.com.mario.cadastro.modelo.Cliente;
import br.com.mario.cadastro.modelo.Vendedor;

public interface VendedorDAO {
	public void salvar(Vendedor vendedor);

	public void atualizar(Vendedor vendedor);

	public void excluir(Vendedor vendedor);

	public Vendedor carregar(Integer pesId);

	public Vendedor buscarPorVendedor(String vendedor);

	public List<Vendedor> listar();
	
	public List<Cliente> listarClientesPorVendedor(Vendedor vendedor);
}
