package br.com.mario.cadastro.modelo;

// Generated 16/05/2015 14:37:37 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 * PessoaFisica generated by hbm2java
 */
@Audited
@Entity
@Table(name = "PESSOA_FISICA", catalog = "cadastro")
@AuditTable(value="PESSOA_FISICA", schema="log", catalog="cadastro")
public class PessoaFisica implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4418383483127397116L;
	private int pesId;
	private Pessoa pessoa;
	private String pesCpf;
	private String pesIdentidade;

	public PessoaFisica() {
	}

	public PessoaFisica(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public PessoaFisica(Pessoa pessoa, String pesCpf, String pesIdentidade) {
		this.pessoa = pessoa;
		this.pesCpf = pesCpf;
		this.pesIdentidade = pesIdentidade;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "pessoa"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "PES_ID", unique = true, nullable = false)
	public int getPesId() {
		return this.pesId;
	}

	public void setPesId(int pesId) {
		this.pesId = pesId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Column(name = "PES_CPF", length = 14)
	public String getPesCpf() {
		return this.pesCpf;
	}

	public void setPesCpf(String pesCpf) {
		this.pesCpf = pesCpf;
	}

	@Column(name = "PES_IDENTIDADE", length = 18)
	public String getPesIdentidade() {
		return this.pesIdentidade;
	}

	public void setPesIdentidade(String pesIdentidade) {
		this.pesIdentidade = pesIdentidade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pesCpf == null) ? 0 : pesCpf.hashCode());
		result = prime * result + pesId;
		result = prime * result
				+ ((pesIdentidade == null) ? 0 : pesIdentidade.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaFisica other = (PessoaFisica) obj;
		if (pesCpf == null) {
			if (other.pesCpf != null)
				return false;
		} else if (!pesCpf.equals(other.pesCpf))
			return false;
		if (pesId != other.pesId)
			return false;
		if (pesIdentidade == null) {
			if (other.pesIdentidade != null)
				return false;
		} else if (!pesIdentidade.equals(other.pesIdentidade))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PessoaFisica [pesId=" + pesId + "]";
	}

}
