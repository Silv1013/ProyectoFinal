# Proyecto Final
![image](https://github.com/user-attachments/assets/b7911555-df7e-446c-a1e1-fa5542b0ca58)

# Descripción del Problema

Se nos pidió hacer un código que contenga lo aprendido en clases de Estructura de Datos y aplicar interfaces, las cuales se aprendieron en otra materia. El objetivo es crear un laberinto que se pueda visualizar y resolver aplicando los métodos BFS, DFS, Recursivo, Cache e implementando el patrón MVC. 

Además, se solicita que el laberinto pueda ser modificado en la interfaz por el usuario, que se muestren los tiempos de resolución y el mejor camino obtenido por cada método.

## Introducción

En este código, podemos ver cómo un laberinto designado por el usuario es resuelto por varios algoritmos, proporcionando al usuario información relevante como:
- Tiempo en realizar el proceso
- Caminos transitados por el algoritmo
- Mejor camino obtenido

El laberinto se resuelve paso a paso y se visualiza en la GUI.

En el proyecto, se ha implementado el modelo MVC, en el que cada paquete tiene un propósito diferente y contiene lo que corresponde a su categoría.

### Controlador

Contiene la clase `LaberintoControlador` donde se inicializa un constructor con parámetros de la Vista y también contiene los métodos de resolución empleados para el laberinto:
- BFS
- DFS
- Recursivo
- Cache

### Modelo

Contiene la clase `LaberintoModelo`, donde se inicializan las variables empleadas en todo el código, así como los getters y setters.

### Vista

Contiene la clase `LaberintoVista`, que extiende `JFrame`, lo que significa que toda esta clase es una ventana. Se añaden todos los botones, campos de texto, generador de laberinto, colores y se les asignan los `ActionListener`. También se implementaron métodos para resolver con delay, limpiar el laberinto y asignar celdas de bloqueo.

## Marco Teórico

### Programación Dinámica

Es una técnica de optimización utilizada en algoritmos para resolver problemas complejos dividiéndolos en subproblemas más simples. La clave de la Programación Dinámica es almacenar los resultados de los subproblemas ya resueltos para evitar cálculos redundantes y mejorar la eficiencia. Es particularmente útil en problemas de optimización donde las soluciones de subproblemas pueden ser reutilizadas. La Programación Dinámica suele aplicarse a problemas que pueden ser descompuestos en subproblemas solapados.

### BFS (Breadth-First Search)

Es un algoritmo de búsqueda utilizado en grafos y árboles que explora todos los nodos en el nivel actual antes de pasar al siguiente nivel. BFS utiliza una cola para mantener los nodos a medida que se exploran. Comienza desde un nodo inicial y explora todos sus vecinos antes de mover a los nodos vecinos de esos vecinos.

### DFS (Depth-First Search)

Es un algoritmo de búsqueda para grafos y árboles que explora tanto como sea posible a lo largo de una rama antes de retroceder. DFS utiliza una pila (o recursión, que implícitamente usa una pila) para realizar un seguimiento de los nodos a medida que se exploran. Comienza desde un nodo inicial, explora tan profundo como sea posible a lo largo de un camino antes de retroceder y probar el siguiente camino.

### Cache

Es una técnica de optimización que almacena los resultados de operaciones costosas o subproblemas ya calculados para evitar cálculos repetidos. En la búsqueda con cache (como en la programación dinámica), se utiliza una estructura de datos como un mapa o un diccionario para almacenar resultados intermedios y mejorar el rendimiento.

## Descripción de la Solución, Herramientas y Lenguajes Usados

Se llegó a la solución del problema aplicando lo aprendido en clase y llevando a cabo una investigación adicional sobre el tema. Se utilizaron videos como guía, entre otras ayudas. El código fue implementado en Java utilizando el IDE de Visual Studio Code.

## Criterio por Estudiante

**Emily Espinoza**

Se propuso inicialmente hacer que cada método tenga un color diferente para visualizar cada ruta y también poner un robot que recorra el camino resuelto. Se sugirió también la visualización de videos para comprender mejor el tema y usar de guía.

**Silvio Pinos**

Se propuso primero hacer el controlador y el modelo y probar su funcionamiento sin GUI. Luego, se investigó en videos y con ayudas externas para la resolución del problema y, poco a poco, se llegó al proyecto final.

## Capturas de la GUI

![image](https://github.com/user-attachments/assets/c05854e4-cf57-4511-9e2e-af490e12d18d)
![image](https://github.com/user-attachments/assets/5c5462e0-968c-40ff-879d-ec37c3b6c2b5)
![image](https://github.com/user-attachments/assets/2b571e48-cd2f-4996-bc9c-7874e9620c11)

## Conclusiones

El algoritmo que resultó ser más rápido en nuestra GUI fue el de Recursividad, teniendo una diferencia con los otros de 0.0004 segundos, ya que en la recursividad se almacenan los resultados que ya se subdividieron, evitando cálculos redundantes y haciendo el algoritmo más eficiente. Por otra parte, el más lento para nosotros fue el de Cache, demorando 0.0005 segundos más que los demás, a pesar de que usa una estructura muy similar a la recursiva y también almacena resultados.

## Consideraciones

**Emily Espinoza**

Podríamos poner todos los métodos con diferentes colores y todos en un mismo laberinto para ver cuál es más corto o más rápido. También podríamos permitir que el usuario cambie las formas y no solo sea rectángulos, o hacer que el usuario pueda poner algo similar a un juego. Considero que en algunas partes se puede mejorar y hacer algo mejor.

**Silvio Pinos**

Se podría mejorar para que sea de alguna manera más interactivo con el usuario, ya sea con alguna figura recorriendo el tramo o permitiendo que el usuario elija el color a usar en su interacción. Considero que se puede mejorar mucho en la parte visual, para que sea más atractivo y eficiente.


