from ..salas import Tipo

class Silla:
    def __init__(self,tipo,numero):
        self._tipo:Tipo = tipo
        self._numero:int = numero
        #!!! Aqui falta la variable del serializador



    #
    #Getting and setting
    #
    def getTipo(self):
        return self._tipo

    def setTipo(self, tipo:str):
        if (tipo=="VIP"):
            self._tipo=Tipo.VIP            ##Esta raro revisar el enum
        else:
            self._tipo=Tipo.SENCILLA


    def getNumero(self):
        return self._numero
    def setNumero(self, numero):
        self._numero = numero


    def getPrecio(self):
        if(self._tipo==Tipo.VIP):       #Revisar enum
            return 7000
        else:
            return 5000

    def setPrecio(self, precio):
        self._precio = precio