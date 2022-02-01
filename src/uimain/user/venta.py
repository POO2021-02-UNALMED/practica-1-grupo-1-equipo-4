from tkinter import *
from uimain.user.fieldFrame import FieldFrame
from gestionAplicacion.cinemas.cliente import Cliente
from gestionAplicacion.cinemas.cine import Cine
from gestionAplicacion.boleteria.funcion import Funcion
from gestionAplicacion.boleteria.pelicula import Pelicula
from gestionAplicacion.boleteria.horario import Horario
from gestionAplicacion.salas.sala2D import Sala2D



"""/*window = Tk()

cine = Cine("pene")
cine.agregarCliente(Cliente(1001, "Pedro", 15, "Estudiante", cine))
rey_leon = Pelicula("Rey Leon", "Animada", 2, "Espanol", 0, cine)
Sala2D(7, 8, 2,cine)
Funcion.crearFuncion(17, 12, Horario.UNO, rey_leon, 1, cine)*/"""

def ventana(window, cine):
    venta = Frame()
    cliente = None
    frame  = FieldFrame("Cedula Cliente",["Cedula"],"Ingrese Dato",None, None,window)
    
    def vender(existente):
        
        nonlocal frame
        nonlocal venta
        nonlocal cliente
        nonlocal cine

        boton_recomendada = None
        boton_funcion = None
        boton_pelicula = None
        label = None

        frame.pack_forget()
        frame = Frame(window)
        frame.pack()
        venta = Frame(frame)
        venta.pack()
        texto = Label(venta,text="Busqueda por : ")
        texto.pack()


        nueva = None


        def mostrar_sillas(funcion):
            nonlocal nueva
            nonlocal cliente
            nonlocal cine
            nonlocal label

            sillas = funcion.verDisponibilidad()
            filas = funcion.getSala().getFilas()
            columnas = funcion.getSala().getColumnas()
            nueva.pack_forget()
            label.pack_forget()
            nueva = Frame(venta)
            nueva.pack()


            for silla in sillas:
                print(silla)
            num = 0
            total = []
            for i in range(filas):
                fila=[]
                for j in range(columnas):
                    if (num<funcion.getSala().cantidadsillas()):
                        fila.append(Button(master = nueva, text=str(sillas[num][1]), height=2, width = 4))

                        num += 1
                        fila[j].grid(column= j, row = i, padx = 3, pady = 3)
                total.append(fila)            

        def mostrar_funciones(funciones):
            #se usa funciones para mostrar en pantalla las funciones disponibles
            nonlocal nueva
            nonlocal texto
            nonlocal cliente
            nonlocal cine
            nonlocal label

            try:
                nueva.pack_forget()
            except AttributeError:
                pass
            nueva = FieldFrame("Mostrar", ["Número de Funcion"], "Ingrese Dato", None, None, venta)
            nueva.pack()
            texto.pack_forget()

            def obtenerFuncion():
                nonlocal nueva

                numero = nueva.getValue("Número de Funcion")
                mostrar_sillas(cine.BuscadorFuncion(numero))


            nueva.button.bind('<ButtonRelease>', lambda x: obtenerFuncion())
            
            label = Label(venta,text = str(Funcion.formatearFunciones(funciones)))
            label.pack()

            try:
                boton_recomendada.pack_forget()
                boton_funcion.pack_forget()
                boton_pelicula.pack_forget()
            except AttributeError:
                boton_funcion.pack_forget()
                boton_pelicula.pack_forget()
            

                         




        def llamar_funcion():
            
            nonlocal nueva
            nonlocal cine

            funcion = FieldFrame("Fecha", ["Dia","Mes"], "Ingrese datos", None, None, venta)
            funcion.pack()
            try:
                nueva.pack_forget()
            except AttributeError:
                pass
            nueva = funcion
            funciones = None
            def funcionesxfuncion():
                nonlocal funciones
                
                funciones = cine.verFuncion(int(funcion.getValue("Dia")),int(funcion.getValue("Mes")))
                mostrar_funciones(funciones)
                
            nueva.button.bind('<ButtonRelease>', lambda x: funcionesxfuncion())
            
            
        def llamar_pelicula():
            
            nonlocal nueva
            nonlocal cine

            pelicula = FieldFrame("Fecha", ["Nombre"], "Ingrese datos", None, None, venta)
            pelicula.pack()
            try:
                nueva.pack_forget()
            except AttributeError:
                pass
            nueva = pelicula
            funciones = None
            nueva.button.bind('<ButtonRelease>', lambda x: mostrar_funciones(funciones))

        if(existente):

            boton_recomendada = Radiobutton(venta,text="Recomendada",value=1, command = lambda: mostrar_funciones(cine.verFuncion(cliente)))
            boton_recomendada.pack()
            boton_funcion=Radiobutton(venta,text="Funcion",value=3,command=llamar_funcion)
            boton_funcion.pack()
            boton_pelicula=Radiobutton(venta,text="Pelicula",value=2,command=llamar_pelicula)
            boton_pelicula.pack()
        else:

            boton_funcion=Radiobutton(venta,text="Funcion",value=3,command=llamar_funcion)
            boton_funcion.pack()
            boton_pelicula=Radiobutton(venta,text="Pelicula",value=2,command=llamar_pelicula)
            boton_pelicula.pack()            
    
    def cedula(numero):
        nonlocal cine
        nonlocal frame
        nonlocal cliente
        
        if(cine.buscadorCliente(numero) == None):
            
            frame.pack_forget()
            frame = FieldFrame("Inscripción", ["Referido","Cedula referido","Nombre","Edad", "Ocupacion"],"ingrese datos", None, None, window)
            frame.pack()
            def crearCliente():
                nonlocal cliente
                cliente = Cliente(cedula, frame.getValue("Nombre"), frame.getValue("Edad"), frame.getValue("Ocupacion"), cine)
            
            frame.button.bind('<ButtonRelease>',lambda x: crearCliente())
            frame.button.bind('<ButtonRelease>',lambda x: vender(False))
            
        else:

            cliente = cine.buscadorCliente(numero)
            vender(True)

    
    frame.pack()
    
    
    frame.button.bind('<ButtonRelease>',lambda x: cedula(frame.getValue("Cedula")))



