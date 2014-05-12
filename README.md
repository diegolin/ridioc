ridioc - ridiculously small inversion of control for Java
=========================================================

## Intro

For maven projects just add this dependency:

```xml
<dependency>
  <groupId>ch.dkitc</groupId>
  <artifactId>ridioc-core</artifactId>
  <version>0.1</version>
</dependency>
```

## Usage

Say you have these interfaces and their single implementation classes:

```java
public interface UberService { void executeUberService(); }
public interface FirstService { void executeFirstService(); }
public interface SecondService { void executeSecondService(); }
public interface ThirdService { void executeThirdService(); }
public interface MicroService { void executeMicroService(); }
public interface NanoService { void executeNanoService(); }
public interface PicoService { void executePicoService(); }

...
public class UberServiceImpl implements UberService {
    private final FirstService first;
    private final SecondService second;
    private final ThirdService third;
    public UberServiceImpl(FirstService first, SecondService second, ThirdService third) { ... }
    @Override public void executeUberService() {...}
}
...
public class FirstServiceImpl implements FirstService {
    private final MicroService microService;
    public FirstServiceImpl(MicroService microService) { ... }
    @Override public void executeFirstService() { ...  }
}
...
public class SecondServiceImpl implements SecondService {
    private final NanoService nano;
    public SecondServiceImpl(NanoService nano) { ... }
    @Override public void executeSecondService() { ... }
}
...
public class ThirdServiceImpl implements ThirdService {
    private final PicoService pico;
    public ThirdServiceImpl(PicoService picoService) { ... }
    @Override public void executeThirdService() { ... }
}
...
public class MicroServiceImpl implements MicroService {
    private final NanoService nano;
    public MicroServiceImpl(NanoService nano) { ... }
    @Override public void executeMicroService() { ... }
}
...
public class NanoServiceImpl implements NanoService {
    private final PicoService pico;
    public NanoServiceImpl(PicoService pico) { ...  }
    @Override
    public void executeNanoService() { ... }
}
...
public class PicoServiceImpl implements PicoService {
    public PicoServiceImpl() {}
    @Override
    public void executePicoService() { ... }
}

```

Now, to access an instance of type `UberInterface`, you can do this:

```java
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
