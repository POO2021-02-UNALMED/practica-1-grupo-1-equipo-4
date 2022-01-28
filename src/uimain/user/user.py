from tkinter import *
from zona1 import Zona1
from zona2 import Zona2

## Esta es la ventana de usuario
user = Toplevel()
user.title("hola")
user.geometry("1000x600")
user.option_add("*tearOff",False)


frame = Zona2(user)         #acá creamos la zona 2, que es la que corresponde a la aplicación como tal
zona1 = Zona1(user, frame)  #acá creamos la barra de navegación del menú, se pasa el parametro frame para que coja las funciones que este tiene
user.mainloop()         
