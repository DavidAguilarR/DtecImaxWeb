package com.dtecimax.ejb.services.ar;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import com.dtecimax.jpa.dto.ar.PagosOrdenesEstudiosV1Dto;

@Local
public interface PagosOrdenesEstudiosV1Local {

	public List<PagosOrdenesEstudiosV1Dto> findByFiltros(String pNombrePaciente
											            ,String pApellidoPaterno
											            ,String pApellidoMaterno
											            ,Date pFechaDesde
											            ,Date pFechaHasta
											            );
	
}
