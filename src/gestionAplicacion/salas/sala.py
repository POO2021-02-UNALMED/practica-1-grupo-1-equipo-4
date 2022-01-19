from sys import int_info

from gestionAplicacion.cinemas.cine import Cine
from ..salas import Silla
from ..boleteria import Funcion


class Sala:


    def __init__(self,filas,columnas,filasvip,precio,cine):
        
        self._numero : int
        self._sillas : list[Silla] = []
        self._funciones : list[Funcion] = []

        self._filas : int = filas
        self._columnas : int = columnas
        self._filasvip : int = filasvip
        self._precio : float = precio
        self._cine : Cine = cine

        self.crearSilleteria()
        cine.agregarSala(self)

        self._numero = cine.getSalas().size()

    #
    #Metodos
    #

    def agregarFuncion(self, funcion: Funcion):
        self._funciones.append(funcion)

    def verificarDisponibilidad(self, *args: tuple) -> bool:
        #sobrecarga de metodos chambona 
        if(len(args) == 3):

            dia, mes, hora = args

            consulta: str = str(dia)+"/"+str(mes)+"/"+str(hora)

            fechasfunciones: list[str] = []

            for func in self._funciones:
                info: str = func.getDia()+"/"+func.getMes()+"/"+func.getHorario();
                fechasfunciones.append(info)
                
                info = ""

            for i in fechasfunciones:
                if ( i == consulta):
                    return False
            
            return True
        
        
        elif(len(args) == 2):

            dia, mes = args
            
	
            consulta : str = ""+str(dia)+str(mes);					
            
            fechas: list[str] = []	

            horarios: list[str] = [] 

            
            disponibles: list[str]= ["12:00","14:00","16:00","18:00","20:00","22:00"]				
            
            for func in self._funciones:

                info : str=""+func.getDia()+func.getMes()		
                fechas.append(info)								
                info=""								
                
            
            
            for i in range(len(fechas)):
                if (fechas[i] ==    consulta):				
                    horarios.append(self._funciones[i].getHorario())
                
            
            
            for horario in horarios:
                disponibles.remove(horario);		
            
            
            respuesta=""
            
            for d in disponibles:
                respuesta+=d+"\n";
            
            
            return respuesta == "12:00\n14:00\n16:00\n18:00\n20:00\n22:00\n"
        
        return False

    
    def almenosUnoDisponible(self, dia: int, mes: int) ->  bool:
	    

		consulta: str   =   ""  + str(dia) + str(mes)					
		
		fechas : list[str] = []	 
														

		horarios: list[str] = []
												 
	
		disponibles: list[str] = ["12:00","14:00","16:00","18:00","20:00","22:00"];				
		
		for func in self._funciones:
			info : str =""+str(func.getDia())+str(func.getMes())	
			fechas.append(info)							
			info = ""								
			
		
		for i in range(len(fechas)):
			if (fechas[i] == consulta) 
				horarios.append(self._funciones[i].getHorario());	

		
		for horario in horarios:
			disponibles.remove(horario)
		
		
		respuesta : str ="";
		
		for d in disponibles:
			respuesta+=d+"\n"
		
		
		if(len(respuesta) >= 5):
			return True
		else:
			return False
		
	


    def getNumero(self):
        return self._numero
    def setNumero(self, numero):
        self._numero = numero


    def getFilas(self):
        return self._filas
    def setFilas(self, filas):
        self._filas = filas

    def getColumnas(self):
        return self._columnas
    def setColumnas(self, columnas):
        self._columnas = columnas


    def getFilasvip(self):
        return self._filasvip
    def setFilasvip(self, filasvip):
        self._filasvip = filasvip


    def getPrecio(self):
        return self._precio
    def setPrecio(self, precio):
        self._precio = precio


    def getCine(self):
        return self._cine
    def setCine(self, cine):
        self._cine = cine


    def getSillas(self):
        return self._sillas
    def setSillas(self, sillas):
        self._sillas = sillas


    def getFunciones(self):
        return self._funciones
    def setFunciones(self, funciones):
        self._funciones = funciones