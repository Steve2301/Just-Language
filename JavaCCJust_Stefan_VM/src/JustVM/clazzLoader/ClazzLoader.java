package JustVM.clazzLoader;

import java.util.Arrays;
import java.util.List;

import JustVM.VirtualMachine;
import JustVM.clazz.Clazz;
import JustVM.clazz.ClazzVar;
import JustVM.clazz.Method;
import JustVM.type.Type;

public class ClazzLoader {

	public static void load(VirtualMachine vm) {
		//TODO should parse clazzfile
		vm.addConstant(0, "0", 0, Type.intType);
		vm.addConstant(1, "10", 10, Type.intType);
		vm.addConstant(2, "1", 1, Type.intType);
		vm.addConstant(3, "2", 2, Type.intType);
		vm.addConstant(7, "globalLimit", 0, Type.intType);
		
		loadCode(vm);

		Clazz c = new Clazz("RealTest");
		Method main = new Method(0, 0, 0, 1, 2, 14);
		Method doIt = new Method(17, 14, 1, 0, 2, 16);
		c.addMethod(main);
		c.addMethod(doIt);
		
		c.addClazzVar(new ClazzVar("globalLimit", Type.intType));
		
		vm.addClazz(c);
	}
	
	private static void loadCode(VirtualMachine vm) {
		//Constants IDs already adapted to IDs of constants in constant pool
		List<String> codes = Arrays.asList("LDC_W 0", "ISTORE 0", "LDC_W 1", "PUTSTATIC 7", "L1: NOP",
					"ILOAD 0", "GETSTATIC 7", "IF_ICMPLE L2", "ILOAD 0", 
					"INVOKESTATIC 17", "ISTORE 0", "GOTO L1", "L2: NOP", "RETURN",
					"ILOAD 0", "GETSTATIC 7", "IF_ICMPLT L3", "ILOAD 0",
					"LDC_W 2", "IADD", "ISTORE 0", "GOTO L4", "L3: NOP", 
					"ILOAD 0", "LDC_W 3", "IADD", "ISTORE 0", "L4: NOP", "ILOAD 0", "IRETURN");
		
		for (String c : codes) {
			vm.addCodeLine(c);
		}
	}
}
