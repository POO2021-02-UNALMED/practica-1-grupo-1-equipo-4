from boleteria import Funcion
class Cine: 

    def __init__(self,nombre,clientes,cartelera,peliculas,salas,dineroGanado,DESCUENTOMVC):
        self._nombre = nombre
        self._clientes = clientes
        self._cartelera = cartelera
        self._peliculas = peliculas
        self._salas = salas
        self._dineroGanado = dineroGanado
        self._DESCUENTOMVC = DESCUENTOMVC
        #TODO:Take care of this constant
        #!!! Aqui falta la variable del serializador


    def programarFuncionesAuto(self,mes:int,dia:int,sala:int):
        programadas:list(Funcion)=[]   #Recibiria funcion
        funciones= verFuncion(mes)     #Recibe objetos funcion   
		ArrayList<Funcion> programadas = new ArrayList<Funcion>();
		ArrayList<Funcion> funciones = verFuncion(mes);	
		List<Pelicula> peliculasMes = new ArrayList<Pelicula>();
		
		for(Funcion funcion: funciones){		 
			peliculasMes.add(funcion.getPelicula()); 
		

		peliculasMes = peliculasMes.stream().distinct().collect(Collectors.toList()); 

		Object[][] pelicula_boletos = new Object[peliculasMes.size()][2];


		for(int i = 0; i < peliculasMes.size() ; i++){		
			pelicula_boletos[i][0] = peliculasMes.get(i);	
			pelicula_boletos[i][1] = 0;					

			for(Funcion funcion: funciones){			
				
				if( peliculasMes.get(i) == funcion.getPelicula()){
					pelicula_boletos[i][1] = (int) pelicula_boletos[i][1] + funcion.getCantidadBoletosVendidos(); 
				}																								  
			}
		}

		Arrays.sort(pelicula_boletos, (b, a) -> Integer.compare((int) a[1],(int) b[1])); 

		
		for(int i = 0; i < peliculasMes.size() ; i++){
			peliculasMes.set(i, (Pelicula) pelicula_boletos[i][0]);
		}

		ArrayList<String> disponibles= new ArrayList<>(Arrays.asList("22:00","20:00","18:00","16:00","14:00","12:00"));
		
		 
		/*Sala salaAuto=null;
		for(Sala sala: salas):
			if(sala.verificarDisponibilidad(dia, mes)) {
				salaAuto=sala;
				break;
			}
		}
		  
		if(peliculasMes.size()>=6){ 				
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
    #
    #Getting and setting
    #
    def getNombre(self):
        return self._nombre
    def setNombre(self, nombre):
        self._nombre = nombre

    def getClientes(self):
        return self._clientes
    def setClientes(self, clientes):
        self._clientes = clientes


    def getCartelera(self):
      return self._cartelera
    def setCartelera(self, cartelera):
      self._cartelera = cartelera


    def getPeliculas(self):
     return self._peliculas
    def setPeliculas(self, peliculas):
        self._peliculas = peliculas


    def getSalas(self):
        return self._salas
    def setSalas(self, salas):
        self._salas = salas


    def getDineroganado(self):
        return self._dineroGanado
    def setDineroganado(self, dineroGanado):
        self._dineroGanado = dineroGanado


    def getDescuentomvc(self):
        return self._DESCUENTOMVC
    def setDescuentomvc(self, DESCUENTOMVC):
        self._DESCUENTOMVC = DESCUENTOMVC