class Sala:
    def __init__(self,numero,filas,columnas,filasvip,precio,cine,sillas,funciones):
        self._numero = numero
        self._filas = filas
        self._columnas = columnas
        self._filasvip = filasvip
        self._precio = precio
        self._cine = cine
        self._sillas = sillas
        self._funciones = funciones



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