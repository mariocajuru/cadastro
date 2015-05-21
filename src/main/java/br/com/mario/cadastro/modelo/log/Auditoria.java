package br.com.mario.cadastro.modelo.log;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;
@Entity
@RevisionEntity(Gravar.class)
@Table(name="AUDITORIA", schema="log", catalog="cadastro")
public class Auditoria extends DefaultRevisionEntity implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7197655600139767254L;

	/**
	 * 
	 */
	
	private String login;
	
	private String nome;
	private String ip_maquina;
	private String nome_maquina;
	
	private Integer ip_usuario;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIp_maquina() {
		return ip_maquina;
	}
	public void setIp_maquina(String ip_maquina) {
		this.ip_maquina = ip_maquina;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Integer getIp_usuario() {
		return ip_usuario;
	}
	public void setIp_usuario(Integer ip_usuario) {
		this.ip_usuario = ip_usuario;
	}
	public String getNome_maquina() {
		return nome_maquina;
	}
	public void setNome_maquina(String nome_maquina) {
		this.nome_maquina = nome_maquina;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((ip_usuario == null) ? 0 : ip_usuario.hashCode());
		result = prime * result
				+ ((ip_maquina == null) ? 0 : ip_maquina.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((nome_maquina == null) ? 0 : nome_maquina.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auditoria other = (Auditoria) obj;
		if (ip_usuario == null) {
			if (other.ip_usuario != null)
				return false;
		} else if (!ip_usuario.equals(other.ip_usuario))
			return false;
		if (ip_maquina == null) {
			if (other.ip_maquina != null)
				return false;
		} else if (!ip_maquina.equals(other.ip_maquina))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nome_maquina == null) {
			if (other.nome_maquina != null)
				return false;
		} else if (!nome_maquina.equals(other.nome_maquina))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Auditoria [getId()=" + getId() + "]";
	}
	
}
