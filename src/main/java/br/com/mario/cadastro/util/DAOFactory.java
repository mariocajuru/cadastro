package br.com.mario.cadastro.util;
import br.com.mario.cadastro.dao.BairroDAO;
import br.com.mario.cadastro.dao.BairroDAOHibernate;
import br.com.mario.cadastro.dao.CidadeDAO;
import br.com.mario.cadastro.dao.CidadeDAOHibernate;
import br.com.mario.cadastro.dao.ClienteDAO;
import br.com.mario.cadastro.dao.ClienteDAOHibernate;
import br.com.mario.cadastro.dao.EMailDAO;
import br.com.mario.cadastro.dao.EMailDAOHibernate;
import br.com.mario.cadastro.dao.EnderecoDAO;
import br.com.mario.cadastro.dao.EnderecoDAOHibernate;
import br.com.mario.cadastro.dao.PessoaDAO;
import br.com.mario.cadastro.dao.PessoaDAOHibernate;
import br.com.mario.cadastro.dao.PessoaFisicaDAO;
import br.com.mario.cadastro.dao.PessoaFisicaDAOHibernate;
import br.com.mario.cadastro.dao.PessoaJuridicaDAO;
import br.com.mario.cadastro.dao.PessoaJuridicaDAOHibernate;
import br.com.mario.cadastro.dao.TelefoneDAO;
import br.com.mario.cadastro.dao.TelefoneDAOHibernate;
import br.com.mario.cadastro.dao.UsuarioDAO;
import br.com.mario.cadastro.dao.UsuarioDAOHibernate;
import br.com.mario.cadastro.dao.VendedorDAO;
import br.com.mario.cadastro.dao.VendedorDAOHibernate;


public class DAOFactory {

	public static PessoaDAO criarPessoaDAO() {
		PessoaDAOHibernate pessoaDAO = new PessoaDAOHibernate();
		pessoaDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return pessoaDAO;
	}
	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate funcionarioDAOHibernate = new UsuarioDAOHibernate();
		funcionarioDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return funcionarioDAOHibernate;
	}
	public static PessoaFisicaDAO criarPessoaFisicaDAO() {
		PessoaFisicaDAOHibernate pessoaFisicaDAOHibernate  = new PessoaFisicaDAOHibernate();
		pessoaFisicaDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return pessoaFisicaDAOHibernate;
	}
	
	public static PessoaJuridicaDAO criarPessoaJuridicaDAO() {
		PessoaJuridicaDAOHibernate pessoaJuridicaDAOHibernate  = new PessoaJuridicaDAOHibernate();
		pessoaJuridicaDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return pessoaJuridicaDAOHibernate;
	}
	
	public static BairroDAO criarBairroDAO() {
		BairroDAOHibernate bairroDAODAOHibernate  = new BairroDAOHibernate();
		bairroDAODAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return bairroDAODAOHibernate;
	}
	
	public static CidadeDAO criarCidadeDAO() {
		CidadeDAOHibernate cidadeDAOHibernate  = new CidadeDAOHibernate();
		cidadeDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return cidadeDAOHibernate;
	}
	
	public static EnderecoDAO criarEnderecoDAO() {
		EnderecoDAOHibernate enderecoDAOHibernate  = new EnderecoDAOHibernate();
		enderecoDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return enderecoDAOHibernate;
	}
	
	public static ClienteDAO criarClienteDAO() {
		ClienteDAOHibernate clienteDAOHibernate  = new ClienteDAOHibernate();
		clienteDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return clienteDAOHibernate;
	}
	public static TelefoneDAO criarTelefoneDAO() {
		TelefoneDAOHibernate telefoneDAOHibernate  = new TelefoneDAOHibernate();
		telefoneDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return telefoneDAOHibernate;
	}
	
	public static EMailDAO criarEMailDAO() {
		EMailDAOHibernate mailDAOHibernate  = new EMailDAOHibernate();
		mailDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return mailDAOHibernate;
	}
	
	public static VendedorDAO criarVendedorDAO() {
		VendedorDAOHibernate vendedorDAOHibernate  = new VendedorDAOHibernate();
		vendedorDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return vendedorDAOHibernate;
	}
}
