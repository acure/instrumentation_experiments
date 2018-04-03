package xxx.yyy.zzz;

public abstract class ParentAbstractClass implements InterfaceOfParentAbstracClass{
    String do_PAC_ParentAbstractClass() {
        return "ParentAbstractClass#do_PAC_ParentAbstractClass";
    }

    public abstract String do_PAC_AbstractClass();

    @Override
    public String do_IOPAC_ParentAbstractClass() {
        return "ParentAbstractClass#do_IOPAC_ParentAbstractClass";
    }
}
