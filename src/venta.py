from tkinter import *
from uimain.user.fieldFrame import FieldFrame
from gestionAplicacion.cinemas.cliente import Cliente
from gestionAplicacion.cinemas.cine import Cine
from gestionAplicacion.boleteria.funcion import Funcion
from gestionAplicacion.boleteria.pelicula import Pelicula
from gestionAplicacion.boleteria.horario import Horario
from gestionAplicacion.salas.sala2D import Sala2D
window = Tk()

cine = Cine("pene")
cine.agregarCliente(Cliente(1001, "Pedro", 15, "Estudiante", cine))
rey_leon = Pelicula("Rey Leon", "Animada", 2, "Espanol", 0, cine)
Sala2D(7, 8, 2,cine)
Funcion.crearFuncion(17, 12, Horario.UNO, rey_leon, 1, cine)

print(cine.verFuncion(17,12))

def ventana():
    global window
    venta = Frame()
    cliente = None
    frame  = FieldFrame("Cedula Cliente",["Cedula"],"Ingrese Dato",None, None,window)
    
    def vender(existente):
        
        nonlocal frame
        nonlocal venta
        nonlocal cliente
        boton_recomendada = None
        boton_funcion = None
        boton_pelicula = None


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
            nueva.pack_forget()
            nueva = FieldFrame("Mostrar", ["Número de silla"], "Ingrese Dato", None, None, venta)
            nueva.pack()

        def mostrar_funciones(funciones):
            #se usa funciones para mostrar en pantalla las funciones disponibles
            nonlocal nueva
            nonlocal texto
            nonlocal cliente

            nueva.pack_forget()
            venta.pack_forget()
            nueva = Frame()
            mostrar = FieldFrame("Mostrar", ["Número de Funcion"], "Ingrese Dato", None, None, nueva)
            mostrar.pack()
            nueva.pack()
            texto.pack_forget()
            numero = None
            mostrar.button.bind('<ButtonRelease>', lambda x: mostrar_sillas(numero))
            
            label = Label(nueva,text = str(Funcion.formatearFunciones(funciones)))
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
                
                print(cine.verFuncion(int(funcion.getValue("Dia")),int(funcion.getValue("Mes"))))
                funciones = cine.verFuncion(int(funcion.getValue("Dia")),int(funcion.getValue("Mes")))
                mostrar_funciones(funciones)
                
            nueva.button.bind('<ButtonRelease>', lambda x: funcionesxfuncion())
            
            
        def llamar_pelicula():
            
            nonlocal nueva

            pelicula = FieldFrame("Fecha", ["Pene","Vagina"], "Ingrese datos", None, None, venta)
            pelicula.pack()
            try:
                nueva.pack_forget()
            except AttributeError:
                pass
            nueva = pelicula
            funciones = None
            nueva.button.bind('<ButtonRelease>', lambda x: mostrar_funciones(funciones))

        def llamar_recomendada():
            #mostrar recomendadas cliente
            nonlocal nueva
            nonlocal cliente
            recomendada = FieldFrame("Pelicula", ["Número de pelicula"], "Ingrese datos", None, None, venta)
            recomendada.pack()
            try:
                nueva.pack_forget()
            except AttributeError:
                pass
            nueva = recomendada
            funciones = None
            nueva.button.bind('<ButtonRelease>', lambda x: mostrar_funciones(funciones))            



        
        if(existente):

            boton_recomendada = Radiobutton(venta,text="Recomendada",value=1,command=llamar_recomendada)
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
        global cine
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
    window.mainloop()
ventana()



