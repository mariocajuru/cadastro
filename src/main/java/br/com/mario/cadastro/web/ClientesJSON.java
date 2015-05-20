package br.com.mario.cadastro.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import lombok.Getter;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import br.com.mario.cadastro.modelo.Cliente;
import br.com.mario.cadastro.rn.ClienteRN;


@ManagedBean(name = "JSONClientes")
@ViewScoped
public class ClientesJSON implements Serializable{
	
	/**
	 * 
	 */
	@Getter private static final long serialVersionUID = -8489722558999043689L;

	public void rendenizarJson() throws IOException, JSONException {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    externalContext.setResponseContentType("application/json");
	    externalContext.setResponseCharacterEncoding("UTF-8");
	    externalContext.getResponseOutputWriter().write(carregarLista());
	    facesContext.responseComplete();
	}
	
	private String carregarLista() throws JSONException {
		List<Cliente> lista = new ClienteRN().listar();
		
		JSONArray jsonArray = new JSONArray();
		
		for (Cliente item : lista) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("id", item.getPesId());
			jsonObject.put("value", item.getPessoa().getPesNome());
			
			jsonArray.put(jsonObject);
		}
		
		return jsonArray.toString();
	}
	
}