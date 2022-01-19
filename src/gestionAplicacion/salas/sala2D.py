from salas import Sala, Silla

class Sala2D(Sala):


    def __init__ (self,*args):
        if(len(args) == 4):
            filas, columnas, filasvip, cine = args
            super().__init__(filas, columnas, filasvip, 2000,cine);
            
        elif(len(args) == 2):
            vip , cine = args
            super().__init__(8, 12, vip, 2000, cine);

    
    def cantidadSillas(self):
        return len(super().getSillas())
    
    def crearSilleteria(self):
        total: int =  self._filas*self._columnas
        totalvip: int = self._filasvip*self._columnas

        tipo : str = "VIP"

        for i in range(total):
            
            if(totalvip<=0):				
                tipo = "SENCILLA"

            else:								
                totalvip-=1
                
            silla : Silla = Silla(tipo,i+1)
            
            self._sillas.append(silla)
