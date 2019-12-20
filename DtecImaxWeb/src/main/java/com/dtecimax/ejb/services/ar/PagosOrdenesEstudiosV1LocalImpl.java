package com.dtecimax.ejb.services.ar;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.dtecimax.jpa.dao.ar.PagosOrdenesEstudiosV1Dao;
import com.dtecimax.jpa.dto.ar.PagosOrdenesEstudiosV1Dto;

@Stateless 
public class PagosOrdenesEstudiosV1LocalImpl implements PagosOrdenesEstudiosV1Local {

	@Inject
	PagosOrdenesEstudiosV1Dao pagosOrdenesEstudiosV1Dao;

	@Override
	public List<PagosOrdenesEstudiosV1Dto> findByFiltros(String pNombrePaciente
			                                           , String pApellidoPaterno
			                                           , String pApellidoMaterno
			                                           , Date pFechaDesde
			                                           , Date pFechaHasta) {
		return pagosOrdenesEstudiosV1Dao.findByFiltros(pNombrePaciente
				                                     , pApellidoPaterno
				                                     , pApellidoMaterno
				                                     , pFechaDesde
				                                     , pFechaHasta);
	}
	
}
