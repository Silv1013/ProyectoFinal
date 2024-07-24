# Proyecto Final
![image](https://github.com/user-attachments/assets/b7911555-df7e-446c-a1e1-fa5542b0ca58)

**Descripcion del Problema**
<br>
Se nos pidio hacer un codigo que contenga lo aprendido en clases de Estructura de datos y aplicar interfaces, las cuales se aprendieron de otra materia.
<br>
Crear un laberinto que se pueda visualizar y resolver aplicando los metodos BFS, DFS, Recursivo, Cache e implementando el MVC
<br>
Como revision extra se pide que el laberinto pueda ser modificado en la interfaz por el usuario, que se muestre los tiempos de resolucion y la mejor ruta obtenida gracias al metodo.

**Introduccion**
<br>
En este codigo podemos ver como un laberinto designado por el usuario es resulto por varios algoritmos, proporcionando al usuario informacion relevante como: <br>
- Tiempo en realizar el proceso <br>
- Caminos transitados por el algoritmo <br>
- Mejor camino obtenido <br>
  <br>
El laberinto se resuelve paso a paso y se visualiza en la GUI.
<br>
En el proyecto podemos ver que esta implementado el modelo MVC el cual cada paquete tiene un fin diferente y contiene lo que corresponde con su categoria.
<br>
**Controlador** -> Contiene la clase LaberintoControlador donde se inicializa un constructor con parametros de la Vista y tambien contiene los metodos de resolucion empleados para el laberinto:
  <br>
  - BFS <br>
  - DFS <br>
  - Recursivo <br>
  - Cache <br>
<br>
**Modelo** -> Contiene la clase LaberintoModelo, se inicializa las variables empleadas en todo el codigo y asi mismo los getter and setters
<br>
**Vista** -> Contiene la clase LaberintoVista, esta clase hace un extends al Jframe, quiere decir que toda esta clase es una ventana, se añaden todos los botones, campos de textos, generador de laberinto, colores y se les asigna los actionListener, tambien se implemento metodos para que se resuelva con delay, limpiar el laberinto, asignar celdas de bloqueo.
<br>
# Marco Teorico
<br>
**Programacion dinamica**
<br>
Es una técnica de optimización utilizada en algoritmos para resolver problemas complejos dividiéndolos en subproblemas más simples. La clave de la Programacion Dinamica es almacenar los resultados de los subproblemas ya resueltos para evitar cálculos redundantes y mejorar la eficiencia. Es particularmente útil en problemas de optimización donde las soluciones de subproblemas pueden ser reutilizadas. La **Programacion Dinamica** suele aplicarse a problemas que pueden ser descompuestos en subproblemas solapados.
<br>
**BFS**
<br>
Es un algoritmo de búsqueda utilizado en grafos y árboles que explora todos los nodos en el nivel actual antes de pasar al siguiente nivel. BFS utiliza una cola para mantener los nodos a medida que se exploran. Comienza desde un nodo inicial y explora todos sus vecinos antes de mover a los nodos vecinos de esos vecinos.
<br>
**DFS**
<br>
Algoritmo de búsqueda para grafos y árboles que explora tanto como sea posible a lo largo de una rama antes de retroceder. DFS utiliza una pila (o recursión, que implícitamente usa una pila) para realizar un seguimiento de los nodos a medida que se exploran. Comienza desde un nodo inicial, explora tan profundo como sea posible a lo largo de un camino antes de retroceder y probar el siguiente camino.
<br>
**Cache**
<br>
Es una técnica de optimización que almacena los resultados de operaciones costosas o subproblemas ya calculados para evitar cálculos repetidos. En la búsqueda con cache (como en la programación dinámica), se utiliza una estructura de datos como un mapa o un diccionario para almacenar resultados intermedios y mejorar el rendimiento.
<br>
#Descripcion de la solucion, herramientas y lenguajes usados
<br>
Se llego a la solucion del problema aplicando lo aprendido y anotado en clase llevado de investigacion hacia el problema, se utilizo videos como medio de guia, entre otras ayudas.
<br>
Se uso java con el IDE de Visual Studio Code
<br>
**Criterio por Estudiante**
<br>
**Emily Espinoza**
<br>
Se propuso en un inicio hacer que cada metodo tenga su color diferente y que se peuda visualizar cada ruta, tambien poner un robot que recorra el camino resuelto, se propuso tambien la visualizacion de videos para comprender mejor el tema y usar de guia.

<br>
**Silvio Pinos**
<br>
Se propuso primero hacer el controlador y el modelo y probar sin GUI el funcionamiento de estos, luego investigar en videos y con ayudas externas la resolucion del problema y poco a poco se llego al proyecto final.
<br>
# Capturas de la GUI
<br>
![image](https://github.com/user-attachments/assets/c05854e4-cf57-4511-9e2e-af490e12d18d)

<br>
![image](https://github.com/user-attachments/assets/5c5462e0-968c-40ff-879d-ec37c3b6c2b5)
<br>
![image](https://github.com/user-attachments/assets/2b571e48-cd2f-4996-bc9c-7874e9620c11)

<br>

## Conclusiones
<br>
El algoritmo que se hizo mas rapido para nuestra GUI fue el de Recursividad, teniendo una diferencia con los otros de 0.0004 segundos y es porque en la recursividad almacena los resultado que ya subdividio y evita calculos redundantes, haciendo mas eficiente al algoritmo.
<br>
Por otra parte el mas lento para nosotros fue el de cache, demorando 0.0005 segundos mas que los demas, usa una estructura muy similar a la recursiva y asi mismo tambien almacena resultados.
<br>
## Consideraciones
<br>
**Emily Espinoza**
<br>
Podriamos poner todos los métodos con diferentes colores y todos en un mismo laberinto para ver cuál es más corto o más rápido también podría algo para que el usuario pueda cambiar las formas y no solo sea rectángulos o que pueda poner como si fuera un juego, considero que en algunas partes se puede mejorar y se puede hacer algo mejor
<br>
**Silvio Pinos**
<br>
Se podria mejorar para que sea de alguna manera mas interactivo con el ususario, ya sea alguna figura recorriendo el tramo o haciendo que el usuario elija el color a usar en su interaccion, considero que se puede mejorar mucho en la parte visual, para que sea mas atractivo y eficiente.

