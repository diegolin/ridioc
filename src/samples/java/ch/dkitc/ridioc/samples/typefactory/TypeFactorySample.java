package ch.dkitc.ridioc.samples.typefactory;

import ch.dkitc.ridioc.DI;
import ch.dkitc.ridioc.DIObjectFactory;
import ch.dkitc.ridioc.samples.typefactory.api.Bean;
import ch.dkitc.ridioc.samples.typefactory.api.BeanToCreateOtherBeans;
import ch.dkitc.ridioc.samples.typefactory.api.Service;

public class TypeFactorySample {

    public static final String PACKAGE_PREFIX = TypeFactorySample.class.getPackage().getName();

    public static void main(String [] args) {
        DIObjectFactory objectFactory = DI.createObjectFactory(DIObjectFactory.class, PACKAGE_PREFIX);

        BeanToCreateOtherBeans beanToCreateOtherBeans = objectFactory.instance(BeanToCreateOtherBeans.class);

        Bean bean1 = beanToCreateOtherBeans.createBean();
        Service service1 = bean1.getService();
        service1.executeService();

        Bean bean2 = beanToCreateOtherBeans.createBean();
        Service service2 = bean2.getService();
        service2.executeService();
    }

}
