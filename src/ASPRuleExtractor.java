import com.sun.javafx.binding.SelectBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author bobhu
 * 2018/12/19
 * function: single rule head/body/posBody/negBody extraction
 */
public class ASPRuleExtractor extends LPMLNBaseVisitor {

    private ASPRule simpleRule = new ASPRule();
    private HashSet<String> literals = new HashSet<>();
    private HashMap<String, Integer> literalMap = new HashMap<>();

    @Override
    public Object visitHead(LPMLNParser.HeadContext ctx) {
        String head = ctx.getText();
        int idx = getIndexOrAddItemsIntoLiteralAndLiteralMap(head);
        simpleRule.setHead(idx);
        return super.visitHead(ctx);
    }

    private int getIndexOrAddItemsIntoLiteralAndLiteralMap(String head) {
        int headIdx;
        if (literalMap.containsKey(head)) {
            headIdx = literalMap.get(head);
        } else {
            literalMap.put(head, literalMap.size());
            headIdx = literalMap.size() - 1;
        }

        return headIdx;
    }


    @Override
    public Object visitAtom(LPMLNParser.AtomContext ctx) {
        //System.out.println(ctx);
        return super.visitAtom(ctx);
    }

    @Override
    public Object visitExtended_literal(LPMLNParser.Extended_literalContext ctx) {
        int litIdx;

        // 下方是正原子(extended不是pos就是neg）
        if(ctx.literal()!=null) {
            litIdx = getIndexOrAddItemsIntoLiteralAndLiteralMap(ctx.literal().getText());
            simpleRule.setPosbody(litIdx);
        }else{
            litIdx = getIndexOrAddItemsIntoLiteralAndLiteralMap(ctx.default_literal().literal().getText());
            simpleRule.setNegbody(litIdx);
        }
        return super.visitExtended_literal(ctx);
    }

    @Override
    public Object visitLiteral(LPMLNParser.LiteralContext ctx) {
        int literalIdx;
        literalIdx = getIndexOrAddItemsIntoLiteralAndLiteralMap(ctx.getText());
        simpleRule.setLiterals(ctx.getText(), literalIdx);
        return super.visitLiteral(ctx);
    }

    @Override
    public Object visitBody(LPMLNParser.BodyContext ctx) {
        return super.visitBody(ctx);
    }

    @Override
    public Object visitLpmln_rule(LPMLNParser.Lpmln_ruleContext ctx) {
        return super.visitLpmln_rule(ctx);
    }

    ASPRule ASPRuleGetter() {
        return this.simpleRule;
    }



    void getLiteralMap() {
        System.out.println(this.literalMap);
    }
}
