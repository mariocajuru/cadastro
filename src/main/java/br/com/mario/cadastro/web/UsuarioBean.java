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
import br.com.mario.cadastro.util.ContextoBean;
import br.com.mario.cadastro.util.ContextoUtil;
@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable{

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
	
	@Getter @Setter private ContextoBean genericBean=ContextoUtil.getContextoBean();
	
	@PostConstruct
	public void init(){
		UsuarioRN usuarioRN = new UsuarioRN();
		this.usuario=new Usuario();
		this.pessoa=new Pessoa();
		
		String paginaAtual = this.genericBean.getPaginaAtual();

		if (paginaAtual.contains("admin/funcionario/consulta")) {
			this.listaFuncionarios=usuarioRN.listarUsuarioPessoas();
		}

		if (paginaAtual.contains("admin/funcionario/cadastro")) {
			this.permissoes=new ArrayList<String>();
			
			this.listaPessoasNaoFuncionarios=usuarioRN.listaPessoasNaoUsuario();
			
			int usuarioID = this.genericBean.getParametro("id", -1);

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
			this.genericBean.mostrarErro("Selecione o funcionário ");
			return ;
		}
		
		if(this.usuario.getUseCargo()==null || this.usuario.getUseCargo()==""){
			this.genericBean.mostrarErro("Campo Cargo obrigatorio");
			return ;
		}
		
		if (!this.senha.equals(this.confirmaSenha)) {
			this.senha=new String();
			this.confirmaSenha=new String();
			this.genericBean.mostrarErro("A senha não foi confirmada corretamente");
			return ;
		}

		UsuarioRN usuarioRN = new UsuarioRN();

		Usuario user=usuarioRN.buscarPorUsuario(loguin);
		if(user!=null){
			if(this.alteracao){
				if(user.getPesId()!=this.usuario.getPesId()){
					this.genericBean.mostrarErro("Esse login já existe no sistema. Pertence ao Sr."+user.getPessoa().getPesNome());
					return ;
				}else{
					//eliminado objeto da session
					this.genericBean.evict(user);
				}
			}else{
				this.genericBean.mostrarErro("Esse login já existe no sistema. Pertence ao Sr."+user.getPessoa().getPesNome());
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
		
		this.genericBean.redirecionarParaPagina("admin/funcionario/consulta.jsf");
	}
}
