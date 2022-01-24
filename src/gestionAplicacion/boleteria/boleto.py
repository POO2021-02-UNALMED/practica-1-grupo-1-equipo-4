##Funcionalidad de la clase: Albergar los datos de una silla respectiva a una sala de una funcion 


##Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa

from funcion import Funcion
from ..salas import Tipo, Silla
from ..cinemas import Cliente

#
#Creo que esta completo
#
class Boleto:

    def __init__(self, funcion: "Funcion" , silla: "Silla"):
        
        self._disponibilidad : bool= True
        self._funcion : Funcion = funcion
        self.setAtr_silla(silla)
        self._precioTotal: float =  self.calcularPrecio()
        self._num_silla: int = 0
        self._precio_silla: float = 0
        
    # funciones

    def calcularPrecio(self) -> float:
        bruto: float = self._funcion.getSala().getPrecio()+self._precio_silla
        return bruto

    def calcularPrecioDefinitivo(self, cliente : Cliente):

        total: float = self.calcularPrecio()-(self.calcularPrecio()*(cliente.getDescuento()))
        self.setPrecioTotal(total)

    def setAtr_silla(self, silla : Silla):

        self._num_silla = silla.getNumero()
        self.setTipo_silla(silla.getTipo())
        self.setPrecio_silla(silla.getPrecio())

    def tipoString(self) -> str:
        if(self._tipo_silla == Tipo.VIP):
            return "V-"
        return "S-"
    # 
    # getter and setters
    # 
    def getNum_silla(self):
        return self._num_silla
    def setNum_silla(self, num_silla):
        self._num_silla = num_silla


    def getTipo_silla(self):
        return self._tipo_silla
    def setTipo_silla(self, tipo_silla):
        self._tipo_silla = tipo_silla


    def getPrecioTotal(self):
        return self._precioTotal
    def setPrecioTotal(self, precioTotal):
        self._precioTotal = precioTotal


    def isDisponibilidad(self):
        return self._disponibilidad
    def setDisponibilidad(self, disponibilidad):
        self._disponibilidad = disponibilidad


    def getFuncion(self) -> Funcion:
        return self._funcion
    def setFuncion(self, funcion):
        self._funcion = funcion

    def getPrecio_silla(self):
        return self._precio_silla
    def setPrecio_silla(self, precio_silla):
        self._precio_silla = precio_silla