package com.dtecimax.ejb.services.as;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import com.dtecimax.jpa.dao.as.OrdenesEstudiosVDao;
import com.dtecimax.jpa.dto.as.OrdenesEstudiosVDto;

@Stateless 
public class OrdenesEstudiosVLocalImpl implements OrdenesEstudiosVLocal {

	@Inject 
	OrdenesEstudiosVDao ordenesEstudiosVDao; 
	
	@Override
	public List<OrdenesEstudiosVDto> findOneYearOld() {
		return ordenesEstudiosVDao.findOneYearOld(); 
	}

	@Override
	public OrdenesEstudiosVDto findByNumeroOrden(long pNumeroOrden) {
		return ordenesEstudiosVDao.findByNumeroOrden(pNumeroOrden);
	}

	@Override
	public List<OrdenesEstudiosVDto> findByEstaus(String pEstatus,int pTipoOrden) {
		return ordenesEstudiosVDao.findByEstaus(pEstatus,pTipoOrden);
	}
	
	

}
