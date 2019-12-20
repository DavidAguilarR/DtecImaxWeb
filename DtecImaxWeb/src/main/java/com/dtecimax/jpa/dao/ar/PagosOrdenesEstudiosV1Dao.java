package com.dtecimax.jpa.dao.ar;

import java.sql.Date;
import java.util.List;

import com.dtecimax.jpa.dto.ar.PagosOrdenesEstudiosV1Dto;

public interface PagosOrdenesEstudiosV1Dao {

	public List<PagosOrdenesEstudiosV1Dto> findByFiltros(String pNombrePaciente
			                                            ,String pApellidoPaterno
			                                            ,String pApellidoMaterno
			                                            ,Date pFechaDesde
			                                            ,Date pFechaHasta
			                                            );
	
}
