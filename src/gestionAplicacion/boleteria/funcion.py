from code import interact
from hashlib import new
from tkinter import NONE
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
    def crearFuncion(self,dia:int,mes:int,horario:Horario,pelicula:Pelicula,num_sala:int,cine:Cine): #devuelve una funcion o none
        self._sala:Sala=cine.buscarSala(num_sala)
        if(self._sala!=None):
            if(self._sala.verificarDisponibilidad(dia,mes,horario.getHora())):
                return Funcion(dia,mes,horario,pelicula,self._sala,cine)
            else:
                return None
        else:
            return None
    

    def crearBoleteria(self):
        sillas:list[Silla]=self._sala.getSillas()
        disponibles:int=self._sala.cantidadSillas()
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


    def getCantidadboletosvendidos(self):
        return self._cantidadBoletosVendidos
    def setCantidadboletosvendidos(self, cantidadBoletosVendidos):
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