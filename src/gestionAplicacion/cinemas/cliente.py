class Cliente:

    def __init__(self,cedula,nombre,edad,ocupacion,descuento,historialCompras,referidos,cine):
        self._cedula = cedula
        self._nombre = nombre
        self._edad = edad
        self._ocupacion = ocupacion
        self._descuento = descuento
        self._historialCompras = historialCompras
        self._referidos = referidos
        self._cine = cine


    #
    #Getting and setting
    #
    def getCedula(self):
        return self._cedula
    def setCedula(self, cedula):
        self._cedula = cedula

    def getNombre(self):
        return self._nombre
    def setNombre(self, nombre):
        self._nombre = nombre

    def getEdad(self):
        return self._edad
    def setEdad(self, edad):
        self._edad = edad

    def getOcupacion(self):
        return self._ocupacion
    def setOcupacion(self, ocupacion):
        self._ocupacion = ocupacion

    def getDescuento(self):
        return self._descuento
    def setDescuento(self, descuento):
        self._descuento = descuento

    def getHistorialcompras(self):
        return self._historialCompras
    def setHistorialcompras(self, historialCompras):
        self._historialCompras = historialCompras

    def getReferidos(self):
        return self._referidos
    def setReferidos(self, referidos):
        self._referidos = referidos

    def getCine(self):
        return self._cine
    def setCine(self, cine):
        self._cine = cine