import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DemoTest {
    public static void main(String[] args) throws IOException {
        String filename = null;
        if(args.length>0)
            filename = args[0];

        FileInputStream program = null;
        try {
            program = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //String program = "a:-b,c,d. e:-not a,m,n.";
        CharStream input = CharStreams.fromStream(program);
        LPMLNLexer lexer = new LPMLNLexer(input);
        CommonTokenStream token = new CommonTokenStream(lexer);
        LPMLNParser parser = new LPMLNParser(token);
        ParseTree tree = parser.lpmln_rule();
        ASPRuleExtractor visitor = new ASPRuleExtractor();
        visitor.visit(tree);
        visitor.ASPRuleGetter();
        //System.out.println(ruleToComplete.toString());
        visitor.getLiteralMap();

    }
}
