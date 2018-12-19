import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ASPRule {
    private HashSet<Integer> head = new HashSet<>();
    private HashSet<Integer> posbody = new HashSet<>();
    private HashSet<Integer> negbody = new HashSet<>();
    private HashSet<HashSet<Integer>> body = new HashSet<>();
    private HashMap<String,Integer> literals = new HashMap<>();

    void setHead(int headIndex) {
        this.head.add(headIndex);
    }

    void setPosbody(int posLiteral) {
    /*    for (Map.Entry<String, Integer> entry : literals.entrySet()) {
            if(!negbody.contains(entry.getValue())){
                //System.out.println(entry.getValue());
                posbody.add(entry.getValue());
            }
        }*/
        posbody.add(posLiteral);
    }

    void setNegbody(int negLiteral) {
        negbody.add(negLiteral);
    }

    public void setBody() {
        body.add(getPosbody());
        body.add(getNegbody());
    }

    void setLiterals(String literal, int idx) {
        this.literals.put(literal,idx);
    }

    private HashSet<Integer> getHead() {
        return this.head;
    }

    private HashSet<Integer> getPosbody() {
        return posbody;
    }

    private HashSet<Integer> getNegbody() {
        return negbody;
    }

    public HashSet<HashSet<Integer>> getBody() {
        return body;
    }

    public HashMap<String, Integer> getLiterals() {
        return literals;
    }

    @Override
    public String toString() {
        return "head:"+this.getHead()+",posBody:"+this.getPosbody()+",negBody:"+this.getNegbody();
    }


}
