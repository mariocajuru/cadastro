package br.com.mario.cadastro.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.mario.cadastro.modelo.Pessoa;
import br.com.mario.cadastro.modelo.Usuario;

public class UsuarioDAOHibernate implements UsuarioDAO{

	private Session session;
	public void setSession(Session session) {
	this.session = session;
}
	
	public void salvar(Usuario usuario) {
		this.session.save(usuario);

	}

	public void atualizar(Usuario usuario) {
		this.session.update(usuario);

	}

	public void excluir(Usuario usuario) {
		this.session.delete(usuario);

	}

	public Usuario carregar(Integer pesId) {
		return (Usuario)this.session.get(Usuario.class, pesId);
	}

	public Usuario buscarPorUsuario(String usuario) {
		String hql="select b from Usuario b where b.useLogin = :usuario";
		Query consulta= this.session.createQuery(hql);
		consulta.setString("usuario", usuario);
		Usuario fun= (Usuario) consulta.uniqueResult();
		return fun;
	}
	@SuppressWarnings("unchecked")
	public List<Usuario> listar() {
		Criteria crit= this.session.createCriteria(Usuario.class);
		crit.addOrder(Order.asc("useLogin"));
		return crit.list();
	}

	public boolean dependecias(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarUsuarioPessoas() {
		List<Pessoa> lista=new ArrayList<Pessoa>();
		Criteria crit= this.session.createCriteria(Pessoa.class);
		List<Pessoa> l= crit.list();
		for(Pessoa lis: l){
			if(lis.getUsuario()!=null)
				lista.add(lis);
		}
		
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listaPessoasNaoUsuario() {
		List<Pessoa> lista=new ArrayList<Pessoa>();
		Criteria crit= this.session.createCriteria(Pessoa.class);
		List<Pessoa> l= crit.list();
		for(Pessoa lis: l){
			if((lis.getUsuario()==null)&&(lis.getPessoaFisica()!=null))
				lista.add(lis);
		}
		
		return lista;
	}

}
