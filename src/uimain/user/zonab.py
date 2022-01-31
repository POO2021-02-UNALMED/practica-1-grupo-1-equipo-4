from tkinter import *
from tkinter import messagebox
from uimain.user.zonaa import ZonaA
from gestionAplicacion.cinemas.cine import Cine
from gestionAplicacion.boleteria.pelicula import Pelicula
from uimain.user.fieldFrame import FieldFrame

class ZonaB: 
   
    def __init__(self, user):


        self.todo = Frame(user, width =1000, height = 500, bg = "black") #Este es lo que contiene toda la zona 2
        self.todo.pack()

        self.funciones = {"Venta":self.venta,
                         "Agregar Pelicula":self.agregarPelicula,
                         "Quitar Pelicula":self.quitarPelicula, 
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

        self.titulo.configure(text = "Cine Bahía")
        self.texto.configure(text = "Bienvendio al cine, seleccione lo que quiera hacer")

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
        self.cambiar()

        self.titulo.configure(text="Programación automática")
        self.texto.configure(text = "Permite agregar funciones de un día en una sala")

        nomCriterios="Fecha"
        criterios=["Mes","Día","Sala"]
        nomValores="Información"
        valIniciales=None
        valHabilitados=None
        agregarAuto = FieldFrame(nomCriterios, criterios,nomValores,valIniciales,valHabilitados,self.cuerpo)

        agregarAuto.pack()
     
        
        #TODO: Luego de realizar la programcion automatica ¿qué?
        #TODO: Mostrar los nombres de las salas, las peliculas y los horarios para cada una
        #TODO: Falta serializar.
    def rifa(self):

        self.cambiar()

        self.titulo.configure(text = "Rifar Boleto")
        self.texto.configure(text = "Permite rifar un boleto a una función deseada entre los clientes mas fieles")

        nomCriterios="Función"
        criterios=["Dia","Mes","Codigo"]
        nomValores="Información"
        valIniciales=None
        valHabilitados=None
        rifa = FieldFrame(nomCriterios, criterios,nomValores,valIniciales,valHabilitados,self.cuerpo)
        
        rifa.pack()

        funciones=Label(self.cuerpo,text="Aca irian las funciones disponibles")     ###FALTA ENLAZAR CON LAS FUNCIONES DISPONIBLES QUE BOTA LA CAPA LOGICA
        funciones.pack()

        def resultado(action):
            messagebox.showinfo(title='Rifa de Boleto!',message="Clientes fieles candidatos a la rifa: ", detail='GANADOR: ')      ###FALTA MOSTRAR LOS CANDIDATOS A GANAR Y EL GANADOR QUE ESO ES LO DE LA CAPA LOGICA 
            self.cambiar()

        rifa.button.bind('<ButtonRelease>',resultado)

    def agregarSala(self):
        self.cambiar()

        self.titulo.configure(text = "Agregar una sala")
        self.texto.configure(text = "Permite agregar una sala segun su tipo (2D o 3D)")

        checked=IntVar()

        global nueva

        #sala = FieldFrame("Tamaño", ["Filas","Columnas"],"Cantidad",None,None,self.cuerpo)
        #sala.pack()

        def create(action):
            messagebox.showinfo(title="Información",message="Sala creada con éxito!")      ###FALTA LLAMAR AL METODO QUE CREA LA SALA DE LA CAPA LOGICA
            self.cambiar()

        def tres():
            global nueva
            try:
                nueva
            except NameError:
                nueva = FieldFrame("Tamaño", ["Filas","Columnas","Gafas disponibles"],"Cantidad",None,None,self.cuerpo)
                nueva.pack()
                nueva.button.bind('<ButtonRelease>',create)
            else:
                nueva.pack_forget()
                nueva = FieldFrame("Tamaño", ["Filas","Columnas","Gafas disponibles"],"Cantidad",None,None,self.cuerpo)
                nueva.pack()
                nueva.button.bind('<ButtonRelease>',create)
        
        def dos():
            global nueva
            try:
                nueva
            except NameError:
                nueva = FieldFrame("Tamaño", ["Filas","Columnas"],"Cantidad",None,None,self.cuerpo)
                nueva.pack()
                nueva.button.bind('<ButtonRelease>',create)
            else:
                nueva.pack_forget()
                nueva = FieldFrame("Tamaño", ["Filas","Columnas"],"Cantidad",None,None,self.cuerpo)
                nueva.pack()
                nueva.button.bind('<ButtonRelease>',create)

        tresd=Radiobutton(self.cuerpo,text="3D",variable=checked,value=3,command=tres)
        tresd.pack()
        dosd=Radiobutton(self.cuerpo,text="2D",variable=checked,value=2,command=dos)
        dosd.pack()


        