package it.polito.tdp.corsi.model;

import java.util.*;
import it.polito.tdp.corsi.db.*;

public class Model {
	
	private CorsoDAO dao;
	
	public Model() {
		super();
		dao = new CorsoDAO();
	}

	public List <Corso> getCorsiPeriodo (int pd) {
		return dao.getCorsiPeriodo(pd);
	}

	public Map <Corso, Integer> getNumStudentiCorsiPeriodo (int pd) {
		return dao.getNumStudentiCorsiPeriodo(pd);
	}
}
