package com.dtecimax.ejb.services.ar;

import javax.ejb.Local;

import com.dtecimax.jpa.dto.ar.AsGastosDto;

@Local
public interface AsGastosLocal {

	public void insertAsGasto(AsGastosDto pAsGastosDto);
	
}
