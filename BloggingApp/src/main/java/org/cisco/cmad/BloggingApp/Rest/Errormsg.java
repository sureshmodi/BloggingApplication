package org.cisco.cmad.BloggingApp.Rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Errormsg {

	private String errormsg;
	
	public Errormsg() {
		
	}

	public Errormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	
	
}
