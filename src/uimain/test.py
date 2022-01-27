from cProfile import label
from tkinter import *
from tkinter import messagebox
window = Tk()
window.geometry("300x300")
window.option_add('*tearOff', FALSE)
framea = Frame(window, width=100, height=100, bg="red")
label=Label(framea,text="Ahhh")
frameb = Frame(window, width=100, height=100, bg="blue")
text=Text(framea,height=5, width=13)
def message():
    INPUT = text.get("1.0", "end-1c")
    messagebox.showinfo(
        title="Test",
        message=INPUT
    )
def frame_a(e):
    if e=="<Enter>":
        label["text"]="Adentro"
    else:
        label["text"]="Afuera"
    print(e)
    print("Posición x:", e.x, "y:", e.y)
window.bind('<Enter>', frame_a)
window.bind('<Leave>', frame_a)    
framea.pack()
text.pack()
Display = Button(framea, height = 2,
                 width = 20,
                 text ="Show",
                 command = lambda:message())
label.pack()
Display.pack()
window.mainloop()