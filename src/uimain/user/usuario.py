from tkinter import *
from uimain.user.zonaa import ZonaA
from uimain.user.zonab import ZonaB
    

class Usuario:
    def __init__(self,cine):
        self.cine=cine
        self.user = Toplevel()
        self.user.title("Ventana de Usuario")
        self.user.geometry("1000x600")
        self.user.option_add("*tearOff",False)

        self.frame = ZonaB(self.user,cine)
        self.zona1 = ZonaA(self.user, self.frame)

# Usuario().user.mainloop()