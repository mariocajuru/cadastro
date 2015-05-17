package br.com.mario.cadastro.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;
import br.com.mario.cadastro.modelo.Pessoa;
import br.com.mario.cadastro.modelo.Usuario;
import br.com.mario.cadastro.rn.UsuarioRN;
@ManagedBean(name = "funcionarioBean")
@ViewScoped
public class UsuarioBean extends GenericBean implements Serializable{

	/**
	 * 
	 */
	@Getter private static final long serialVersionUID = 8618463921451389645L;
	@Getter @Setter	private Pessoa pessoa=new Pessoa();
	@Getter @Setter	private Usuario usuario=new Usuario();
	@Getter @Setter	private String confirmaSenha;
	@Getter @Setter	private String senha;
	@Getter @Setter	private String loguin;
	@Getter @Setter boolean alteracao=false;
	
	@Getter @Setter	private List<Pessoa> listaFuncionarios;
	@Getter @Setter	private List<Pessoa> listaPessoasNaoFuncionarios;
	@Getter @Setter	private List<String> permissoes;
	
	@PostConstruct
	public void init(){
		UsuarioRN usuarioRN = new UsuarioRN();
		this.usuario=new Usuario();
		this.pessoa=new Pessoa();
		
		String paginaAtual = super.getPaginaAtual();

		if (paginaAtual.contains("admin/funcionario/consulta")) {
			this.listaFuncionarios=usuarioRN.listarUsuarioPessoas();
		}

		if (paginaAtual.contains("admin/funcionario/cadastro")) {
			this.permissoes=new ArrayList<String>();
			
			this.listaPessoasNaoFuncionarios=usuarioRN.listaPessoasNaoUsuario();
			
			int usuarioID = super.getParametro("id", -1);

			if (usuarioID <= 0) {
				setAlteracao(false);
			} else {
				setAlteracao(true);
				Usuario usuarioCarregado = usuarioRN.carregar(usuarioID);
				if(usuarioCarregado!=null){
					this.usuario=usuarioCarregado;
					Set<String> lista=this.usuario.getPermissao();
					for(String per: lista)
						this.permissoes.add(per);
					this.pessoa=this.usuario.getPessoa();
					this.loguin=this.usuario.getUseLogin();
					this.senha=new String();
					this.confirmaSenha=new String();
				}
			}
		}
	}
	
	public void salvar(){
		if((this.pessoa==null)||(this.pessoa.getPesId()<1)){
			this.senha=new String();
			this.confirmaSenha=new String();
			super.mostrarErro("Selecione o funcionário ");
			return ;
		}
		
		if(this.usuario.getUseCargo()==null || this.usuario.getUseCargo()==""){
			super.mostrarErro("Campo Cargo obrigatorio");
			return ;
		}
		
		if (!this.senha.equals(this.confirmaSenha)) {
			this.senha=new String();
			this.confirmaSenha=new String();
			super.mostrarErro("A senha não foi confirmada corretamente");
			return ;
		}

		UsuarioRN usuarioRN = new UsuarioRN();

		Usuario user=usuarioRN.buscarPorUsuario(loguin);
		if(user!=null){
			if(this.alteracao){
				if(user.getPesId()!=this.usuario.getPesId()){
					super.mostrarErro("Esse login já existe no sistema. Pertence ao Sr."+user.getPessoa().getPesNome());
					return ;
				}else{
					//eliminado objeto da session
					super.evict(user);
				}
			}else{
				super.mostrarErro("Esse login já existe no sistema. Pertence ao Sr."+user.getPessoa().getPesNome());
				return ;
			}
		}

		this.usuario.setPermissao(new HashSet<String>());
		for(String per: this.permissoes){
			this.usuario.getPermissao().add(per);
		}
		this.usuario.setUseLogin(loguin);
		this.usuario.setUseSenha(this.senha);
		this.usuario.setPessoa(this.pessoa);
		this.usuario.setUseAtivo(true);
		usuarioRN.salvar(this.usuario);
		
		super.redirecionarParaPagina("admin/funcionario/consulta.jsf");
	}
}
