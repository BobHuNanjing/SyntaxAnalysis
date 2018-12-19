import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class DemoTest {
    public static void main(String[] args) throws Exception{
        String program = "bird(X):-fly(X),animal(X).";
        CharStream input = CharStreams.fromString(program);
        LPMLNLexer lexer = new LPMLNLexer(input);
        CommonTokenStream token = new CommonTokenStream(lexer);
        LPMLNParser parser = new LPMLNParser(token);
        ParseTree tree = parser.lpmln_rule();
        ASPRuleExtractor visitor = new ASPRuleExtractor();
        visitor.visit(tree);
        ASPRule ruleToComplete = visitor.ASPRuleGetter();
        System.out.println(ruleToComplete.toString());
        visitor.getLiteralMap();

    }
}
