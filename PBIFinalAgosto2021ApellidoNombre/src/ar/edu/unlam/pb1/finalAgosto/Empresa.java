package ar.edu.unlam.pb1.finalAgosto;

public class Empresa {
	private String nombre;
	private Contacto[] contactos = new Contacto[50];
	private int[] zonasDeCobertura = new int[50];
	
	
	public Empresa(String nombre) {
		this.nombre = nombre;
	}
		
	public boolean agregarNuevoContacto(Contacto nuevo) {
		int i = 0;
		boolean contactoAgregado = false;
		
		while(i < contactos.length && !contactoAgregado) {
			if(contactos[i] == null ) {
				contactos[i] = nuevo;
			} else {
				i++;
			}
		}
		return contactoAgregado;
	}
	
	public String getNombreEmpresa() {
		return nombre;
	}

	public boolean agregarNuevaZonaDeCobertura(int codigoPostal) {
		int i = 0;
		boolean zonaAgregada = false;
		while(i < zonasDeCobertura.length && !zonaAgregada) {
			if(zonasDeCobertura[i] == 0) {
				zonasDeCobertura[i] = codigoPostal;
				zonaAgregada = true;
			}else {
				i++;
			}
		}
		return zonaAgregada;
	}	
	
	private boolean elCodigoPostalEstaDentroDeLaZonaDeCobertura(int codigoPostal) {
		boolean estaDentroDeLaZonaDeCobertura = false;
		for(int i = 0; i < zonasDeCobertura.length; i++) {
			if(zonasDeCobertura[i] != 0 && zonasDeCobertura[i] == codigoPostal) {
				estaDentroDeLaZonaDeCobertura = true;
			}
		}
		return estaDentroDeLaZonaDeCobertura;
	}
	
	public Contacto buscarCandidato(String nombre) {
		Contacto[] candidatos = new Contacto[contactos.length];
		
		for(int i = 0; i < contactos.length; i++) {
			if(contactos[i] != null && contactos[i].getNombre().equalsIgnoreCase(nombre) && !contactos[i].isEsCliente() && contactos[i].isDeseaSerLlamadoNuevamente()
			&& this.elCodigoPostalEstaDentroDeLaZonaDeCobertura(contactos[i].getCodigoPostal())) {
				candidatos[i] = contactos[i];
			}
		}
		
		int numeroAleatorio = (int)Math.random() * candidatos.length;
		Contacto candidatoElegido = null;
		
		for(int i = 0; i < candidatos.length; i++) {
			if(candidatos[numeroAleatorio] != null) {
				candidatoElegido = candidatos[numeroAleatorio];
				break;
			}
		}

		return candidatoElegido;
	}
}
