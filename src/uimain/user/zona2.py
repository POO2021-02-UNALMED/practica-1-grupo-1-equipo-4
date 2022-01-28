from ssl import CertificateError
from tkinter import *
# from src.gestionAplicacion.cinemas.cine import Cine
# from src.gestionAplicacion.boleteria.pelicula import Pelicula

class Zona2: 
   
    def __init__(self, user):


        self.todo = Frame(user, width =1000, height = 500, bg = "black") #Este es lo que contiene toda la zona 2
        self.todo.pack()

        self.funciones = {"Venta":self.venta,
                         "Agregar Pelicula":self.ensayo,
                         "Quitar Pelicula":self.agregarPelicula, 
                         "Agregar Funcion":self.agregarFuncion,
                         "Generar Funcion Auto":self.agregarAuto,
                         "Rifar Boleto":self.rifa,
                         "Agregar Sala":self.agregarSala} #aca se guardan las los procesos y las consultas

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

    ## Procesos y consultas

    def venta(self):
        pass

    def agregarPelicula(self):

        self.cambiar()

        self.titulo.configure(text = "Agregar Pelicula")
        self.texto.configure(text = "Permite agregar películas a la base de datos")

        nomCriterios="Pelicula"
        criterios=["Nombre","Genero","Duración","Idioma","Edad mínima"]
        nomValores="Información"
        valIniciales=None
        valHabilitados=None
        agregarPelicula = FieldFrame(nomCriterios, criterios,nomValores,valIniciales,valHabilitados,self.cuerpo)
        
        agregarPelicula.pack()
        
        pelicula=Pelicula(agregarPelicula.getValue("nombre"),
                agregarPelicula.getValue("Genero"),
                agregarPelicula.getValue("Duración"),
                agregarPelicula.getValue("Idioma"),
                agregarPelicula.getValue("Edad mínima"),
                cine= ELCINE) #TODO: ¿Cuál es nuestro cine? Creo que va a tocar meter el argumento de cine en esta función o en la clase en general
        
        ELCINE.agregarPelicula(pelicula) #TODO: Esto no sé que tan correcto esté pero creo que al guardarlo en Cine el garbage collector no lo termina de matar
        
        #TODO: 
        #TODO: Luego de agregar la peplícula ¿qué?
        #TODO: Probar para varios casos y falta serializar

    def quitarPelicula(self):
        
        
        self.cambiar()

        self.titulo.configure(text = "Quitar pelicula")
        self.texto.configure(text = "Permite quitar películas de la base de datos")

        nomCriterios="Pelicula"
        criterios=["Nombre"]
        nomValores="Información"
        valIniciales=None
        valHabilitados=None
        quitarPelicula = FieldFrame(nomCriterios, criterios,nomValores,valIniciales,valHabilitados,self.cuerpo)
        
        quitarPelicula.pack()

        ELCINEL.getPeliculas().pop(ELCINE.getPeliculas().index(quitarPelicula.getValue("Nombre")))

        #El .pop si afectará la lista
        #TODO: Luego de quitar la película ¿qué?
        #TODO: Mostrar los nombres de las peliculas por hacer
        #TODO: Falta serializar.

    def agregarFuncion(self):
        self.cambiar()

        self.titulo.configure(text = "Agregar función")
        self.texto.configure(text = "Permite agregar funciones")

        nomCriterios="Función"
        criterios=["Dia","Mes","Sala","Horario","Nombre pelicula"]
        nomValores="Información"
        valIniciales=None
        valHabilitados=None
        agregarFuncion = FieldFrame(nomCriterios, criterios,nomValores,valIniciales,valHabilitados,self.cuerpo)
        
        agregarFuncion.pack()

        #TODO: Luego de quitar la película ¿qué?
        #TODO: Mostrar los nombres de las salas, las peliculas y los horarios para cada una
        #TODO: Falta serializar.

    def agregarAuto(self):
        pass

    def rifa(self):
        pass

    def agregarSala(self):
        pass

    
    def ensayo(self):

        self.cambiar()

        self.titulo.configure(text = "Ensayo")
        self.texto.configure(text = "Esto es una calculadora que no funciona")
        
        # Ignorar 
        # 
        # 
        
        window = self.cuerpo

        text = Frame(window, width=500, height=100, bg="grey")
        buttons = Frame(window, width=500, height=500)
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

        