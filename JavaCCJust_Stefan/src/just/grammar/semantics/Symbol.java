package just.grammar.semantics;

public class Symbol {
	public enum Kind {
		undefKind, constKind, varKind, parKind, funcKind
	};

	public int spix; // Spelling index for name list
	public Kind kind;
	public Type type; // data type
	public boolean init; // is init? for vars only
	public int val; // for constants and initialized vars only
	public int addr; // for globals, params, consts and local vars
	// for functions only
	public boolean defined; // is func already defined
	public Symbol symbols; // params and loc vars
	public int level; // scope level
	public Symbol next; // linear list of symbols within a scope
	
	
	public Symbol(int spix, Kind kind) {
		this.spix = spix;
		this.kind = kind;
	}
	
}