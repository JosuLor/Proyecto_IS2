package org.business_logic;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;

public class BusinessLogicFactory {
	
	public BLFacade getBusinessLogicFromFactory(boolean logicMode) {
		
		BLFacade appFacadeInterface = null;
		
		if (logicMode) {
			// server
			ConfigXML c=ConfigXML.getInstance();
			
			String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
			 
			try {
				URL url;
				url = new URL(serviceName);
				QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
				Service service = Service.create(url, qname);

				appFacadeInterface = service.getPort(BLFacade.class);

			} catch (MalformedURLException e) {
				e.printStackTrace();
				System.out.println("Error obtaining remote businessLogic");
			}	 

		} else {
			// local businessLogic
			
			appFacadeInterface = new BLFacadeImplementation(false);
		}
		
		return appFacadeInterface;
	}
}
