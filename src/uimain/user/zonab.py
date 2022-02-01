from dataclasses import field
from tkinter import *
from tkinter import messagebox
from uimain.user.zonaa import ZonaA
from gestionAplicacion.cinemas.cine import Cine
from gestionAplicacion.boleteria.pelicula import Pelicula
from gestionAplicacion.boleteria.funcion import Funcion
from uimain.user.fieldFrame import FieldFrame
from gestionAplicacion.salas.sala2D import Sala2D
from gestionAplicacion.salas.sala3D import Sala3D
from uimain.user.fieldFrame import FieldFrame
import uimain.user.venta as venta
import pickle

class ZonaB: 
   
    def __init__(self, user,cine):
        
        self.cine=cine
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
        self.cambiar()
        self.titulo.configure(text = "Venta")
        self.texto.configure(text = "Permite vender buscando por diferentes peliculas")
        venta.ventana(self.cuerpo, self.cine)
       
    #TODO: Agregar serializador a venta
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

        def addPeli(action):
            pelicula=Pelicula(agregarPelicula.getValue("Nombre"),
                agregarPelicula.getValue("Genero"),
                agregarPelicula.getValue("Duración"),
                agregarPelicula.getValue("Idioma"),
                agregarPelicula.getValue("Edad mínima"),
                self.cine) #TODO: ¿Cuál es nuestro cine? Creo que va a tocar meter el argumento de cine en esta función o en la clase en general
        
            self.cine.agregarPelicula(pelicula) #TODO: Esto no sé que tan correcto esté pero creo que al guardarlo en Cine el garbage collector no lo termina de matar
            #print([i.getNombre() for i in self.cine.getPeliculas()])
            
            picklefile = open('pcs', 'wb')
            pickle.dump(self.cine,picklefile) #Bloque de serialización
            picklefile.close()
            
            messagebox.showinfo(title="Información",message="Pelicula chimbita agregada, la buena pai")

        agregarPelicula.button.bind('<ButtonRelease>',addPeli)

        

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

        def removePeli(action):
            titles=[i.getNombre() for i in self.cine.getPeliculas()]
            self.cine.getPeliculas().pop(titles.index(quitarPelicula.getValue("Nombre")))
            
            picklefile = open('pcs', 'wb')
            pickle.dump(self.cine,picklefile) #Bloque de serialización
            picklefile.close()

            messagebox.showinfo(title="Información",message="Como me dejo meter este ganso ciego ome,quite la pelicula.Yo si soy mucha loca")

        quitarPelicula.button.bind('<ButtonRelease>',removePeli)


        #TODO: Luego de quitar la película ¿qué?
        #TODO: Mostrar los nombres de las peliculas por hacer
        #TODO: Falta serializar.

    def agregarFuncion(self):
        self.cambiar()

        self.titulo.configure(text = "Agregar función")
        self.texto.configure(text = "Permite agregar funciones")

        nomValores="Información"
        valIniciales=None
        valHabilitados=None
        diames = FieldFrame("Fecha", ["Dia","Mes"],nomValores,valIniciales,valHabilitados,self.cuerpo)
        diames.pack()
        diames.button.configure(text="Siguiente")

        info=[]      #[0]=dia, [1]=mes, [2]=sala, [3]=hora, [4]=pelicula

        def salasdia(action):       ##Aca se muestran las salas disponibles segun el dia seleccionado
            info.append(diames.getValue("Dia"))
            info.append(diames.getValue("Mes"))
            diames.pack_forget()

            salasdispo=FieldFrame("Sala",["Numero"],nomValores,valIniciales,valHabilitados,self.cuerpo)
            salasdispo.pack()
            salasdispo.button.configure(text="Siguente")

            def horariosala(action):        ##Aca se muestran los horarios disponibles de la sala escogida
                info.append(salasdispo.getValue("Numero"))
                salasdispo.pack_forget()
                disponibles.pack_forget()

                horariodispo=FieldFrame("Horarios",["Hora"],nomValores,valIniciales,valHabilitados,self.cuerpo)
                horariodispo.pack()
                horariodispo.button.configure(text="Siguiente")

                #print(self.cine.buscarSala(int(info[2])))

                horarioslibres=self.cine.buscarSala(int(info[2])).verHorarios(int(info[0]),int(info[1]))

                disponibles.configure(text="Horarios disponibles de la sala " + str(info[2])+":" + "\n"+horarioslibres)
                disponibles.pack()

                def peliscine(action):        ###Peliculas disponibles en el cine
                    info.append(horariodispo.getValue("Hora"))
                    horariodispo.pack_forget()
                    disponibles.pack_forget()

                    pelisdispo=FieldFrame("Titulo",["Pelicula"],nomValores,valIniciales,valHabilitados,self.cuerpo)
                    pelisdispo.pack()
                    pelisdispo.button.configure(text="Finalizar creacion")
                    
                    peliculasdisponibles=""
                    for p in self.cine.getPeliculas():
                        peliculasdisponibles+=p.getNombre()+"\n"

                    disponibles.configure(text=peliculasdisponibles)
                    disponibles.pack()

                    def creacionfinal(action):
                        info.append(pelisdispo.getValue("Pelicula"))
                        funcion=Funcion(int(info[0]),int(info[1]),info[3],info[4],self.cine.buscarSala(int(info[2])),self.cine) 

                        picklefile = open('pcs', 'wb')
                        pickle.dump(self.cine,picklefile) #Bloque de serialización
                        picklefile.close()

                        messagebox.showinfo(title="Información",message="Como me dejo meter este ganso ciego ome,quite la pelicula.Yo si soy mucha loca")
        
                        #self.cine.agregarFuncion(funcion) #TODO: Esto no sé que tan correcto esté pero creo que al guardarlo en Cine el garbage collector no lo termina de matar
                        #for i in self.cine.getCartelera():
                        #    print(i.getPelicula())

                    pelisdispo.button.bind("<ButtonRelease>", creacionfinal)       #Cuarto boton

                horariodispo.button.bind("<ButtonRelease>",peliscine)      ##Tercer boton

            salasdispo.button.bind("<ButtonRelease>",horariosala)       #Segundo boton

            salaslibres=self.cine.salasDisponibles(int(diames.getValue("Dia")),int(diames.getValue("Dia")))
            textosalas="Salas disponibles del dia/mes "+str(diames.getValue("Dia"))+"/"+str(diames.getValue("Mes"))+" :\n"
            for d in salaslibres:
                textosalas+="Sala "+str(d.getNumero())+"\n"

            disponibles=Label(self.cuerpo,text=textosalas)
            disponibles.pack()

        diames.button.bind("<ButtonRelease>",salasdia)      ###Primer boton


        """def addFuncion(action):
            funcion=Funcion(info[0],
                info[1],
                info[3],
                info[4],
                info[2],
                self.cine) #TODO: ¿Cuál es nuestro cine? Creo que va a tocar meter el argumento de cine en esta función o en la clase en general
        
            self.cine.agregarFuncion(funcion)""" #TODO: Esto no sé que tan correcto esté pero creo que al guardarlo en Cine el garbage collector no lo termina de matar
        
        #agregarFuncion.button.bind('<ButtonRelease>',addFuncion)

        #TODO: Luego de quitar la película ¿qué?
        #TODO: Mostrar los nombres de las salas, las peliculas y los horarios para cada una
        #TODO: Falta serializar.

    def agregarAuto(self):
        self.cambiar()

        self.titulo.configure(text="Programación automática")
        self.texto.configure(text = "Permite agregar funciones de un día en una sala")

        
        nomValores="Información"
        valIniciales=None
        valHabilitados=None

        diames = FieldFrame("Fecha", ["Dia","Mes"],nomValores,valIniciales,valHabilitados,self.cuerpo)
        diames.button.configure(text="Siguiente")
        diames.pack()
        

        info=[]

        def salasDisponibles(action):
            info.append(diames.getValue("Dia"))
            info.append(diames.getValue("Mes"))

            diames.pack_forget()

            salas = FieldFrame("Sala",["Numero"],nomValores,valIniciales,valHabilitados,self.cuerpo)
            salas.button.configure(text="Siguiente")

            salas.pack()

            textosalas=""

            for sala in self.cine.getSalas():
                if sala.verificarDisponibilidad(int(info[0]),int(info[1])):
                    textosalas+= "Sala "+ str(sala.getNumero()) +"\n"
            disponibles=Label(self.cuerpo,text=textosalas)
            disponibles.pack()

            def funcionesAuto(action):
                info.append(salas.getValue("Numero"))
                self.cine.programarFuncionesAuto(int(info[0]),int(info[1]),self.cine.buscarSala(int(info[2])))  #TODO: Revisar que funcione
                
                picklefile = open('pcs', 'wb')
                pickle.dump(self.cine,picklefile) #Bloque de serialización
                picklefile.close()

                messagebox.showinfo(title="Información",message="Función generada con éxito")
                self.cambiar()


            
            salas.button.bind("<ButtonRelease>",funcionesAuto)

        diames.button.bind("<ButtonRelease>", salasDisponibles)

        
        #TODO: Revisar que esté funcionando
        #TODO: Mostrar los nombres de las salas, las peliculas y los horarios para cada una?

    
    #TODO: Falta serializar rifa
    def rifa(self):

        self.cambiar()

        self.titulo.configure(text = "Rifar Boleto")
        self.texto.configure(text = "Permite rifar un boleto a una función deseada entre los clientes mas fieles")

        nomCriterios="Función"
        criterios=["Dia","Mes","Codigo"]
        nomValores="Información"
        valIniciales=None
        valHabilitados=None
        info=[]

        diames=FieldFrame("Fecha", ["Dia","Mes"],nomValores,valIniciales,valHabilitados,self.cuerpo)
        diames.pack()
        diames.button.configure(text="Siguiente")

        def funcdispo(action):
            info.append(diames.getValue("Dia"))
            info.append(diames.getValue("Mes"))
            diames.pack_forget()

            funcdia=FieldFrame("Funcion", ["Numero"],nomValores,valIniciales,valHabilitados,self.cuerpo)
            funcdia.pack()
            funcdia.button.configure(text="Rifar")

            if len(self.cine.verFuncion(int(info[0]),int(info[1])))==0:
                messagebox.showinfo(title="Error",message="No hay funciones disponibles para ese dia")
                self.cambiar()

            else:
                funcioneslibres="Funciones del dia\n"+Funcion.formatearFunciones(self.cine.verFuncion(int(info[0]),int(info[1])))
                textofunc=Label(self.cuerpo,text=funcioneslibres)
                textofunc.pack()

            def resultado(action):
                info.append(funcdia.getValue("Numero"))
                candidatos="Clientes fieles candidatos a la rifa: "

                for c in self.cine.clientesValiosos():
                    candidatos+=c.getNombre()+" "

                ganador="GANADOR: "+self.cine.rifarBoleto(int(info[2]))

                messagebox.showinfo(title='Rifa de Boleto!', message=candidatos,
                                    detail=ganador)  ###FALTA MOSTRAR LOS CANDIDATOS A GANAR Y EL GANADOR QUE ESO ES LO DE LA CAPA LOGICA
                self.cambiar()
            funcdia.button.bind("<ButtonRelease>",resultado)        ##Segundo boton

        diames.button.bind("<ButtonRelease>",funcdispo)  ##Primer boton

        #rifa = FieldFrame(nomCriterios, criterios,nomValores,valIniciales,valHabilitados,self.cuerpo)
        
        #rifa.pack()

        #funciones=Label(self.cuerpo,text="Aca irian las funciones disponibles")     ###FALTA ENLAZAR CON LAS FUNCIONES DISPONIBLES QUE BOTA LA CAPA LOGICA
        #funciones.pack()

    def agregarSala(self):
        self.cambiar()

        self.titulo.configure(text = "Agregar una sala")
        self.texto.configure(text = "Permite agregar una sala segun su tipo (2D o 3D)")

        checked=IntVar()

        global nueva

        def create(action):         ###En teoria funciona, falta capturar excepciones
            if checked.get()==2:
                Sala2D(nueva.getValue("Filas"),nueva.getValue("Columnas"),nueva.getValue("Filas VIP"),self.cine)
                print("Se creó sala 2D")
            elif checked.get()==3:
                Sala3D(nueva.getValue("Filas"), nueva.getValue("Columnas"),nueva.getValue("Gafas disponibles"), self.cine)
                print("Se creó sala 3D")

            picklefile = open('pcs', 'wb')
            pickle.dump(self.cine,picklefile) #Bloque de serialización
            picklefile.close()

            messagebox.showinfo(title="Información",message="Sala creada con éxito!")
            #for i in self.cine.getSalas():
            #    print(i.getNumero())
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
                nueva = FieldFrame("Tamaño", ["Filas","Columnas","Filas VIP"],"Cantidad",None,None,self.cuerpo)
                nueva.pack()
                nueva.button.bind('<ButtonRelease>',create)
            else:
                nueva.pack_forget()
                nueva = FieldFrame("Tamaño", ["Filas","Columnas","Filas VIP"],"Cantidad",None,None,self.cuerpo)
                nueva.pack()
                nueva.button.bind('<ButtonRelease>',create)

        tresd=Radiobutton(self.cuerpo,text="3D",variable=checked,value=3,command=tres)
        tresd.pack()
        dosd=Radiobutton(self.cuerpo,text="2D",variable=checked,value=2,command=dos)
        dosd.pack()



        