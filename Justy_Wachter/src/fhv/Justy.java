/* Generated By:JavaCC: Do not edit this line. Justy.java */
package fhv;

import java.io.*;

import fhv.symbol.*;
import fhv.code.*;
import fhv.code.fixup.*;
import fhv.classfile.*;
import fhv.classfile.constant.*;

public class Justy implements JustyConstants {
  private static NameList nameList;
  private static SymbolTable symbolTable;
  private static ClassFile classFile;
  private static CodeGenerator codeGen;

  public static void main(String args []) throws ParseException
  {
    nameList = new NameList();
    symbolTable = new SymbolTable(nameList);
    classFile = new ClassFile();
    codeGen = new CodeGenerator();

    try
    {
      Justy parser = new Justy(new FileInputStream("Just/BTest.jus"));
      parser.file();
      System.out.println("OK.");
      classFile.write("Just/BTest.jl");
    }
    catch (ParseException e)
    {
      System.out.println("NOK.");
      System.out.println(e.getMessage());
      Justy.ReInit(System.in);
    }
    catch (Error e)
    {
      System.out.println("Oops.");
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    catch (Exception e)
    {
      System.out.println("Other oops.");
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  static final public Type type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VOID:
      jj_consume_token(VOID);
      {if (true) return Type.Void;}
      break;
    case INT:
      jj_consume_token(INT);
      {if (true) return Type.Int;}
      break;
    case BOOL:
      jj_consume_token(BOOL);
      {if (true) return Type.Bool;}
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public void file() throws ParseException {
    program();
  }

  static final public void program() throws ParseException {
  Symbol s;
  Constant classConstant;
    jj_consume_token(PROGRAM);
    token = jj_consume_token(IDENTIFIER);
    s = nameList.insert(token.image, Kind.programKind);
    symbolTable.insert(s);
    symbolTable.enterScope("program: " + token.image);

        classConstant = classFile.addClassConstant(s);
    jj_consume_token(OPEN_CURLY);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
      case INT:
      case BOOL:
      case VOID:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        fieldVarDef();
        break;
      case INT:
      case BOOL:
      case VOID:
        methodDef();
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(CLOSE_CURLY);
    symbolTable.leaveScope();
  }

  static final public void fieldVarDef() throws ParseException {
  Constant constant;
    jj_consume_token(VAR);
    constant = varDef(Kind.fieldKind);
    jj_consume_token(END);
  }

  static final public void localVarDef() throws ParseException {
  Constant constant;
    jj_consume_token(VAR);
    constant = varDef(Kind.localKind);
    jj_consume_token(END);
  }

  static final public Constant varDef(Kind kind) throws ParseException {
  Type type;
  Symbol s;
  Constant varConstant;
    type = type();
    token = jj_consume_token(IDENTIFIER);
    s = nameList.insert(token.image, kind);
    s.setType(type);
    symbolTable.insert(s);
        {if (true) return classFile.addVarConstant(s);}
    throw new Error("Missing return statement in function");
  }

  static final public void methodDef() throws ParseException {
  Type type;
  MethodSymbol s;
  MethodRefConstant methodConstant;
  Scope scope;
    type = type();
    token = jj_consume_token(IDENTIFIER);
    s = (MethodSymbol) nameList.insert(token.image, Kind.methodKind);
    s.setType(type);

    symbolTable.insert(s);
    scope = symbolTable.enterScope(token.image);
    jj_consume_token(OPEN);
    funcParamList(s);
    jj_consume_token(CLOSE);
    methodConstant = (MethodRefConstant) classFile.addMethodConstant(s, scope);
    codeGen.setCurrentCode(methodConstant.getCode());
    block();
    symbolTable.leaveScope();
  }

  static final public void funcParamList(MethodSymbol s) throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BYREF:
    case BYVAL:
    case INT:
    case BOOL:
    case VOID:
      funcParam(s);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SEPERATOR:
          ;
          break;
        default:
          jj_la1[3] = jj_gen;
          break label_2;
        }
        jj_consume_token(SEPERATOR);
        funcParam(s);
      }
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
  }

  static final public void funcParam(MethodSymbol method) throws ParseException {
  Type type;
  Symbol s;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BYREF:
    case BYVAL:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BYREF:
        jj_consume_token(BYREF);
        break;
      case BYVAL:
        jj_consume_token(BYVAL);
        break;
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
    type = type();
    token = jj_consume_token(IDENTIFIER);
    s = nameList.insert(token.image, Kind.paramKind);
    s.setType(type);
    symbolTable.insert(s);

        s.setConstant(classFile.addVarConstant(s));
    method.addParam(s);
  }

  static final public void block() throws ParseException {
    jj_consume_token(OPEN_CURLY);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
      case RETURN:
      case IDENTIFIER:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_3;
      }
      stat();
    }
    jj_consume_token(CLOSE_CURLY);
  }

  static final public void stat() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VAR:
      localVarDef();
      break;
    case IDENTIFIER:
      assignStat();
      break;
    case RETURN:
      returnStat();
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void assignStat() throws ParseException {
  Descriptor desc;
    desc = var(false);
    jj_consume_token(ASSIGN);
    expr();
    codeGen.save(desc);
    jj_consume_token(END);
  }

  static final public void returnStat() throws ParseException {
    jj_consume_token(RETURN);
    expr();
    codeGen.emit(Opcode.IRETURN);
    jj_consume_token(END);
  }

  static final public void expr() throws ParseException {
  Constant constant;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMBER:
      token = jj_consume_token(NUMBER);
    constant = classFile.addValueConstant(token.image, "integer");
    codeGen.emit2(Opcode.LDC_W, constant.getIndex());
      break;
    case IDENTIFIER:
      var(true);
      break;
    case CALL:
      simpleCallStat();
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public Descriptor var(boolean generate) throws ParseException {
  Symbol s;
  Descriptor desc;
    token = jj_consume_token(IDENTIFIER);
    s = symbolTable.lookup(token.image);
    if(s==null)
    {
      s = nameList.insert(token.image, Kind.noneKind);
    }
    desc = codeGen.getDescriptor(s);

    if(generate)
    {
      codeGen.load(desc);
    }
    {if (true) return desc;}
    throw new Error("Missing return statement in function");
  }

  static final public void callStat() throws ParseException {
    simpleCallStat();
    jj_consume_token(END);
  }

  static final public void simpleCallStat() throws ParseException {
    jj_consume_token(CALL);
    call();
  }

  static final public void call() throws ParseException {
  Descriptor desc;
    desc = var(false);
    jj_consume_token(OPEN);
    actParamList();
    jj_consume_token(CLOSE);
    codeGen.emit2(Opcode.INVOKESTATIC, new VarAddressFixup(desc, symbolTable.getCurScope()));
  }

  static final public void actParamList() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CALL:
    case IDENTIFIER:
    case NUMBER:
      expr();
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SEPERATOR:
          ;
          break;
        default:
          jj_la1[10] = jj_gen;
          break label_4;
        }
        jj_consume_token(SEPERATOR);
        expr();
      }
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public JustyTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[12];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x400,0x400,0x800000,0xc000,0xc000,0xc000,0x2400,0x2400,0x800,0x800000,0x800,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x380,0x380,0x380,0x0,0x380,0x0,0x0,0x10000,0x10000,0x90000,0x0,0x90000,};
   }

  /** Constructor with InputStream. */
  public Justy(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Justy(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new JustyTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Justy(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new JustyTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Justy(JustyTokenManager tm) {
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
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(JustyTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
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
    boolean[] la1tokens = new boolean[53];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 12; i++) {
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
    for (int i = 0; i < 53; i++) {
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
