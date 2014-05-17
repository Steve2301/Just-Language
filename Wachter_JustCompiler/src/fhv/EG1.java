/* Generated By:JavaCC: Do not edit this line. EG1.java */
package fhv;
import java.io.*;
import fhv.semantic.context.*;
import fhv.classfile.*;
import fhv.classfile.constant.*;
import fhv.semantic.*;
import fhv.code.*;

public class EG1 implements EG1Constants {
  private static NameList nameList;
  private static SymbolTable symbolTable;

  private static ClassFile classFile;
  private static CodeGenerator codeGen;

  public static void main(String args [])
  {
    nameList = NameList.nameList;
    symbolTable = SymbolTable.symbolTable;
    classFile = new ClassFile();

    try
        {
          int s1 = nameList.insert("println");
          Symbol printlnSymbol = new Symbol(s1, Symbol.Kind.systemFuncKind);
          int s2 = nameList.insert("start");
          Symbol startSymbol = new Symbol(s2, Symbol.Kind.systemFuncKind);

          symbolTable.insert(printlnSymbol);
          symbolTable.insert(startSymbol);

      EG1 parser = new EG1(new FileInputStream("Just/ATest.jus"));
      parser.file();
      System.out.println("OK.");
    }
    catch (ParseException e)
    {
      System.out.println("NOK.");
      System.out.println(e.getMessage());
      EG1.ReInit(System.in);
    }
    catch (Error e)
    {
      System.out.println("Oops.");
      System.out.println(e.getMessage());e.printStackTrace();
    }
    catch (Exception e)
    {
      System.out.println("Other oops.");
      System.out.println(e.getMessage());

      e.printStackTrace();
    }
  }

