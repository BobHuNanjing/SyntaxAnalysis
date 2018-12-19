import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ASPRule {
    private HashSet<Integer> head = new HashSet<>();
    private HashSet<Integer> posbody = new HashSet<>();
    private HashSet<Integer> negbody = new HashSet<>();
    private HashSet<HashSet<Integer>> body = new HashSet<>();
    private HashMap<String,Integer> literals = new HashMap<>();

    public void setHead(int headIndex) {
        this.head.add(headIndex);
    }

    public void setPosbody(int posLiteral) {
        posbody.add(posLiteral);
    }

    public void setNegbody(int negLiteral) {
        negbody.add(negLiteral);
    }

    public void setBody() {
        body.add(getPosbody());
        body.add(getNegbody());
    }

    public void setLiterals(String literal,int idx) {
        this.literals.put(literal,idx);
    }

    public HashSet<Integer> getHead() {
        return this.head;
    }

    public HashSet<Integer> getPosbody() {
        return posbody;
    }

    public HashSet<Integer> getNegbody() {
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
