package com.onepiece;

import com.onepiece.dao.PirataDAO;
import com.onepiece.model.Pirata;
import com.onepiece.util.JPAUtil;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final PirataDAO pirataDAO = new PirataDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.print("Selecciona una opción: ");
            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> registrarPirata();
                case 2 -> listarPiratas();
                case 3 -> buscarPirataPorId();
                case 4 -> actualizarPirata();
                case 5 -> eliminarPirata();
                case 6 -> {
                    salir = true;
                    JPAUtil.shutdown();
                    System.out.println("\n¡Navegación finalizada! Has salido del Grand Line.");
                }
                default -> System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=================================");
        System.out.println("    GESTIÓN DE PIRATAS (CRUD)   ");
        System.out.println("=================================");
        System.out.println("1. Registrar nuevo pirata");
        System.out.println("2. Listar todos los piratas");
        System.out.println("3. Buscar pirata por ID");
        System.out.println("4. Actualizar datos de un pirata");
        System.out.println("5. Eliminar un pirata");
        System.out.println("6. Salir");
        System.out.println("=================================");
    }

    private static void registrarPirata() {
        System.out.println("\n--- REGISTRAR NUEVO PIRATA ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Banda / Tripulación: ");
        String banda = scanner.nextLine();

        System.out.print("Recompensa (Bounties): ");
        Long recompensa = leerLong();

        System.out.print("Fruta del Diablo (pulsa Enter si no tiene): ");
        String fruta = scanner.nextLine();
        if (fruta.trim().isEmpty()) {
            fruta = null;
        }

        Pirata nuevoPirata = new Pirata(nombre, banda, recompensa, fruta);
        pirataDAO.guardar(nuevoPirata);
        System.out.println("¡Pirata registrado con éxito!");
    }

    private static void listarPiratas() {
        System.out.println("\n--- LISTA DE PIRATAS REGISTRADOS ---");
        List<Pirata> piratas = pirataDAO.listarTodos();
        if (piratas.isEmpty()) {
            System.out.println("No hay piratas en la base de datos.");
        } else {
            piratas.forEach(p -> System.out.println(p));
        }
    }

    private static void buscarPirataPorId() {
        System.out.println("\n--- BUSCAR PIRATA POR ID ---");
        System.out.print("Introduce el ID: ");
        Long id = leerLong();

        Pirata pirata = pirataDAO.buscarPorId(id);
        if (pirata != null) {
            System.out.println("Encontrado: " + pirata);
        } else {
            System.out.println("No se encontró ningún pirata con el ID " + id);
        }
    }

    private static void actualizarPirata() {
        System.out.println("\n--- ACTUALIZAR PIRATA ---");
        System.out.print("Introduce el ID del pirata a actualizar: ");
        Long id = leerLong();

        Pirata pirata = pirataDAO.buscarPorId(id);
        if (pirata == null) {
            System.out.println("No se encontró ningún pirata con el ID " + id);
            return;
        }

        System.out.println("Datos actuales: " + pirata);
        System.out.print("Nuevo nombre (deja vacío para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.trim().isEmpty()) pirata.setNombre(nombre);

        System.out.print("Nueva banda (deja vacío para no cambiar): ");
        String banda = scanner.nextLine();
        if (!banda.trim().isEmpty()) pirata.setBanda(banda);

        System.out.print("Nueva recompensa (o -1 para no cambiar): ");
        Long recompensa = leerLong();
        if (recompensa != -1) pirata.setRecompensa(recompensa);

        System.out.print("Nueva Fruta del Diablo (deja vacío para no cambiar): ");
        String fruta = scanner.nextLine();
        if (!fruta.trim().isEmpty()) pirata.setFrutaDelDiablo(fruta);

        pirataDAO.actualizar(pirata);
        System.out.println("¡Datos del pirata actualizados con éxito!");
    }

    private static void eliminarPirata() {
        System.out.println("\n--- ELIMINAR PIRATA ---");
        System.out.print("Introduce el ID del pirata a eliminar: ");
        Long id = leerLong();

        Pirata pirata = pirataDAO.buscarPorId(id);
        if (pirata != null) {
            pirataDAO.eliminar(id);
            System.out.println("¡Pirata eliminado de la base de datos!");
        } else {
            System.out.println("No se encontró ningún pirata con el ID " + id);
        }
    }

    private static int leerEntero() {
        try {
            int num = Integer.parseInt(scanner.nextLine());
            return num;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static Long leerLong() {
        try {
            long num = Long.parseLong(scanner.nextLine());
            return num;
        } catch (NumberFormatException e) {
            return -1L;
        }
    }
}