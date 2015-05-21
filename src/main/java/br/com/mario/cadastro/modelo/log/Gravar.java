package br.com.mario.cadastro.modelo.log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.envers.RevisionListener;

import br.com.mario.cadastro.util.ContextoBean;
import br.com.mario.cadastro.util.ContextoUtil;


public class Gravar implements RevisionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8644920676174993870L;
	@Getter @Setter private ContextoBean genericBean=ContextoUtil.getContextoBean();

	@Override
	public void newRevision(Object arg0) {
		Auditoria auditoria=(Auditoria) arg0;
		
	
		auditoria.setNome(this.genericBean.getUsuarioLogado().getPessoa().getPesNome());
		auditoria.setLogin(this.genericBean.getUsuarioLogado().getUseLogin());
		auditoria.setIp_usuario(this.genericBean.getUsuarioLogado().getPesId());
		
		 @SuppressWarnings("rawtypes")
		Enumeration ee = null;
		try {
			ee = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		boolean achou=false;
	      while (ee.hasMoreElements()) { 
	         NetworkInterface i = (NetworkInterface) ee.nextElement(); 
	         @SuppressWarnings("rawtypes")
			Enumeration ds = i.getInetAddresses(); 
	         while (ds.hasMoreElements()) { 
	            InetAddress myself = (InetAddress) ds.nextElement();
	            if (!myself.isLoopbackAddress() && myself.isSiteLocalAddress()) {
	            	auditoria.setIp_maquina(myself.getHostAddress());
	            	auditoria.setNome_maquina(myself.getHostName());
	            	achou=true;
	            	//http://mariojp.wordpress.com/2012/03/11/obtendo-o-endereco-ip-real-da-maquina-na-rede/
	            }
	            if(achou)break;
	         } 
	         if(achou)break;
	      }
		
		
		}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Gravar [getPaginaAtual()=" + this.genericBean.getPaginaAtual() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ this.genericBean.toString() + "]";
	}
	

}
