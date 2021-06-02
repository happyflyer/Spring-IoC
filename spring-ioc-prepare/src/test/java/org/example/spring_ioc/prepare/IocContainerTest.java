package org.example.spring_ioc.prepare;

import org.example.spring_ioc.prepare.car.Audi;
import org.example.spring_ioc.prepare.car.Buick;
import org.example.spring_ioc.prepare.human.Human;
import org.example.spring_ioc.prepare.human.LiSi;
import org.example.spring_ioc.prepare.human.ZhangSan;
import org.junit.Before;
import org.junit.Test;

public class IocContainerTest {
    private final IocContainer iocContainer = new IocContainer();

    @Before
    public void before() {
        iocContainer.setBean(Audi.class, "audi");
        iocContainer.setBean(Buick.class, "buick");
        iocContainer.setBean(ZhangSan.class, "zhangsan", "audi");
        iocContainer.setBean(LiSi.class, "lisi", "buick");
    }

    @Test
    public void test() {
        Human zhangSan = (Human) iocContainer.getBean("zhangsan");
        zhangSan.goHome();
        Human liSi = (Human) iocContainer.getBean("lisi");
        liSi.goHome();
    }
}
