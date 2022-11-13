#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdio.h>

// constantes para el control de la aplicacion
const char HELLO[] = "Hello\n";
const char HALLO[] = "Hallo\n";
const char READY[] = "Ready?\n";
const char OK[] = "Ok\n";
const char NOK[] = "Nok\n";
const char SALIR[] = "Salir\n";
const char SUMA[] = "Sumar\n";
const char MULTI[] = "Multi\n";

int main()
{
  // variables que utilizaremos para controlar el programa
  char mensaje[10];
  char buffer[10];
  int fp;
  int controlador = 0;
  int bytesleidos = 1;
  int permisos = 1;
  int controladornum = 0;
  int opcion;
  int num1;
  int num2;
  char numero1[20];
  char numero2[20];
  int i;
  // bucle infinito que actua sobre el fifo
  while (1)
  {
    // abrimos el fifo para trabajar con el con los permisos necesarios(empezamos con permisos de escritura)
    fp = open("FIFO2", permisos);
    // si hay un error al abrir el fifo el programa finaliza con el codigo de error -1
    if (fp == -1)
    {
      printf("ERROR AL ABRIR EL FICHERO...\n");
      exit(-1);
    }
    switch (controlador)
    {
    case 0:
      // si la variable controlador es 0 empezara la comunicacion escribiendo Hello
      // cambia el controlador a 1 y los pemisos a lectura
      // el programa 2 escribe Hello en el fifo
      write(fp, HELLO, strlen(HELLO));
      sleep(1);
      permisos = 0;
      controlador = 1;
      break;
    case 1:
      /*en el caso 1 se ha vuelto a abrir el fifo con permisos de lectura
      leeremos hasta encontrar un mensaje del programa 1 y cambiaremos los permisos
      a escritura para volver a escribir en el fifo pasaremos al estado 2*/
      i = 0;
      bytesleidos = read(fp, buffer, 1);
      mensaje[i++] = buffer[0];
      printf("Obteniendo información... \n");
      if (bytesleidos != 0)
      {
        controlador = 2;
        permisos = 1;
      }
      while (bytesleidos != 0)
      {
        bytesleidos = read(fp, buffer, 1); // lee otro byte
        mensaje[i++] = buffer[0];
      }
      printf("%s", mensaje);
      break;

    case 2:
      /*una vez ha leido un mensaje y vuelto a abrir el fifo con permisos de escritura analiza
      lo que ha escrito el programa uno y segun lo escrito actua de una forma u otra*/
      // si ha escrito hallo
      if (strncmp(mensaje, HALLO, sizeof(HALLO) - 1) == 0)
      {
        // escribe Ready y vuelve al estado de controlador 1 y permisos de lectura
        write(fp, READY, strlen(READY));
        permisos = 0;
        controlador = 1;
        sleep(1);
      }
      // si ha escrito OK
      if (strncmp(mensaje, OK, sizeof(OK) - 1) == 0)
      {
        printf("Bienvenido, puede empezar a calcular:\n");
        // pasamos al caso 3 del controlador donde calcularemos y le daremos los permisos de escritura
        permisos = 1;
        controlador = 3;
      }
      // si ha escrito Nok
      if (strncmp(mensaje, NOK, sizeof(NOK) - 1) == 0)
      {
        /*se reinicia al estado inicial
        controlador 0 permisos de escritura
        empiezan a conversar de nuevo*/
        printf("Reinicio conversacion");
        controlador = 0;
        permisos = 1;
      }
      /*antes de volver a leer o actuar de alguna forma sobre la variable mensajes
      la vaciamos para que esta no produzca ningun error
      sobre todo en las comparaciones*/
      memset(mensaje, 0, 10);
      break;
    case 3:
      // Le doy las opciones al usuario
      printf("Opciones:\n---------\n1-Suma\n2-Multiplica\n3-Salir\n");
      scanf("%d", &opcion);
      switch (opcion)
      {
      case 1:
        // indica que va a hacer una suma
        write(fp, SUMA, strlen(SUMA));
        // una vez escribimos la operacion pasamos al caso 4
        controlador = 4;
        permisos = 0;
        break;
      case 2:
        // indica que va a hacer una multi
        write(fp, MULTI, strlen(MULTI));
        // una vez escribimos la operacion pasamos al caso 4
        permisos = 0;
        controlador = 4;
        break;
      case 3:
        // escribimos salir
        // ponemos controlador = 5 y cuando llegue al final del bucle saldra
        printf("Saliendo\n");
        controlador = 5;
        write(fp, SALIR, strlen(SALIR));
        sleep(1);
        break;
      default:
        printf("No has introducido ninguna opcion\n");
        break;
      }
      break;
    case 4:
      switch (controladornum)
      {
      case 0:
        i = 0;
        bytesleidos = read(fp, buffer, 1);
        mensaje[i++] = buffer[0];
        printf("Obteniendo información... \n");
        if (bytesleidos != 0)
        {
          permisos = 1;
        }
        while (bytesleidos != 0)
        {
          bytesleidos = read(fp, buffer, 1); // lee otro byte
          mensaje[i++] = buffer[0];
        }
        if (strncmp(mensaje, OK, sizeof(OK) - 1) == 0)
          controladornum = 1;
        break;
      case 1:
        // escribimos un numero
        printf("Procederemos al calculo\nIntroduce un numero\n");
        scanf("%d", &num1);
        sprintf(numero1, "%d", num1);
        write(fp, numero1, strlen(numero1));
        permisos=0;
        controladornum = 2;
        printf("\nescribo uno\n");
        sleep(1);
        break;
      case 2:
        i = 0;
        bytesleidos = read(fp, buffer, 1);
        mensaje[i++] = buffer[0];
        printf("Obteniendo información... \n");
        if (bytesleidos != 0)
        {
          permisos = 1;
        }
        while (bytesleidos != 0)
        {
          bytesleidos = read(fp, buffer, 1); // lee otro byte
          mensaje[i++] = buffer[0];
        }
        if (strncmp(mensaje, OK, sizeof(OK) - 1) == 0)controladornum = 3;
        break;
      case 3:
        // escribimos el segundo numero
        printf("Intoduce otro numero\n");
        scanf("%d", &num2);
        sprintf(numero2, "%d", num2);
        write(fp, numero2, strlen(numero2));
        controladornum = 4;
        permisos = 0;
        printf("\nescribo otro\n");
        sleep(2);
        break;
      case 4:
        memset(mensaje, 0, 10);
         // leemos la solucion
        i = 0;
        bytesleidos = read(fp, buffer, 1);
        mensaje[i++] = buffer[0];
        printf("Obteniendo solucion... \n");
        while (bytesleidos != 0)
        {
          bytesleidos = read(fp, buffer, 1); // lee otro byte
          mensaje[i++] = buffer[0];
        }
        int sol=atoi(mensaje);
        printf("La solucion es %s\n", mensaje);
        // volvemos al estado 3(el menu)
        controladornum = 0;
        controlador = 3;
        permisos = 1;
        break;
      }
      break;
    }
    /*cerramnos el fifo con cada paso por el bucle
    para hacer el cambio de permisos de forma correcta*/
    close(fp);
    if (controlador == 5)
      break;
  }
  return 0;
}