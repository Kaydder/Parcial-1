import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> shoesInformation = new HashMap<>();
        HashMap<String, Integer> shoesStartingInventory = new HashMap<>();

        String shoeName;
        int shoeAmount;
        int shoeAmountToAdd;
        int sellAmount;
        byte choice;

        do {
            System.out.println("--- MENÚ ---");
            System.out.println("1. Agregar zapato");
            System.out.println("2. Ver stock disponible de X producto");
            System.out.println("3. Realizar una venta");
            System.out.println("4. Agregar stock a X producto");
            System.out.println("5. Información de inventario");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            choice = scanner.nextByte();
            System.out.println("\n---------------------");

            switch (choice) {
                case 1:
                    System.out.println("--- Agregar zapato ---");
                    scanner.nextLine();
                    System.out.print("Escriba el nombre del zapato: ");
                    shoeName = scanner.nextLine().toLowerCase();
                    System.out.print("Cantidad disponible en stock (números): ");
                    shoeAmount = scanner.nextInt();
                    shoesInformation.put(shoeName, shoeAmount);
                    shoesStartingInventory.put(shoeName, shoeAmount);
                    System.out.println("Zapato " + shoeName + " agregado con éxito al inventario con un stock de: " + shoeAmount);
                    break;

                case 2:
                    System.out.println("--- Ver stock disponible de X producto ---");
                    scanner.nextLine();
                    System.out.print("Escriba el nombre del zapato: ");
                    shoeName = scanner.nextLine().toLowerCase();
                    if (shoesInformation.containsKey(shoeName)) {
                        System.out.println("La cantidad disponible en stock del zapato: " + shoeName + " es de: " + shoesInformation.get(shoeName));
                    } else {
                        System.out.println("Ese zapato no existe en inventario. Agregue un nuevo zapato en la opción 1.");
                    }
                    break;

                case 3:
                    System.out.println("-------------- Menú de Ventas --------------");
                    scanner.nextLine();
                    System.out.print("Escriba el nombre del zapato que va a vender: ");
                    shoeName = scanner.nextLine().toLowerCase();
                    if (!shoesInformation.containsKey(shoeName)) {
                        System.out.println("Ese zapato no existe en el inventario.");
                        break;
                    }
                    System.out.print("Cantidad a vender: ");
                    sellAmount = scanner.nextInt();
                    int currentStock = shoesInformation.get(shoeName);
                    if (sellAmount > currentStock) {
                        System.out.println("No hay suficiente stock disponible para la venta.");
                    } else {
                        shoesInformation.put(shoeName, currentStock - sellAmount);
                        System.out.println("Venta realizada. Nuevo stock de " + shoeName + ": " + shoesInformation.get(shoeName));
                    }
                    break;

                case 4:
                    System.out.println("--- Agregar Stock a un Zapato ---");
                    scanner.nextLine();
                    System.out.print("Escriba el nombre del zapato: ");
                    shoeName = scanner.nextLine().toLowerCase();
                    if (!shoesInformation.containsKey(shoeName)) {
                        System.out.println("No es posible agregar stock a un zapato inexistente.");
                        break;
                    }
                    System.out.print("Cantidad de stock a agregar: ");
                    shoeAmountToAdd = scanner.nextInt();
                    shoesInformation.put(shoeName, shoesInformation.get(shoeName) + shoeAmountToAdd);
                    System.out.println("Stock actualizado. Nueva cantidad de " + shoeName + " en stock: " + shoesInformation.get(shoeName));
                    break;

                case 5:
                    System.out.println("--- Información del Stock ---");
                    if (shoesInformation.isEmpty()) {
                        System.out.println("No hay zapatos en inventario.");
                    } else {
                        System.out.println("Inventario actual:");
                        for (String key : shoesStartingInventory.keySet()) {
                            System.out.println(key + " - " + shoesInformation.get(key));
                        }
                    }
                    break;
                case 6:
                    System.out.println("--- Saliendo ---");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

            for (String key : shoesInformation.keySet()) {
                if (shoesInformation.get(key) == 0) {
                    System.out.println("El zapato " + key + " se ha agotado. Duplicando stock inicial...");
                    shoesInformation.put(key, shoesStartingInventory.get(key) * 2);
                    System.out.println("Nuevo stock de " + key + ": " + shoesInformation.get(key));
                }
            }
        } while (choice != 6);

        scanner.close();
    }
}
