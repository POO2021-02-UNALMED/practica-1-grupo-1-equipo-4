from tkinter import *

if __name__ == "__main__":
    from zonaa import ZonaA
    from zonab import ZonaB

else:
    from uimain.user.zonaa import ZonaA
    from uimain.user.zonab import ZonaB
    

class Usuario:
    def __init__(self):
        self.user = Toplevel()
        self.user.title("Ventana de Usuario")
        self.user.geometry("1000x600")
        self.user.option_add("*tearOff",False)

        self.frame = ZonaB(self.user)
        self.zona1 = ZonaA(self.user, self.frame)

# Usuario().user.mainloop()