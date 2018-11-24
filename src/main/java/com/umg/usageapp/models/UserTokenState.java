package com.umg.usageapp.models;


public class UserTokenState {
    private String access_token;
    private Long expires_in;
    private User user;
    private Empresa empresa;
    private Puesto puesto;


    
    
    
    
    public UserTokenState(String access_token, Long expires_in, User user, Empresa empresa, Puesto puesto) {
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.user = user;
		this.empresa = empresa;
		this.puesto = puesto;
	}

    
    
    
    public UserTokenState() {
        this.access_token = null;
        this.expires_in = null;
        this.user = null;
        this.puesto = null;
        this.empresa = null;

    }


    
    
    


	public UserTokenState(String access_token, long expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}




	public Empresa getEmpresa() {
		return empresa;
	}




	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}




	public Puesto getPuesto() {
		return puesto;
	}




	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}
	
	
    
}