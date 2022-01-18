class Funcion:
    def __init__(self,dia,mes,horario,pelicula,sala,boletos,cantidadBoletosVendidos,cine,cantidadFunciones,numero):
        self._dia = dia
        self._mes = mes
        self._horario = horario
        self._pelicula = pelicula
        self._sala = sala
        self._boletos = boletos
        self._cantidadBoletosVendidos = cantidadBoletosVendidos
        self._cine = cine
        self._cantidadFunciones = cantidadFunciones
        self._numero = numero



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


    def getCantidadboletosvendidos(self):
        return self._cantidadBoletosVendidos
    def setCantidadboletosvendidos(self, cantidadBoletosVendidos):
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