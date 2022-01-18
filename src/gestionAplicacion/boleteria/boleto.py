from funcion import Funcion

class Boleto:

    def __init__(self,num_sillas,tipo_sillas,precioTotal,disponibilidad,funcion,precio_silla):
        self._num_sillas : int = num_sillas
        self._tipo_sillas : Tipo = tipo_sillas
        self._precioTotal: float = precioTotal
        self._disponibilidad : bool= disponibilidad
        self._funcion : Funcion = funcion
        self._precio_silla : float = precio_silla



    def getNum_sillas(self):
        return self._num_sillas
    def setNum_sillas(self, num_sillas):
        self._num_sillas = num_sillas


    def getTipo_sillas(self):
        return self._tipo_sillas
    def setTipo_sillas(self, tipo_sillas):
        self._tipo_sillas = tipo_sillas


    def getPreciototal(self):
        return self._precioTotal
    def setPreciototal(self, precioTotal):
        self._precioTotal = precioTotal


    def getDisponibilidad(self):
        return self._disponibilidad
    def setDisponibilidad(self, disponibilidad):
        self._disponibilidad = disponibilidad


    def getFuncion(self):
        return self._funcion
    def setFuncion(self, funcion):
        self._funcion = funcion

    def getPrecio_silla(self):
        return self._precio_silla
    def setPrecio_silla(self, precio_silla):
        self._precio_silla = precio_silla