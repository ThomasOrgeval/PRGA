package orgeval.td2;

public class Main {

    public static void main(String[] args) {
        ListeTrieeDurableV2 lt = new ListeTrieeDurableV2();
        TexteEnObservationV2 txt = new TexteEnObservationV2("3333");

        lt.ajoutTrieDurable(txt);
        lt.ajoutTrieDurable(new TexteEnObservationV2("4444"));
        lt.ajoutTrieDurable(new TexteEnObservationV2("2222"));
        lt.ajoutTrieDurable(new TexteEnObservationV2("1111"));

        System.out.println(lt); // Affiche : (1111, 2222, 3333, 4444)
        txt.setTexte("5555");
        System.out.println(lt);// Affiche : (1111, 2222, 4444, 5555)
    }
}
