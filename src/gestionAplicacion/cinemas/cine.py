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