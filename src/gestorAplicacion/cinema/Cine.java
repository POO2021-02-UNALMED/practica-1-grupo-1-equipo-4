package gestorAplicacion.cinema;
import java.io.Serializable;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import gestorAplicacion.boleteria.*;
import gestorAplicacion.salas.Sala;
import gestorAplicacion.salas.Sala2D;
//import gestorAplicacion.cinema.*;

//!!!
public class Cine implements Serializable{
	private static final long serialVersionUID = 1L;
	//!!! same question about BD
	private String nombre;//??? 
	private List<Cliente> clientes= new ArrayList<Cliente>();
	private List<Funcion> cartelera= new ArrayList<Funcion>();
	private List<Pelicula> peliculas= new ArrayList<Pelicula>();
	private List<Sala> salas = new ArrayList<Sala>();
	private float dineroGanado;
	private static final int descuentoMVC=40;	//Descuento al cliente mas fiel
	
	//Constructor
	public Cine(String nombre) {
		this.nombre = nombre;
	}
	
	
	//
	//methods
	//
	public void programarFuncionesAuto(int mes,int dia){
		//debo hacer una lista de las peliculas mas vendidas en funciones de 3 dias antes
		
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
		
		//??? Aquí podríamos hacer otra sobrecarga si contamos que el verificar verifique que la sala tenga gafas 
		Sala salaAuto=null;
		//Se busca una sala sala disponible para ese dia y mes
		for(Sala sala: salas) { //
			if(sala.verificarDisponibilidad(dia, mes)) {
				salaAuto=sala;
				break;
			}
		}
		// Con la sala disponible creamos funcion automatica para las peliculas organizadas por ventas 
		for(int i=0;i<6;i++) {
			Pelicula p =peliculasMes.get(i);
			Horario h= Horario.getHorario(disponibles.get(i));
			Funcion.crearFuncion(dia,mes, h, p , salaAuto.getNumero(), this);
			
		}

	}//TODO: Considerar el tema de los generos podría pensarse en el futuro
	


	//Reflejar cliente mas fiel y aplicarle el descuento de 40%
	public String mostValueClient() {
		
		List<Integer> clienteList=new ArrayList<Integer>();
		
		for(Cliente cliente: clientes) {
			clienteList.add(cliente.historialCompras.size()); 	//Recorre el historial de compras del cliente y anexa el tamaï¿½o de su historial de compra
		}
		
		int valormax=Collections.max(clienteList);		//Se establece el mayor numero de boletos comprados por parte de un cliente
		
		for (Cliente cliente: clientes) {
			
			if (valormax==cliente.historialCompras.size()) {	//Si la cantidad de boletos comprados es igual a valor max conseguir el nombre de este
				cliente.setDescuento(descuentoMVC);
				return cliente.getNombre();
			}
		}
		return "Se ha aplicado el descuentos  a nuestro cliente mas fiel ";
	}

	
	
	//Al 10 por ciento de los clientes mas fieles aplicarle un 10% de descuento a cada uno de ellos 
	
	public List<Cliente> clientesValiosos() {
		
		List<Integer> clienteList=new ArrayList<Integer>();		//Aca estaran los tamanos de historial de compra de cada cliente
		
		for(Cliente cliente: clientes) {
			clienteList.add(cliente.historialCompras.size()); 	//Recorre el historial de compras del cliente y anexa el tamano de su historial de compra
		}
		
		int cantidad= clienteList.size();					//Cantidad de clientes que se tiene
		
		Collections.sort(clienteList, Collections.reverseOrder());		//Ordenar la lista de mayor a menor
				
		int top10= Math.round(cantidad/10);								// El 10% de los clientes 
		List<Cliente> mejoresCompas=new ArrayList<Cliente>();			//Clientes mas fieles
			
		for(int i=0; i<=top10; i++) {						
			int valor =clienteList.get(i);								//Conseguir el 10% de los tamanos de historial de compra mas grandes
		
			for(Cliente cliente: clientes) {
			
			if(cliente.historialCompras.size()==valor) {				//Si el tamano de historial de compra es igual al valor agregar a los mejores compas
				mejoresCompas.add(cliente);
			}
		}
		}
		return mejoresCompas;
	}
	
