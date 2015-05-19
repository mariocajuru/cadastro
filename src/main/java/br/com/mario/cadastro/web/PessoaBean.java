package br.com.mario.cadastro.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;
import br.com.mario.cadastro.modelo.Bairro;
import br.com.mario.cadastro.modelo.Cidade;
import br.com.mario.cadastro.modelo.EMail;
import br.com.mario.cadastro.modelo.Endereco;
import br.com.mario.cadastro.modelo.Pessoa;
import br.com.mario.cadastro.modelo.PessoaFisica;
import br.com.mario.cadastro.modelo.PessoaJuridica;
import br.com.mario.cadastro.modelo.Telefone;
import br.com.mario.cadastro.rn.BairroRN;
import br.com.mario.cadastro.rn.CidadeRN;
import br.com.mario.cadastro.rn.EMailRN;
import br.com.mario.cadastro.rn.EnderecoRN;
import br.com.mario.cadastro.rn.PessoaFisicaRN;
import br.com.mario.cadastro.rn.PessoaJuridicaRN;
import br.com.mario.cadastro.rn.PessoaRN;
import br.com.mario.cadastro.rn.TelefoneRN;
import br.com.mario.cadastro.util.ContextoBean;
import br.com.mario.cadastro.util.ContextoUtil;

@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean implements Serializable{

	/**
	 * 
	 */
	@Getter private static final long serialVersionUID = 1947094287442850522L;
	@Getter @Setter	private Bairro bairro=new Bairro();
	@Getter @Setter	private Cidade cidade=new Cidade();
	@Getter @Setter	private Endereco endereco=new Endereco();
	@Getter @Setter	private Pessoa pessoa=new Pessoa();
	@Getter @Setter	private PessoaFisica pessoaFisica=new PessoaFisica();
	@Getter @Setter	private PessoaJuridica pessoaJuridica=new PessoaJuridica();
	@Getter @Setter	private Telefone telefone=new Telefone();
	@Getter @Setter	private EMail email=new EMail();
	@Getter @Setter	private Character tipoPessoa=new Character('F');//false para pessoa fisica e true para pessoa juridica
	@Getter @Setter	private List<Pessoa> listaPessoas;
	@Setter	private List<Telefone> listaTelefones;
	@Setter	private List<EMail> listaEmails;
	
	@Getter @Setter private ContextoBean genericBean=ContextoUtil.getContextoBean();

	@Getter @Setter	private Boolean alteracao=false;

	@PostConstruct
	public void init(){
		this.endereco=new Endereco();
		this.bairro=new Bairro();
		this.cidade=new Cidade();
		this.telefone=new Telefone();
		this.email=new EMail();
		
		String paginaAtual = this.genericBean.getPaginaAtual();

		if (paginaAtual.contains("restrito/pessoa/consulta")) {
			this.listaPessoas=new PessoaRN().listar();
		}
		
		if (paginaAtual.contains("restrito/pessoa/consulta_vendedor")) {
			this.listaPessoas=new PessoaRN().listar();
			for(Pessoa p: this.listaPessoas){
				if(p.getVendedor()==null)
					this.listaPessoas.remove(p);
			}
		}

		if (paginaAtual.contains("restrito/pessoa/cadastro")) {
			int pessoaID = this.genericBean.getParametro("id", -1);

			if (pessoaID <= 0) {
				alteracao = false;
			} else {
				alteracao = true;
				this.pessoa=new PessoaRN().carregar(pessoaID);
				if(this.pessoa!=null){
					this.endereco=this.pessoa.getEndereco();
					if(this.endereco != null){
						this.bairro=this.endereco.getBairro();
						this.cidade=this.endereco.getCidade();
					}else{
						this.endereco=new Endereco();
						this.bairro=new Bairro();
						this.cidade=new Cidade();
					}
					this.pessoaFisica=this.pessoa.getPessoaFisica();
					if(this.pessoaFisica != null){
						this.tipoPessoa='F';
					}else{
						this.pessoaFisica=new PessoaFisica();
					}
					this.pessoaJuridica=this.pessoa.getPessoaJuridica();
					if(this.pessoaJuridica != null){
						this.tipoPessoa='J';
					}else{
						this.pessoaJuridica=new PessoaJuridica();
					}
					return;
				}
			}

			this.pessoaFisica=new PessoaFisica();
			this.pessoaJuridica=new PessoaJuridica();
			this.pessoa=new Pessoa();
			this.bairro=new Bairro();
			this.cidade=new Cidade();
			this.endereco=new Endereco();
		}
	}

	public void salvar(){
		/** Grava Cidade */
		CidadeRN cidadaRN = new CidadeRN();
		
		if (cidadaRN.buscarPorCidade(cidade.getCidNome()) == null){
			cidadaRN.salvar(this.cidade);
		} else {
			this.cidade = cidadaRN.buscarPorCidade(cidade.getCidNome());
		}
		
		/** Grava Bairro */
		BairroRN bairroRN = new BairroRN();
		
		if (bairroRN.buscarPorBairro(bairro.getBaiNome()) == null) {
			bairroRN.salvar(this.bairro); 
		} else {
			this.bairro = bairroRN.buscarPorBairro(this.bairro.getBaiNome());
		}
		

			/** Endere�o */
			String	cep = endereco.getEndCep();
			cep = cep.replaceAll("[.-]", "");
			this.endereco.setEndCep(cep);

			this.endereco.setCidade(cidade);
			this.endereco.setBairro(bairro);

			this.pessoa.setEndereco(endereco);

		new PessoaRN().salvar(this.pessoa);
		
		this.endereco.setPessoa(this.pessoa);
		
		new EnderecoRN().salvar(this.endereco);

		if(tipoPessoa.equals('F')){
			this.pessoaFisica.setPessoa(this.pessoa);

			new PessoaFisicaRN().salvar(this.pessoaFisica);

		}else{
			this.pessoaJuridica.setPessoa(this.pessoa);

			new PessoaJuridicaRN().salvar(this.pessoaJuridica);
		}
		
		TelefoneRN telefoneRN = new TelefoneRN();
		EMailRN eMailRN = new EMailRN(); 
		
		for(Telefone tel: this.listaTelefones){
			tel.setPessoa(this.pessoa);
			telefoneRN.salvar(tel);
		}
		
		for(EMail mail: this.listaEmails){
			mail.setPessoa(pessoa);
			eMailRN.salvar(mail);
		}

		this.genericBean.redirecionarParaPagina("restrito/pessoa/consulta.jsf");
	}


	public List<Telefone> getListaTelefones() {
		if(this.listaTelefones == null){
			if((this.pessoa!=null)&&(this.pessoa.getPesId()>0)){
				this.listaTelefones=new TelefoneRN().carregarTelefonesPorPessoa(this.pessoa);
				if(this.listaTelefones == null){
					this.listaTelefones=new ArrayList<Telefone>();
				}
			}else{
				this.listaTelefones=new ArrayList<Telefone>();
			}
		}
		return listaTelefones;
	}

	public List<EMail> getListaEmails() {
		if(this.listaEmails == null){
			if((this.pessoa!=null)&&(this.pessoa.getPesId()>0)){
				this.listaEmails=new EMailRN().carregarEmailsPorPessoa(this.pessoa);
				if(this.listaEmails == null){
					this.listaEmails=new ArrayList<EMail>();
				}
			}else{
				this.listaEmails=new ArrayList<EMail>();
			}
		}
		return listaEmails;
	}
	
	public void addTel(){
		String tel = telefone.getTelNumero();
		tel = tel.replaceAll("[.-]", "");
		tel = tel.replaceAll("[)]", "");
		tel = tel.replaceAll("[(]", "");
		tel = tel.replaceAll("[ ]", "");
		TelefoneRN telefoneRN=new TelefoneRN();
		Telefone telefoneTeste=telefoneRN.buscarPorTelefone(tel);
		
		if(telefoneTeste == null){
			this.listaTelefones.add(telefone);
			this.telefone = new Telefone();
			return;
		}

	}

	public void removerEmail(EMail e) {
		if(listaEmails == null)
			return;
		
		listaEmails.remove(e);
		this.email = new EMail();
	}
	
	public void addEmail(){
		EMailRN emailRN = new EMailRN();
		EMail emailTeste = emailRN.buscarPorEMail(email.getMaiEndereco());
		
		if(emailTeste == null){
			this.listaEmails.add(email);
			this.email = new EMail();
			return;
		}
	}

	public void removerTel(Telefone t){
		if(listaTelefones != null){
			this.listaTelefones.remove(t);
			this.telefone = new Telefone();			
		}
	}
	
	
	public void excluir(){
		new PessoaRN().excluir(this.pessoa);
		this.genericBean.redirecionarParaPagina("restrito/pessoa/consulta.jsf");
	}


}
