from boleteria import Funcion, Pelicula, Horario
from salas import Sala
from cinemas import Cliente

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


    def verificarCliente(self, num: int) -> bool:
        lista : list[int] = []
        
        for cliente in self.getClientes():
            lista.append(cliente.getCedula())
        
        return num in lista
    
    
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