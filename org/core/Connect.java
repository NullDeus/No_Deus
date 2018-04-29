package org.core;

import org.dreambot.api.methods.MethodContext;

public abstract class Connect {

	  private final MethodContext context;
	  
	    public Connect(MethodContext context) {
	        this.context = context;
	    }
	 
	    public abstract int priority();
	    
	 
	    public abstract boolean validate();
	 
	    public abstract int execute();
	 
	    public MethodContext g() {
	        return context;
	    }
}
