import com.sun.javafx.binding.SelectBinding;

public class ASPRuleExtractor extends LPMLNBaseVisitor {

    private ASPRule simpleRule = new ASPRule();

    @Override
    public Object visitLiteral(LPMLNParser.LiteralContext ctx) {
        //ctx.atom()
        return super.visitLiteral(ctx);
    }

    @Override
    public Object visitHead(LPMLNParser.HeadContext ctx) {
        simpleRule.setHead(ctx.getText());
        return super.visitHead(ctx);
    }

    @Override
    public Object visitAtom(LPMLNParser.AtomContext ctx) {
        //System.out.println(ctx);
        return super.visitAtom(ctx);
    }

    @Override
    public Object visitBody(LPMLNParser.BodyContext ctx) {
        for (LPMLNParser.Extended_literalContext ectx:
             ctx.extended_literal()) {
            simpleRule.setPosbody(ectx.getText());
        }
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
}
