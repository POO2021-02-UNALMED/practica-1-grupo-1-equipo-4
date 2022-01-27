from tkinter import *

class Zona2: 
   
    def __init__(self, user):


        self.todo = Frame(user, width =1000, height = 500, bg = "black") #Este es lo que contiene toda la zona 2
        self.todo.pack()

        self.funciones = {"ensayo2":self.ensayo2, "ensayo":self.ensayo} #aca se guardan las los procesos y las consultas

        self.titulo_texto = Frame(self.todo,width=800, height = 150)   
        self.titulo_texto.pack()

        self.titulo = Label(self.titulo_texto, bg="grey",text = "titulo")  #label del titulo
        self.titulo.pack()

        self.texto = Label(self.titulo_texto, bg = "red",text = "texto")   #label del titulo
        self.texto.pack()


        self.cuerpo = Frame(self.todo,width=800, height = 350, bg= "green") #este es el cuerpo, se inicializa vacio
        self.cuerpo.pack()
        

    def cambiar(self):
        self.cuerpo.pack_forget()
        self.cuerpo = Frame(self.todo,width=800, height = 350, bg= "green")
        self.cuerpo.pack()


    def ensayo(self):

        self.cambiar()

        self.titulo.configure(text = "Ensayo")
        self.texto.configure(text = "Esto es una calculadora que no funciona")
        
        # Ignorar 
        # 
        # 

        window = self.cuerpo

        text = Frame(self.cuerpo, width=500, height=100, bg="grey")
        buttons = Frame(self.cuerpo, width=500, height=500)
        text.pack()
        buttons.pack()


        columnas = 4
        filas = 4
        elementos = list("789/456*123-.0+=")
        num = 0
        total = []

        for i in range(filas):
            fila=[]
            for j in range(columnas):
                fila.append(Button(master = buttons, text=str(elementos[num]), height=3, width = 10))

                num += 1
                fila[j].grid(column= j, row = i, padx = 3, pady = 3)
            total.append(fila)

        Cbutton = Button(master = buttons, text = "C", height=3,width= 49)
        Cbutton.grid(column = 0, row= 4, padx=3, pady=3,columnspan=4)

    def ensayo2(self):

        self.cambiar()

        self.titulo.configure(text = "Ensayo2")
        self.texto.configure(text = "Me funciono hpta")

        text = Frame(self.cuerpo, width=500, height=100, bg="grey")
        buttons = Frame(self.cuerpo, width=500, height=500)
        text.pack()
        buttons.pack()


        columnas = 4
        filas = 4
        elementos = list("789/456*123-.0+=")
        num = 0
        total = []

        for i in range(filas):
            fila=[]
            for j in range(columnas):
                fila.append(Button(master = buttons, text=str(elementos[num]), height=3, width = 10, bg = "red"))

                num += 1
                fila[j].grid(column= j, row = i, padx = 3, pady = 3)
            total.append(fila)

        Cbutton = Button(master = buttons, text = "C", height=3,width= 49)
        Cbutton.grid(column = 0, row= 4, padx=3, pady=3,columnspan=4)

        