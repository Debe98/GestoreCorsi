package it.polito.tdp.corsi.model;

import java.util.*;
import it.polito.tdp.corsi.db.*;

public class Model {
	
	private CorsoDAO cDao;
	private StudenteDAO sDao;
	
	public Model() {
		super();
		cDao = new CorsoDAO();
		sDao = new StudenteDAO();
	}

	public List <Corso> getCorsiPeriodo (int pd) {
		return cDao.getCorsiPeriodo(pd);
	}

	public Map <Corso, Integer> getNumStudentiCorsiPeriodo (int pd) {
		return cDao.getNumStudentiCorsiPeriodo(pd);
	}
	
	public List <Studente> getStudentiCorso(String codins) {
		return sDao.getStudentiCorso(codins);
	}
}
