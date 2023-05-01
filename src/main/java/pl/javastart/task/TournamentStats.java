package pl.javastart.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;

public class TournamentStats {
    private static final int ASC_SORTING_ORDER = 1;
    private static final int DESC_SORTING_ORDER = 2;
    private static final int NAME_SORTING_PARAMETER = 1;
    private static final int SURNAME_SORTING_PARAMETER = 2;
    private static final int RESULT_SORTING_PARAMETER = 3;
    private static final String END_PROGRAM = "STOP";
    private static final String FILE_NAME = "stats.csv";

    void run(Scanner scanner) {
        List<Player> players = createPlayersWithStats(scanner);
        Comparator<Player> comparator = selectSortingParameter(scanner);

        sort(scanner, players, comparator);
        System.out.println(players);
        saveStatsToFile(FILE_NAME, players);
    }

    private void sort(Scanner scanner, List<Player> players, Comparator<Player> comparator) {
        System.out.printf("Sortować rosnąco czy malejąco? (%d - rosnąco, %d - malejąco) ", ASC_SORTING_ORDER, DESC_SORTING_ORDER);
        int sortingOrder = scanner.nextInt();

        switch (sortingOrder) {
            case ASC_SORTING_ORDER -> players.sort(comparator);
            case DESC_SORTING_ORDER -> players.sort(comparator.reversed());
            default -> throw new IllegalArgumentException("Niewłaściwy parametr");
        }
    }

    private Comparator<Player> selectSortingParameter(Scanner scanner) {
        System.out.printf("Po jakim parametrze posortować? (%d - imię, %d - nazwisko, %d - wynik) ",
                NAME_SORTING_PARAMETER, SURNAME_SORTING_PARAMETER, RESULT_SORTING_PARAMETER);
        int parameter = scanner.nextInt();

        return switch (parameter) {
            case 1 -> new NameComparator();
            case 2 -> new SurnameComparator();
            case 3 ->  new ResultComparator();
            default -> throw new IllegalArgumentException("Nieprawidłowy parametr");
        };
    }

    private List<Player> createPlayersWithStats(Scanner scanner) {
        List<Player> players = new ArrayList<>();
        String prompt = "Podaj wynik kolejnego gracza (imię nazwisko rezultat). Jeśli chcesz przerwać wpisz: " + END_PROGRAM;
        System.out.println(prompt);
        String line;
        while (!(line = scanner.nextLine()).equalsIgnoreCase(END_PROGRAM)) {
            String[] fields = line.split(" ");
            players.add(new Player(fields[0], fields[1], Integer.parseInt(fields[2])));
            System.out.println(prompt);
        }
        return players;
    }

    private void saveStatsToFile(String fileName, List<Player> players) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Player player : players) {
                writer.write(player.getName() + " " + player.getSurname() + ";" + player.getResult());
                writer.newLine();
            }
            System.out.println("Dane posortowano i zapisano do pliku stats.csv.");
        } catch (IOException e) {
            throw new UncheckedIOException("Nie można zapisać pliku: " + fileName, e);
        }
    }
}
