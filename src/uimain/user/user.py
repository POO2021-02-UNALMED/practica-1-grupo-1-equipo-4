from tkinter import *
from zona1 import Zona1
from zona2 import Zona2
user = Toplevel()
user.title("hola")
user.geometry("1000x600")
user.option_add("*tearOff",False)


frame = Zona2(user)
zona1 = Zona1(user, frame)
user.mainloop()
