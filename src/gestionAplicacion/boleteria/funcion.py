from code import interact
from hashlib import new
from tkinter import NONE
from xmlrpc.client import Boolean

from gestionAplicacion.boleteria.horario import Horario
from gestionAplicacion.boleteria.boleto import Boleto
from gestionAplicacion.salas.sala import Sala

class Funcion:
    cantidadFunciones = 1

    def __init__(self,dia,mes,horario,pelicula,sala,cine):
        self._boletos = []
        self._dia:int = dia
        self._mes:int = mes
        self._horario = horario
        self._pelicula = pelicula
        self._numero:int = Funcion.cantidadFunciones
        self.setSala(sala)
        self.setCine(cine)
        cine.agregarFuncion(self)
        sala.agregarFuncion(self)
        self._cantidadBoletosVendidos:int=0
        Funcion.cantidadFunciones+=1
        self.crearBoleteria()

    @classmethod
    def crearFuncion(cls,dia:int,mes:int,horario:Horario,pelicula,num_sala:int,cine): #devuelve una funcion o none
        cls._sala:Sala=cine.buscarSala(num_sala)
        if(cls._sala!=None):
            if(cls._sala.verificarDisponibilidad(dia,mes,horario.getHora())):
                return Funcion(dia,mes,horario,pelicula,cls._sala,cine)
            else:
                return None
        else:
            return None

    @classmethod
    def formatearFunciones(cls,funciones):

        resultado = ""
        for funcion in funciones:
            formato = "{}|{}|{}|{}"
            fecha = "Fecha: " + "{:>02d}/{:>02d}".format(funcion.getDia(),funcion.getMes())
            resultado += str(funcion.getPelicula().getNombre())+" "+str(funcion.getPelicula().getClasificacion())+"+"+"\n"

            resultado += formato.format(funcion.getHorario().getHora().center(6),
                                        ("Sala "+str(funcion.getSala().getNumero())).center(8),
                                        str(funcion.getSala().getTipo()).center(4),
                                        "{:>3d}".format(funcion.getNumero()).center(5))
            resultado +=  "\n" + fecha
            resultado += "\n\n"
        return resultado

    def crearBoleteria(self):
        sillas = self._sala.getSillas()
        disponibles:int=self._sala.cantidadSillas() #ignorar, catidadSillas() se encuentra en cada subclase, por lo que acá marca el error
        total:int=len(self._sala.getSillas())
        for i in range(total):
            if(disponibles>0):
                boleto:Boleto= Boleto(self,sillas[i])
                self._boletos.append(boleto)
                disponibles-=1
    
    def verDisponibilidad(self):
        # En este caso la funcion devuelve una lista de strings, para agregar cada strings
        total =[]

        for boleto in self._boletos:
            if boleto!=None:
                tupla_boleto:tuple=(boleto.isDisponibilidad(),boleto.tipoString()+str(boleto.getNum_silla()))
                total.append(tupla_boleto)

        return total

    def VentaBoleto(self,boleto,cliente)->bool:
        if (boleto.isDisponibilidad()==True and cliente.getEdad()>=self.getPelicula().getClasificacion()):
            boleto.setDisponibilidad(False)
            cliente.getHistorialCompras().append(boleto)
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