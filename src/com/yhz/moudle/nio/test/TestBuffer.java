package com.yhz.moudle.nio.test;

import java.nio.IntBuffer;

/**
 * @Auther: yanghz
 * @Date: 2018/11/26 11:05
 * @Description:
 */
public class TestBuffer {
    public static void main(String[] args) {
        //1、基本操作
        //创建指定长度的缓冲区
        IntBuffer buf=IntBuffer.allocate(10);
        buf.put(23);//position位置：0->1
        buf.put(52);//position位置：1->2
        buf.put(21);//position位置：2->3
        buf.flip();//把位置复位为0，也就是position位置：3 - > 0
        System.out.println("使用flip复位："+buf);
        System.out.println("容量为："+buf.capacity());
        System.out.println("限制为："+buf.limit());

        System.out.println("获取下标为1的元素："+buf.get(1));
        System.out.println("get(index)方法，position位置不改变："+buf);
        buf.put(1,11);
        System.out.println("put(index,change)方法，position位置不改变："+buf);

        for (int i = 0; i < buf.limit(); i++) {
            //调用get方法会使缓冲区位置（position)向后递增一位
            System.out.println(buf.get()+"\t");
        }
        System.out.println("buf对象遍历之后为："+buf);
        System.out.println("----------------------------------------");
        //2、wrap方法使用
        //  wrap方法会包裹一个数组: 一般这种用法不会先初始化缓存对象的长度，因为没有意义，最后还会被wrap所包裹的数组覆盖掉。
        //  并且wrap方法修改缓冲区对象的时候，数组本身也会跟着发生变化。
        int[] arr=new int[]{2,4,6};
        IntBuffer buf1=IntBuffer.wrap(arr);
        System.out.println(buf1);
        IntBuffer buf2=IntBuffer.wrap(arr,0,2);
        //这样使用表示容量为数组arr的长度，但是可操作的元素只有实际进入缓存区的元素长度
        System.out.println(buf2);
        System.out.println("-----------------------------------------");
        //3、其他方法
        IntBuffer buf4=IntBuffer.allocate(10);
        int[] arr1=new int[]{9,5,6,34,5};
        buf4.put(arr1);
        System.out.println("buf4=="+buf4);
        //一种复制方法
        IntBuffer buf5=buf4.duplicate();
        System.out.println("buf5=="+buf5);

        //设置buf4的位置属性
        buf4.position(2);
        System.out.println("buf4.position(2)后："+buf4);
        //可读数据表示position到capacity的值
        System.out.println("可读数据为："+buf4.remaining());
        int[] arr2=new int[buf4.remaining()];
        //将缓冲区数据放入arr2数组中去
        buf4.get(arr2);
        for (int i = 0; i <arr2.length ; i++) {
            System.out.print(Integer.toString(arr2[i])+",");
        }
    }
}
