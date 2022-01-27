from cProfile import label
from tkinter import *
from tkinter import messagebox

class Fotos(Frame):
    def __init__(self,a,hoja):
        super().__init__(a)
        self.fot=[]
        self.hoja1Foto1=[Label(self,width=50, height=50) for i in range(4)]
        for i in range(1,5):
            fil="hoja"+hoja+"Foto"+str(i)+".png"
            self.fot.append(PhotoImage(file=getPath(fil)))
        i=0
        for r in range(2):
            for c in range(2):
                self.hoja1Foto1[i]["image"]=self.fot[i]
                self.hoja1Foto1[i].grid(row=r,column=c)
                i+=1

        


window = Tk()
window.option_add('*tearOff', FALSE)

def getPath(txt):
    import os
    import sys
    return os.path.join(sys.path[0],txt) 

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

def ayuda(place,r,c,text):
    hojaXFotoY=Label(place)
    Foto11=PhotoImage(file=getPath(text))
    hojaXFotoY["image"]=Foto11
    return hojaXFotoY,Foto11




hojaFotos1=Fotos(frameDesarrolladores,"1")
hojaFotos2=Fotos(frameDesarrolladores,"2")
hojaFotos3=Fotos(frameDesarrolladores,"3")
hojaFotos4=Fotos(frameDesarrolladores,"4")

frameDesarrolladores.pack()
frameHoja.pack()
hojaIns.pack()
hojaIns.bind('<ButtonPress-1>', cambioDeInstrucciones)
hoja1.bind('<ButtonPress-1>', cambioAHoja2)
hoja2.bind('<ButtonPress-1>', cambioAHoja3)
hoja3.bind('<ButtonPress-1>', cambioAHoja4)
hoja4.bind('<ButtonPress-1>', cambioAHoja1)
window.mainloop()