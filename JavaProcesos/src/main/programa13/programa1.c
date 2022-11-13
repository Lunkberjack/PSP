#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>

// constantes para el control de la aplicacion
const char HELLO[] = "Hello\n";
const char HALLO[] = "Hallo\n";
const char READY[] = "Ready?\n";
const char OK[] = "Ok\n";
const char NOK[] = "Nok\n";
const char SALIR[] = "Salir\n";
const char SUMA[] = "Sumar\n";

int main()
{
  // variables que utilizaremos para controlar el programa
  int p;
  char mensajes[10];
  char numeros[20];
  char buffer[10];
  int fp;
  int bytesleidos;
  int controlador = 0;
  int controladornum = 0;
  int permisos = 0;
  int i = 0;
  int op1 = 0;
  int op2 = 0;
  char signo;
  // borra el fifo si ya existe
  remove("FIFO2");
  // crea el fifo con permiso de lectura y escritura
  p = mkfifo("FIFO2", S_IFIFO | 0666);
  // si el fifo no se crea finaliza el programa con el codigo de error -1
  if (p == -1)
  {
    printf("HA OCURRIDO UN ERROR...\n");
    exit(-1);
  }
  // bucle infinito que actua sobre el fifo
  while (1)
  {
    // abrimos el fifo para trabajar con el con los permisos necesarios(empezamos con permisos de escritura)
    fp = open("FIFO2", permisos);
    // si el fifo no se abre correctamente finaliza el programa con el codigo de error -2
    if (fp == -1)
    {
      printf("ERROR AL ABRIR EL FICHERO...\n");
      exit(-2);
    }
    switch (controlador)
    {
    case 0:
      // si controlador vale 0 (estado inicial) leera el fifo buscando algo escrito por el programa 2
      /* El programa 1 lee el fifo hasta encontrar mensajes escritos por el programa2
       si lee algo pasa al estado de controlador =1 y cambia los permisos a escritura para poder
       escribir en el fifo mientras el programa 2 ha cambiado su permiso a lectura para leer lo que
       este escriba*/
      i = 0;
      bytesleidos = read(fp, buffer, 1);
      mensajes[i++] = buffer[0];
      printf("Obteniendo información... \n");
      if (bytesleidos != 0)
      {
        controlador = 1;
        permisos = 1;
      }
      while (bytesleidos != 0)
      {
        bytesleidos = read(fp, buffer, 1); // lee otro byte
        mensajes[i++] = buffer[0];
      }
      printf("%s", mensajes);
      break;

    case 1:
      // en el estado de controlador =1 y tras abrir el fifo con permisos de escritura escribiremos en el fifo
      //  si el mensaje escrito es Hello...
      if (strncmp(mensajes, HELLO, sizeof(HELLO) - 1) == 0)
      {
        /*escribiremos Hallo en el fifo y cambiaremos al estado
        controlador =0 y abriremos el fifo con permisos
        de lectura para esperar una respuesta del pograma 1*/
        write(fp, HALLO, strlen(HALLO));
        sleep(1);
        controlador = 0;
        permisos = 0;
      }
      // si el mensaje escrito es Ready?...
      if (strncmp(mensajes, READY, sizeof(READY) - 1) == 0)
      {
        // escribe ok y pasa al estado 2 donde espera leer una operacion y optener la solucion
        write(fp, OK, strlen(OK));
        controlador = 2;
        permisos = 0;
      }
      // si el mensaje escrito no es ni Hello ni Ready
      if (strncmp(mensajes, HELLO, sizeof(HELLO) - 1) != 0 && strncmp(mensajes, READY, sizeof(READY) - 1) != 0)
      {
        /* escribe NOK en el fifo y vuelve al estado de controlador 0 y pemisos de lectura
        a la espera de que el programa2 restablezca la conversacion*/
        write(fp, NOK, strlen(NOK));
        sleep(1);
        controlador = 0;
        permisos = 0;
      }
      /*antes de volver a leer o actuar de alguna forma sobre la variable mensajes
      la vaciamos para que esta no produzca ningun error
      sobre todo en las comparaciones*/
      memset(mensajes, 0, 10);
      break;
    case 2:
      // leemos la operacion y mediante el controlador = 3 cambiamos al estado 3 donde calculariamos segun lo pedido
      i = 0;
      bytesleidos = read(fp, buffer, 1);
      mensajes[i++] = buffer[0];
      printf("Obteniendo información... \n");
      if (bytesleidos != 0)
      {
        controlador = 3;
        permisos = 1;
      }
      while (bytesleidos != 0)
      {
        bytesleidos = read(fp, buffer, 1); // lee otro byte
        mensajes[i++] = buffer[0];
      }
      printf("%s", mensajes);
      // si se ha escrito salir ponemos el controlador en 5 y salimos
      if (strncmp(mensajes, SALIR, sizeof(SALIR) - 1) == 0)
        controlador = 5;
      break;
    case 3:
      // Comprobamos si quiere hacer una suma o una multiplicacion y calculamos
      if (strncmp(mensajes, SUMA, sizeof(SUMA) - 1) == 0)
      {
        signo = 's';
      }
      else
      {
        signo = 'm';
      }
      switch (controladornum)
      {
      case 0:
        write(fp, OK, strlen(OK));
        sleep(1);
        permisos = 0;
        controladornum = 1;
        break;
      case 1:
        // leemos el numero uno y lo pasamos a entero
        i = 0;
        bytesleidos = read(fp, buffer, 1);
        numeros[i++] = buffer[0];
        printf("Obteniendo numero1... \n");
        if (bytesleidos != 0)
        {
          // pasamos a leer el numero 2
          controladornum = 2;
          permisos = 1;
        }
        while (bytesleidos != 0)
        {
          bytesleidos = read(fp, buffer, 1); // lee otro byte
          numeros[i++] = buffer[0];
        }
        op1 = atoi(numeros);
        printf("%d\n", op1);
        memset(numeros, 0, 20);
        break;
      case 2:
        write(fp, OK, strlen(OK));
        sleep(1);
        permisos = 0;
        controladornum = 3;
        break;
      case 3:
        // leemos el numero dos y lo pasamos a entero
        i = 0;
        bytesleidos = read(fp, buffer, 1);
        numeros[i++] = buffer[0];
        printf("Obteniendo numero2... \n");
        if (bytesleidos != 0)
        {
          // pasamos a leer el numero 2
          controladornum = 4;
          permisos = 1;
        }
        while (bytesleidos != 0)
        {
          bytesleidos = read(fp, buffer, 1); // lee otro byte
          numeros[i++] = buffer[0];
        }
        op2 = atoi(numeros);
        printf("%d\n", op1);
        memset(numeros, 0, 20);
        break;
      case 4:
        // sumamos y escribimos
        int sum;
        if(signo=='s'){
        sum = op1 + op2;
        }else if(signo=='m'){
        sum = op1 * op2;
        }
        printf("%d\n",sum);
        char sumas[20];
        sprintf(sumas, "%d", sum);
        write(fp, sumas, strlen(sumas));
        sleep(1);
        // reiniciamos al estado anterior buscando una operacion
        controladornum = 0;
        controlador = 2;
        permisos=0;
        /*antes de volver a leer o actuar de alguna forma sobre la variable mensajes
        la vaciamos para que esta no produzca ningun error
       sobre todo en las comparaciones*/
        memset(mensajes, 0, 10);
        break;
      }
      break;
    }
    // devolvemos la solucion de la operacion
    /*cerramnos el fifo con cada paso por el bucle
    para hacer el cambio de permisos de forma correcta*/
    close(fp);
    if (controlador == 5)
      break;
  }
  return (0);
}