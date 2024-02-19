package com.aca.springdata.springProject.generators;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

@SuppressWarnings("serial")
public class CustomIdGenerator implements IdentifierGenerator {

	//private BigDecimal id = BigDecimal.ZERO;
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		/*id = id.add(BigDecimal.ONE);
		return id;*/
		
		/*BigDecimal id = BigDecimal.ZERO;
		BigDecimal tmp = BigDecimal.ZERO;
		List<User> ids = repository.findAll();
		if (ids.isEmpty()) {
			id = BigDecimal.ONE;
		}else {
			for(int i = 0; i < ids.size(); i++) {
				tmp = ids.get(i).getUserId();
				if (tmp.compareTo(id) == 1) {
					id = tmp;
				}
			}
			id = id.add(BigDecimal.ONE);
		}
		
		return id;*/
		
		Random random = null;
		int id = 0;
		random = new Random();
		id = random.nextInt(50);
		return new BigDecimal(id);
	}

}
