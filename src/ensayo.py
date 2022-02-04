from uimain.principal.first import First
from tkinter import *
from gestionAplicacion.cinemas.cine import Cine
from gestionAplicacion.boleteria.pelicula import Pelicula
from gestionAplicacion.cinemas.cliente import Cliente
from gestionAplicacion.salas.sala3D import Sala3D
from gestionAplicacion.salas.sala2D import Sala2D
from gestionAplicacion.boleteria.funcion import Funcion
from gestionAplicacion.boleteria.horario import Horario
from uimain.user.excepciones.notin import NotIn
from uimain.user.excepciones.notipo import NoTipo
from uimain.user.excepciones.rangonoper import RangoNoPer
import pickle

#entana=Usuario()
"""cine = Cine("Bahia")
rey_leon =  Pelicula("Rey Leon", "Animada", 2, "Espanol", 0, cine)
avengers =  Pelicula("Avengers", "Accion", 2, "Espanol", 12, cine)
naranja =  Pelicula("La Naranja Mecanica", "Clasica", 2, "Espanol", 18, cine)
resplandor =  Pelicula("El Resplandor", "Terror", 2, "Espanol", 15, cine)
toystory =  Pelicula("Toy Story", "Animada", 2, "Espanol", 8, cine)
harrypotter =  Pelicula("Harry Potter", "Fantasia", 2, "Espanol", 10, cine)
avatar =  Pelicula("Avatar", "Fantasia", 2, "Espanol", 15, cine)
pianista =  Pelicula("El Pianista", "Drama", 2, "Espanol", 18, cine)

cliente1 =  Cliente(1001, "Pedro", 15, "Estudiante", cine)
cliente2 =  Cliente(1002, "Daniel Santiago", 20, "Estudiante", cine)
cliente3 =  Cliente(1003, "Daniel Daza", 20, "Estudiante", cine)
cliente4 =  Cliente(1004, "Marlon Calle", 20, "Estudiante", cine)
cliente5 =  Cliente(1005, "Jose Daniel Bustamante", 21, "Estudiante", cine)
cliente6 =  Cliente(1006, "Jaime Guzman", 40, "Trabajador", cine)
cliente7 =  Cliente(1007, "Luisa Guarin", 30, "Trabajadora", cine)
cliente8 =  Cliente(1008, "Pedro Gomez", 15, "Estudiante", cine)
cliente9 =  Cliente(1009, "Juan Esteban", 15, "Estudiante", cine)
cliente10 =  Cliente(1010, "Alejandra", 20, "Trabajadora", cine)
cliente11 =  Cliente(1011, "Sofia", 25, "Estudiante", cine)
cliente12 =  Cliente(1012, "Jose Daniel Bustamante", 21, "Trabajadora", cine)
cliente13 =  Cliente(1013, "Luisa Guarin", 30, "Trabajadora", cine)

sala1 =  Sala2D(7, 8, 2,cine)
sala2 =  Sala2D(9, 8, 2,cine)
sala3 =  Sala3D(9, 9, 50,cine)
sala4 =  Sala3D(8, 9, 36,cine)


funcion_1 = Funcion.crearFuncion(17, 12, Horario.UNO, rey_leon, 1, cine)
funcion_2 = Funcion.crearFuncion(17, 12, Horario.DOS, naranja, 1, cine)
funcion_3 = Funcion.crearFuncion(17, 12, Horario.TRES, naranja, 1, cine)
funcion_11 = Funcion.crearFuncion(17, 12, Horario.CUATRO, rey_leon, 1, cine)
funcion_22 = Funcion.crearFuncion(17, 12, Horario.CINCO, naranja, 1, cine)
funcion_33 = Funcion.crearFuncion(17, 12, Horario.SEIS, naranja, 1, cine)
funcion_4 = Funcion.crearFuncion(17, 12, Horario.CINCO, resplandor, 3, cine)
funcion_5 = Funcion.crearFuncion(17, 12, Horario.SEIS, avengers, 3, cine)
funcion_7 = Funcion.crearFuncion(16, 12, Horario.UNO, toystory, 4, cine)
funcion_8 = Funcion.crearFuncion(17, 12, Horario.CUATRO, toystory, 2, cine)
funcion_9 = Funcion.crearFuncion(16, 12, Horario.TRES, toystory, 2, cine)

funcion_1.VentaBoleto(funcion_1.getBoletos()[0],cliente1)
funcion_1.VentaBoleto(funcion_1.getBoletos()[2],cliente1)
funcion_2.VentaBoleto(funcion_2.getBoletos()[3],cliente2)
funcion_2.VentaBoleto(funcion_2.getBoletos()[4],cliente2)
funcion_1.VentaBoleto(funcion_1.getBoletos()[5],cliente2)
funcion_1.VentaBoleto(funcion_1.getBoletos()[6],cliente3)
funcion_2.VentaBoleto(funcion_2.getBoletos()[7],cliente3)
funcion_2.VentaBoleto(funcion_2.getBoletos()[8],cliente3)
funcion_1.VentaBoleto(funcion_1.getBoletos()[9],cliente4)
funcion_1.VentaBoleto(funcion_1.getBoletos()[25], cliente5)
funcion_2.VentaBoleto(funcion_2.getBoletos()[15], cliente6)
funcion_2.VentaBoleto(funcion_2.getBoletos()[17], cliente5)


funcion_4.VentaBoleto(funcion_4.getBoletos()[0], cliente7)
funcion_3.VentaBoleto(funcion_3.getBoletos()[45], cliente8)
funcion_3.VentaBoleto(funcion_3.getBoletos()[3],cliente9)
funcion_3.VentaBoleto(funcion_3.getBoletos()[5],cliente10)
funcion_3.VentaBoleto(funcion_3.getBoletos()[6],cliente11)
funcion_3.VentaBoleto(funcion_3.getBoletos()[9],cliente12)
funcion_3.VentaBoleto(funcion_3.getBoletos()[25], cliente13)"""


window = Tk()
window.option_add('*tearOff', FALSE)

picklefile = open('pcs', 'rb')
cine = pickle.load(picklefile) #Bloque de deserialzación
picklefile.close()

first=First(window,cine)
first.pack()

window.mainloop()

picklefile = open('pcs', 'wb')
pickle.dump(cine,picklefile) #Bloque de serialización
picklefile.close()
