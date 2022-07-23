package orbag.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LimitedSizeListTest {

	@Test
	void test() {
		LimitedSizeList<String> list = new LimitedSizeList<>();
		list.setLimit(10);
		
		for (int cnt=0;cnt<10;cnt++) {
			list.add("value");
		}
		assertThrowsExactly(LimitExceededException.class,()-> list.add("ciao"));
	}

}
