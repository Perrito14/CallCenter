package ar.edu.unlam.pb1.finalAgosto;

public class Contacto {
	private String nombre;
	private String apellido;
	private int celular;
	private String email;
	private String direccion;
	private int codigoPostal;
	private String localidad;
	private Provincia provincia;
	private boolean esCliente = false;
	private boolean deseaSerLlamadoNuevamente = true;
	
	
	public Contacto(String nombre, String email, int codigoPostal) {
		this.nombre = nombre;
		this.email = email;
		this.codigoPostal = codigoPostal;
	}

	public static boolean esEmailValido(String eMail) {
		boolean emailValido = false;
		if(eMail.contains("@") && eMail.contains(".")) {
			emailValido = true;
		}
		return emailValido;
	}
	
	public boolean registrarNuevaLlamada(Llamada nueva) {
		
		if(nueva.isFueExitosa()) {
			this.setEsCliente(true);
			this.setDeseaSerLlamadoNuevamente(false);
			nueva.setFueExitosa(true);
		} else if(!nueva.isFueExitosa()) {
			this.setEsCliente(false);
			nueva.setFueExitosa(false);
			
			if(this.deseaSerLlamadoNuevamente) {
				this.setDeseaSerLlamadoNuevamente(true);
			} else {
				this.setDeseaSerLlamadoNuevamente(false);
			}
		}
//		Una vez que el operador realiza la llamada debe registrar la misma:
//			a. Si la llamada fue exitosa (en ese caso el contacto pasa a ser cliente, y no se lo debe volver a
//			llamar).
//			b. Si el contacto no desea ser llamado nuevamente (la llamada pudo no haber sido exitosa, pero se
//			haga un nuevo intento en el futuro).
		
//			c. Observaciones: Texto libre donde el operador deja registro de lo conversado.
		/*
		 * Registra una nueva llamada asociada al contacto actual.
		 */
		return nueva.isFueExitosa();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public boolean isEsCliente() {
		return esCliente;
	}

	public void setEsCliente(boolean esCliente) {
		this.esCliente = esCliente;
	}

	public boolean isDeseaSerLlamadoNuevamente() {
		return deseaSerLlamadoNuevamente;
	}

	public void setDeseaSerLlamadoNuevamente(boolean deseaSerLlamadoNuevamente) {
		this.deseaSerLlamadoNuevamente = deseaSerLlamadoNuevamente;
	}

	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", apellido=" + apellido + ", celular=" + celular + ", email=" + email
				+ ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", localidad=" + localidad
				+ ", provincia=" + provincia + ", esCliente=" + esCliente + ", deseaSerLlamadoNuevamente="
				+ deseaSerLlamadoNuevamente + "]";
	}
	
	
	
}
