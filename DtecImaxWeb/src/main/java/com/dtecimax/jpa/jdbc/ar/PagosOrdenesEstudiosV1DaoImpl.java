package com.dtecimax.jpa.jdbc.ar;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.dtecimax.jpa.dao.ar.PagosOrdenesEstudiosV1Dao;
import com.dtecimax.jpa.dto.ar.PagosOrdenesEstudiosV1Dto;

@Stateless 
public class PagosOrdenesEstudiosV1DaoImpl implements PagosOrdenesEstudiosV1Dao {

	@PersistenceContext(unitName = "DTECIMAXPU") 
	EntityManager em;
	
	@Override
	public List<PagosOrdenesEstudiosV1Dto> findByFiltros(String pNombrePaciente
			                                           , String pApellidoPaterno
			                                           , String pApellidoMaterno
			                                           , Date pFechaDesde
			                                           , Date pFechaHasta) {
		List<PagosOrdenesEstudiosV1Dto> retval = null; 
		String strQuery = "SELECT p FROM PagosOrdenesEstudiosV1Dto p WHERE 1=1";
		if(null!=pNombrePaciente&&!"".equals(pNombrePaciente)) {
			strQuery = strQuery+" AND p.nombrePaciente like '%"+pNombrePaciente+"%'";
		}
		if(null!=pApellidoPaterno&&!"".equals(pApellidoPaterno)) {
			strQuery = strQuery+" AND p.apellidoPaternoPaciente like '%"+pApellidoPaterno+"%'";
		}
		if(null!=pApellidoMaterno&&!"".equals(pApellidoMaterno)) {
			strQuery = strQuery+" AND p.apellidoMaternoPaciente like '%"+pApellidoMaterno+"%'";
		}
		strQuery = strQuery+" AND p.castFechaCreacion BETWEEN :pFechaDesde AND :pFechaHasta";
		Query query = em.createQuery(strQuery);
		query.setParameter("pFechaDesde", pFechaDesde, TemporalType.DATE);
		query.setParameter("pFechaHasta", pFechaHasta, TemporalType.DATE);
		retval = query.getResultList(); 
		return retval;
	}

}
