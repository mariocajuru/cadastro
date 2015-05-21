package br.com.mario.cadastro.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;


import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.mario.cadastro.modelo.Usuario;
import br.com.mario.cadastro.rn.UsuarioRN;


@ManagedBean(name="contextoBean")
@SessionScoped
public class ContextoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1124570040777405053L;
	private Usuario usuarioLogado = null;

	@PostConstruct
	public void testandoNumeroDaSessao(){
		System.out.println("Numero da Sessao Inicio do ContextBean "+hashCode());
	}

	@PreDestroy
	public void testandoNumeroDaSessaoFim(){
		System.out.println("Numero da Sessao Fim do ContextBean "+hashCode());
	}

	public Usuario getUsuarioLogado() {
		if (this.usuarioLogado == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext external = context.getExternalContext();
			String login = external.getRemoteUser();
			if (this.usuarioLogado == null|| !login.equals(this.usuarioLogado.getUseLogin())) {
				if (login != null) {
					UsuarioRN usuarioRN = new UsuarioRN();
					this.usuarioLogado = usuarioRN.buscarPorUsuario(login);
				}
			}
		}
		return usuarioLogado;
	}

	public void setFuncionarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public void mostrarAviso(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "")); 
	}

	public void mostrarErro(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "")); 
	}

	/**
	 * 
	 * 
	 * @param caminhoPagina Exemplo: http://www.google.com
	 */
	public void redirecionarParaPaginaExterna(String caminhoPagina) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(caminhoPagina);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Problema ao redirecionar para " + caminhoPagina);
		}
	}

	/**
	 * Redireciona para uma página do sistema.
	 * 
	 * @param caminhoPagina Exemplo: restrito/imovel/cadastro.jsf
	 */
	public void redirecionarParaPagina(String caminhoPagina) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/" + caminhoPagina);
		} catch (IOException e) {
			System.out.println("Problema ao redirecionar para " + caminhoPagina);
		}
	}

	public void commit() {
		Transaction sessao = HibernateUtil.getSessionFactory().getCurrentSession().getTransaction();

		if (sessao != null && sessao.isActive())		
			sessao.commit();
	}

	/**
	 * Cancela todas alterações feitas no banco de dados na página
	 * @param msg mensagem de aviso para o úsuario
	 */
	public void rollback(String msg) {	
		/** TODO: O rollback não está funcionado corretamente */
		Transaction sessao = HibernateUtil.getSessionFactory().getCurrentSession().getTransaction();

		sessao.isParticipating();

		if (sessao.isActive() && !sessao.wasCommitted() && !sessao.isParticipating() && !sessao.wasRolledBack())
			sessao.rollback();

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
	}

	/**
	 * Eliminar objetos na session
	 * @param Eliminar objetos na session
	 */
	public void evict(Object object) {	
		Session sessao =  HibernateUtil.getSessionFactory()
				.getCurrentSession();
		sessao.evict(object);
	}

	public int getParametro(String nome, Integer valorPadrao) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		try { 
			int valor = Integer.parseInt(facesContext.getExternalContext().getRequestParameterMap().get(nome));

			return valor;
		} catch(NumberFormatException e) { 
			return valorPadrao; 
		}
	}

	public String getParametro(String nome, String valorPadrao) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		String valor = facesContext.getExternalContext().getRequestParameterMap().get(nome);

		return valor == null ? valorPadrao : valor;
	}

	public String getPaginaAtual() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		String viewId = facesContext.getViewRoot().getViewId();

		return viewId;
	}

	public double arredondarCasasDecimais(double value) {
		long factor = (long) Math.pow(10, 2);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public Date primeiroDiaMes(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public Date ultimoDiaMes(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

}
