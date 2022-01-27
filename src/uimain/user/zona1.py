from tkinter import *

class Zona1(Menu):
    def __init__(self, user,zona2):
        super().__init__(user)
        self.zona2 = zona2
        self.archivos()
        self.procesos()
        self.ayuda()
        user["menu"] = self

    def archivos(self):
        archivos = Menu(self)
        archivos.add_command(label = "Aplicacion")
        archivos.add_command(label = "Salir" )
        self.add_cascade(menu = archivos, label = "Archivos")


    def procesos(self):
        self.procesos = Menu(self)
        dic = self.zona2.funciones
        for key,value in dic.items():
            self.procesos.add_command(label = key, command = value)
        
        self.add_cascade(menu = self.procesos, label = "Procesos y Consultas")
    
    
    def ayuda(self):
        ayuda = Menu(self)
        ayuda.add_command(label = "Acerca de")
        self.add_cascade(menu = ayuda, label = "Ayuda")

        