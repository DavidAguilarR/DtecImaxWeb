/**
 * 10012020 se solicita que solo exista una fecha como filtro
 */

package com.dtecimax.ejb.backing.ar;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import com.dtecimax.ejb.model.ar.PagosOrdenesEstudiosV1;
import com.dtecimax.ejb.services.ar.PagosOrdenesEstudiosV1Local;
import com.dtecimax.ejb.services.as.OrdenesEstudiosLocal;
import com.dtecimax.jpa.dto.ar.PagosOrdenesEstudiosV1Dto;

@ManagedBean  
@ViewScoped
public class PagoDeEstudiosForm {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private String searchNomPaci; 
	private String searchApellPatPaci; 
	private String searchApellMatPaci;
	private Date searchFechaDesde;
	private Date searchFechaHasta;
	private int pacientesAtendidos; 
	private float totalPorCobrar; 
	private List<PagosOrdenesEstudiosV1> listPagosOrdenesEstudiosV1 = new ArrayList<PagosOrdenesEstudiosV1>(); 
	private PagosOrdenesEstudiosV1 pagosOrdenesEstudiosV1Selected = new PagosOrdenesEstudiosV1(); 
	
	private BigDecimal monto; 
	private String observDesc; 
	private BigDecimal total; 
	private String observPago; 
	private String infoFactura; 
	private BigDecimal gananciaTotalDia;
	private BigDecimal gastosTotalDia; 
	
	@Inject 
	PagosOrdenesEstudiosV1Local pagosOrdenesEstudiosV1Local;
	
