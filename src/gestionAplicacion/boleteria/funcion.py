"""Funcionalidad de la clase: Es la funcion que se presenta de una pelicula respectiva
esta va asociada a una sala en la que se presenta la pelicula y cuenta con una fecha y
horario, esta cuenta con una cantidad de boletos los cuales son los vendidos a los clientes
Autores: Daniel Santiago Cadavid, Marlon Calle, Daniel Daza, Juan Esteban Ochoa"""

from gestionAplicacion.boleteria.horario import Horario
from gestionAplicacion.boleteria.boleto import Boleto
from gestionAplicacion.salas.sala import Sala

class Funcion:
    
    def __init__(self,dia,mes,horario,pelicula,sala,cine):
        # Recibe atributos de tipo Cine e int (la cedula del cliente), no devuelve nada y su proposito es 
        # mostrar en pantalla las funciones por pelicula de un dia en 
        # especifico

        self._boletos = []
        self._dia:int = dia
        self._mes:int = mes
        self._horario = horario
        self._pelicula = pelicula
        self.setSala(sala)
        self.setCine(cine)

        sala.agregarFuncion(self)
        self._numero = len(cine.getCartelera()) + 1
        self._cantidadBoletosVendidos:int=0
        cine.agregarFuncion(self)
        self.crearBoleteria()
        """Se crea la boleteria de la funcion
		agrega la funcion a sala y a el cine"""

# 	// PARA CREAR FUNCION SE VA A USAR ESTE METODO, NO EL CONSTRUCTOR
    @classmethod
    def crearFuncion(cls,dia:int,mes:int,horario:Horario,pelicula,num_sala:int,cine): #devuelve una funcion o none
        
        # No recibe parametros pero trabaja con los atributos sala y boletos
		# Se encarga de crear un boleto para cada silla de la sala de la funcion correspondiente,
		# y se limita depende de la cantidad de sillas disponibles de sala, es decir cantidadSillas
		# que se limita a la cantidad de gafas disponibles
    
        sala = cine.buscarSala(num_sala)                                #aqui se revisa si la sala existe en cine
        if(sala!=None):
            if(sala.verificarDisponibilidad(dia,mes,horario.getHora())):#verifica que la sala tenga disponibilidad en dicha hora
                return Funcion(dia,mes,horario,pelicula,sala,cine)      # crea la función
            else:
                return None #no la crea
        else:
            return None     #no la crea

    @classmethod
    def formatearFunciones(cls,funciones):

        """No recibe parametros pero trabaja con los atributos sala y boletos
		Se encarga de crear un boleto para cada silla de la sala de la funcion correspondiente,
		y se limita depende de la cantidad de sillas disponibles de sala, es decir cantidadSillas
		que se limita a la cantidad de gafas disponibles"""

        resultado = ""
        for funcion in funciones:
            formato = "{}|{}|{}|{}"
            fecha = "Fecha: " + "{:>02d}/{:>02d}".format(funcion.getDia(),funcion.getMes())
            resultado += str(funcion.getPelicula().getNombre())+" "+str(funcion.getPelicula().getClasificacion())+"+"+"\n"

            resultado += formato.format(funcion.getHorario().getHora().center(6),
                                        ("Sala "+str(funcion.getSala().getNumero())).center(8),
                                        str(funcion.getSala().getTipo()).center(4),
                                        "{:>3d}".format(funcion.getNumero()).center(5))
            resultado +=  "\n" + fecha
            resultado += "\n\n"
        return resultado

    def crearBoleteria(self):
        sillas = self._sala.getSillas()
        disponibles:int=self._sala.cantidadSillas() #ignorar, catidadSillas() se encuentra en cada subclase, por lo que acá marca el error
        total:int=len(self._sala.getSillas())
        for i in range(total):
            if(disponibles>0):
                boleto:Boleto= Boleto(self,sillas[i])
                self._boletos.append(boleto)
                disponibles-=1
    
    def verDisponibilidad(self):
        # Metodo que devuelve las sillas de una sala con tipo y disponibilidad de silla para una función dada
        
        total = [] #lista de filas
        
        # for para hacer una lista de listas, cada lista corresponde a una fila de boletos
        for boleto in self._boletos:        #fila acumula los elementos de la fila para luego convertirlo en string
            if boleto!=None:
                tupla_boleto:tuple=(boleto.isDisponibilidad(),boleto.tipoString()+str(boleto.getNum_silla()))   
                total.append(tupla_boleto)

        return total                                #retorna el total de 

    def VentaBoleto(self,boleto,cliente)->bool:

        """Recibe boleto al que se le cambiara el estado, y el cliente al que se le agregara la compra,
	Metodo que vende un boleto, es decir, cambia valores, y devuelve un bool de si se pudo vender o no el boleto
	retorna true o false dependiendo si pudo hacerse la venta o no """

        if (boleto.isDisponibilidad()==True and cliente.getEdad()>=self.getPelicula().getClasificacion()):
            boleto.setDisponibilidad(False)                             #Al comprar el boleto se quita su disponibilidad
            cliente.getHistorialCompras().append(boleto)                #Agregamos el boleto que se comprar� al historial del cliente
            self._cantidadBoletosVendidos+=1                            
            boleto.calcularPrecioDefinitivo(cliente)                    #//le calculamos el precio del boleto al cliente si este posee un descuento o algo

            ganancia:float=self._cine.getDineroGanado()+boleto.getPrecioTotal() #Se suma las ganancias que se tienen hasta el momento con el precio total del boleto

            self._cine.setDineroGanado(ganancia)   #se establece el nuevo valor
            self._pelicula.anadirCantidadBoletos() #se suma en uno el valor de los boletos vendidos por pelicula

            return True
        else:
            return False


        


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


    def getCantidadBoletosVendidos(self):
        return self._cantidadBoletosVendidos
    def setCantidadBoletosVendidos(self, cantidadBoletosVendidos):
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