package orgeval.td1.iterable;

import orgeval.td1.gen.MultiEnsGen;

import java.util.HashMap;
import java.util.Iterator;

public class MultiEnsIterable<T> extends MultiEnsGen<T> implements Iterable<T> {

    HashMap<T, Integer> map;

    public MultiEnsIterable() {
        map = new HashMap<>();
    }

    @Override
    public void ajouter(T val) {
        this.map.put(val, this.frequence(val) + 1);
    }

    @Override
    public boolean enlever(T val) {
        if (this.frequence(val) > 0) {
            this.map.put(val, this.frequence(val) - 1);
            if (this.frequence(val) == 0) this.map.remove(val);
            return true;
        } else return false;
    }

    @Override
    public int frequence(T val) {
        if (this.map.get(val) != null) return this.map.get(val);
        else return 0;
    }

    @Override
    public boolean present(T val) {
        return this.map.containsKey(val);
    }

    @Override
    public int taille() {
        int ret = 0;
        for (T key : this.map.keySet()) ret += this.map.get(key);
        return ret;
    }

    @Override
    public Iterator<T> iterator() {
        for (T key : this.map.keySet()) {
            for (int i = 0; i < this.map.get(key); i++) {
                System.out.print(key + " ");
            }
            System.out.println();
        }
        return null;
    }
}
