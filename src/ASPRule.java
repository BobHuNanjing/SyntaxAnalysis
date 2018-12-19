import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ASPRule {
    private String head;
    private ArrayList<String> posbody = new ArrayList<>();
    private ArrayList<String> negbody;
    private ArrayList<ArrayList<String>> body;
    private HashMap<String,Integer> literals;

    public void setHead(String head) {
        this.head = head;
    }

    public void setPosbody(String posLiteral) {
        posbody.add(posLiteral);
    }

    public void setNegbody(ArrayList<String> negbody) {
        this.negbody = negbody;
    }

    public void setBody(ArrayList<ArrayList<String>> body) {
        this.body = body;
    }

    public void setLiterals(HashMap<String, Integer> literals) {
        this.literals = literals;
    }

    public String getHead() {
        return head;
    }

    public ArrayList<String> getPosbody() {
        return posbody;
    }

    public ArrayList<String> getNegbody() {
        return negbody;
    }

    public ArrayList<ArrayList<String>> getBody() {
        return body;
    }

    public HashMap<String, Integer> getLiterals() {
        return literals;
    }

    @Override
    public String toString() {
        return "head:"+this.getHead()+",body:"+this.getPosbody();
    }
}
