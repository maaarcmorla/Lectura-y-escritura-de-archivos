import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Act6 {
    public static Scanner scanner;

    public static void main(String[] args) throws Exception {
        Act6.scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("\nLectura y escritura");
            System.out.println("\n1- Byte Stream");
            System.out.println("2- Character Stream");
            System.out.println("3- Buffer Stream");
            System.out.println("9- Salir\n");
            String datos = Act6.scanner.next();

            int datosInt = 0;
            try {
                datosInt = Integer.parseInt(datos);
            } catch (NumberFormatException e) {

            }

            switch (datosInt) {
                case 1:
                    Act6.menu1();
                    break;
                case 2:
                    Act6.menu2();
                    break;
                case 3:
                    Act6.menu3();
                    break;
                case 9:
                    flag = false;
                    Act6.scanner.close();
                    break;
            }
        }
    }

    public static void menu1() {
        boolean flagM1 = true;
        String texto = "";

        System.out.println("\nByte Stream");
        while (flagM1) {
            System.out.println("1- Lectura");
            System.out.println("2- Escribir");
            System.out.println("9- Salir\n");
            String datos = Act6.scanner.next();

            int datosInt = 0;
            try {
                datosInt = Integer.parseInt(datos);
            } catch (NumberFormatException e) {
            }

            switch (datosInt) {
                case 1:
                    try (FileInputStream FIS = new FileInputStream("datos/datos.txt")) {
                        int i;
                        do {
                            i = FIS.read();
                            if (i != -1) {
                                texto += (char) i;
                            }
                        } while (i != -1);
                    } catch (IOException ioe) {
                    }
                    texto = Act6.prettifytext(texto);
                    System.out.println(texto);
                    break;
                case 2:
                    if (texto.equals("")) {
                        break;
                    } else {
                        try (FileOutputStream FOS = new FileOutputStream("datos/datosByteStream.txt")) {
                            byte[] bytes = texto.getBytes();
                            FOS.write(bytes);
                            System.out.println("Archivo creado");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 9:
                    flagM1 = false;
                    break;
            }
        }

    }

    public static void menu2() throws IOException {
        boolean flagM2 = true;
        String texto = "";
        FileWriter escritor = new FileWriter("datos/datosCharacterStreams.txt");

        System.out.println("\nCharacter Streams");
        while (flagM2) {
            System.out.println("1- Leer");
            System.out.println("2- Escribir");
            System.out.println("9- Salir\n");

            String datos = Act6.scanner.next();

            int datosInt = 0;
            try {
                datosInt = Integer.parseInt(datos);
            } catch (NumberFormatException e) {
            }

            switch (datosInt) {
                case 1:
                    try {
                        FileReader lector = new FileReader("datos/datos.txt");
                        int data = lector.read();

                        while (data != -1) {
                            texto += (char) data;
                            data = lector.read();
                        }

                        lector.close();
                        texto = Act6.prettifytext(texto);
                        System.out.println(texto);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    if (texto.equals("")) {
                        break;
                    } else {
                        try {
                            escritor.write(texto);
                            System.out.println("Archivo creado");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    break;
                case 9:
                    escritor.close();
                    flagM2 = false;
                    break;
            }
        }

    }

    public static void menu3() throws IOException {
        boolean flagM3 = true;
        String texto = "";

        System.out.println("\nBuffered Streams");
        while (flagM3) {
            System.out.println("1- Leer");
            System.out.println("2- Escribir");
            System.out.println("9- Salir\n");
            String datos = Act6.scanner.next();

            int datosInt = 0;
            try {
                datosInt = Integer.parseInt(datos);
            } catch (NumberFormatException e) {
            }

            switch (datosInt) {
                case 1:
                    try {
                        BufferedReader BRD = new BufferedReader(new FileReader("datos/datos.txt"));

                        String string;
                        while ((string = BRD.readLine()) != null) {
                            texto += string;
                        }

                        BRD.close();
                        texto = Act6.prettifytext(texto);
                        System.out.println(texto);
                    } catch (Exception e) {
                    }
                    break;
                case 2:
                    if (texto.equals("")) {
                        break;
                    } else {
                        try (BufferedWriter BWT = new BufferedWriter(
                                new FileWriter("datos/DatosBufferedStream.txt"));) {
                            BWT.write(texto);
                            System.out.println("Archivo creado");
                            BWT.close();
                        } catch (Exception e) {
                        }
                    }
                    break;
                case 9:
                    flagM3 = false;
                    break;
            }
        }

    }

    public static String prettifytext(String datos) {
        String pft = "\tCartelera de Cine";

        String[] peli = datos.split(";");

        for (String f : peli) {
            String[] pelicula = f.split("#");
            String titulo = pelicula[0];
            titulo.replace("\n", " ");
            String ano = pelicula[1];
            String director = pelicula[2];
            director.replace("\n", " ");
            String duracion = pelicula[3];
            String sinopsis = pelicula[4];
            String reparto = pelicula[5];
            reparto.replace("n", " ");
            String sesion = pelicula[6];

            pft += "\n" + titulo + "\n";
            pft += "Año: " + ano + "\n";
            pft += "Director: " + director + "\n";
            pft += "Duración: " + duracion + "\n";
            pft += "Sinopsis: " + sinopsis + "\n";
            pft += "Reparto: " + reparto + "\n";
            pft += "Sesión: " + sesion + " horas\n\n";
        }
        return pft;
    }
}
