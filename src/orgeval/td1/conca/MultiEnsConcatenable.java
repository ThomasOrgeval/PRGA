package orgeval.td1.conca;

import orgeval.td1.iterable.MultiEnsIterable;

import java.util.HashMap;
import java.util.TreeMap;

public class MultiEnsConcatenable<T> extends MultiEnsIterable<T> implements Concatenable<T> {

    HashMap<T, Integer> map;

    public MultiEnsConcatenable() {
        this.map = new HashMap<>();
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
    public String concatenation() {
        TreeMap<T, Integer> sorted = new TreeMap<>(this.map);
        StringBuilder ret = new StringBuilder();
        for (T key : sorted.keySet()) {
            for (int i = 0; i < this.map.get(key); i++) {
                ret.append(key);
            }
        }
        return ret.toString();
    }
}
