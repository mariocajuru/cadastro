package br.com.mario.cadastro.util;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.bean.*;

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

	public Usuario getUsuarioLogado() {
		if (this.usuarioLogado == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext external = context.getExternalContext();
			String login = external.getRemoteUser();
			if (this.usuarioLogado == null|| !login.equals(this.usuarioLogado.getUseLogin())) {
				if (login != null) {
					UsuarioRN funcionarioRN = new UsuarioRN();
					this.usuarioLogado = funcionarioRN.buscarPorUsuario(login);
					//this.contaAtiva = null;
				}
			}
		}
		return usuarioLogado;
	}

	public void setFuncionarioLogado(Usuario funcionarioLogado) {
		this.usuarioLogado = funcionarioLogado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
