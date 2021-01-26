package orgeval.td1.nonGen;

import java.util.HashMap;

public class MultiEnsNonGen implements SpecifMultiEnsNonGen{

    protected HashMap<Integer, Integer> map;

    public MultiEnsNonGen() {
        this.map = new HashMap<>();
    }


    @Override
    public void ajouter(int val) {
        this.map.put(val, this.frequence(val) + 1);
    }

    @Override
    public boolean enlever(int val) {
        if (this.frequence(val) > 0) {
            this.map.put(val, this.frequence(val) - 1);
            if (this.frequence(val) == 0) this.map.remove(val);
            return true;
        } else return false;
    }

    @Override
    public int frequence(int val) {
        if (this.map.get(val) != null) return this.map.get(val);
        else return 0;
    }

    @Override
    public boolean present(int val) {
        return this.map.containsKey(val);
    }

    @Override
    public int taille() {
        int ret = 0;
        for (Integer key : this.map.keySet()) ret += this.map.get(key);
        return ret;
    }
}
