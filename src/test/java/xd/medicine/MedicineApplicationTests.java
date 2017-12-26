package xd.medicine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicineApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test(){
		System.out.println((int)9.67);
	}


	@Test
	public void test1(){
		HashMap<Integer,Integer> hashMap=new HashMap<>();
		hashMap.put(1,1);
		hashMap.put(1,2);
		System.out.println(hashMap.get(1));
	}


}
