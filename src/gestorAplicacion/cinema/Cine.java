//Funcionalidad de la clase: TODO

//Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa

package gestorAplicacion.cinema;
import java.io.Serializable;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import gestorAplicacion.boleteria.*;
import gestorAplicacion.salas.Sala;
import gestorAplicacion.salas.Sala2D;
//import gestorAplicacion.cinema.*;


public class Cine implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nombre;
	private List<Cliente> clientes= new ArrayList<Cliente>();
	private List<Funcion> cartelera= new ArrayList<Funcion>();
	private List<Pelicula> peliculas= new ArrayList<Pelicula>();
	private List<Sala> salas = new ArrayList<Sala>();
	private float dineroGanado;
	private static final float descuentoMVC=(float)0.4;	//Descuento al cliente mas fiel
	
	//Constructor
	public Cine(String nombre) {
		this.nombre = nombre;
	}
	
	
	//
	//methods
	//
	public ArrayList<Funcion> programarFuncionesAuto(int mes,int dia, Sala sala){
		/*
		 Recibe los parametros mes, dia y sala, devuelve una lista de funciones. Su proposito es recibir un dia, un mes y una sala para 
		 programar de forma automatica en esa sala, para todos los horarios disponibles de acuerdo al numero de peliculas con mayor 
		 cantidad de boletos vendidos, una funcion para ese dia.
		 */
		ArrayList<Funcion> programadas = new ArrayList<Funcion>();
		ArrayList<Funcion> funciones = verFuncion(mes);	//realizo una lista de las funciones dadas ese mes
		List<Pelicula> peliculasMes = new ArrayList<Pelicula>();
		
		for(Funcion funcion: funciones){		 
			peliculasMes.add(funcion.getPelicula()); //obtengo una lista de las peliculas dadas ese mes
		}

		peliculasMes = peliculasMes.stream().distinct().collect(Collectors.toList()); //borro las repeticiones

		Object[][] pelicula_boletos = new Object[peliculasMes.size()][2];

		// lista que obtiene las peliculas por la cantidad de boletos vendidos por esta pelicula dicho mes
		// 
		for(int i = 0; i < peliculasMes.size() ; i++){		// se recorre la lista pelicula
			pelicula_boletos[i][0] = peliculasMes.get(i);	// se asigna al primer elemeto de la fila  i el valor de la pelicula
			pelicula_boletos[i][1] = 0;					// se asigna al segundo elemento de la fila i un 0

			for(Funcion funcion: funciones){			// se recorren las funciones de dicho mes para 
														// hacer cuenta de cuantas boletas ha vendido la pelicula i

				if( peliculasMes.get(i) == funcion.getPelicula()){
					pelicula_boletos[i][1] = (int) pelicula_boletos[i][1] + funcion.getCantidadBoletosVendidos(); // se suman la cantidad de boletos
				}																								  // 
			}
		}

		Arrays.sort(pelicula_boletos, (b, a) -> Integer.compare((int) a[1],(int) b[1])); //ordeno de mayor a menor por ventas

		// reasigno los valores de peliculas por ventas de mayor a menor 
		for(int i = 0; i < peliculasMes.size() ; i++){
			peliculasMes.set(i, (Pelicula) pelicula_boletos[i][0]);
		}

		ArrayList<String> disponibles= new ArrayList<>(Arrays.asList("22:00","20:00","18:00","16:00","14:00","12:00"));
		
		//??? Aqui podriamos hacer otra sobrecarga si contamos que el verificar verifique que la sala tenga gafas 
		/*Sala salaAuto=null;
		//Se busca una sala sala disponible para ese dia y mes
		for(Sala sala: salas) { //
			if(sala.verificarDisponibilidad(dia, mes)) {
				salaAuto=sala;
				break;
			}
		}*/
		// Con la sala disponible creamos funcion automatica para las peliculas organizadas por ventas y se terminan por agregar a la lista programadas  
		if(peliculasMes.size()>=6){ 				//Se revisa que hacer para cuando la cantidad de peliculas es menor a la cantidad de horarios disponibles
			for(int i=0;i<6;i++) {
				Pelicula p =peliculasMes.get(i);
				Horario h= Horario.getHorario(disponibles.get(i));
				programadas.add(Funcion.crearFuncion(dia,mes, h, p , sala.getNumero(), this));
			}
		}else{
			for(int i=0;i<peliculasMes.size();i++) {
				Pelicula p =peliculasMes.get(i);
				Horario h= Horario.getHorario(disponibles.get(i));
				programadas.add(Funcion.crearFuncion(dia,mes, h, p , sala.getNumero(), this));
			}
		}
		
		return programadas;


	}
	
	public ArrayList<Sala> salasDisponibles(int mes, int dia){
		/*
		 Recibe un mes y un dia y retorna una lista de salas que tengan al menos un horario disponible ese dia
		 */
		ArrayList<Sala> disponibles=new ArrayList<>();
		for(Sala sala: salas) { 
			if(sala.almenosUnoDisponible(dia, mes)) {
				disponibles.add(sala);
			}
		}
		
		return disponibles;
	}


	
	public String mostValueClient() {
		/*
		 Recibe nada y retorna una String con el nombre del cliente mas fiel al que le fue
		 aplicado el descuento. Su proposito es calcular el cliente que mas compras ha hecho
		 para dar un descuento del 0.4.
		 */
		List<Integer> clienteList=new ArrayList<Integer>();
		
		for(Cliente cliente: clientes) {
			clienteList.add(cliente.getHistorialCompras().size()); 	//Recorre el historial de compras del cliente y anexa el tamano de su historial de compra
		}
		
		int valormax=Collections.max(clienteList);		//Se establece el mayor numero de boletos comprados por parte de un cliente
		
		for (Cliente cliente: clientes) {
			
			if (valormax==cliente.getHistorialCompras().size()) {	//Si la cantidad de boletos comprados es igual a valor max conseguir el nombre de este
				cliente.setDescuento(descuentoMVC);
				return cliente.getNombre();
			}
		}
		return "Se ha aplicado el descuentos  a nuestro cliente mas fiel ";
	}

	
	
	//Al 10 por ciento de los clientes mas fieles aplicarle un 10% de descuento a cada uno de ellos 
	
	public List<Cliente> clientesValiosos() {
		/*
		 Recibe nada y retorna una List des objetos tipo Cliente. Su proposito es calcular
		 de entre la lista de clientes el 0.1 que tiene mayor cantidad de compras en historialCompras
		 */
		List<Integer> clienteList=new ArrayList<Integer>();		//Aca estaran los tamanos de historial de compra de cada cliente
		
		for(Cliente cliente: clientes) {
			clienteList.add(cliente.getHistorialCompras().size()); 	//Recorre el historial de compras del cliente y anexa el tamano de su historial de compra
		}
		
		int cantidad= clienteList.size();					//Cantidad de clientes que se tiene
		
		Collections.sort(clienteList, Collections.reverseOrder());		//Ordenar la lista de mayor a menor
				
		int top10= Math.round(cantidad/10);								// El 10% de los clientes 
		List<Cliente> mejoresCompas=new ArrayList<Cliente>();			//Clientes mas fieles
			
		for(int i=0; i<=top10; i++) {						
			int valor =clienteList.get(i);								//Conseguir el 10% de los tamanos de historial de compra mas grandes
		
			for(Cliente cliente: clientes) {
			
			if(cliente.getHistorialCompras().size()==valor) {				//Si el tamano de historial de compra es igual al valor agregar a los mejores clientes (mejoresCompas)
				mejoresCompas.add(cliente);
			}
		}
		}
		return mejoresCompas;
	}
	

	
	
	public String rifarBoleto(int numeroFuncion) {
		/*
		 Recibe el numero de la funcion que se va a rifar y retorna un String con el ganador. 
		 Su proposito es de entre los clientes valiosos, rifar un boleto a una funcion deseada
		 */
		
		List<Cliente> top10= clientesValiosos();	//Saco la lista del 10% de los clientes mas fieles
		
		int tamano= top10.size();			//tamano de esa lista
		
		int ganador= (int)(Math.random()*tamano);	//Un numero aleatorio entre 0 y el tamano de la lista
		
		Cliente panitaGanador= top10.get(ganador);	// Con el numero conseguido sacar al cliente escogido
		
		//int aleatoriofuncion= (int)(Math.random()*cartelera.size());	//Numero aleatorio de la lista de cartelera
		
		Funcion fescogida= BuscadorFuncion(numeroFuncion);	//Funcion escogida de la cartelera con el numero de la funcion
		
		int aleatorioboleto= (int)(Math.random()*fescogida.getSala().cantidadSillas());	//Otro numero aleatorio con base al tamano de la lista de boletos
		
		Boleto bescogido= fescogida.getBoletos().get(aleatorioboleto);	//Boleto escogido con el numero aleatorio
		
		if(bescogido.isDisponibilidad()==true) {	//Si este boleto esta disponible se puede rifar ese boleto al cliente ganador
			
			fescogida.VentaBoleto(bescogido,panitaGanador);
			fescogida.setCantidadBoletosVendidos(fescogida.getCantidadBoletosVendidos()-1);		// Cada vez que se aplica la venta de boletos se suma al atributo, como se esta rifando
														// Se tendria que anular esa suma
		}

		
		else {
			//Se puede ser muy demalas y que se escoja aleatoriamente un boleto que ya esta comprado
			
			for (Boleto boleto: fescogida.getBoletos()) {
				
				if (boleto.isDisponibilidad()==true) {	//Se va recorriendo los boletos de la funcion escogida aleatoriamente 
														//hasta encontrar el primero disponible 
					
					fescogida.VentaBoleto(boleto, panitaGanador);	//Se vende el boleto
					fescogida.setCantidadBoletosVendidos(fescogida.getCantidadBoletosVendidos()-1);	
					break;		//Solo voy a rifar uno entonces rompo el for
				}
			}
			
		}
		return panitaGanador.getNombre();
	}
	
	
	public ArrayList<Funcion> verFuncion(Pelicula pelicula, int dia, int mes) { 
		/*
		 Recibe una pelicula, un dia y un mes, retorna un ArrayList de funciones que cumplan las
		 indicaciones. Su proposito es obtener las funciones disponibles en la cartelera de acuerdo
		 al dia y siguientes dias del mes y de los siguientes meses
		 */

		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		
		for(Funcion funcion:cartelera) { //Se agregan las funciones que para ese mes cumplan con el dia y los dias de ese mes
			if(funcion.getPelicula()==pelicula && funcion.getDia()>=dia && funcion.getMes()==mes ){
				funciones.add(funcion);
			}
		}
		
		for(Funcion funcion:cartelera) { //Se agregan todas las funciones de los siguientes meses
			if(funcion.getPelicula()==pelicula && funcion.getMes()>mes ){
				funciones.add(funcion);
			}
		}

		return funciones;
	}
	

	public ArrayList<Funcion> verFuncion(Cliente cliente) { 
		/*
		 Recibe un cliente, retorna un ArrayList de funciones que cumplan las indicaciones. 
		 Su proposito es obtener las funciones disponibles en la cartelera de acuerdo
		 al genero mas visto de el cliente ingresado
		 */
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula().getGenero()== cliente.mostWatchedGenre()){	//Compara genero mas visto del historial de compra del cliente
				funciones.add(funcion);											//con los generos del las peliculas en cartelera
			}
		}
		return funciones;
	}
	


	public ArrayList<Funcion> verFuncion(Pelicula pelicula, String horario) {				
		/*
		 Recibe una pelicula y un horario, retorna un ArrayList de funciones que cumplan las
		 indicaciones. Su proposito es obtener las funciones disponibles en la cartelera de acuerdo
		 al horario y la pelicula solicitada
		 */
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getHorario()==horario){	
				funciones.add(funcion);
			}
		}
		return funciones;
	}
	


	public ArrayList<Funcion> verFuncion(Pelicula pelicula, String horario, int dia, int mes) {		
		/*
		 Recibe una pelicula y un horario, retorna un ArrayList de funciones que cumplan las
		 indicaciones. Su proposito es obtener las funciones disponibles en la cartelera de acuerdo
		 al horario y la pelicula solicitada
		 */
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getHorario()==horario && funcion.getDia()==dia && funcion.getMes()==mes){
				funciones.add(funcion);
			}
		}
		return funciones;
	}

	

	public ArrayList<Funcion> verFuncion( int dia, int mes) { 
		/*
		 Recibe un dia y un mes, retorna un ArrayList de funciones que cumplan las
		 indicaciones. Su proposito es obtener las funciones disponibles en la cartelera de acuerdo
		 al dia y al mes solicitado
		 */
		ArrayList<Funcion> funciones = new ArrayList<Funcion>(); 
		for(Funcion funcion:cartelera) {
			if( funcion.getDia()==dia && funcion.getMes()==mes){
				funciones.add(funcion);
			}
		}
		return funciones;
	}



	public ArrayList<Funcion> verFuncion(int mes) {	
		/*
		 Recibe un mes, retorna un ArrayList de funciones que cumplan las
		 indicaciones. Su proposito es obtener las funciones disponibles en la cartelera de acuerdo
		 al mes solicitado
		 */
		ArrayList<Funcion> funciones = new ArrayList<Funcion>(); 
		for(Funcion funcion:cartelera) {
			if( funcion.getMes()==mes) {
				funciones.add(funcion);
			}
		}
		return funciones;
	}
	


	public ArrayList<Funcion> verFuncion(Boleto boleto) {  
		/*
		 Recibe un objeto de tipo Boleto, retorna un ArrayList de funciones que cumplan las
		 indicaciones. Su proposito es obtener las funciones disponibles en la cartelera de acuerdo
		 a la funcion que se relaciona con este objeto boleto
		 */
		ArrayList<Funcion> funciones = new ArrayList<Funcion>(); 
		for(Funcion funcion:cartelera) {
			if(funcion==boleto.getFuncion()) {
				funciones.add(funcion);
			}
		}
		return funciones;
	}




	/*
	 Metodos para agregar elementos a las listas de la clase Cine
	 */
	public void agregarCliente(Cliente nuevo) {
		clientes.add(nuevo);
	}
	public void agregarPelicula(Pelicula nuevo) {
		peliculas.add(nuevo);
	}
	public void agregarSala(Sala nuevo){
		salas.add(nuevo);
	}
	public void agregarFuncion(Funcion nuevo){
		cartelera.add(nuevo);
	}

	
	public Boolean verificarCliente(int num) {
		/*
		 Recibe un numero de cedula, retorna un boolean. Su proposito es verificar
		 que un cliente este en la lista de clientes de acuerdo a su numero de cedula
		 */
		ArrayList<Integer> lista = new ArrayList<Integer>();   //lista de cedulas
		for(Cliente cliente: clientes) {
			lista.add(cliente.getCedula());
		}
		
		if (lista.contains(num)) {		//Si el numero esta contenido de la lista de todas las cedulas si esta registrado
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	public Cliente BuscadorCliente(int num) {
		/*
		 Recibe un numero de cedula, retorna un objeto de clase Cliente. Su proposito es retornar el
		 cliente cuyo numero de cedula concuerde con el ingresado al metodo
		 */
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		for(Cliente cliente: clientes) {
			lista.add(cliente.getCedula());
		
			if (cliente.getCedula()==num) {
			
				return cliente;
			}
		}
		return null ;
	}
	
	
	
	public Funcion BuscadorFuncion(int numero) {
		/*
		 Recibe un numero de funcion, retorna un objeto de clase Funcion. Su proposito es retornar la
		 funcion cuyo numero de cedula concuerde con el ingresado al metodo
		 */
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		for(Funcion funcion: cartelera) {
			
			lista.add(funcion.getNumero());
			
			if (funcion.getNumero()==numero) {	//Si el numero de la funcion es igual al numero que se ingreso es la que se estaba buscando
				return funcion;
			}
		}
		return null ;
	}
	
	
	
	public Boleto BuscadorBoleto(int num_silla,Funcion funcion) {
		/*
		 Recibe un numero de silla y una funcion, retorna un objeto de clase Boleto. Su proposito es retornar el
		 boleto cuyo numero de silla asociado a una funcion ingresada concuerde con el ingresado.
		 */
		ArrayList<Integer> lista= new ArrayList<Integer>();

		for (Boleto boleto : funcion.getBoletos()) {
			
			lista.add(boleto.getNum_silla());
			
			if(boleto.getNum_silla()==num_silla) {			//Si el numero que se ingreso concuerda con el numero de la silla del boleto 
															//es el que se estaba buscando
				return boleto;
			}
		}

		return null;
	}
	
	
	public Pelicula BuscadorPelicula(String nombre) {
		/*
		 Recibe el nombre de una pelicula, retorna un objeto de clase Pelicula. Su proposito es retornar la
		 pelicula cuyo nombre coincida con el nombre ingresado
		 */
		ArrayList<String> lista = new ArrayList<String>();
		for(Funcion funcion: cartelera) {
			lista.add(funcion.getPelicula().getNombre());
			if (funcion.getPelicula().getNombre().equals(nombre)) {
				return funcion.getPelicula();
			}
		}
		return null ;
	}

	
	public Sala buscarSala(int num) {
		/*
		 Recibe el numero de una sala, retorna un objeto de clase Sala. Su proposito es retornar la
		 sala cuyo numero coincida con el numero ingresado
		 */
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for(Sala sala: salas) {
			lista.add(sala.getNumero());
		}
		
		if (lista.contains(num)) {
			return salas.get(lista.indexOf(num));
		}else {
			return null;
		}
		
	}
	
	//
	//getting and setting
	//
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public List<Funcion> getCartelera() {
		return cartelera;
	}
	public void setCartelera(List<Funcion> cartelera) {
		this.cartelera = cartelera;
	}
	
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}
	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	public float getDineroGanado() {
		return dineroGanado;
	}
	public void setDineroGanado(float dineroGanado) {
		this.dineroGanado = dineroGanado;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}


	public static float getDescuentomvc() {
		return descuentoMVC;
	}

}
