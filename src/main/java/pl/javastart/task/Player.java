package pl.javastart.task;

public class Player {
    private String name;
    private String surname;
    private int result;

    public Player(String name, String surname, int result) {
        this.name = name;
        this.surname = surname;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "\nimię='" + name + '\'' +
                ", nazwisko='" + surname + '\'' +
                ", wynik=" + result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
