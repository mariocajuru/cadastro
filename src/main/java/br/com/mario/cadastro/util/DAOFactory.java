package br.com.mario.cadastro.util;
import br.com.mario.cadastro.dao.UsuarioDAO;
import br.com.mario.cadastro.dao.UsuarioDAOHibernate;


public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate funcionarioDAOHibernate = new UsuarioDAOHibernate();
		funcionarioDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return funcionarioDAOHibernate;
	}
}
