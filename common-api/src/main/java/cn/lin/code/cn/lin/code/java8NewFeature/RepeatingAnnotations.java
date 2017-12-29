package cn.lin.code.cn.lin.code.java8NewFeature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Copyright (c)2017,德稻  All rights reserved.
 * <br/>
 * <core></core>
 *
 * @author wxl
 */


    interface JDK8Interface1 {

        //1.接口中可以定义静态方法了
        public static void staticMethod(){
            System.out.println("接口中的静态方法");
        }

        //2.使用default之后就可以定义普通方法的方法体了
        public default void defaultMethod(){
            System.out.println("接口中的默认方法");
        }
    }

interface JDK8Interface2 {

   //接口中可以定义静态方法了
   public static void staticMethod(){
       System.out.println("接口中的静态方法");
   }
   //使用default之后就可以定义普通方法的方法体了
   public default void defaultMethod(){
       System.out.println("接口中的默认方法");
   }
}

/**
 *
 * @ClassName:RepeatingAnnotations
 * @Description:重复注解@Repeatable
 * @author diandian.zhang
 * @date 2017年3月31日下午3:48:13
 */
public class RepeatingAnnotations {
    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    public @interface Filters {
        Filter[] value();
    }

    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable( Filters.class )
    public @interface Filter {
        String value();
        String value2();
    };

    @Filter( value="filter1",value2="111" )
    @Filter( value="filter2", value2="222")
    //@Filters({@Filter(  value="filter1",value2="111" ),@Filter(  value="filter2", value2="222")}).注意：JDK8之前：1.没有@Repeatable2.采用本行“注解容器”写法
    public interface Filterable {
    }

    public static void main(String[] args) {
        //获取注解后遍历打印值
        for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
            System.out.println( filter.value() +filter.value2());
        }
    }
}

/**
 *
 * @ClassName:Annotations
 * @Description:新增类型注解:ElementType.TYPE_USE 和ElementType.TYPE_PARAMETER（在Target上）
 * @author diandian.zhang
 * @date 2017年3月31日下午4:39:57
 */
 class Annotations {
    @Retention( RetentionPolicy.RUNTIME )
    @Target( { ElementType.TYPE_USE, ElementType.TYPE_PARAMETER } )
    public @interface NonEmpty {
    }

    public static class Holder< @NonEmpty T > extends Object {
        public void method() throws Exception {
        }
    }

    public static void main(String[] args) {
        final Holder<String> holder = new @NonEmpty Holder<String>();
        @NonEmpty Collection< @NonEmpty String > strings = new ArrayList<>();
    }
}