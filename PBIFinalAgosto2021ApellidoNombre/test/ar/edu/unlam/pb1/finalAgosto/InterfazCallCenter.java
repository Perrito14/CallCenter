package ar.edu.unlam.pb1.finalAgosto;

import java.util.Scanner;

public class InterfazCallCenter {
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Bienvenido al callcenter");
		
		Empresa empresa = new Empresa("Oeste Cable Color");
		Contacto[] contactos = new Contacto[50];
		Contacto nuevoCliente = null;
		
		int respuesta = 0;
		
		
		while(respuesta != 5) {
			
			do {
				System.out.println("************************");
				System.out.println("Menu de opciones\n");
				System.out.println("1 - Incorporar zona de cobertura");
				System.out.println("2 - Dar de alta un nuevo contacto");
				System.out.println("3 - Realizar nueva llamada");
				System.out.println("4 - Ver informaci�n del contacto");
				System.out.println("5 - Salir");
				System.out.println("************************");
				respuesta = scanner.nextInt();
				
				if(respuesta < 1 || respuesta > 5) {
					System.out.println("Error, ingrese un valor válido nuevamente");
				}
				
			} while(respuesta < 1 && respuesta > 5);
			switch (respuesta) {
			case 1:
				incorporarZonaDeCobertura(scanner, empresa);
				break;
			
			case 2:
				darDeAltaNuevoContacto(scanner, empresa, contactos);
				break;
			
			case 3:
				realizarNuevaLlamada(scanner, empresa);
				break;
			
			case 4:
				verInformacionDelContacto(scanner, contactos);
				break;
			
			case 5:
				System.out.println("Gracias por la visita");
				break;
			}
		}
		
		
		scanner.close();
	}

	private static void verInformacionDelContacto(Scanner scanner, Contacto[] contactos) {
		System.out.println("Ingrese el nombre del contacto que desea ver");
		String nombreContacto = scanner.next();
		for(int i = 0; i < contactos.length; i++) {
			if(contactos[i] != null && contactos[i].getNombre().equalsIgnoreCase(nombreContacto)) {
				System.out.println(contactos[i].toString());
				break;
			}
		}
	}

	private static void realizarNuevaLlamada(Scanner scanner, Empresa empresa) {
		//Encontramos al candidato y lo mostramos
		System.out.println("Ingrese el nombre del candidato que busca");
		String nombreBuscado = scanner.next();
		Contacto candidato = empresa.buscarCandidato(nombreBuscado);
		System.out.println(candidato.toString());
		Llamada llamada = null;
		int respuestaOperador = 0;
		System.out.println("La llamada fue exitosa? 1-SI | 2-NO");
		 respuestaOperador = scanner.nextInt();
		int respuestaCandidato = 0;
		do {
			//En caso de respuesta positiva, el candidato es cliente y no lo llamamos más
			if(respuestaOperador == 1) {
				candidato.setEsCliente(true);
				candidato.setDeseaSerLlamadoNuevamente(false);
				llamada = new Llamada(true, "LLAMADA EXITOSA");
				candidato.registrarNuevaLlamada(llamada);
				break;
			//En caso de respuesta negativa, el candidato no es cliente, pero ...
			}else if(respuestaOperador == 2) {
				candidato.setEsCliente(false);
				llamada = new Llamada(false, "LLAMADA FALLIDA");
				
				//Le preguntamos esto para saber si desea ser llamado o no
				System.out.println("El candidato desea ser llamado nuevamente? 1-SI | 2-NO");
				respuestaCandidato = scanner.nextInt();
				
				do {
					
					//En caso de respuesta positiva, desea ser llamado
					if(respuestaCandidato == 1) {
						candidato.setDeseaSerLlamadoNuevamente(true);
						candidato.registrarNuevaLlamada(llamada);
						break;
					}//En caso de respuesta negativa, no desea ser llamado
					else if(respuestaCandidato == 2) {
						candidato.setDeseaSerLlamadoNuevamente(false);
						candidato.registrarNuevaLlamada(llamada);
						break;
					
					} //Ingresó un número incorrecto
					else {
						System.out.println("Ingresaste un numero incorrecto");
					}
				}while(respuestaCandidato != 1 || respuestaCandidato != 2);
				
			} else {
				System.out.println("Ingresaste un numero incorrecto");
			}
		} while(!(respuestaOperador == 1 || respuestaOperador == 2) || !(respuestaCandidato == 1 || respuestaCandidato == 2));
		
		candidato.registrarNuevaLlamada(llamada);
	}

	private static void darDeAltaNuevoContacto(Scanner scanner, Empresa empresa, Contacto[] contactos) {
		Contacto nuevoCliente;
		System.out.println("Ingrese el nombre del cliente");
		String nombreCliente = scanner.next();
		
//				System.out.println("Ingrese el apellido del cliente");
//				String apellidoCliente = scanner.next();
		
//				System.out.println("Ingrese el celular del cliente Compuesto del codigo de pais + codigo de area + numero de celular");
//				int celularCliente = scanner.nextInt();
		
		String email = "";
		do {
			System.out.println("Ingrese el email del cliente");
			email = scanner.next();
			
			if(!Contacto.esEmailValido(email)) {
				System.out.println("Email invalido");
			} else {
				System.out.println("Email validado correctamente");
			}
		} while(!Contacto.esEmailValido(email));
		
		
//				System.out.println("Ingrese la direccion del cliente");
//				String direccion = scanner.next();
		
		System.out.println("Ingrese el codigo postal del cliente");
		int codigoPostal = scanner.nextInt();
		
		nuevoCliente = new Contacto(nombreCliente, email, codigoPostal);
		for(int i = 0; i < contactos.length; i++) {
			
			if(contactos[i] == null) {
				contactos[i] = nuevoCliente;
				empresa.agregarNuevoContacto(contactos[i]);
				break;
			}
		}
	}

	private static void incorporarZonaDeCobertura(Scanner scanner, Empresa empresa) {
		System.out.println("Ingrese un codigo para agregar una nueva zona de cobertura");
		int zonaDeCobertura = scanner.nextInt();
		if(empresa.agregarNuevaZonaDeCobertura(zonaDeCobertura)) {
			empresa.agregarNuevaZonaDeCobertura(zonaDeCobertura);
			System.out.println("Zona incorporada con exito");
		} else if(!empresa.agregarNuevaZonaDeCobertura(zonaDeCobertura)) {
			System.out.println("Error");
		}
	}
}
