from uimain.user.excepciones.funcionamiento import Funcionamiento
from tkinter import messagebox

class NoDisp(Funcionamiento):

    def __init__(self):
        super().__init__()
        messagebox.showerror(
            title="Error de funcionamiento",
            message="No hay funciones disponibles para ese dia")