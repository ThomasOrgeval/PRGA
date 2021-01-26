package orgeval.td1.iterable;

import orgeval.td1.gen.MultiEnsGen;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Iterable;
import java.util.List;

public class MultiEnsIterable<T> extends MultiEnsGen<T> implements Iterable<T> {

    public List<T> liste() {
        List<T> ret = new ArrayList<>(this.map.size());
        for (T key : this.map.keySet()) {
            for (int i = 0; i < this.map.get(key); i++) {
                ret.add(key);
            }
        }
        return ret;
    }

    @Override
    public Iterator<T> iterator() {
        return liste().iterator();
    }
}
