package xxx.yyy.zzz;

public abstract class AbstractClass extends ParentAbstractClass implements InterfaceOfAbstractClass{

    @Override
    public String do_IOAC_AbstractClass() {
        return "AbstractClass#do_IOAC_AbstractClass";
    }

    public static String do_IOAC_Static() {
        return "AbstractClass#do_IOAC_Static";
    }

    public abstract String do_IOAC_Abstract();

    public String do_IOAC_OVERRIDEDinCommonClass() {
        return"AbstractClass#do_IOAC_OVERRIDEDinCommonClass";
    }

    @Override
    public String do_PAC_AbstractClass() {
        return "AbstractClass#do_PAC_AbstractClass";
    }
}
