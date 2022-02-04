from ast import NotIn
from tkinter import *
from uimain.user.fieldFrame import FieldFrame
from gestionAplicacion.cinemas.cliente import Cliente
from gestionAplicacion.cinemas.cine import Cine
from gestionAplicacion.boleteria.funcion import Funcion
from gestionAplicacion.boleteria.pelicula import Pelicula
from gestionAplicacion.boleteria.horario import Horario
from gestionAplicacion.salas.sala2D import Sala2D
from uimain.user.excepciones.notipo import NoTipo
from uimain.user.excepciones.notin import NotIn
from uimain.user.excepciones.notchair import NotChair

"""
Zona donde se trabajan todas las ventanas relacionadas a Venta
Aquí se incluye inscripción de cliente, venta de boleta por recomendación y por función.

"""


def ventana(variable, window, cine):
    venta = Frame()
    cliente = None
    frame = FieldFrame("Cedula Cliente", ["Cedula"], "Ingrese Dato", None, None, window)

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
        texto = Label(venta, text="Busqueda por : ")
        

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
            label = Label(venta, text="Seleccione la silla que desea")
            label.pack()
            nueva = Frame(venta)
            nueva.pack()

            def vender_boleto(numero):
                nonlocal nueva
                funcion.VentaBoleto(funcion.getBoletos()[numero], cliente)
                nueva.pack_forget()
                nueva = FieldFrame("Se ha vendido el boleto",
                                   ["El precio es"],
                                   "numero " + str(numero + 1),
                                   [str((funcion.getBoletos()[numero].getPrecioTotal()))], None, venta)
                nueva.pack()
                nueva.button.bind('<ButtonRelease>', lambda x=variable: variable.cambiar())
                descuento_aplicado = Label(venta, text = "Descuento aplicado \n" + str((cliente.getDescuento())*funcion.getBoletos()[numero].getPrecioTotal()))
                descuento_aplicado.pack()

            def holi():
                raise NotChair

            num = 0
            botones = []
            funciones = []
            for i in range(filas):
                for j in range(columnas):
                    if (num < funcion.getSala().getCantidadSillas()):

                        if (sillas[num][0] == True):
                            funciones.append(lambda: vender_boleto(num))
                            a = Button(master=nueva, text=str(sillas[num][1]), height=2, width=4,
                                       command=lambda x=(columnas * i + j): vender_boleto(x))
                        else:
                            a = Button(master=nueva, text=str(sillas[num][1]), height=2, width=4, bg="blue",
                                       command=holi)
                        botones.append(a)
                        botones[num].grid(column=j, row=i, padx=3, pady=3)
                        num += 1

        def mostrar_funciones(funciones):
            # se usa funciones para mostrar en pantalla las funciones disponibles
            nonlocal nueva
            nonlocal texto
            nonlocal cliente
            nonlocal cine
            nonlocal label
            

            
            try:
                nueva.pack_forget()
                label.pack_forget()
            except AttributeError:
                pass


            nueva = FieldFrame("Mostrar", ["Número de Funcion"], "Ingrese Dato", None, None, venta)
            nueva.pack()
            texto.pack_forget()

            def obtenerFuncion():
                nonlocal nueva
                numero = nueva.getValue("Número de Funcion")
                try:
                    cine.BuscadorFuncion(numero).getHorario()
                except:
                    raise NotIn

                mostrar_sillas(cine.BuscadorFuncion(numero))

            nueva.button.bind('<ButtonRelease>', lambda x: obtenerFuncion())
            label = Label(venta, text=str(Funcion.formatearFunciones(funciones)))
            label.pack()

            try:
                boton_recomendada.pack_forget()
                boton_funcion.pack_forget()
            except AttributeError:
                boton_funcion.pack_forget()

        def llamar_funcion():
            nonlocal label
            nonlocal nueva
            nonlocal cine

            label.pack_forget()
            funcion = FieldFrame("Fecha", ["Dia", "Mes"], "Ingrese datos", None, None, venta)
            funcion.pack()
            try:
                nueva.pack_forget()
            except AttributeError:
                pass
            nueva = funcion
            funciones = None

            def funcionesxfuncion():
                nonlocal funciones

                funciones = cine.verFuncion(int(funcion.getValue("Dia")), int(funcion.getValue("Mes")))
                mostrar_funciones(funciones)

            nueva.button.bind('<ButtonRelease>', lambda x: funcionesxfuncion())

        if (existente):
            
            nombre = cliente.getNombre()
            puesto = cliente.getOcupacion()

            label = Label(venta, text = str(nombre)+" / "+ str(puesto))
            label.pack()
            texto.pack()
            boton_recomendada = Radiobutton(venta, text="Recomendada", value=1,
                                            command=lambda: mostrar_funciones(cine.verFuncion(cliente)))
            boton_recomendada.pack()
            boton_funcion = Radiobutton(venta, text="Funcion", value=3, command=llamar_funcion)
            boton_funcion.pack()
        else:
            
            nombre = cliente.getNombre()
            puesto = cliente.getOcupacion()

            label = Label(venta, text = str(nombre)+" / "+ str(puesto))
            label.pack()

            boton_funcion = Radiobutton(venta, text="Funcion", value=3, command=llamar_funcion)
            boton_funcion.pack()

    def cedula(numero):
        nonlocal cine
        nonlocal frame
        nonlocal cliente
        try:
            int(numero)
        except:
            raise NoTipo

        if (cine.buscadorCliente(numero) == None):

            frame.pack_forget()
            frame = FieldFrame("Inscripción", ["Cedula referido", "Nombre", "Edad", "Ocupacion"], "ingrese datos", None,
                               None, window)
            frame.pack()

            def crearCliente():
                nonlocal cliente
                if (int(frame.getValue("Cedula referido")) != 0):
                    try:
                        [int(i) / 0 for i in frame.getValue("Nombre") if i in list("123456789")]
                        [int(i) / 0 for i in frame.getValue("Ocupacion") if i in list("123456789")]
                        int(frame.getValue("Edad"))
                        cine.buscadorCliente(int(frame.getValue("Cedula referido"))).getDescuento()

                    except:
                        raise NoTipo

                    cliente = Cliente(numero, str(frame.getValue("Nombre")), int(frame.getValue("Edad")),
                                      frame.getValue("Ocupacion"), cine)
                    cliente.referidos()
                    vender(False)
                else:
                    try:
                        [int(i) / 0 for i in frame.getValue("Nombre") if i in list("123456789")]
                        [int(i) / 0 for i in frame.getValue("Ocupacion") if i in list("123456789")]
                        int(frame.getValue("Edad"))

                    except:
                        raise NoTipo

                    cliente = Cliente(numero, str(frame.getValue("Nombre")), int(frame.getValue("Edad")),
                                      frame.getValue("Ocupacion"), cine)
                    vender(False)

            frame.button.bind('<ButtonRelease>', lambda x: crearCliente())

        else:

            cliente = cine.buscadorCliente(numero)
            vender(True)

    frame.pack()

    frame.button.bind('<ButtonRelease>', lambda x: cedula(frame.getValue("Cedula")))