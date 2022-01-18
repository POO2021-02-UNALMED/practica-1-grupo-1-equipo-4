from enum import Enum

class Horario(Enum):
    UNO="12:00"
    DOS="14:00"
    TRES="16:00"
    CUATRO="18:00" 
    CINCO="20:00" 
    SEIS="22:00"

    def __init__(self,hora):
        self._hora = hora   

    @classmethod
    def getHorario(cls,hora)->"Horario":
        horarios=[cls.UNO,cls.DOS,cls.TRES,cls.CUATRO,cls.CINCO,cls.SEIS]
        for horario in horarios:
            if(hora==horario.getHora()):
                return horario
        return Horario(None)

    def getHora(self)->str:
        return self._hora
    def setHora(self,hora):
        self._hora=hora