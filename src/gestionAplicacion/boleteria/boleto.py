##Funcionalidad de la clase: Albergar los datos de una silla respectiva a una sala de una funcion 


##Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa

from gestionAplicacion.salas.tipo import Tipo

#
#Creo que esta completo
#
class Boleto:

    def __init__(self, funcion, silla):
        
        self._disponibilidad : bool= True
        self._funcion = funcion
        self.setAtr_silla(silla)
        self._precioTotal: float =  self.calcularPrecio()
        self._num_silla: int = 0
        self._precio_silla: float = 0
        
    # funciones

    def calcularPrecio(self) -> float:
        ''' No recibe nada y devuelve un float el cual corresponde al calculo del precio bruto del boleto 
	    el cual depende del precio de la sala y el precio de la silla '''

        bruto: float = self._funcion.getSala().getPrecio()+self._precio_silla   #Se suma el precio de la sala y el precio de la silla
        return bruto

    def calcularPrecioDefinitivo(self, cliente):

        #Recibe a un cliente  y no devuelve nada, este precio se le descuenta un descuento(Si este cliente lo tiene)
        
        total: float = self.calcularPrecio()-(self.calcularPrecio()*(cliente.getDescuento()))
        self.setPrecioTotal(total)

    def setAtr_silla(self, silla):

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


    def getFuncion(self):
        return self._funcion
    def setFuncion(self, funcion):
        self._funcion = funcion

    def getPrecio_silla(self):
        return self._precio_silla
    def setPrecio_silla(self, precio_silla):
        self._precio_silla = precio_silla