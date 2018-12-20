import com.sun.javafx.binding.SelectBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author bobhu
 * 2018/12/19
 * function: head/body/posBody/negBody extraction for each LPMLN rules
 */
public class ASPRuleExtractor extends LPMLNBaseVisitor {

    private ASPRule simpleRule;
    private HashSet<ASPRule> ruleList = new HashSet<>();
    private HashSet<String> literals = new HashSet<>();
    private HashMap<String, Integer> literalMap = new HashMap<>();

    @Override
    public Object visitHead(LPMLNParser.HeadContext ctx) {
        String head = ctx.getText();
        int idx = getIndexOrAddItemsIntoLiteralAndLiteralMap(head);
        simpleRule.setHead(idx);
        return super.visitHead(ctx);
    }

    @Override
    public Object visitHard_rule(LPMLNParser.Hard_ruleContext ctx) {
        return super.visitHard_rule(ctx);
    }

    @Override
    public Object visitSoft_rule(LPMLNParser.Soft_ruleContext ctx) {
        simpleRule = new ASPRule();
        ruleList.add(simpleRule);
        return super.visitSoft_rule(ctx);
    }

    @Override
    public Object visitInteger(LPMLNParser.IntegerContext ctx) {
        simpleRule.setWeight(Integer.valueOf(ctx.getText()));
        return super.visitInteger(ctx);
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
            simpleRule.getBody().get(0).add(litIdx);
        }else{
            litIdx = getIndexOrAddItemsIntoLiteralAndLiteralMap(ctx.default_literal().literal().getText());
            simpleRule.setNegbody(litIdx);
            simpleRule.getBody().get(1).add(litIdx);
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
    public Object visitConstraint(LPMLNParser.ConstraintContext ctx) {
        simpleRule = new ASPRule();
        ruleList.add(simpleRule);
        return super.visitConstraint(ctx);
    }

    @Override
    public Object visitFact(LPMLNParser.FactContext ctx) {
        simpleRule = new ASPRule();
        ruleList.add(simpleRule);
        return super.visitFact(ctx);
    }

    @Override
    public Object visitFull_rule(LPMLNParser.Full_ruleContext ctx) {
        if(!ctx.getParent().getClass().getName().equals("LPMLNParser$Hard_ruleContext")) {
            simpleRule = new ASPRule();
            ruleList.add(simpleRule);
        }

        //System.out.println(ruleList.size()+" rules have been added!");
        return super.visitFull_rule(ctx);
    }

    void ASPRuleGetter() {
        for (ASPRule rule :
                this.ruleList) {
            System.out.println(rule.toString());
        }
    }

    void getLiteralMap() {
        System.out.println(this.literalMap);
    }
}
