package com.dtecimax.ejb.backing.ar;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean  
@ViewScoped
public class PagoDeEstudiosForm {

	private String searchNomPaci; 
	private String searchApellPatPaci; 
	private String searchApellMatPaci;
	private Date searchFechaDesde;
	private Date searchFechaHasta;
	
	public void filtraPorFechas() {
		System.out.println("Entra filtraPorFechas");
		
		System.out.println("searchNomPaci:"+searchNomPaci);
		System.out.println("searchApellPatPaci:"+searchApellPatPaci);
		System.out.println("searchApellMatPaci:"+searchApellMatPaci);
		System.out.println("searchFechaDesde:"+searchFechaDesde);
		System.out.println("searchFechaHasta:"+searchFechaHasta);
		
		System.out.println("Sale filtraPorFechas");
	}
	
	public String getSearchNomPaci() {
		return searchNomPaci;
	}
	public void setSearchNomPaci(String searchNomPaci) {
		this.searchNomPaci = searchNomPaci;
	}
	public String getSearchApellPatPaci() {
		return searchApellPatPaci;
	}
	public void setSearchApellPatPaci(String searchApellPatPaci) {
		this.searchApellPatPaci = searchApellPatPaci;
	}
	public String getSearchApellMatPaci() {
		return searchApellMatPaci;
	}
	public void setSearchApellMatPaci(String searchApellMatPaci) {
		this.searchApellMatPaci = searchApellMatPaci;
	}
	public Date getSearchFechaDesde() {
		return searchFechaDesde;
	}
	public void setSearchFechaDesde(Date searchFechaDesde) {
		this.searchFechaDesde = searchFechaDesde;
	}
	public Date getSearchFechaHasta() {
		return searchFechaHasta;
	}
	public void setSearchFechaHasta(Date searchFechaHasta) {
		this.searchFechaHasta = searchFechaHasta;
	} 
	
}
