public class BulletPersonPrinter extends PersonPrinter {

    @Override
    public void printNames(Person[] items) {
        for (Person person : items) {
            System.out.println("* " + person.getName());
        }
    }
}
