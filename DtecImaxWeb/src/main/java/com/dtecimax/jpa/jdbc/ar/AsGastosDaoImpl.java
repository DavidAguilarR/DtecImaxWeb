package com.dtecimax.jpa.jdbc.ar;

import java.math.BigInteger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dtecimax.jpa.dao.ar.AsGastosDao;
import com.dtecimax.jpa.dto.ar.AsGastosDto;

@Stateless 
public class AsGastosDaoImpl implements AsGastosDao {

	@PersistenceContext(unitName = "DTECIMAXPU") 
	EntityManager em;
	
	@Override
	public void insertAsGasto(AsGastosDto pAsGastosDto) {
	
		Query q = em.createNativeQuery("SELECT NEXT VALUE FOR dbo.AS_GASTOS_S");
		BigInteger lAsGastosS = (BigInteger)q.getSingleResult();
		pAsGastosDto.setNumero(lAsGastosS.longValue());
		em.persist(pAsGastosDto);
		
	}

}
