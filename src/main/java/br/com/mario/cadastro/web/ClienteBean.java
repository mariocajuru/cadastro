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
import br.com.mario.cadastro.modelo.Cliente;
import br.com.mario.cadastro.modelo.Endereco;
import br.com.mario.cadastro.modelo.Pessoa;
import br.com.mario.cadastro.rn.ClienteRN;
import br.com.mario.cadastro.rn.PessoaRN;

@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean extends GenericBean implements Serializable{

	/**
	 * 
	 */
	@Getter private static final long serialVersionUID = 7178116138020731713L;
	@Getter @Setter private Pessoa pessoa;
	@Getter @Setter private Endereco endereco;
	@Getter @Setter private Bairro bairro;
	@Getter @Setter private Cidade cidade;
	@Getter @Setter	private Character tipoPessoa=new Character('F');
	
	@Getter @Setter private Cliente cliente;
	
	@Getter @Setter private List<Pessoa> listaClientes;
	
 	
	@PostConstruct
	public void init(){
		this.pessoa=new Pessoa();
		
		String paginaAtual = super.getPaginaAtual();
		
		PessoaRN pessoaRN=new PessoaRN();
		
		if (paginaAtual.contains("restrito/principal")) {
			this.listaClientes=new ArrayList<Pessoa>();
			List<Pessoa> lista=pessoaRN.listar();			
			for(Pessoa pes: lista){
				if(pes.getCliente()!=null){
					this.listaClientes.add(pes);
				}
			}
		
		}
		
	

		if (paginaAtual.contains("restrito/cliente/consulta")) {
			this.listaClientes=new ArrayList<Pessoa>();
			List<Pessoa> lista=pessoaRN.listar();			
			for(Pessoa pes: lista){
				if(pes.getUsuario()==null){
					this.listaClientes.add(pes);
				}
			}
			
		}
		
		if (paginaAtual.contains("restrito/cliente/cadastro")) {
			int pessoaID = super.getParametro("id", -1);
			
			this.pessoa=pessoaRN.carregar(pessoaID);
			
			if(this.pessoa != null){
				if(this.pessoa.getPessoaFisica() != null){
					this.tipoPessoa='F';
				}				
				if(this.pessoa.getPessoaJuridica() != null){
					this.tipoPessoa='J';
				}
				
				if(this.pessoa.getCliente() == null){
					this.cliente=new Cliente();
				}else{
					this.cliente=this.pessoa.getCliente();
				}
				
			
			}
		}
		
		if (paginaAtual.contains("restrito/cliente/vistoria")) {
			int pessoaID = super.getParametro("id", -1);
			
			this.pessoa=pessoaRN.carregar(pessoaID);
			
			if(this.pessoa != null){
				if(this.pessoa.getCliente()==null){
					this.cliente=new Cliente();
					this.cliente.setPessoa(this.pessoa);
				
					new ClienteRN().salvar(this.cliente);
					this.pessoa.setCliente(this.cliente);
				}
				
					
			}
		}

	}
	
	public void salvarInformacoes() {

		if(this.pessoa.getCliente() == null){
			this.cliente.setPessoa(this.pessoa);
		}
		
		new ClienteRN().salvar(this.cliente);

	}


}
