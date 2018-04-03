package xxx.yyy.zzz;

public class CommonClass extends AbstractClass implements InterfaceOfCommonClass {

    @Override
    public String do_IOAC_CommonClass() {
        return "CommonClass#do_IOAC_CommonClass";
    }

    @Override
    public String do_IOCC_CommonClass() {
        return "CommonClass#do_IOAC_CommonClass";
    }

    @Override
    public String do_IOAC_Abstract() {
        return "CommonClass#do_IOAC_Abstract";
    }

    @Override
    public String do_IOPAC_AbstractClass() {
        return "CommonClass#do_IOPAC_AbstractClass";
    }

    @Override
    public String do_IOPAC_CommonClass() {
        return "CommonClass#do_IOPAC_CommonClass";
    }

    @Override
    public String do_IOAC_OVERRIDEDinCommonClass() {
        return "CommonClass#do_IOAC_OVERRIDEDinCommonClass";
    }

    public String exception_CC() throws Exception {
        throw new Exception(" dupa ");
    }
}
