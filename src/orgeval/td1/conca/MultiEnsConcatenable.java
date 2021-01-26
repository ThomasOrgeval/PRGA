package orgeval.td1.conca;

import orgeval.td1.iterable.MultiEnsIterable;

import java.util.Iterator;

public class MultiEnsConcatenable<T> extends MultiEnsIterable<T> implements Concatenable<T> {

    @Override
    public String concatenation() {
        Iterator<T> it = this.iterator();
        StringBuilder ret = new StringBuilder();
        while (it.hasNext()) ret.append(it.next());
        return ret.toString();
    }
}
