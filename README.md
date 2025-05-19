Compilador Básico

Este es un compilador sencillo ya que no se enfoca en ejecutar operaciones matemáticas, no genera código máquina ni ejecuta el programa, su enfoque está en validar la sintaxis de un lenguaje muy simple,
permite declaraciones como lo son int x = 5; y float y = 3.14; también verifica semánticamente que las variables sean declaradas antes de usarlas y no se repiten nombres de variables. Los únicos tipos
de datos válidos son el int y el float.

La diferencia entre los proyectos brindados como material de estudio y guía es que el de nosotros está organizado por fases (lexer, parser y semántico separados), tiene mensajes de error claros personalizados,
comentarios educativos linea por linea, relación fuerte con la teoría, validaciones semánticas reales y archivo de prueba personalizado que sería ejemplo.txt

La relacion que tiene este codigo con un AFD: 
Tokens clave:
int (palabra clave)

float (palabra clave)

print (palabra clave)

x, y, z (identificadores)

5, 3.14 (números)

=, ;, (, ) (símbolos)


AFD: 


        [inicio]
            |
        (letra)
            ↓
        [ID parcial] ←─────
            |              ↑
      ("int"|"float"|"print") → [PALABRA_CLAVE]
            ↓
        [IDENTIFICADOR]

        [inicio]
            |
        (dígito)
            ↓
        [NUMERO] ———→ (punto) —→ [DECIMAL]

        [inicio]
            |
        (= ; ( )) —→ [SIMBOLO]

        Ejemplo: 
        entrada: int x = 5;
        tokens desconocidos:
        [PALABRA_CLAVE(int), IDENTIFICADOR(x), SIMBOLO(=), NUMERO(5), SIMBOLO(;)]
        

        Arbol sintactico: 
        Regla gramatical correspondiente: 
        DECLARACION → tipo identificador = numero ;
                  DECLARACION
              /     |     \       \
           tipo  identificador  =  número
            |         |           |
           int        x           5

           Arbol para un print(x);

               PRINT
                 |
               print
                 |
               ( x )
  ![image](https://github.com/user-attachments/assets/fccf55e3-b2be-4163-b6da-37aeb793f2fc)



        