	//Rifa de un boletos entre los clientes mas fieles
	
	
	public void rifarBoleto() {
		
		List<Cliente> top10= clientesValiosos();
		
		int tamano= top10.size();
		
		int ganador= (int)(Math.random()*tamano);
		
		Cliente panitaGanador= top10.get(ganador);
		
		
	}
	
	
	public ArrayList<Funcion> verFuncion(Pelicula pelicula, int dia, int mes) { //Ver funciones luego de un dï¿½a de un mes de una pelï¿½cula
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getDia()>=dia && funcion.getMes()==mes ){
				funciones.add(funcion);
			}
		}
		
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getMes()>mes ){
				funciones.add(funcion);
			}
		}

		return funciones;
	}
	

	public ArrayList<Funcion> verFuncion(Cliente cliente) { //Ver funcion por las recomendaciones del cliente
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula().getGenero()== cliente.mostWatchedGenre()){	//Compara genero mas visto del historial de compra del cliente
				funciones.add(funcion);											//con los generos del las peliculas en cartelera
			}
		}
		return funciones;
	}
	


	public ArrayList<Funcion> verFuncion(Pelicula pelicula, String horario) {				//Mostrar funciones de una pelicula en un horario especifico
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getHorario()==horario){
				funciones.add(funcion);
			}
		}
		return funciones;
	}
	


	public ArrayList<Funcion> verFuncion(Pelicula pelicula, String horario, int dia, int mes) {		//Mostrar funcion en una fecha y hora de una pelicula en especifico
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		for(Funcion funcion:cartelera) {
			if(funcion.getPelicula()==pelicula && funcion.getHorario()==horario && funcion.getDia()==dia && funcion.getMes()==mes){
				funciones.add(funcion);
			}
		}
		return funciones;
	}

	

	public ArrayList<Funcion> verFuncion( int dia, int mes) { //peliculas del dia 
		ArrayList<Funcion> funciones = new ArrayList<Funcion>(); 
		for(Funcion funcion:cartelera) {
			if( funcion.getDia()==dia && funcion.getMes()==mes){
				funciones.add(funcion);
			}
		}
		return funciones;
	}



	public ArrayList<Funcion> verFuncion(int mes) {	//Mostrar funcion por el mes
		ArrayList<Funcion> funciones = new ArrayList<Funcion>(); 
		for(Funcion funcion:cartelera) {
			if( funcion.getMes()==mes) {
				funciones.add(funcion);
			}
		}
		return funciones;
	}
	


	public ArrayList<Funcion> verFuncion(Boleto boleto) {  //Mostrar funcion por boleto
		ArrayList<Funcion> funciones = new ArrayList<Funcion>(); 
		for(Funcion funcion:cartelera) {
			if(funcion==boleto.getFuncion()) {
				funciones.add(funcion);
			}
		}
		return funciones;
	}




	//
	//Metodos para agregar elementos a las listas
	//
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

	// funcion para ver si el cliente ya se encuentar inscrito en el cine
	public Boolean verificarCliente(int num) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for(Cliente cliente: clientes) {
			lista.add(cliente.getCedula());
		}
		
		if (lista.contains(num)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	// funcion para encontrar el cliente ingresando la cï¿½dula
	public Cliente BuscadorCliente(int num) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for(Cliente cliente: clientes) {
			lista.add(cliente.getCedula());
			if (cliente.getCedula()==num) {
				return cliente;
			}
		}
		return null ;
	}
	
	
	//
	//metodo para encontrar una funcion
	public Funcion BuscadorFuncion(int numero) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for(Funcion funcion: cartelera) {
			lista.add(funcion.getNumero());
			if (funcion.getNumero()==numero) {
				return funcion;
			}
		}
		return null ;
	}
	
	
	//metodo para encontrar un boleto con su id y una funcion determinada
	public Boleto BuscadorBoleto(int num_silla,Funcion funcion) {
		ArrayList<Integer> lista= new ArrayList<Integer>();

		for (Boleto boleto : funcion.getBoletos()) {
			lista.add(boleto.getNum_silla());
			if(boleto.getNum_silla()==num_silla) {
				return boleto;
			}
		}

		return null;
	}
	
	
	// funcion para encontrar la pelicula ingresando el nombre
	public Pelicula BuscadorPelicula(String nombre) {
		ArrayList<String> lista = new ArrayList<String>();
		for(Pelicula pelicula: peliculas) {
			lista.add(pelicula.getNombre());
			if (pelicula.getNombre()==nombre) {
				return pelicula;
			}
		}
		return null ;
	}

	// metodo para obtener la sala por numero
	public Sala buscarSala(int num) {
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


	public static int getDescuentomvc() {
		return descuentoMVC;
	}

}
