from uimain.user.excepciones.ingresodatos import IngresoDatos
from tkinter import messagebox

class NotIn(IngresoDatos):
    def __init__(self):
        super().__init__()
        messagebox.showerror(
            title="Error de ingreso",
            message="Los datos ingresados no se encuentran en la base de datos")