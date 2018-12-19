import com.sun.javafx.binding.SelectBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ASPRuleExtractor extends LPMLNBaseVisitor {

    private ASPRule simpleRule = new ASPRule();
    private HashSet<String> literals  = new HashSet<String>();
    private HashMap<String,Integer> literalMap = new HashMap<>();

    @Override
    public Object visitLiteral(LPMLNParser.LiteralContext ctx) {
        //ctx.atom()
        return super.visitLiteral(ctx);
    }

    @Override
    public Object visitHead(LPMLNParser.HeadContext ctx) {
        String head = ctx.getText();
        int idx = getIndexOrAddItemsIntoLiteralAndLiteralMap(head);
        simpleRule.setHead(idx);
        return super.visitHead(ctx);
    }

    private int getIndexOrAddItemsIntoLiteralAndLiteralMap(String head) {
        int headIdx = 0;
        if(literalMap.containsKey(head))
        {
            headIdx = literalMap.get(head);
        }
        else
        {
            literalMap.put(head,literalMap.size()-1);
            headIdx = literalMap.size()-1;
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
        int literalIdx;
        literalIdx = getIndexOrAddItemsIntoLiteralAndLiteralMap(ctx.literal().getText());
        simpleRule.setLiterals(ctx.literal().getText(),literalIdx);
        return super.visitExtended_literal(ctx);
    }

    @Override
    public Object visitBody(LPMLNParser.BodyContext ctx) {
        return super.visitBody(ctx);
    }

    @Override
    public Object visitLpmln_rule(LPMLNParser.Lpmln_ruleContext ctx) {
        return super.visitLpmln_rule(ctx);
    }

    public ASPRule ASPRuleGetter()
    {
        return this.simpleRule;
    }
    
    public void getLiteralMap()
    {
        System.out.println(this.literalMap);
    }
}
