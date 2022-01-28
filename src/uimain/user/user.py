from tkinter import *
from zona1 import Zona1
from zona2 import Zona2

class User:
    def __init__(self):
        self.user = Toplevel()
        self.user.title("Ventana de Usuario")
        self.user.geometry("1000x600")
        self.user.option_add("*tearOff",False)

        self.frame = Zona2(self.user)
        self.zona1 = Zona1(self.user, self.frame)
    
    
        
