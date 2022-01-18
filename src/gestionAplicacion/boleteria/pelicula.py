from gestionAplicacion import cinemas.Cine

class Pelicula:
    def __init__(self,nombre="",genero="",duracion=0,lenguaje="",clasificacion=0,cine=None):
        self._nombre = nombre
        self._genero = genero
        self._duracion = duracion
        self._lenguaje = lenguaje
        self._clasificacion = clasificacion
        self._cine = cine
        self._cantidadTotalBoletosVendidos=0
        self._expectativaVentas=0

    @staticmethod
    def anadirCantidadBoletos(self):
        self._cantidadTotalBoletosVendidos+=1


    def getNombre(self):
        return self._nombre
    def setNombre(self, nombre):
        self._nombre = nombre


    def getGenero(self):
        return self._genero
    def setGenero(self, genero):
        self._genero = genero


    def getDuracion(self):
        return self._duracion
    def setDuracion(self, duracion):
        self._duracion = duracion


    def getLenguaje(self):
        return self._lenguaje
    def setLenguaje(self, lenguaje):
        self._lenguaje = lenguaje


    def getClasificacion(self):
        return self._clasificacion
    def setClasificacion(self, clasificacion):
        self._clasificacion = clasificacion


    def getCine(self):
        return self._cine
    def setCine(self, cine):
        self._cine = cine