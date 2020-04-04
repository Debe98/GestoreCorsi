package it.polito.tdp.corsi;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnElenco;

    @FXML
    private Button btnNumIscritti;

    @FXML
    private Button btnIscritti;

    @FXML
    private Button btnDivisione;

    @FXML
    private TextField txtPeriodo;

    @FXML
    private TextField txtCorso;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doDivisione(ActionEvent event) {

    }

    @FXML
    void doElenco(ActionEvent event) {
    	String rawPd = txtPeriodo.getText();
    	int pd;
    	
    	try {
    		pd = Integer.parseInt(rawPd);
    	} catch (Exception e) {
			txtRisultato.setText("Errore, "+rawPd+" non è valido\n("+e.getMessage()+")");
			txtPeriodo.setText("");
			return;
		}
    	if (!(pd == 1 || pd == 2)) {
    		txtRisultato.setText("Errore, "+pd+" non è un periodo valido (Inserire 1 o 2)");
    		txtPeriodo.setText("");
			return;
    	}
    		
    	List <Corso> corsi = GestoreCorso.getCorsiPeriodo(pd);
    	String s = "Corsi del periodo: "+pd+"\n";
    	
    	for(Corso c : corsi) {
    		s+= c.toString()+"\n";
    	}
    	txtRisultato.setText(s);
    }

    @FXML
    void doIscritti(ActionEvent event) {

    }

    @FXML
    void doNumIscritti(ActionEvent event) {
    	String rawPd = txtPeriodo.getText();
    	int pd;
    	
    	try {
    		pd = Integer.parseInt(rawPd);
    	} catch (Exception e) {
			txtRisultato.setText("Errore, "+rawPd+" non è valido\n("+e.getMessage()+")");
			txtPeriodo.clear();
			return;
		}
    	if (!(pd == 1 || pd == 2)) {
    		txtRisultato.setText("Errore, "+pd+" non è un periodo valido (Inserire 1 o 2)");
    		txtPeriodo.clear();
			return;
    	}
    		
    	Map <Corso, Integer> numCorsi = GestoreCorso.getNumStudentiCorsiPeriodo(pd);
    	String s = "Corsi del periodo: "+pd+"\n";
    	
    	for(Corso c : numCorsi.keySet()) {
    		s+= "(#"+ numCorsi.get(c)+ ") "+ c.toString()+"\n";
    	}
    	txtRisultato.setText(s);
    }

    @FXML
    void initialize() {
        assert btnElenco != null : "fx:id=\"btnElenco\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumIscritti != null : "fx:id=\"btnNumIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscritti != null : "fx:id=\"btnIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisione != null : "fx:id=\"btnDivisione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    private Model GestoreCorso;
	public void setModel(Model m) {
		GestoreCorso = m;		
	}    
}
