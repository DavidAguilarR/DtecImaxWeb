package com.dtecimax.ejb.services.ar;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.dtecimax.jpa.dao.ar.AsGastosDao;
import com.dtecimax.jpa.dto.ar.AsGastosDto;

@Stateless
public class AsGastosLocalImpl implements AsGastosLocal {

	@Inject 
	AsGastosDao asGastosDao;
	
	@Override
	public void insertAsGasto(AsGastosDto pAsGastosDto) {
		asGastosDao.insertAsGasto(pAsGastosDto);
	}

}