  static final public void file() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IMPORT:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(IMPORT);
      jj_consume_token(IDENTIFIER);
      jj_consume_token(END);
    }
    program();
  }

  static final public void program() throws ParseException {
        ClassConstant classConstant;
        UTF8Constant nameConstant;
        Token token;
    jj_consume_token(PROGRAM);
    symbolTable.enterScope("program");
    token = jj_consume_token(IDENTIFIER);
    jj_consume_token(OPEN_CURLY);
    programDef();
    symbolTable.leaveScope();
    nameConstant = new UTF8Constant(token.image);
    classConstant = new ClassConstant(nameConstant);

    classFile.addConstant(classConstant);
    classFile.addConstant(nameConstant);
    jj_consume_token(CLOSE_CURLY);
  }

  static final public void programDef() throws ParseException {
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
      case COROUTINE:
      case INT:
      case BOOL:
      case VOID:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        varDef();
        break;
      case COROUTINE:
      case INT:
      case BOOL:
      case VOID:
        funcDef();
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VOID:
      jj_consume_token(VOID);
      break;
    case INT:
      jj_consume_token(INT);
      break;
    case BOOL:
      jj_consume_token(BOOL);
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void funcDef() throws ParseException {
  Token token;
  Symbol symbol;
  Integer spix;

  Code code = new Code();
  Method method;
  codeGen = new CodeGenerator(code, classFile);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COROUTINE:
      jj_consume_token(COROUTINE);
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    type();
    token = jj_consume_token(IDENTIFIER);
    method = new Method(token.image, code);

    spix = nameList.insert(token.image);
    symbol = new Symbol(spix, Symbol.Kind.funcKind);
    symbolTable.insert(symbol);
    symbolTable.enterScope(token.image);
    jj_consume_token(OPEN);
    funcParamList();
    jj_consume_token(CLOSE);
    block();
    symbolTable.leaveScope();

    // TODO add to classfile
    codeGen = null;
  }

  static final public void funcParamList() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BYREF:
    case INT:
    case BOOL:
    case VOID:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BYREF:
        jj_consume_token(BYREF);
        break;
      default:
        jj_la1[5] = jj_gen;
        ;
      }
      funcParam();
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SEPERATOR:
          ;
          break;
        default:
          jj_la1[6] = jj_gen;
          break label_3;
        }
        jj_consume_token(SEPERATOR);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BYREF:
          jj_consume_token(BYREF);
          break;
        default:
          jj_la1[7] = jj_gen;
          ;
        }
        funcParam();
      }
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
  }

  static final public void funcParam() throws ParseException {
  Token token;
  Integer spix;
  Symbol symbol;
    type();
    token = jj_consume_token(IDENTIFIER);
    spix = nameList.insert(token.image);
    symbol = new Symbol(spix, Symbol.Kind.parKind);
    symbolTable.insert(symbol);
  }

  static final public void varDef() throws ParseException {
  Token token;
  Integer spix;
  Symbol symbol;
    jj_consume_token(VAR);
    type();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ARRAY:
      jj_consume_token(ARRAY);
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
    token = jj_consume_token(IDENTIFIER);
    spix = nameList.insert(token.image);
    symbol = new Symbol(spix, Symbol.Kind.varKind);
    symbolTable.insert(symbol);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASSIGN:
      jj_consume_token(ASSIGN);
      expr();
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
    jj_consume_token(END);
  }

  static final public void block() throws ParseException {
    jj_consume_token(OPEN_CURLY);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
      case CALL:
      case RETURN:
      case SYNCHRONIZED:
      case OPEN_CURLY:
      case IF:
      case WHILE:
      case IDENTIFIER:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        varDef();
        break;
      case CALL:
      case RETURN:
      case SYNCHRONIZED:
      case OPEN_CURLY:
      case IF:
      case WHILE:
      case IDENTIFIER:
        stat();
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(CLOSE_CURLY);
  }

  static final public void stat() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case OPEN_CURLY:
      block();
      break;
    case IDENTIFIER:
      assignStat();
      break;
    case IF:
      ifStat();
      break;
    case CALL:
      callStat();
      break;
    case WHILE:
      whileStat();
      break;
    case SYNCHRONIZED:
      synchronizedStat();
      break;
    case RETURN:
      returnStat();
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void assignStat() throws ParseException {
    var();
    jj_consume_token(ASSIGN);
    expr();
    jj_consume_token(END);
  }

  static final public void callStat() throws ParseException {
    simpleCallStat();
    jj_consume_token(END);
  }

  static final public void simpleCallStat() throws ParseException {
    jj_consume_token(CALL);
    call();
  }

  static final public void ifStat() throws ParseException {
    jj_consume_token(IF);
    jj_consume_token(OPEN);
    expr();
    jj_consume_token(CLOSE);
    stat();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ELSE:
      jj_consume_token(ELSE);
      stat();
      break;
    default:
      jj_la1[14] = jj_gen;
      ;
    }
  }

  static final public void whileStat() throws ParseException {
    jj_consume_token(WHILE);
    jj_consume_token(OPEN);
    expr();
    jj_consume_token(CLOSE);
    symbolTable.enterScope("while");
    stat();
    symbolTable.leaveScope();
  }

  static final public void synchronizedStat() throws ParseException {
    jj_consume_token(SYNCHRONIZED);
    jj_consume_token(OPEN);
    expr();
    jj_consume_token(CLOSE);
    symbolTable.enterScope("synchronized");
    stat();
    symbolTable.leaveScope();
  }

  static final public void returnStat() throws ParseException {
    jj_consume_token(RETURN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
    case MINUS:
    case CALL:
    case NEW:
    case TRUE:
    case FALSE:
    case OPEN:
    case NOT:
    case IDENTIFIER:
    case NUMBER:
      expr();
      break;
    default:
      jj_la1[15] = jj_gen;
      ;
    }
    jj_consume_token(END);
  }

  static final public void call() throws ParseException {
    var();
    jj_consume_token(OPEN);
    actParamList();
    jj_consume_token(CLOSE);
  }

  static final public void expr() throws ParseException {
    orExpr();
  }

  static final public void orExpr() throws ParseException {
    andExpr();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[16] = jj_gen;
        break label_5;
      }
      jj_consume_token(OR);
      andExpr();
    }
  }

  static final public void andExpr() throws ParseException {
    relExpr();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        ;
        break;
      default:
        jj_la1[17] = jj_gen;
        break label_6;
      }
      jj_consume_token(AND);
      relExpr();
    }
  }

  static final public void relExpr() throws ParseException {
    simpleExpr();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EQUAL:
      case NOT_EQUAL:
      case SMALLER:
      case GREATOR:
      case SMALLER_EQUAL:
      case GREATOR_EQUAL:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_7;
      }
      comparisonOperator();
      simpleExpr();
    }
  }

  static final public void comparisonOperator() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQUAL:
      jj_consume_token(EQUAL);
      break;
    case NOT_EQUAL:
      jj_consume_token(NOT_EQUAL);
      break;
    case SMALLER:
      jj_consume_token(SMALLER);
      break;
    case GREATOR:
      jj_consume_token(GREATOR);
      break;
    case SMALLER_EQUAL:
      jj_consume_token(SMALLER_EQUAL);
      break;
    case GREATOR_EQUAL:
      jj_consume_token(GREATOR_EQUAL);
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void simpleExpr() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
    case MINUS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        break;
      case MINUS:
        jj_consume_token(MINUS);
        break;
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[21] = jj_gen;
      ;
    }
    term();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[22] = jj_gen;
        break label_8;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        break;
      case MINUS:
        jj_consume_token(MINUS);
        break;
      default:
        jj_la1[23] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      term();
    }
  }

  static final public void term() throws ParseException {
    notFactor();
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULTIPLY:
      case DIVIDE:
        ;
        break;
      default:
        jj_la1[24] = jj_gen;
        break label_9;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULTIPLY:
        jj_consume_token(MULTIPLY);
        break;
      case DIVIDE:
        jj_consume_token(DIVIDE);
        break;
      default:
        jj_la1[25] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      notFactor();
    }
  }

  static final public void notFactor() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NOT:
      jj_consume_token(NOT);
      break;
    default:
      jj_la1[26] = jj_gen;
      ;
    }
    factor();
  }

  static final public void factor() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMBER:
      jj_consume_token(NUMBER);
      break;
    case IDENTIFIER:
      var();
      break;
    case CALL:
      simpleCallStat();
      break;
    case OPEN:
      jj_consume_token(OPEN);
      expr();
      jj_consume_token(CLOSE);
      break;
    case NEW:
      jj_consume_token(NEW);
      type();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OPEN_RECT:
        jj_consume_token(OPEN_RECT);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case IDENTIFIER:
          var();
          break;
        case NUMBER:
          jj_consume_token(NUMBER);
          break;
        default:
          jj_la1[27] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        jj_consume_token(CLOSE_RECT);
        break;
      default:
        jj_la1[28] = jj_gen;
        ;
      }
      break;
    case TRUE:
      jj_consume_token(TRUE);
      break;
    case FALSE:
      jj_consume_token(FALSE);
      break;
    default:
      jj_la1[29] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void var() throws ParseException {
  Token token;
  Symbol symbol;
    token = jj_consume_token(IDENTIFIER);
    symbol = symbolTable.lookup(token.image);
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case POINT:
      case OPEN_RECT:
        ;
        break;
      default:
        jj_la1[30] = jj_gen;
        break label_10;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OPEN_RECT:
        jj_consume_token(OPEN_RECT);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case IDENTIFIER:
          var();
          break;
        case NUMBER:
          jj_consume_token(NUMBER);
          break;
        default:
          jj_la1[31] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        jj_consume_token(CLOSE_RECT);
        break;
      case POINT:
        subVar();
        break;
      default:
        jj_la1[32] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    codeGen.load(symbol);
  }

  static final public void subVar() throws ParseException {
    jj_consume_token(POINT);
    jj_consume_token(IDENTIFIER);
  }

  static final public void actParamList() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
    case MINUS:
    case CALL:
    case NEW:
    case TRUE:
    case FALSE:
    case OPEN:
    case NOT:
    case IDENTIFIER:
    case NUMBER:
      expr();
      label_11:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SEPERATOR:
          ;
          break;
        default:
          jj_la1[33] = jj_gen;
          break label_11;
        }
        jj_consume_token(SEPERATOR);
        expr();
      }
      break;
    default:
      jj_la1[34] = jj_gen;
      ;
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public EG1TokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[35];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1000,0x8400,0x8400,0x0,0x8000,0x4000,0x400000,0x4000,0x4000,0x0,0x0,0x2012c00,0x2012c00,0x2012800,0x0,0x81a0860,0x800000,0x1000000,0x80000000,0x80000000,0x60,0x60,0x60,0x60,0x180,0x180,0x0,0x0,0x20000000,0x81a0800,0x20040000,0x0,0x20040000,0x400000,0x81a0860,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x1c0,0x1c0,0x1c0,0x0,0x0,0x0,0x0,0x1c0,0x200,0x4000,0xa400,0xa400,0xa400,0x800,0x48020,0x0,0x0,0x1f,0x1f,0x0,0x0,0x0,0x0,0x0,0x0,0x20,0x48000,0x0,0x48000,0x0,0x48000,0x0,0x0,0x48020,};
   }

  /** Constructor with InputStream. */
  public EG1(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public EG1(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new EG1TokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 35; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 35; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public EG1(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new EG1TokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 35; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 35; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public EG1(EG1TokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 35; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(EG1TokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 35; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[52];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 35; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 52; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
