from boleteria import Funcion, Pelicula, Horario
from salas import Sala
from cinemas import Cliente
import random

class Cine: 

    def __init__(self,nombre,clientes,cartelera,peliculas,salas,dineroGanado,DESCUENTOMVC):
        self._nombre = nombre
        self._clientes = clientes
        self._cartelera : list[Funcion]= cartelera
        self._peliculas = peliculas
        self._salas = salas
        self._dineroGanado = dineroGanado
        self._DESCUENTOMVC = DESCUENTOMVC
        #TODO:Take care of this constant
        #!!! Aqui falta la variable del serializador


    def programarFuncionesAuto( self, mes: int, dia: int, sala: Sala):
        programadas : list[] = []
        funciones : list[Funcion] = self.verFuncion(mes)
        peliculasMes : list[Pelicula] = list(set([funcion.getPelicula() for funcion in funciones]))
        pelicula_boletos = []
        disponibles = ["22:00","20:00","18:00","16:00","14:00","12:00"]

        for i in range(len(peliculasMes)):
            pelicula_boletos.append([peliculasMes,0])
            for funcion in funciones:
                if(peliculasMes[i] == funcion.getPelicula()):
                    pelicula_boletos[i][1]+= funcion.getCantidadboletosvendidos()
                   
        pelicula_boletos.sort(key = lambda x:x[1], reverse = True)
		
        if(len(peliculasMes)):
            for i in range(6):
                p : Pelicula = peliculasMes[i]
                h : Horario = Horario.getHorario(disponibles[i])
                programadas.append( Funcion.crearFuncion(dia, mes, h, p , sala.getNumero(), self))
        else:
            for i in range(len(peliculasMes)):
                p : Pelicula = peliculasMes[i]
                h : Horario = Horario.getHorario(disponibles[i])
                programadas.append(Funcion.crearFuncion(dia, mes, h, p , sala.getNumero(), self))
        
        return programadas



           
    
    def salasDisponibles(self, mes: int, dia: int) -> list[Sala]:
        disponibles=list()
        
        for sala in self._salas:
            if sala.almenosUnoDisponible(mes, dia):
                disponibles.append(sala)
        
        return disponibles

    def mostValueClient(self) -> str:
        clienteList=list()
        
        for client in self._clientes:
            clienteList.append(len(client.getHistorialCompras()))
        
        valormax=max(clienteList)

        for client in self._clientes:
            if(valormax==len(client.getHistorialCompras())):
                client.setDescuento(self._DESCUENTOMVC)
                return client.getNombre()

        return "Se ha aplicado el descuentos  a nuestro cliente mas fiel "


    def verFuncion(self, *args) -> list[Funcion]:
        funciones: list[Funcion] = []

        # Este es el ver funcion que recibe pelicula, dia, mes
        if(len(args) == 3):
            pelicula: Pelicula = args[0]
            dia, mes = args[1:]

            for funcion in self.getCartelera():
                if(funcion.getPelicula() == pelicula and funcion.getDia() >= dia and funcion.getMes() == mes):
                    funciones.append(funcion)
            for funcion in self.getCartelera()
                if(funcion.getPelicula() == pelicula and funcion.getMes() > mes):
                    funciones.append(funcion)

        # Este es el que recibe dia, mes
        elif(len(args) == 2):
            dia, mes = args
            for funcion in self.getCartelera():
                if(funcion.getDia() == dia and funcion.getMes() == mes):
                    funciones.append(funcion)

        elif(len(args) == 1):

            #recibe mes
            if(type(args[0] == int)):
                mes = args[0]
                for funcion in self.getCartelera():
                    if(funcion.getMes() == mes):
                        funciones.append(funcion)
                        
            #recibe cliente 
            else:
                cliente: Cliente = args[0]
                for funcion in self.getCartelera():
                   if(funcion.getPelicula() == cliente.mostWatchedGenre()):
                       funciones.append(funcion)
            

        return funciones

    def clientesValiosos(self)-> list[Cliente]:
        
        clienteList=[]		#Aca estaran los tamanos de historial de compra de cada cliente
        
        for cliente in self._clientes:
            clienteList.append(cliente.getHistorialCompras().size()) 	#Recorre el historial de compras del cliente y anexa el tamano de su historial de compra
        
        cantidad= len(clienteList)				#Cantidad de clientes que se tiene
        
        clienteList.sort(reverse=True)		#Ordenar la lista de mayor a menor
        top10= round(cantidad/10)								# El 10% de los clientes 
        mejoresCompas=[]			                                    #Clientes mas fieles
        
        for i in range(0, top10) :					
            valor =clienteList[i]								#Conseguir el 10% de los tamanos de historial de compra mas grandes
            for client in self._clientes:
                
                if(len(client.getHistorialCompras())==valor):				#Si el tamano de historial de compra es igual al valor agregar a los mejores clientes (mejoresCompas)
                    if not client in mejoresCompas:
                        mejoresCompas.append(client)
        
        return mejoresCompas

    
    def rifarBoleto(self, numeroFuncion: int):
        top10: list[Cliente]= self.clientesValiosos();	#Saco la lista del 10% de los clientes mas fieles
        
        tamano: int= len(top10)			#tamano de esa lista
        
        ganador: int= random.randint(0,tamano-1)	#Un numero aleatorio entre 0 y el tamano de la lista
        
        panitaGanador: Cliente= top10[ganador]  #Con el numero conseguido sacar al cliente escogido

        aleatoriofuncion: int = random.randint(0,len(self._cartelera)-1)	#Numero aleatorio de la lista de cartelera
        
        fescogida: Funcion= BuscadorFuncion(numeroFuncion)	#Funcion escogida de la cartelera con el numero de la funcion
        
        aleatorioboleto: int= random.randint(0,len(fescogida.getSala().cantidadSillas()-1))	#Otro numero aleatorio con base al tamano de la lista de boletos
        
        bescogido: Boleto = fescogida.getBoletos()[aleatorioboleto];	#Boleto escogido con el numero aleatorio
        
        if(bescogido.isDisponibilidad()):	#Si este boleto esta disponible se puede rifar ese boleto al cliente ganador
            
            fescogida.VentaBoleto(bescogido,panitaGanador)
            fescogida.setCantidadBoletosVendidos(fescogida.getCantidadBoletosVendidos()-1);		# Cada vez que se aplica la venta de boletos se suma al atributo, como se esta rifando
														# Se tendria que anular esa suma
        else:
			#Se puede ser muy demalas y que se escoja aleatoriamente un boleto que ya esta comprado
            
            for boleto in fescogida.getBoletos():
                if (boleto.isDisponibilidad()):	    #Se va recorriendo los boletos de la funcion escogida aleatoriamente 
                                                    #hasta encontrar el primero disponible 
                    
                    fescogida.VentaBoleto(boleto, panitaGanador)	#Se vende el boleto
                    fescogida.setCantidadBoletosVendidos(fescogida.getCantidadBoletosVendidos()-1)
                    break;		#Solo voy a rifar uno entonces rompo el for
                    
        return panitaGanador.getNombre()


	


	
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