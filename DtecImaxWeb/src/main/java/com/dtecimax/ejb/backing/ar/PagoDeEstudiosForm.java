package com.dtecimax.ejb.backing.ar;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.dtecimax.ejb.model.ar.PagosOrdenesEstudiosV1;
import com.dtecimax.ejb.services.ar.PagosOrdenesEstudiosV1Local;
import com.dtecimax.jpa.dto.ar.PagosOrdenesEstudiosV1Dto;

@ManagedBean  
@ViewScoped
public class PagoDeEstudiosForm {

	private String searchNomPaci; 
	private String searchApellPatPaci; 
	private String searchApellMatPaci;
	private Date searchFechaDesde;
	private Date searchFechaHasta;
	private int pacientesAtendidos; 
	private float totalPorCobrar; 
	
	private List<PagosOrdenesEstudiosV1> listPagosOrdenesEstudiosV1 = new ArrayList<PagosOrdenesEstudiosV1>(); 
	
	@Inject 
	PagosOrdenesEstudiosV1Local pagosOrdenesEstudiosV1Local;
	
	public void filtraPorFechas() {
		System.out.println("Entra filtraPorFechas");
		
		System.out.println("searchNomPaci:"+searchNomPaci);
		System.out.println("searchApellPatPaci:"+searchApellPatPaci);
		System.out.println("searchApellMatPaci:"+searchApellMatPaci);
		System.out.println("searchFechaDesde:"+searchFechaDesde);
		System.out.println("searchFechaHasta:"+searchFechaHasta);
		
		java.sql.Date sqlFechaDesde = new java.sql.Date(searchFechaDesde.getTime());
		java.sql.Date sqlFechaHasta = new java.sql.Date(searchFechaHasta.getTime()); 
		
		List<PagosOrdenesEstudiosV1Dto> listPagosEstudiosV1Dto = pagosOrdenesEstudiosV1Local.findByFiltros(searchNomPaci
																		                                 , searchApellPatPaci
																		                                 , searchApellMatPaci
																		                                 , sqlFechaDesde
																		                                 , sqlFechaHasta
																		                                 );
		listPagosOrdenesEstudiosV1 = new ArrayList<PagosOrdenesEstudiosV1>(); 
		pacientesAtendidos = 0; 
		totalPorCobrar = 0f; 
		Iterator<PagosOrdenesEstudiosV1Dto> iterPagosEstudiosV1Dto = listPagosEstudiosV1Dto.iterator();
		while(iterPagosEstudiosV1Dto.hasNext()) {
			PagosOrdenesEstudiosV1Dto pagosOrdenesEstudiosV1Dto = iterPagosEstudiosV1Dto.next(); 
			PagosOrdenesEstudiosV1 pagosOrdenesEstudiosV1 = new PagosOrdenesEstudiosV1();
			pagosOrdenesEstudiosV1.setNombreCompletoPaciente(pagosOrdenesEstudiosV1Dto.getNombreCompletoPaciente());
			pagosOrdenesEstudiosV1.setNombreEstudio(pagosOrdenesEstudiosV1Dto.getNombreEstudio());
			pagosOrdenesEstudiosV1.setCostoEstudio(pagosOrdenesEstudiosV1Dto.getCostoEstudio());
			pagosOrdenesEstudiosV1.setCastFechaCreacion(pagosOrdenesEstudiosV1Dto.getCastFechaCreacion());
			listPagosOrdenesEstudiosV1.add(pagosOrdenesEstudiosV1); 
			pacientesAtendidos = pacientesAtendidos +1; 
			totalPorCobrar = totalPorCobrar + (float)pagosOrdenesEstudiosV1Dto.getCostoEstudio();  
		}
		
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

	public List<PagosOrdenesEstudiosV1> getListPagosOrdenesEstudiosV1() {
		return listPagosOrdenesEstudiosV1;
	}

	public void setListPagosOrdenesEstudiosV1(List<PagosOrdenesEstudiosV1> listPagosOrdenesEstudiosV1) {
		this.listPagosOrdenesEstudiosV1 = listPagosOrdenesEstudiosV1;
	}

	public int getPacientesAtendidos() {
		return pacientesAtendidos;
	}

	public void setPacientesAtendidos(int pacientesAtendidos) {
		this.pacientesAtendidos = pacientesAtendidos;
	}

	public float getTotalPorCobrar() {
		return totalPorCobrar;
	}

	public void setTotalPorCobrar(float totalPorCobrar) {
		this.totalPorCobrar = totalPorCobrar;
	} 
	
}