	@Inject 
	OrdenesEstudiosLocal ordenesEstudiosLocal; 
	
	
	 @PostConstruct
	 public void init() {
		 try {
			searchFechaDesde = sdf.parse("08/08/2019");
			searchFechaHasta = sdf.parse("08/08/2019");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	 }
	
	public void filtraPorFechas() {
		System.out.println("Entra filtraPorFechas");
		
		System.out.println("searchNomPaci:"+searchNomPaci);
		System.out.println("searchApellPatPaci:"+searchApellPatPaci);
		System.out.println("searchApellMatPaci:"+searchApellMatPaci);
		System.out.println("searchFechaDesde:"+searchFechaDesde);
		System.out.println("searchFechaHasta:"+searchFechaHasta);
		
		java.sql.Date sqlFechaDesde = new java.sql.Date(searchFechaDesde.getTime());
		/* java.sql.Date sqlFechaHasta = new java.sql.Date(searchFechaHasta.getTime()); 10012020 */
		java.sql.Date sqlFechaHasta = new java.sql.Date(searchFechaDesde.getTime());
		
		List<PagosOrdenesEstudiosV1Dto> listPagosEstudiosV1Dto = pagosOrdenesEstudiosV1Local.findByFiltros(searchNomPaci
																		                                 , searchApellPatPaci
																		                                 , searchApellMatPaci
																		                                 , sqlFechaDesde
																		                                 , sqlFechaHasta
																		                                 );
		listPagosOrdenesEstudiosV1 = new ArrayList<PagosOrdenesEstudiosV1>(); 
		pacientesAtendidos = 0; 
		totalPorCobrar = 0f; 
		gananciaTotalDia = new BigDecimal(0);
		Iterator<PagosOrdenesEstudiosV1Dto> iterPagosEstudiosV1Dto = listPagosEstudiosV1Dto.iterator();
		while(iterPagosEstudiosV1Dto.hasNext()) {
			PagosOrdenesEstudiosV1Dto pagosOrdenesEstudiosV1Dto = iterPagosEstudiosV1Dto.next(); 
			if(null==pagosOrdenesEstudiosV1Dto.getEstatusPago()||"".equals(pagosOrdenesEstudiosV1Dto.getEstatusPago())) {
			PagosOrdenesEstudiosV1 pagosOrdenesEstudiosV1 = new PagosOrdenesEstudiosV1();
			pagosOrdenesEstudiosV1.setNumeroOrden(pagosOrdenesEstudiosV1Dto.getNumeroOrden());
			pagosOrdenesEstudiosV1.setNombreCompletoPaciente(pagosOrdenesEstudiosV1Dto.getNombreCompletoPaciente());
			pagosOrdenesEstudiosV1.setNombreEstudio(pagosOrdenesEstudiosV1Dto.getNombreEstudio());
			pagosOrdenesEstudiosV1.setCostoEstudio(pagosOrdenesEstudiosV1Dto.getCostoEstudio());
			pagosOrdenesEstudiosV1.setCastFechaCreacion(pagosOrdenesEstudiosV1Dto.getCastFechaCreacion());
			pagosOrdenesEstudiosV1.setDescuento(pagosOrdenesEstudiosV1Dto.getDescuento());
			pagosOrdenesEstudiosV1.setDecodeTipoPago(pagosOrdenesEstudiosV1Dto.getDecodeTipoPago());
			float fTotal =0f; 
			if(null!=pagosOrdenesEstudiosV1Dto.getDescuento()) {
				fTotal = pagosOrdenesEstudiosV1Dto.getCostoEstudio()-pagosOrdenesEstudiosV1Dto.getDescuento().floatValue();
			}else {
				fTotal = pagosOrdenesEstudiosV1Dto.getCostoEstudio(); 
			}
			pagosOrdenesEstudiosV1.setTotal(new BigDecimal(fTotal));
			pagosOrdenesEstudiosV1.setDecodeRequiereFactura(pagosOrdenesEstudiosV1Dto.getDecodeRequiereFactura());
			pagosOrdenesEstudiosV1.setInfoFactura(pagosOrdenesEstudiosV1Dto.getInfoFactura());
			listPagosOrdenesEstudiosV1.add(pagosOrdenesEstudiosV1); 
			pacientesAtendidos = pacientesAtendidos +1; 
			totalPorCobrar = totalPorCobrar + (float)pagosOrdenesEstudiosV1Dto.getCostoEstudio();  
			}else {
				gananciaTotalDia = gananciaTotalDia.add(pagosOrdenesEstudiosV1Dto.getTotal());
			}
			
		}
		
		System.out.println("Sale filtraPorFechas");
	}
	
	public void selectForDescuento(PagosOrdenesEstudiosV1 pPagosOrdenesEstudiosV1) {
		pagosOrdenesEstudiosV1Selected = new PagosOrdenesEstudiosV1();
		pagosOrdenesEstudiosV1Selected.setNumeroOrden(pPagosOrdenesEstudiosV1.getNumeroOrden());
	}
	
	public void selectForPago(PagosOrdenesEstudiosV1 pPagosOrdenesEstudiosV1) {
		pagosOrdenesEstudiosV1Selected = new PagosOrdenesEstudiosV1();
		
		float fTotal =0f; 
		if(null!=pPagosOrdenesEstudiosV1.getDescuento()) {
			fTotal = pPagosOrdenesEstudiosV1.getCostoEstudio()-pPagosOrdenesEstudiosV1.getDescuento().floatValue();
		}else {
			fTotal = pPagosOrdenesEstudiosV1.getCostoEstudio(); 
		}
		
		this.total = new BigDecimal(fTotal); 
		pagosOrdenesEstudiosV1Selected.setNumeroOrden(pPagosOrdenesEstudiosV1.getNumeroOrden());
	}
	
	public void selectForInfoFactura(PagosOrdenesEstudiosV1 pPagosOrdenesEstudiosV1) {
		pagosOrdenesEstudiosV1Selected = new PagosOrdenesEstudiosV1();
		pagosOrdenesEstudiosV1Selected.setNumeroOrden(pPagosOrdenesEstudiosV1.getNumeroOrden());
	}
	
	public void aplicarDescuento() {
		System.out.println("Entra aplicarDescuento");
		System.out.println("monto:"+this.monto);
		System.out.println("observDesc:"+this.observDesc);
		System.out.println("NumeroOrden:"+pagosOrdenesEstudiosV1Selected.getNumeroOrden());
		System.out.println("Sale aplicarDescuento");
		
		boolean descuentoIn = false; 
		ordenesEstudiosLocal.aplicarDescuento(pagosOrdenesEstudiosV1Selected.getNumeroOrden()
				                            , this.monto
				                            , this.observDesc
				                            );
		filtraPorFechas();
		descuentoIn = true; 
		PrimeFaces.current().ajax().addCallbackParam("descuentoIn", descuentoIn);
		
	}
	
	public void aplicarPago() {
		boolean pagoIn = false; 
		ordenesEstudiosLocal.aplicarPago(pagosOrdenesEstudiosV1Selected.getNumeroOrden()
				                       , this.total
				                       , this.observPago
				                       );
		filtraPorFechas();
		pagoIn = true; 
		PrimeFaces.current().ajax().addCallbackParam("pagoIn", pagoIn);
	}
	
	public void aplicarInfoFactura() {
		boolean infoFacturaIn = false; 	
		ordenesEstudiosLocal.aplicarInfoFactura(pagosOrdenesEstudiosV1Selected.getNumeroOrden()
				                              , this.infoFactura
				                              );
		filtraPorFechas();
		infoFacturaIn = true; 
		PrimeFaces.current().ajax().addCallbackParam("infoFacturaIn", infoFacturaIn);
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

	public PagosOrdenesEstudiosV1 getPagosOrdenesEstudiosV1Selected() {
		return pagosOrdenesEstudiosV1Selected;
	}

	public void setPagosOrdenesEstudiosV1Selected(PagosOrdenesEstudiosV1 pagosOrdenesEstudiosV1Selected) {
		this.pagosOrdenesEstudiosV1Selected = pagosOrdenesEstudiosV1Selected;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getObservDesc() {
		return observDesc;
	}

	public void setObservDesc(String observDesc) {
		this.observDesc = observDesc;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getObservPago() {
		return observPago;
	}

	public void setObservPago(String observPago) {
		this.observPago = observPago;
	}

	public String getInfoFactura() {
		return infoFactura;
	}

	public void setInfoFactura(String infoFactura) {
		this.infoFactura = infoFactura;
	}

	public BigDecimal getGananciaTotalDia() {
		return gananciaTotalDia;
	}

	public void setGananciaTotalDia(BigDecimal gananciaTotalDia) {
		this.gananciaTotalDia = gananciaTotalDia;
	}

	public BigDecimal getGastosTotalDia() {
		return gastosTotalDia;
	}

	public void setGastosTotalDia(BigDecimal gastosTotalDia) {
		this.gastosTotalDia = gastosTotalDia;
	} 
	
}
