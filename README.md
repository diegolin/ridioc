ridioc - ridiculously small inversion of control for Java
=========================================================

## Usage

```java
public interface UberService { void executeUberService(); }
...
public class UberServiceImpl implements UberService {
    private final FirstService firstService;
    private final SecondService secondService;
    private final ThirdService thirdService;
    public UberServiceImpl(FirstService firstService, SecondService secondService, ThirdService thirdService) { ... }
    @Override public void executeUberService() {...}
    }
}
...
public interface FirstService { void executeFirstService(); }
...
public class FirstServiceImpl implements FirstService {
    private final MicroService microService;
    public FirstServiceImpl(MicroService microService) { ... }
    @Override public void executeFirstService() { ...  }
}
...
public class Example {
    public static final String PACKAGE_PREFIX = Example.class.getPackage().getName();
    public static void main(String[] args) {
        DIObjectFactory objectFactory = DI.createObjectFactory(DIObjectFactory.class, PACKAGE_PREFIX);
        UberService uberService = objectFactory.instance(UberService.class);
        uberService.executeUberService();
    }
}
```

## Credits

Built upon
* [Reflections](https://github.com/ronmamo/reflections)
* [ParaNamer](https://github.com/paul-hammant/paranamer)

Tested using
* [JUnit](https://github.com/junit-team/junit)

## License

[LGPL] (https://www.gnu.org/copyleft/lesser.html)

## About me
* [LinkedIn profile](http://www.linkedin.com/in/dkitc)
* [Xing profile](https://www.xing.com/profile/Diego_Kuenzi)
