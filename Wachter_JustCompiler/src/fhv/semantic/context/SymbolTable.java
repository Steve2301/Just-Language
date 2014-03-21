package fhv.semantic.context;

import fhv.semantic.Symbol;

public class SymbolTable {
	public static SymbolTable symbolTable = new SymbolTable();

	private SymbolTable() {
		curLevel = 0;
		curScope = new Scope(null, curLevel);
	}

	private Integer curLevel;
	private Scope curScope;

	public void enterScope() {
		this.curLevel += 1;
		this.curScope = new Scope(this.curScope, this.curLevel);
	}

	public void leaveScope() {
		this.curLevel -= 1;
		this.curScope = this.curScope.outer;
	}

	public void insert(Symbol symbol) {
		this.curScope.insert(symbol);

	}

	public Symbol lookup(String name) {
		Scope cur = this.curScope;
		Integer spix = Namelist.nameList.lookup(name);
		while (cur != null) {
			Symbol symbol = cur.lookup(spix);
			if (symbol != null) {
				return symbol;
			}
			cur = cur.outer;
		}
		return null;
	}
}