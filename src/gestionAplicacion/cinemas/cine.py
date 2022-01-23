from boleteria import Funcion, Pelicula
from salas import Sala

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


    def programarFuncionesAuto( self, mes: int, dia: int, sala: Sala):
        programadas : list[Funcion] = []
        funciones : list[Funcion] = self.verFuncion(mes)
        peliculaMes : list[Pelicula] = list(set([funcion.getPelicula() for funcion in funciones]))
        pelicula_boletos = []

        for i in range(len(peliculaMes)):
            pelicula_boletos.append([peliculaMes,0])
            for funcion in funciones:
                if(peliculaMes[i] == funcion.getPelicula()):
                    pelicula_boletos[i][1]+= funcion.getCantidadboletosvendidos()
                   
        pelicula_boletos.sort(key = lambda x:x[1], reverse = True)
		
        #TODO verificar que se hace en este for, y realizarlo
        """// reasigno los valores de peliculas por ventas de mayor a menor 
		for(int i = 0; i < peliculasMes.size() ; i++){
			peliculasMes.set(i, (Pelicula) pelicula_boletos[i][0]);
		}"""
           
    
    def salasDisponibles(self, mes: int, dia: int) -> list:
        disponibles=list()
        
        for sala in self._salas:
            if sala.almenosUnoDisponible(mes, dia):
                disponibles.append(sala)
        
        return disponibles

    def mostValueClient(self) -> str:
        clienteList=list()
        for client in self._clientes:
            clienteList.append(len(client.getHistorialCompras()))





	


	
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