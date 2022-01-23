from code import interact
from hashlib import new
from tkinter import NONE
from xmlrpc.client import Boolean
from ..cinemas import Cine, Cliente
from ..salas import Sala,Silla
from ..boleteria import Horario, Pelicula, Boleto

class Funcion:
    cantidadFunciones:int

    def __init__(self,dia,mes,horario,pelicula,sala,cine):
        self._dia:int = dia
        self._mes:int = mes
        self._horario:"Horario" = horario
        self._pelicula:"Pelicula" = pelicula
        self._numero:int = Funcion.cantidadFunciones
        self.setSala(sala)
        self.setCine(cine)
        self.crearBoleteria()
        cine.agregarFuncion(self)
        sala.agregarFuncion(self)
        self._boletos:list[Boleto]=[]
        self._cantidadBoletosVendidos:int=0
        Funcion.cantidadFunciones+=1

    @classmethod
    def crearFuncion(cls,dia:int,mes:int,horario:Horario,pelicula:Pelicula,num_sala:int,cine:Cine): #devuelve una funcion o none
        cls._sala:Sala=cine.buscarSala(num_sala)
        if(cls._sala!=None):
            if(cls._sala.verificarDisponibilidad(dia,mes,horario.getHora())):
                return Funcion(dia,mes,horario,pelicula,cls._sala,cine)
            else:
                return None
        else:
            return None
    

    def crearBoleteria(self):
        sillas:list[Silla]=self._sala.getSillas()
        disponibles:int=self._sala.cantidadSillas() #ignorar, catidadSillas() se encuentra en cada subclase, por lo que acá marca el error
        total:int=len(self._sala.getSillas())
        for i in range(total):
            if(disponibles>0):
                boleto:Boleto= Boleto(self,sillas[i])
                self._boletos.append(boleto)
                disponibles-=1
    
    def verDisponibilidad(self)->str:
        total:list[list[str]]=[]

        for i in range(self._sala.getFilas()):
            fila:list[str]=[]
            for j in range(self._sala.getColumnas()):
                boleto:Boleto=self._boletos[i*self._sala.getColumnas()+j]
                if boleto!=None:
                    formato_boleto:str=boleto.disponibilidad()+boleto.tipoString()+str(boleto.getNum_silla())
                    fila.append(formato_boleto)
                else:
                    formato_boleto:str=""
                    fila.append(formato_boleto)
            total.append(fila)

        formato:str=""

        """ for para formatear un string con la silletería para imprimir
		for(ArrayList<String> fila: total){							
																	//%-6s es una variable que recibe string y se llena con minimo 6 espacios y justifica a la izquierda
			String patron = "%-6s   ".repeat(sala.getColumnas()); 	// se crea el formato con la cantidad de variables necesarias (columnas)
			Object[] fila_args = fila.toArray(new String[0]); 		// se crea una lista para pasar como *args
			formato += String.format(patron,fila_args) + "\n"; 		// se le agrega a el string resultante la fila mas un salto de linea
		}
		formato += "\n"+centerString(sala.getColumnas()*9,"PANTALLA")+"\n"; //se le agrega al string final una linea con la palabra PANTALLA centrado
		return formato;"""

    def VentaBoleto(self,boleto:Boleto,cliente:Cliente)->bool:
        if (boleto.getDisponibilidad()==True and cliente.getEdad()>=self.getPelicula().getClasificacion()):
            boleto.setDisponibilidad(False)
            cliente.getHistorialcompras().append(boleto)
            self._cantidadBoletosVendidos+=1
            boleto.calcularPrecioDefinitivo(cliente)

            ganancia:float=self._cine.getDineroGanado()+boleto.getPrecioTotal()

            self._cine.setDineroGanado(ganancia)
            self._pelicula.anadirCantidadBoletos()

            return True
        else:
            return False


        


    def getDia(self):
        return self._dia
    def setDia(self, dia):
        self._dia = dia


    def getMes(self):
        return self._mes
    def setMes(self, mes):
        self._mes = mes


    def getHorario(self):
        return self._horario
    def setHorario(self, horario):
        self._horario = horario


    def getPelicula(self):
        return self._pelicula
    def setPelicula(self, pelicula):
        self._pelicula = pelicula


    def getSala(self):
        return self._sala
    def setSala(self, sala):
        self._sala = sala


    def getBoletos(self):
        return self._boletos
    def setBoletos(self, boletos):
        self._boletos = boletos


    def getCantidadBoletosVendidos(self):
        return self._cantidadBoletosVendidos
    def setCantidadBoletosVendidos(self, cantidadBoletosVendidos):
        self._cantidadBoletosVendidos = cantidadBoletosVendidos


    def getCine(self):
        return self._cine
    def setCine(self, cine):
        self._cine = cine


    def getCantidadfunciones(self):
        return self._cantidadFunciones
    def setCantidadfunciones(self, cantidadFunciones):
        self._cantidadFunciones = cantidadFunciones


    def getNumero(self):
        return self._numero
    def setNumero(self, numero):
        self._numero = numero