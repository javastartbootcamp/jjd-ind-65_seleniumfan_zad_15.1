package pl.javastart.task;

import java.util.Comparator;

public class SurnameComparator implements Comparator<Player> {
    @Override
    public int compare(Player player1, Player player2) {
        return player1.getSurname().compareTo(player2.getSurname());
    }
}
