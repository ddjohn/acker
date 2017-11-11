package se.avelon.edge.annotations;

public @interface Requirement {
    String tag();
    String descr() default "";
}
