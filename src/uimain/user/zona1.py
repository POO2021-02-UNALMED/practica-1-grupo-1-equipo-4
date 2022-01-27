from tkinter import *

class Zona1(Menu):
    def __init__(self, frame):
        super().__init__(frame)
        self.archivos()
        self.procesos()
        self.ayuda()
        frame["menu"] = self

    def archivos(self):
        archivos = Menu(self)
        archivos.add_command(label = "Aplicacion")
        archivos.add_command(label = "Salir" )
        self.add_cascade(menu = archivos, label = "Archivos")


    def procesos(self):
        """hola"""
        procesos = Menu(self)
        
        """
        acá debo colocar todo lo que va en procesos
        archivos.add_command(label = "Aplicacion" , command = vender)"""
        
        self.add_cascade(menu = procesos, label = "Procesos y Consultas")
    
    
    def ayuda(self):
        ayuda = Menu(self)
        ayuda.add_command(label = "Acerca de")
        self.add_cascade(menu = ayuda, label = "Ayuda")

        