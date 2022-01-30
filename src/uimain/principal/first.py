from msilib.schema import Class
import sys
import os
from tkinter import *
from uimain.user.usuario import Usuario

class First(Frame):
    def __init__(self,master):
        super().__init__(master)
        
        frame1=Frame(self,width=100,height=200,bg="red")
        frame1.pack(fill=Y, side=LEFT)   

        WAndH={"height":200, "width":100}
        frame2=Frame(self, **WAndH) 

        frame3=Frame(frame1,width=100,height=70,bg="yellow")
        frame3.grid(column = 0, row= 0, padx=3, pady=3,columnspan=4)

        greetings="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris commodo interdum blandit. Vestibulum ante ipsum primis in\n faucibus orci luctus et ultrices posuere cubilia curae; Aenean tempor accumsan libero, eget rhoncus erat ullamcorper quis.\nSed eget lobortis libero. Cras ac elit sed elit luctus auctor. Vivamus nisi augue, efficitur eu finibus et, convallis ut lectus.\nPhasellus bibendum eget risus a luctus. Sed congue diam id erat tincidunt, id tincidunt velit vulputate. Donec felis quam, egestas a\nlibero vel, egestas tempus ex. Quisque a massa commodo, mattis risus vel, facilisis velit. Lorem ipsum dolor sit amet, consectetur\nadipiscing elit. Mauris at aliquet lacus, eget efficitur sapien."
        self.saludo=Label(frame3,text=greetings)
        self.saludo.grid(column=0,row=0, padx=3, pady=3)

        frame4=Frame(frame1,width=100,height=130,bg="green")
        frame4.grid(column = 0, row= 1, padx=3, pady=3,columnspan=4)

        self.label1=Label(frame4)
        self.img1=PhotoImage(file=self.getPath('cinepng.png'),width=600,height=600)
        self.label1['image']=self.img1
        self.label1.grid(column=0,row=0,padx=3, pady=3)

        self.img2=PhotoImage(file=self.getPath('cinebahia.png'),width=600,height=600)
        self.img3=PhotoImage(file=self.getPath('cine1.png'),width=600,height=600)
        self.img4=PhotoImage(file=self.getPath('cine2.png'),width=600,height=600)
        self.img5=PhotoImage(file=self.getPath('cine4.png'),width=600,height=600)

        self.label1.bind('<ButtonPress-1>', self.cambioAImg2)

        boton=Button(master=frame4,text="Acceder a ventana de usuario",width=25,height=5,command=self.ventanaUsuario)
        boton.grid(column=0,row=1)

        menubar=Menu(master)
        menu1=Menu(menubar)
        menubar.add_cascade(menu=menu1,label="Opciones")
        menu1.add_command(label="Descripcion",command=self.descripcion)
        menu1.add_command(label="Salir",command=master.destroy)

        master['menu']=menubar

        frameHoja=Frame(frame2,height=50,bg="gray")
        self.hojaIns=Label(frameHoja, text="Pulse sobre cada \n text para cambiar")
        self.hoja1=Label(frameHoja, text="Daniel Daza")
        self.hoja2=Label(frameHoja, text="Ochoa")
        self.hoja3=Label(frameHoja, text="Daniel Santiago")
        self.hoja4=Label(frameHoja, text="Marlon Calle")


        self.hojaFotos1=Fotos(frame2,"1")
        self.hojaFotos2=Fotos(frame2,"2")
        self.hojaFotos3=Fotos(frame2,"3")
        self.hojaFotos4=Fotos(frame2,"4")

        frame2.pack()
        frameHoja.pack()
        self.hojaIns.pack()
        self.hojaIns.bind('<ButtonPress-1>', self.cambioDeInstrucciones)
        self.hoja1.bind('<ButtonPress-1>', self.cambioAHoja2)
        self.hoja2.bind('<ButtonPress-1>', self.cambioAHoja3)
        self.hoja3.bind('<ButtonPress-1>', self.cambioAHoja4)
        self.hoja4.bind('<ButtonPress-1>', self.cambioAHoja1)
    


    def getPath(self,txt):
        import os
        import sys
        txt= "uimain\\principal\\" +  txt
        return os.path.join(sys.path[0],txt)

    def cambioDeInstrucciones(self,action):
        self.hojaIns.pack_forget()
        self.hoja1.pack()
        self.hojaFotos1.pack()

    def cambioAHoja1(self,action):
        self.hoja4.pack_forget()
        self.hojaFotos4.pack_forget()
        self.hoja1.pack()
        self.hojaFotos1.pack()

    def cambioAHoja2(self,action):
        self.hoja1.pack_forget()
        self.hojaFotos1.pack_forget()
        self.hoja2.pack()
        self.hojaFotos2.pack()

    def cambioAHoja3(self,action):
        self.hoja2.pack_forget()
        self.hojaFotos2.pack_forget()
        self.hoja3.pack()
        self.hojaFotos3.pack()

    def cambioAHoja4(self,action):
        self.hoja3.pack_forget()
        self.hojaFotos3.pack_forget()
        self.hoja4.pack()
        self.hojaFotos4.pack()

    def cambioAImg1(self,action):
        self.label1.grid_forget()
        self.label1['image']=self.img1
        self.label1.grid(column=0,row=0,padx=3,pady=3)
        self.label1.bind('<ButtonPress-1>', self.cambioAImg2)


    def cambioAImg2(self,action):
        self.label1.grid_forget()
        self.label1['image']=self.img2
        self.label1.grid(column=0,row=0,padx=3,pady=3)
        self.label1.bind('<ButtonPress-1>', self.cambioAImg3)


    def cambioAImg3(self,action):
        self.label1.grid_forget()
        self.label1['image']=self.img3
        self.label1.grid(column=0,row=0,padx=3,pady=3)
        self.label1.bind('<ButtonPress-1>', self.cambioAImg4)


    def cambioAImg4(self,action):
        self.label1.grid_forget()
        self.label1['image']=self.img4
        self.label1.grid(column=0,row=0,padx=3,pady=3)
        self.label1.bind('<ButtonPress-1>', self.cambioAImg5)


    def cambioAImg5(self,action):
        self.label1.grid_forget()
        self.label1['image']=self.img5
        self.label1.grid(column=0,row=0,padx=3,pady=3)
        self.label1.bind('<ButtonPress-1>', self.cambioAImg1)

    def ventanaUsuario(self):
            ventana_usuario=Usuario()

    def descripcion(self):
            global greetings
            greetings="Buena la rata"
            self.saludo.config(text = greetings)

class Fotos(Frame):
    def __init__(self,a,hoja):
        super().__init__(a)
        self.fot=[]
        self.hoja1Foto1=[Label(self,width=50, height=50) for i in range(4)]
        for i in range(1,5):
            fil="hoja"+hoja+"Foto"+str(i)+".png"
            self.fot.append(PhotoImage(file=self.getPath(fil)))
        i=0
        for r in range(2):
            for c in range(2):
                self.hoja1Foto1[i]["image"]=self.fot[i]
                self.hoja1Foto1[i].grid(row=r,column=c)
                i+=1
    
    def getPath(self,txt):
        import os
        import sys
        txt= "uimain\\principal\\" +  txt
        return os.path.join(sys.path[0],txt)