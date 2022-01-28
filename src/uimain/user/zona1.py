from tkinter import *

class Zona1(Menu):
    def __init__(self, user,zona2):
        super().__init__(user)
        self.zona2 = zona2
        self.archivos()
        self.procesos()
        self.ayuda()
        user["menu"] = self

## ventanita de proceso
    def procesos(self):
        self.procesos = Menu(self)
        dic = self.zona2.funciones
        for key,value in dic.items():
            self.procesos.add_command(label = key, command = value)
        
        self.add_cascade(menu = self.procesos, label = "Procesos y Consultas")


## ventanita de archivos
    def archivos(self):
        archivos = Menu(self)
        archivos.add_command(label = "Aplicacion",command=self.aplicacion)
        archivos.add_command(label = "Salir" )
        self.add_cascade(menu = archivos, label = "Archivos")


    def aplicacion(self):
        
        ventana_nueva=Toplevel()
        ventana_nueva.title("Aplicacion")
        ventana_nueva.geometry("800x400")
        titulo = Label(ventana_nueva, text="Cine Bahía")
        titulo.pack(anchor=CENTER)
        titulo.config(fg="blue",  font=("Cambria",26)) 

        informacion=Label(ventana_nueva, text="Esta aplicación fue creada con la finalidad de venta y administración de un cinema.\n Para esto se tiene a la disposicion un menú en donde se tendrán las siguientes opciones:\n1.Archivos:En el que se se podrá escoger entre Aplicación(Ver información básica) y Salir para regresar al inicio del programa\n2. Procesos y consultas: Para realizar diversas funciones como son la rifa/venta  de un boleto, agregar/quitar una función, programación automática de funciones, entre otras.\n3.Ayuda: Se encontrará los autores del excelente programa")
        informacion.pack(anchor=CENTER)
        exit_button = Button(ventana_nueva, text="Salir", command=ventana_nueva.destroy) 
        exit_button.pack(pady=80) 



## ventanita
    def ayuda(self):
        ayuda = Menu(self)
        ayuda.add_command(label = "Acerca de")
        self.add_cascade(menu = ayuda, label = "Ayuda")

    def acerca(self):
        ventanacerca=Toplevel()
        ventanacerca.title("Acerca de los POOfantasticos ")
        ventanacerca.geometry("800x400")   

        exit_button = Button(ventanacerca, text="Salir", command=ventanacerca.destroy) 
        exit_button.pack(pady=50) 

