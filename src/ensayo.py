from uimain.user.usuario import Usuario
from uimain.principal.first import First
from tkinter import *

#ventana=Usuario()

#ventana.user.mainloop()

window = Tk()
window.option_add('*tearOff', FALSE)

first=First(window)
first.pack()

window.mainloop()