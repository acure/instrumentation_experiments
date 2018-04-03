package xxx.yyy.zzz;

public class CommonClass extends AbstractClass implements InterfaceOfCommonClass {

    @Override
    public String do_IOAC_CommonClass() {
        System.out.println(" ::::::::::::::  do_IOAC_CommonClass  :::::::::::::: ");
        return "CommonClass#do_IOAC_CommonClass";
    }

    @Override
    public String do_IOCC_CommonClass() {
        System.out.println(" :::::::::::::: do_IOCC_CommonClass  :::::::::::::: ");
        return "CommonClass#do_IOAC_CommonClass";
    }

    @Override
    public String do_IOAC_Abstract() {
        System.out.println(" :::::::::::::: do_IOAC_Abstract :::::::::::::: ");
        return "CommonClass#do_IOAC_Abstract";
    }

    @Override
    public String do_IOPAC_AbstractClass() {
        System.out.println(" :::::::::::::: do_IOPAC_AbstractClass :::::::::::::: ");
        return "CommonClass#do_IOPAC_AbstractClass";
    }

    @Override
    public String do_IOPAC_CommonClass() {
        System.out.println(" :::::::::::::: do_IOPAC_CommonClass :::::::::::::: ");
        return "CommonClass#do_IOPAC_CommonClass";
    }

    @Override
    public String do_IOAC_OVERRIDEDinCommonClass() {
        System.out.println(" :::::::::::::: do_IOAC_OVERRIDEDinCommonClass :::::::::::::: ");
        return "CommonClass#do_IOAC_OVERRIDEDinCommonClass";
    }

    public String exception_CC() throws Exception {
        System.out.println(" :::::::::::::: exception_CC :::::::::::::: ");
        throw new Exception(" dupa ");
    }
}
