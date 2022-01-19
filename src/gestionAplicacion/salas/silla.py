class Silla:
    def __init__(self,tipo,numero,precio):
        self._tipo = tipo
        self._numero = numero
        self._precio = precio
        #!!! Aqui falta la variable del serializador



    #
    #Getting and setting
    #
    def getTipo(self):
        return self._tipo
    def setTipo(self, tipo):
        self._tipo = tipo


    def getNumero(self):
        return self._numero
    def setNumero(self, numero):
        self._numero = numero


    def getPrecio(self):
        return self._precio
    def setPrecio(self, precio):
        self._precio = precio