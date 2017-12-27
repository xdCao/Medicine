package xd.medicine.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xd.medicine.entity.bo.PostDuty;
import xd.medicine.entity.bo.ProDuty;
import xd.medicine.service.DoctorService;
import xd.medicine.service.PostDutyService;
import xd.medicine.service.ProDutyService;

import java.util.List;

import static xd.medicine.utils.MathUtils.getRandom;

/**
 * created by liubotao
 */

@RunWith(SpringRunner.class)
@SpringBootTest
/* 只是用来自动生成insert语句，没有其他作用 */
public class DbInsertHelperTest {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PostDutyService postDutyService;

    @Test
    public void prtSql(){
        //INSERT INTO `user_log` VALUES ('17', '10', 'Bob', '60', 'bbb', '2017-12-25', '1', 'X');
        //INSERT INTO `sys_log` VALUES ('23', '10', 'Bob', '63', 'a', '8', '2017-12-13');
        int a=22,b=27;
        for(int i = 1 ; i< 13 ; i++){
            for(int j = 0 ; j< 20 ; j++) {
                a++;
                System.out.println("INSERT INTO `user_log` VALUES ('"+a+"', '"+ i +"', '"+doctorService.getDoctorById(i).getName()+"', '"+ (int)getRandom(0 ,100)+"', 'bbb', '2017-12-25', '1', 'X');");
            }
        }

        for(int i = 1 ; i< 13 ; i++){
            for(int j = 0 ; j< 20 ; j++) {
                b++;
                System.out.println("INSERT INTO `sys_log` VALUES ('"+b+"', '"+i+"', '"+doctorService.getDoctorById(i).getName()+"', '"+ (int)getRandom(0 ,100)+"', 'a', '8', '2017-12-13');");
            }
        }
    }

    @Test
    public void test2(){
        System.out.println(postDutyService.count());
        PostDuty postDuty = new PostDuty();
        //postDuty.setId(11);
        postDuty.setDutyContent("aaa");
        postDuty.setChoose(true);
        postDuty.setPresetTime((byte)8);
        postDuty.setEmer((float) 0.56);
        postDuty.setGraceTime((byte)16);
        postDuty.setType((byte)2);
        int i = postDutyService.insertPostDuty(postDuty);
        System.out.println("---"+i);
        System.out.println(postDutyService.count());
        List<PostDuty> postDuties = postDutyService.getAllPostDuties();
        for(PostDuty postDuty1 : postDuties){
            System.out.println(postDuty1.getDutyContent());
        }

        System.out.println("---");
        List<PostDuty> postDuties3 = postDutyService.getPostDutiesByChosen(true);
        for(PostDuty postDuty1 : postDuties3){
            System.out.println(postDuty1.getDutyContent());
        }
        System.out.println("---");
        postDuty.setDutyContent("bbb");
        postDutyService.updatePostDuty(postDuty);
        List<PostDuty> postDuties5 = postDutyService.getPostDutiesByChosen(true);
        for(PostDuty postDuty5 : postDuties5){
            System.out.println(postDuty5.getDutyContent());
        }

        System.out.println("---");
        postDutyService.deletePostDuty(15);
        List<PostDuty> postDuties2 = postDutyService.getAllPostDuties();
        for(PostDuty postDuty1 : postDuties2){
            System.out.println(postDuty1.getDutyContent());
        }


    }
}
