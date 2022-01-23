from boleteria import Funcion, Pelicula, Horario
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
        programadas : list[] = []
        funciones : list[Funcion] = self.verFuncion(mes)
        peliculasMes : list[Pelicula] = list(set([funcion.getPelicula() for funcion in funciones]))
        pelicula_boletos = []
        disponibles = ["22:00","20:00","18:00","16:00","14:00","12:00"]

        for i in range(len(peliculasMes)):
            pelicula_boletos.append([peliculasMes,0])
            for funcion in funciones:
                if(peliculasMes[i] == funcion.getPelicula()):
                    pelicula_boletos[i][1]+= funcion.getCantidadboletosvendidos()
                   
        pelicula_boletos.sort(key = lambda x:x[1], reverse = True)
		
        if(len(peliculasMes)):
            for i in range(6):
                p : Pelicula = peliculasMes[i]
                h : Horario = Horario.getHorario(disponibles[i])
                programadas.append( Funcion.crearFuncion(dia, mes, h, p , sala.getNumero(), self))
        else:
            for i in range(len(peliculasMes)):
                p : Pelicula = peliculasMes[i]
                h : Horario = Horario.getHorario(disponibles[i])
                programadas.append(Funcion.crearFuncion(dia, mes, h, p , sala.getNumero(), self))
        
        return programadas



           
    
    def salasDisponibles(self, mes: int, dia: int) -> list:
        disponibles=list()
        
        for sala in self._salas:
            if sala.almenosUnoDisponible(mes, dia):
                disponibles.append(sala)
        
        return disponibles

    def


	


	
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