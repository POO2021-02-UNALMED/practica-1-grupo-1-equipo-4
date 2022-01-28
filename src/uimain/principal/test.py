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

def cambioAImg1(action):
    label1.grid_forget()
    label1['image']=img1
    label1.grid(column=0,row=0,padx=3,pady=3)
    label1.bind('<ButtonPress-1>', cambioAImg2)


def cambioAImg2(action):
    label1.grid_forget()
    label1['image']=img2
    label1.grid(column=0,row=0,padx=3,pady=3)
    label1.bind('<ButtonPress-1>', cambioAImg3)


def cambioAImg3(action):
    label1.grid_forget()
    label1['image']=img3
    label1.grid(column=0,row=0,padx=3,pady=3)
    label1.bind('<ButtonPress-1>', cambioAImg4)


def cambioAImg4(action):
    label1.grid_forget()
    label1['image']=img4
    label1.grid(column=0,row=0,padx=3,pady=3)
    label1.bind('<ButtonPress-1>', cambioAImg5)


def cambioAImg5(action):
    label1.grid_forget()
    label1['image']=img5
    label1.grid(column=0,row=0,padx=3,pady=3)
    label1.bind('<ButtonPress-1>', cambioAImg1)

def ventanaUsuario():
        ventana_nueva=Toplevel()                ##Aca va la venta de usuario
        ventana_nueva.geometry("350x400")
        ventana_nueva.title("VENTANA DE DAZA")
        window.iconify()

def descripcion():
        global greetings
        greetings="Buena la rata"
        saludo.config(text = greetings)



frame1=Frame(window,width=100,height=200,bg="red")
frame1.pack(fill=Y, side=LEFT)   

WAndH={"height":200, "width":100}
frame2=Frame(window, **WAndH) #TODO: Change master

frame3=Frame(frame1,width=100,height=70,bg="yellow")
frame3.grid(column = 0, row= 0, padx=3, pady=3,columnspan=4)

greetings="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris commodo interdum blandit. Vestibulum ante ipsum primis in\n faucibus orci luctus et ultrices posuere cubilia curae; Aenean tempor accumsan libero, eget rhoncus erat ullamcorper quis.\nSed eget lobortis libero. Cras ac elit sed elit luctus auctor. Vivamus nisi augue, efficitur eu finibus et, convallis ut lectus.\nPhasellus bibendum eget risus a luctus. Sed congue diam id erat tincidunt, id tincidunt velit vulputate. Donec felis quam, egestas a\nlibero vel, egestas tempus ex. Quisque a massa commodo, mattis risus vel, facilisis velit. Lorem ipsum dolor sit amet, consectetur\nadipiscing elit. Mauris at aliquet lacus, eget efficitur sapien."
saludo=Label(frame3,text=greetings)
saludo.grid(column=0,row=0, padx=3, pady=3)

frame4=Frame(frame1,width=100,height=130,bg="green")
frame4.grid(column = 0, row= 1, padx=3, pady=3,columnspan=4)

label1=Label(frame4)
img1=PhotoImage(file=getPath('cinepng.png'),width=600,height=600)
label1['image']=img1
label1.grid(column=0,row=0,padx=3, pady=3)

img2=PhotoImage(file=getPath('cinebahia.png'),width=600,height=600)
img3=PhotoImage(file=getPath('cine1.png'),width=600,height=600)
img4=PhotoImage(file=getPath('cine2.png'),width=600,height=600)
img5=PhotoImage(file=getPath('cine4.png'),width=600,height=600)

label1.bind('<ButtonPress-1>', cambioAImg2)

boton=Button(master=frame4,text="Acceder a ventana de usuario",width=25,height=5,command=ventanaUsuario)
boton.grid(column=0,row=1)

menubar=Menu(window)
menu1=Menu(menubar)
menubar.add_cascade(menu=menu1,label="Opciones")
menu1.add_command(label="Descripcion",command=descripcion)
menu1.add_command(label="Salir",command=window.destroy)

window['menu']=menubar

frameHoja=Frame(frame2,height=50,bg="gray")
hojaIns=Label(frameHoja, text="Pulse sobre cada \n text para cambiar")
hoja1=Label(frameHoja, text="Daniel Daza")
hoja2=Label(frameHoja, text="Ochoa")
hoja3=Label(frameHoja, text="Daniel Santiago")
hoja4=Label(frameHoja, text="Marlon Calle")

hojaFotos1=Fotos(frame2,"1")
hojaFotos2=Fotos(frame2,"2")
hojaFotos3=Fotos(frame2,"3")
hojaFotos4=Fotos(frame2,"4")

frame2.pack()
frameHoja.pack()
hojaIns.pack()
hojaIns.bind('<ButtonPress-1>', cambioDeInstrucciones)
hoja1.bind('<ButtonPress-1>', cambioAHoja2)
hoja2.bind('<ButtonPress-1>', cambioAHoja3)
hoja3.bind('<ButtonPress-1>', cambioAHoja4)
hoja4.bind('<ButtonPress-1>', cambioAHoja1)
window.mainloop()