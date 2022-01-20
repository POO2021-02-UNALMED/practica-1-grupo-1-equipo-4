from tokenize import String
from gestionAplicacion.cinemas import Cine

class Pelicula:
    def __init__(self,nombre="",genero="",duracion=0,lenguaje="",clasificacion=0,  cine= None): #!!! pendiente para corregir
        self._nombre:str = nombre
        self._genero:str = genero
        self._duracion:int = duracion
        self._lenguaje:str = lenguaje
        self._clasificacion:int = clasificacion
        self._cine: Cine = cine #!!! pendiente para corregir
        self._cantidadTotalBoletosVendidos:int =0
        self._expectativaVentas:int =0

    def anadirCantidadBoletos(self):
        self._cantidadTotalBoletosVendidos+=1


    def getNombre(self)->str:
        return self._nombre
    def setNombre(self, nombre):
        self._nombre = nombre


    def getGenero(self)->str:
        return self._genero
    def setGenero(self, genero):
        self._genero = genero


    def getDuracion(self)->int:
        return self._duracion
    def setDuracion(self, duracion):
        self._duracion = duracion


    def getLenguaje(self)->str:
        return self._lenguaje
    def setLenguaje(self, lenguaje):
        self._lenguaje = lenguaje


    def getClasificacion(self):
        return self._clasificacion
    def setClasificacion(self, clasificacion):
        self._clasificacion = clasificacion


    def getCine(self)->Cine:
        return self._cine
    def setCine(self, cine):
        self._cine = cine