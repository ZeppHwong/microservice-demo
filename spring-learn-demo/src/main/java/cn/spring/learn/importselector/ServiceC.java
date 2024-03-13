package cn.spring.learn.importselector;

public class ServiceC implements IService{
    private final String name;

    public ServiceC(String name) {
        this.name = name;
    }
    @Override
    public void test() {
        System.out.println(name);
    }
}
