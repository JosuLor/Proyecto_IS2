package gui;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.business_logic.BLFacade;
import org.business_logic.BLFacadeImplementation;
import org.business_logic.BusinessLogicFactory;

import configuration.ConfigXML;
import dataAccess.DataAccessGertaerakSortu;

public class ApplicationLauncher { 
	
	
	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();
	
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		
		MainGUI a=new MainGUI();
		a.setVisible(false);
		
		MainUserGUI b = new MainUserGUI(); 
		b.setVisible(true);
	
		try {
			BusinessLogicFactory blfactory = new BusinessLogicFactory();
			boolean blmode = false;
			
			BLFacade appFacadeInterface = blfactory.getBusinessLogicFromFactory(blmode);
			
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			MainGUI.setBussinessLogic(appFacadeInterface);
			
		} catch (Exception e) {
			a.jLabelSelectOption.setText("Error: "+e.toString());
			a.jLabelSelectOption.setForeground(Color.RED);	
			
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
	}

}
