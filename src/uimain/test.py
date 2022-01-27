from cProfile import label
from tkinter import *
from tkinter import messagebox
window = Tk()
window.option_add('*tearOff', FALSE)

def cambioDeInstrucciones(action):
    hojaIns.pack_forget()
    hoja1.pack()
    hojaFotos1.pack()

def cambioAHoja1(action):
    hoja4.pack_forget()
    hojaFotos4.pack_forget()
    hoja1.pack()
    hojaFotos1.pack()

def cambioAHoja2(action):
    hoja1.pack_forget()
    hojaFotos1.pack_forget()
    hoja2.pack()
    hojaFotos2.pack()

def cambioAHoja3(action):
    hoja2.pack_forget()
    hojaFotos2.pack_forget()
    hoja3.pack()
    hojaFotos3.pack()

def cambioAHoja4(action):
    hoja3.pack_forget()
    hojaFotos3.pack_forget()
    hoja4.pack()
    hojaFotos4.pack()   

WAndH={"height":200, "width":100}
frameDesarrolladores=Frame(window, **WAndH) #TODO: Change master

frameHoja=Frame(frameDesarrolladores,height=50,bg="gray")
hojaIns=Label(frameHoja, text="Pulse sobre cada \n text para cambiar")
hoja1=Label(frameHoja, text="Daniel Daza")
hoja2=Label(frameHoja, text="Ochoa")
hoja3=Label(frameHoja, text="Daniel Santiago")
hoja4=Label(frameHoja, text="Marlon Calle")

hojaFotos1=Frame(frameDesarrolladores,height=50)
hoja1Foto1=label(hojaFotos1)
Foto11=PhotoImage(file)
hojaFotos2=Frame(frameDesarrolladores,height=50)
hojaFotos3=Frame(frameDesarrolladores,height=50)
hojaFotos4=Frame(frameDesarrolladores,height=50)


frameDesarrolladores.pack()
frameHoja.pack()
frameFotos.pack()
hojaIns.pack()
hojaIns.bind('<ButtonPress-1>', cambioDeInstrucciones)
hoja1.bind('<ButtonPress-1>', cambioAHoja2)
hoja2.bind('<ButtonPress-1>', cambioAHoja3)
hoja3.bind('<ButtonPress-1>', cambioAHoja4)
hoja4.bind('<ButtonPress-1>', cambioAHoja1)
window.mainloop()