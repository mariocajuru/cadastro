package br.com.mario.cadastro.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.mario.cadastro.modelo.Vendedor;

public class VendedorDAOHibernate implements VendedorDAO {

	private Session session;
	public void setSession(Session session) {
	this.session = session;
}
	
	@Override
	public void salvar(Vendedor vendedor) {
		this.session.save(vendedor);
	}

	@Override
	public void atualizar(Vendedor vendedor) {
		this.session.update(vendedor);
	}

	@Override
	public void excluir(Vendedor vendedor) {
		this.session.delete(vendedor);

	}

	@Override
	public Vendedor carregar(Integer pesId) {
		return (Vendedor)this.session.get(Vendedor.class, pesId);
	}

	@Override
	public Vendedor buscarPorVendedor(String vendedor) {
		String hql="select b from Vendedor b where b.venDescricao = :vendedor";
		Query consulta= this.session.createQuery(hql);
		consulta.setString("vendedor", vendedor);
		Vendedor ven= (Vendedor) consulta.uniqueResult();
		return ven;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendedor> listar() {
		Criteria crit= this.session.createCriteria(Vendedor.class);
		crit.addOrder(Order.asc("pesId"));
		return crit.list();
	}

}
