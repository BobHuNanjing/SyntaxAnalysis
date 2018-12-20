import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ASPRule {
    private HashSet<Integer> head = new HashSet<>();
    private HashSet<Integer> posbody = new HashSet<>();
    private HashSet<Integer> negbody = new HashSet<>();
    private ArrayList<HashSet<Integer>> body = new ArrayList<>();
    private HashMap<String,Integer> literals = new HashMap<>();
    //默认权重为INF
    private int weight = Integer.MAX_VALUE;

    ASPRule() {
        body.add(posbody);
        body.add(negbody);
    }

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

    ArrayList<HashSet<Integer>> getBody() {
        return body;
    }

    private int getWeight() {
        return weight;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }

    private HashMap<String, Integer> getLiterals() {
        return literals;
    }

    @Override
    public String toString() {
        return "head:"+this.getHead()+"body:"+this.getBody()+",posBody:"+this.getPosbody()+
                ",negBody:"+this.getNegbody()+"weight:"+this.getWeight();
    }


}
