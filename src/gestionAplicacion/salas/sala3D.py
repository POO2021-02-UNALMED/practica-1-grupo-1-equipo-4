from fileinput import filename
from importlib.metadata import FileHash

from gestionAplicacion.salas.sala import Sala
from gestionAplicacion.salas.silla import Silla

class Sala3D(Sala):

    def __init__(self, *args):

        if(len(args) == 4):
            filas, columnas,candidadgafas, cine = args
            super().__init__(filas, columnas, 0, 5000,cine)
            self._cantidadgafas = candidadgafas
        elif(len(args) == 3):
            filas, columnas, cine = args
            super().__init__(filas, columnas, 0,5000, cine)
            self._cantidadgafas = filas*columnas
        
    def cantidadSillas(self) -> int:

        totalsillas: int = len(self._sillas)

        if (totalsillas<self._cantidadgafas):
            return totalsillas
        return self._cantidadgafas

    def crearSilleteria(self):

        total: int = int(self._filas)*int(self._columnas)

        tipo: str = "VIP"

        for i in range(total):
            silla: Silla = Silla(tipo, i+1)

            self._sillas.append(silla)

    def getCantidadsillas(self):
        return self._cantidadgafas
    def setCantidadsillas(self, funciones):
        self._cantidadgafas = funciones
