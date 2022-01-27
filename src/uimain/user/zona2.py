from tkinter import *

class Zona2: 
   
    def __init__(self, user,  variable):
        todo = Frame(user, width =1000, height = 500, bg = "black")
        todo.pack()
        funciones = {"ensayo":self.ensayo}
        
        
        texto = funciones[variable].__doc__.split("\n")
        titulo_texto = Frame(todo,width=800, height = 150)
        titulo_texto.pack()
        
        Label(titulo_texto, text = texto[0], bg="grey").pack()
        Label(titulo_texto, text = texto[1], bg = "red").pack()
        
        cuerpo = Frame(todo,width=800, height = 350, bg= "green")
        cuerpo.pack()
        funciones[variable](cuerpo)
        



    def ensayo(self, cuerpo):
        """holi
esta funcion funciona para poder saludarte"""
        Label(cuerpo,text = "Esto es lo que se empaqueta en el cuerpo").pack()
        